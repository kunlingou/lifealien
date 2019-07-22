from dbcompare.ExeE import ExeE, SqlE
import util.FileUtil as FileUtil
import os

config = {}


def initConfig():
    config = FileUtil.getJson("./config.json")
    return config


def getConfig(key):
    return config.get(key)


def getExeE(filePath, name):
    exe = ExeE()
    exe.title = name
    filePath = os.getcwd()
    fileNames = os.walk(filePath+"\\sql\\"+name)
    for fileName in fileNames:
        if(len(fileName[2]) > 0):
            arr = fileName[0][len(filePath)+5:].split("\\")
            if(arr[1] == "上级"):
                sqlE = exe.source
            else:
                sqlE = exe.target
            sqlE.title = arr[1]
            sqlE.fileName = fileName[2][0]
            sqlE.url = filePath+"\\result\\"+name+"\\"+arr[1]
            sqlE.sql = FileUtil.getTxt(fileName[0]+"\\"+sqlE.fileName)
            # print(sqlE.sql)
    return exe


def getSqlE(filePath, name):
    sql = SqlE()
    paths = filePath.split('\\')
    sql.title = name.split('.')[0]
    sql.fileName = name
    sql.connkey = paths[len(paths) - 1]
    sql.url = filePath
    sql.sql = FileUtil.getTxt(sql.url+"\\"+name)
    return sql
