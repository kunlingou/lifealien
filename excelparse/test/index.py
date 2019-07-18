import openpyxl
# import xlrd
import json


class TCell:
    # 单元格
    def __init__(self):
        self.name = None
        self.value = None
        self.font = TFont()


class TFont:
    # 字体
    def __init__(self):
        self.name = None
        self.size = None


def obj_2_json(obj):
    return{
        'value': obj.value
    }


wb = openpyxl.load_workbook('template.xlsx')
# print(type(wb))
# wb.get_sheet_names()
sheet = wb.get_sheet_by_name('Sheet1')
# for i in sheet.max
cell = sheet['A1']
tcell = TCell()
tcell.name = '字段名'
tcell.value = cell.value
tcell.font.name = cell.font.name
tcell.font.size = cell.font.sz
print(sheet.title)
with open('template.json', 'w', encoding='utf-8') as f:
    # f.write(json.dumps(cell, default=obj_2_json, sort_keys=True, indent=4))
    f.write(json.dumps(tcell, default=lambda obj: obj.__dict__, sort_keys=True, ensure_ascii=False, indent=4))
