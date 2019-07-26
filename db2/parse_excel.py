# import xlrd
# import numpy as np
import openpyxl
from retrying import retry


@retry(stop_max_attempt_number=3)
def _get_sheet_with_openpyxl(fileName):
    wb = openpyxl.load_workbook(fileName)
    return wb.active


def get_sheet_with_openpyxl(fileName):
    try:
        sheet = _get_sheet_with_openpyxl(fileName)
    except Exception:
        sheet = None
    return sheet


if __name__ == '__main__':
    sheet = get_sheet_with_openpyxl('template.xlsx')
    print(sheet.title)
