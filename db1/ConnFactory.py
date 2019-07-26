import cx_Oracle as oracle
import pymysql as mysql

conns = {}


def registerConn(connProperties):
    # conn = mysql.connect(tuple(connProperties))
    # print(conn)
    conn = _initConn(
        connProperties.get("type"), connProperties.get("host"),
        connProperties.get("port"), connProperties.get("user"),
        connProperties.get("password"), connProperties.get("database"),
        connProperties.get("charset"))
    conns[connProperties.get("key")] = conn
    return conn


def _initConn(type, host, port, user, password, database, charset):
    if type == "oracle":
        conn = oracle.connect("{0}/{1}@{2}:{3}/{4}".format(
            user, password, host, port, database))
    elif type == "mysql":
        conn = mysql.connect(
            host=host,
            port=port,
            user=user,
            password=password,
            database=database,
            charset=charset)
    else:
        conn = mysql.connect(
            host=host,
            port=port,
            user=user,
            password=password,
            database=database,
            charset=charset)
    cursor = conn.cursor()
    cursor.execute('select ' + (
        'sysdate' if type == "oracle" else 'sysdate()') + ' from dual')
    data = cursor.fetchone()
    print('Database time:%s' % data)
    cursor.close()
    return conn


def getConn(key):
    return conns[key]


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
    if (len(sql) > 0):
        cursor.execute(sql)
        data = cursor.fetchone()
    print(data)
    cursor.close()
