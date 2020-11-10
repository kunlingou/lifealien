## 安装和部署

### Linux 中安装ZooKeeper

- 前提：安装jdk环境
- 下载地址：https://archive.apache.org/dist/zookeeper/zookeeper-3.5.5/
- 文件名：apache-zookeeper-3.5.5-bin.tar.gz

```

[root@kunlingou etc]# cd /root
[root@kunlingou ~]# ls
anaconda-ks.cfg  apache-zookeeper-3.5.5-bin.tar.gz  jdk-8u11-linux-x64.tar.gz
[root@kunlingou ~]# tar -zxvf apache-zookeeper-3.5.5-bin.tar.gz 
[root@kunlingou ~]# ls
anaconda-ks.cfg  apache-zookeeper-3.5.5-bin  apache-zookeeper-3.5.5-bin.tar.gz  jdk-8u11-linux-x64.tar.gz
[root@kunlingou ~]# mkdir /usr/zookeeper
[root@kunlingou ~]# mv apache-zookeeper-3.5.5-bin /usr/zookeeper
[root@kunlingou ~]# cd /usr/zookeeper/
[root@kunlingou zookeeper]# ls
apache-zookeeper-3.5.5-bin
[root@kunlingou zookeeper]# cd apache-zookeeper-3.5.5-bin/
[root@kunlingou apache-zookeeper-3.5.5-bin]# ls
bin  conf  docs  lib  LICENSE.txt  NOTICE.txt  README.md  README_packaging.txt
[root@kunlingou apache-zookeeper-3.5.5-bin]# cd conf
[root@kunlingou conf]# ls
configuration.xsl  log4j.properties  zoo_sample.cfg
[root@kunlingou conf]# cp zoo_sample.cfg zoo.cfg
[root@kunlingou conf]# ls
configuration.xsl  log4j.properties  zoo.cfg  zoo_sample.cfg
[root@kunlingou conf]# vim zoo.cfg
[root@kunlingou conf]# ls
configuration.xsl  log4j.properties  zoo.cfg  zoo_sample.cfg
[root@kunlingou conf]# cd ..
[root@kunlingou apache-zookeeper-3.5.5-bin]# cd /bin
[root@kunlingou bin]# ls
[root@kunlingou bin]# cd -
/usr/zookeeper/apache-zookeeper-3.5.5-bin
[root@kunlingou apache-zookeeper-3.5.5-bin]# cd bin
[root@kunlingou bin]# ls
README.txt  zkCleanup.sh  zkCli.cmd  zkCli.sh  zkEnv.cmd  zkEnv.sh  zkServer.cmd  zkServer-initialize.sh  zkServer.sh  zkTxnLogToolkit.cmd  zkTxnLogToolkit.sh
[root@kunlingou bin]# zkServer.sh start
-bash: zkServer.sh: 未找到命令
[root@kunlingou bin]# echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin:/usr/java/jdk1.8.0_11/bin:/usr/java/jdk1.8.0_11/jre/bin
[root@kunlingou bin]# vim /etc/profile
[root@kunlingou bin]# source /etc/profile
[root@kunlingou bin]# echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin:/usr/java/jdk1.8.0_11/bin:/usr/java/jdk1.8.0_11/jre/bin:/usr/java/jdk1.8.0_11/bin:/usr/java/jdk1.8.0_11/jre/bin:.
[root@kunlingou bin]# zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
[root@kunlingou bin]# zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Error contacting service. It is probably not running.
[root@kunlingou bin]# zkServer.sh stop
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED
[root@kunlingou bin]# chkconfig iptables off
服务 iptables 信息读取出错：没有那个文件或目录
[root@kunlingou bin]# ^C
[root@kunlingou bin]# service iptables status
Redirecting to /bin/systemctl status iptables.service
Unit iptables.service could not be found.
[root@kunlingou bin]# systemctl stop filewalld
Failed to stop filewalld.service: Unit filewalld.service not loaded.
[root@kunlingou bin]# yum install iptavles-services
已加载插件：fastestmirror
Loading mirror speeds from cached hostfile
 * base: ap.stykers.moe
 * extras: ap.stykers.moe
 * updates: ap.stykers.moe
没有可用软件包 iptavles-services。
错误：无须任何处理
[root@kunlingou bin]# yum install iptables-services
[root@kunlingou bin]# chkconfig iptables off
注意：正在将请求转发到“systemctl disable iptables.service”。
[root@kunlingou bin]# service iptables status
Redirecting to /bin/systemctl status iptables.service
● iptables.service - IPv4 firewall with iptables
   Loaded: loaded (/usr/lib/systemd/system/iptables.service; disabled; vendor preset: disabled)
   Active: inactive (dead)
[root@kunlingou bin]# ls
README.txt  zkCleanup.sh  zkCli.cmd  zkCli.sh  zkEnv.cmd  zkEnv.sh  zkServer.cmd  zkServer-initialize.sh  zkServer.sh  zkTxnLogToolkit.cmd  zkTxnLogToolkit.sh
[root@kunlingou bin]# zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
[root@kunlingou bin]# zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Error contacting service. It is probably not running.
[root@kunlingou bin]# cd ../conf
[root@kunlingou conf]# ls
configuration.xsl  log4j.properties  zoo.cfg  zoo_sample.cfg
[root@kunlingou conf]# vim zoo.cfg
[root@kunlingou conf]# cd ../data
[root@kunlingou data]# ls
version-2  zookeeper_server.pid
[root@kunlingou data]# vim zookeeper_server.pid
[root@kunlingou data]# cd ../log
-bash: cd: ../log: 没有那个文件或目录
[root@kunlingou data]# cd ../logs
[root@kunlingou logs]# ls
zookeeper-root-server-kunlingou.out
[root@kunlingou logs]# vim zookeeper-root-server-kunlingou.out 
[root@kunlingou logs]# vim ~/.bashrc

[1]+  已停止               vim ~/.bashrc
[root@kunlingou logs]# ls
zookeeper-root-server-kunlingou.out
[root@kunlingou logs]# echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/root/bin:/usr/java/jdk1.8.0_11/bin:/usr/java/jdk1.8.0_11/jre/bin:/usr/java/jdk1.8.0_11/bin:/usr/java/jdk1.8.0_11/jre/bin:.
[root@kunlingou logs]# cd ../bin
[root@kunlingou bin]# ls
README.txt  zkCleanup.sh  zkCli.cmd  zkCli.sh  zkEnv.cmd  zkEnv.sh  zkServer.cmd  zkServer-initialize.sh  zkServer.sh  zkTxnLogToolkit.cmd  zkTxnLogToolkit.sh
[root@kunlingou bin]# zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Starting zookeeper ... already running as process 7964.
[root@kunlingou bin]# zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /usr/zookeeper/apache-zookeeper-3.5.5-bin/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: standalone
[root@kunlingou bin]# 
```

- zoo.cfg

```
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial 
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between 
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just 
# example sakes.
dataDir=/usr/zookeeper/apache-zookeeper-3.5.5-bin/data
# the port at which the clients will connect
clientPort=2181
# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the 
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1
```

