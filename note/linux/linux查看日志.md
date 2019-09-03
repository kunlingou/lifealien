## 获取文件

### 常用日志路径

```
/var/log/message //系统信息日志
/var/log/secure //系统登录日志
/var/log/cron //定时任务日志
/var/log/maillog //邮件日志
/var/log/boot.log //系统启动日志
```

## 查看日志

### 常用命令

- tail：查看后多少行日志
- -n 显示行号(nl)

```
tail -100f test.log //实时监控100行
tail -n 10 test.log //最后10行
tail -n +10 test.log //10行之后
```

- head：查看前多少行日志

```
head -n 10 test.log //头10行
head -n -10 test.log //除最后10行外所有日志
```

- cat：顺序查看
- tac：倒序查看

```
cat -n test.log | grep "debug" //查看关键字日志
```

### 场景一：按行查看

```
cat -n test.log | grep "debug" //得到关键日志行号
cat -n test.log | tail -n +92 | head -n 20 //查看关键字(102行)前后10行的日志
```

### 场景二：按日期查看

```
grep '2014-12-17 16:17:20' test.log //确定日志中是否有该日期
sed -n '/2014-12-17 16:17:20/,/2014-12-17 16:17:36/p' test.log //两个必须是日志中存在的时间点
```

### 场景三：日志过多

```
cat -n test.log |grep "debug" | more //分页打印，通过点击空格键翻页
cat -n test.log |grep "debug" >debug.txt //将日志保存到文件中
less log2013.log
1.全屏导航：
ctrl + F //向前移动一屏
ctrl + B //向后移动一屏
ctrl + D //向前移动半屏
ctrl + U //向后移动半屏
2.单行导航
j //向前移动一行
k //向后移动一行
3.其它导航
G //移动到最后一行
g //移动到第一行
q / ZZ //退出 less 命令
```

## 参考文档

- [Linux查看日志常用命令](https://www.cnblogs.com/kbkiss/p/7567725.html)

- [Linux less命令](https://www.runoob.com/linux/linux-comm-less.html)

- [Linux系统日志管理](https://www.cnblogs.com/uthnb/p/9375237.html)