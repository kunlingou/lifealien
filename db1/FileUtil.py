import json
import shutil
import os
from parse_excel import get_sheet_with_openpyxl


def getJson(dir):
    with open(dir, 'r', encoding='utf-8') as load_f:
        load_dict = json.load(load_f)
        print(load_dict)
    return load_dict
# load_dict['smallberg'] = [8200,{1:[['Python',81],['shirt',300]]}]
# print(load_dict)

# with open("../config/record.json","w") as dump_f:
#     json.dump(load_dict,dump_f)


def getTxt(dir):
    fileobj = open(dir, 'r')
    sql = ""
    try:
        sql = fileobj.read()
        # for each_line in fileobj.readlines():
        #     if not each_line or each_line == "\n":
        #         continue
        #     else:
        #         sql += each_line
    finally:
        fileobj.close()
    # strings = "".join(strings)
    return sql


def copy_tree(source_path, target_path):
    if(os.path.exists(target_path)):
        shutil.rmtree(target_path)
    # os.makedirs(target_path)
    shutil.copytree(source_path, target_path)
    # for dir in os.listdir(target_path):  # sftp.listdir()返回当前目录下清单列表
    #     target = os.path.join(target_path, dir)
    #     for re_dir in os.listdir(source_path):
    #         if re_dir[0:-16] == dir:
    #             Source = os.path.join(source_path, re_dir, 'conf')
    #             Target = os.path.join(target, 'conf')
    #             print('{0}——{1}'.format(Source, Target))
    #             shutil.copytree(Source, Target)
    print('copy complete!')


def getExcel(path):
    return get_sheet_with_openpyxl(path)


if __name__ == '__main__':
    getJson("./config.json")
