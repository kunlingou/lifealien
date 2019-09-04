### 导入

- 导入多个表空间的数据到一个表空间

```
impdp system/system schemas=np_ningx directory=dir  dumpfile=np_ningx_20190828.dump logfile=exp.log remap_schema=np_ningx:np_ningx remap_tablespace=NP_TEST:NP_NINGX,NP_NINGX:NP_NINGX
```

