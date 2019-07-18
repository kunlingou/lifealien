import json
import pandas as pd
import chardet


def getJson(dir):
    with open(dir, 'r') as load_f:
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


if __name__ == '__main__':
    getJson("./config.json")
