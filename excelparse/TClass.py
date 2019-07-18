class TObject:
    def __init__(self):
        self.name = "com.jiuqi.np.gams2.print.expectedstorage"
        self.description = "没有描述"
        self.title = "延期入库"
        self.type = "gams2.print"
        self.formats = "JSON"
        self.content = TContent()


class TContent:
    def __init__(self):
        self.billdefine = None
        self.printParams = None
        self.maxHeight = 0
        self.maxWidth = 0


class TCell:
    # 单元格(控件)
    type_text = 'text'
    type_grid = 'grid'

    def __init__(self):
        self.name = None
        self.value = TValue()
        self.font = TFont()
        self.position = TPosition()
        # self.alignment = TAlignment()
        self.type = TCell.type_text
        self.border = TBorder()

        self.grid_field = []
        self.grid_colNum = 0
        # self.grid_width = 0
        # self.grid_height = 0


class TFont:
    # 字体
    def __init__(self):
        self.name = None
        self.size = None
        self.horizontal = 'center'
        self.vertical = 'bottom'
        self.font_bold = False


class TPosition:
    # 位置
    def __init__(self):
        self.upper_left = [0, 0]
        self.width = 0
        self.height = 0
        self.rowNum = 0
        self.colNum = 0
        self.detail_height = 0


# class TAlignment:
#     # 显示位置
#     def __init__(self):
#         self.horizontal = None
#         self.vertical = None


class TValue:
    # 值
    type_text = 'text'
    type_function = 'function'

    def __init__(self):
        self.value = None
        self.type = TValue.type_text


class TBorder:
    # 边框
    def __init__(self):
        self.border_width = 0.1
        self.border_style = None
        self.border_color = '0x0'
        self.bottom_border = True
        self.top_border = True
        self.left_border = True
        self.right_border = True


class TGrid_field:
    def __init__(self):
        self.key = None
        self.name = None
        self.width = 0


pattern_grid = r'grid\[(.*)\]:'
