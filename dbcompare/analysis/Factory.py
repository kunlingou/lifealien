from analysis.Entity import ExcelE
import util.FileUtil as FileUtil


config = {}


def initConfig():
    config = FileUtil.getJson("./config.json")
    return config


def getConfig(key):
    return config.get(key)


def getExcelE(filePath, name):
    excel = ExcelE()
    paths = filePath.split('\\')
    excel.title = name.split('.')[0]
    excel.fileName = name
    excel.url = filePath
    excel.connkey = paths[len(paths) - 1]
    excel.file = FileUtil.getExcel(excel.url+"\\"+name)
    return excel
