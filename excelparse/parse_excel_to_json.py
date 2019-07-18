# import xlrd
# import numpy as np
# import openpyxl
import json
import os
import re
import TClass
from TClass import TObject, TCell, TValue, TContent, TGrid_field
from parse_excel import get_sheet_with_openpyxl

# from XmlConverter import XmlConverter
# import xml.etree.ElementTree as ET


class parse_excel_to_json:
    """将excel中的相关数据及参数转为json格式。
    Attributes:
        default_height: 默认高度
        default_width: 默认宽度
    """

    _letter = [
        '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    ]

    def __init__(self):
        self.default_height = 13.5
        self.default_width = 20
        self.mergedCells = {}
        self.per_height = 0.419
        self.per_width = 2.2862

    def get_col_width(self, colNum):
        """获取列的宽度，添加默认值处理
        Args:
            colNum: 第几列
        """
        try:
            width = self.sheet.column_dimensions[self._letter[colNum]].width
        except Exception:
            width = self.default_width
        width = width or self.default_width
        return width * self.per_width

    def get_border_width(self, style):
        if (str(style) == 'thin'):
            border_width = 0.1
        elif (str(style) == 'medium'):
            border_width = 0.3
        elif (str(style) == 'thick'):
            border_width = 0.5
        else:
            border_width = None
        return border_width or 0

    def get_row_height(self, rowNum):
        """获取行的高度，添加默认值处理
        Args:
            rowNum: 第几行
        """
        try:
            height = self.sheet.row_dimensions[rowNum].height
        except Exception:
            height = self.default_height
        height = height or self.default_height
        return height * self.per_height

    def is_mergeCell(self, cell):
        if (self.mergedCells.get(cell.coordinate) is not None):
            return True
        else:
            return False

    def beginWith(self, pattern, string):
        return re.match(pattern, string) is not None

    def grid_handle(self, data, k):
        data[k].type = TCell.type_grid
        position = re.match(TClass.pattern_grid, data[k].value.value).regs[1]
        grid_colNum = int(
            data[k].value.value[int(position[0]):int(position[1])])
        data[k].value.value = data[k].value.value[int(position[1]) + 2:]
        data[k].grid_colNum = grid_colNum
        width = 0
        for i in range(grid_colNum):
            grid_field = TGrid_field()
            grid_field.key = data[k + grid_colNum + i].value.value
            grid_field.name = data[k + i].value.value
            grid_field.width = data[k + i].position.width
            data[k].grid_field.append(grid_field)
            width += data[k + grid_colNum - i - 1].position.width
        data[k].position.detail_height = data[k + grid_colNum].position.height
        data[k].position.width = width
        for i in range(grid_colNum * 2 - 1):
            data.remove(data[k + grid_colNum * 2 - i - 1])

    def get_TCell_with_Cell(self, cell, x, y):
        """装载数据参数到自定义对象中
        Args:
            cell: 单元格对象
            x: 左上角横坐标
            y: 左上角纵坐标
        """
        tcell = TCell()
        if (self.is_mergeCell(cell)):
            mergeCell = self.mergedCells.get(cell.coordinate)
            min_col = mergeCell.min_col
            max_col = mergeCell.max_col
            while (min_col <= max_col):
                tcell.position.width += self.get_col_width(min_col)
                min_col += 1

            min_row = mergeCell.min_row
            max_row = mergeCell.max_row
            while (min_row <= max_row):
                tcell.position.height = self.get_row_height(min_row)
                min_row += 1
        else:
            tcell.position.width = self.get_col_width(cell.column)
            tcell.position.height = self.get_row_height(cell.row)
        tcell.position.upper_left = [x, y]
        tcell.position.rowNum = cell.row
        tcell.position.colNum = cell.column

        tcell.name = cell.coordinate
        tcell.value.value = str(cell.value)
        tcell.font.name = cell.font.name
        tcell.font.size = cell.font.sz
        tcell.font.font_bold = cell.font.b
        tcell.font.horizontal = cell.alignment.horizontal or tcell.font.horizontal
        tcell.font.vertical = cell.alignment.vertical or tcell.font.vertical

        tcell.border.border_width = self.get_border_width(
            cell.border.bottom.style)
        tcell.border.border_style = cell.border.bottom.style

        if self.beginWith(r'func:', str(cell.value)):
            tcell.value.type = TValue.type_function
            tcell.value.value = tcell.value.value[5:]
        return tcell

    # 方法入口
    def run(self, fileName):
        # 获取excel的一个sheet（工作表）
        self.sheet = get_sheet_with_openpyxl(fileName)
        # self.merged_cell = self.sheet.merged_cells
        for mergeCell in self.sheet.merged_cells.ranges:
            self.mergedCells[mergeCell.coord[0:2]] = mergeCell
        # self.merged_cell_left =
        # 数据存储
        tContent = TContent()
        tContent.billdefine = self.sheet.title
        data = []
        i = 0
        y = 0
        for index, row in enumerate(
                self.sheet.iter_rows(
                    min_row=1,
                    max_col=self.sheet.max_column,
                    max_row=self.sheet.max_row)):
            x = 0
            for cell in row:
                if (cell.value is not None):
                    data.append(self.get_TCell_with_Cell(cell, x, y))
                    i += 1
                x += self.get_col_width(cell.column)
                tContent.maxWidth += x
            y += self.get_row_height(index + 1)
            tContent.maxHeight += y
        # 数据处理
        data_length = len(data)
        for i in range(data_length):
            k = data_length - i - 1
            if self.beginWith(TClass.pattern_grid, str(data[k].value.value)):
                self.grid_handle(data, k)
        # 数据过滤
        data = [element for element in data if element != 0]
        tContent.printParams = data

        tObject = TObject()
        tObject.content = tContent
        # root = XmlConverter.class_to_xml(tObject)
        # et = ET.ElementTree(root)  # 生成文档对象
        # et.write("test1.xml", encoding="utf-8", xml_declaration=True, short_empty_elements=False)
        with open(fileName[:-5] + '.meta', 'w', encoding='utf-8') as f:
            f.write(
                json.dumps(
                    tObject,
                    default=lambda obj: obj.__dict__,
                    sort_keys=False,
                    ensure_ascii=False,
                    indent=4))


if __name__ == '__main__':
    parse = parse_excel_to_json()
    filePath = os.getcwd()
    fileNames = os.listdir(filePath)
    fileNames = [
        fileName for fileName in fileNames
        if (fileName.find('.xlsx') != -1 and fileName.find('~$') == -1)
    ]
    for fileName in fileNames:
        parse.run(fileName)
    print("end")
