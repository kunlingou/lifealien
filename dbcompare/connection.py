import cx_Oracle as oracle


def getConn(url):
    db = oracle.connect(url)
    cursor = db.cursor()
    cursor.execute('select sysdate from dual')
    data = cursor.fetchone()
    print('Database time:%s' % data)
    cursor.close()
    return db


def close(db):
    db.close()


def query(conn, sql):
    cursor = conn.cursor()
    # sql = sql.encode('utf-8')
    print(sql)
    data = ''
    try:
        cursor.parse(sql)
    except Exception as e:
        print(e)
    if(len(sql) > 0):
        cursor.execute(sql)
        data = cursor.fetchone()
    print(data)
    cursor.close()
