# -*- coding: utf-8 -*-

import FileUtil
import connection
import sys
import os
import Factory
import pandas as pd


if __name__ == '__main__':
    config = FileUtil.getJson("./config.json")
    try:
        # 源数据库connection
        source = connection.getConn(config.get("datasource").get("source"))
        print('源数据库连接成功:{0}'.format(source))
        # 目标数据库connection
        target = connection.getConn(config.get("datasource").get("target"))
        print('目标数据库连接成功:{0}'.format(target))
    except Exception as e:
        print('连接数据库失败!')
        print(e)
        sys.exit()
    # 已经获取到了connection
    filePath = os.getcwd()
    fileNames = os.walk(filePath+"\\sql")
    exeList = []
    for fileName in fileNames:
        if(len(fileName[1]) > 0):
            for name in fileName[1]:
                exeList.append(Factory.getExeE(filePath, name))
    # 已经获取到了执行对象，开始执行sql
    for exe in exeList:
        sql = exe.target.sql
        try:
            if len(sql) > 0:
                data_sql = pd.read_sql(sql, target)
                if(not os.path.exists(exe.target.url)):
                    os.makedirs(exe.target.url)
                data_sql.to_csv(exe.target.url+"\\"+exe.target.fileName[:(len(exe.target.fileName)-4)]+".csv")
        except Exception as e:
            print(e)
        # connection.query(source, exe.source.sql)
    # str = FileUtil.getTxt(filePath+"\\sql\\卡片\\上级\\卡片.txt")
    # print(str)
        # if fileName[2].l
    # fileNames = [
    #     fileName for fileName in fileNames
    #     # if (fileName.find('.txt') != -1 and fileName.find('~$') == -1)
    # ]
    # print(fileNames)
