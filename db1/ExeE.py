class ExeE:
    def __init__(self):
        self.title = ""
        self.source = SqlE()
        self.target = SqlE()


class SqlE:
    def __init__(self):
        self.title = ""
        self.fileName = ""
        self.url = ""
        self.sql = ""
        self.connkey = ""
