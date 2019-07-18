from ExeE import ExeE
import FileUtil
import os


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
