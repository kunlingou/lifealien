# -*- coding: utf-8 -*-

import ConnFactory as ConnFactory
import sys
import os
import Factory as Factory
import pandas as pd
import FileUtil as FileUtil

if __name__ == '__main__':
    config = Factory.initConfig()
    connconfig = config.get("datasource")
    try:
        for prop in connconfig:
            conn = ConnFactory.registerConn(prop)
            print('数据库连接成功（{0}）:{1}'.format(prop.get("key"), conn))
    except Exception as e:
        print('连接数据库失败!（{0}）'.format(prop.get("key")))
        print(e)
        sys.exit()
    # 已经获取到了connection
    # 拷贝文件夹，从sql到excel
    filePath = os.getcwd()
    excelPath = filePath + "\\" + config.get("path").get("excel_path")
    FileUtil.copy_tree(filePath + "\\" + config.get("path").get("sql_path"),
                       excelPath)
    print('正在准备sql数据...')
    fileNames = os.walk(excelPath)
    sqlList = []
    for fileName in fileNames:
        if (len(fileName[2]) > 0):
            for name in fileName[2]:
                if (name.endswith(".txt") or name.endswith(".sql")):
                    sqlList.append(Factory.getSqlE(fileName[0], name))
    # 已经获取到了执行对象，开始执行sql
    for sqlE in sqlList:
        sql = sqlE.sql
        url = sqlE.url
        try:
            if len(sql) > 0:
                print('正在查询:{0}'.format(url + "\\" + sqlE.fileName))
                data_sql = pd.read_sql(sql, ConnFactory.getConn(sqlE.connkey))
                data_sql.to_excel(url + "\\" + sqlE.title + ".xlsx")
        except Exception as e:
            print(e)
    print('查询数据库完成!')
