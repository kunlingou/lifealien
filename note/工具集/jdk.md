## 安装

### Linux安装jdk1.8

```
[root@kunlingou ~]# ls
anaconda-ks.cfg  jdk-8u11-linux-x64.tar.gz
[root@kunlingou ~]# tar -zxvf jdk-8u11-linux-x64.tar.gz
[root@kunlingou ~]# ls
anaconda-ks.cfg  jdk1.8.0_11  jdk-8u11-linux-x64.tar.gz
[root@kunlingou ~]# mkdir /usr/java
[root@kunlingou ~]# mv jdk1.8.0_11 /usr/java
[root@kunlingou ~]# ls
anaconda-ks.cfg  jdk-8u11-linux-x64.tar.gz
[root@kunlingou ~]# vim /etc/profile
-bash: vim: 未找到命令
[root@kunlingou ~]# yum install vim
[root@kunlingou ~]# cd /etc
[root@kunlingou etc]# ls *file
[root@kunlingou etc]# vim profile
[root@kunlingou etc]# vim profile
[root@kunlingou etc]# source /etc/profile
[root@kunlingou etc]# javac
[root@kunlingou etc]# java -version
java version "1.8.0_11"
Java(TM) SE Runtime Environment (build 1.8.0_11-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.11-b03, mixed mode)
[root@kunlingou etc]# echo $PATH
[root@kunlingou etc]# 

```

- profile

```
export JAVA_HOME=/usr/java/jdk1.8.0_131
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
export JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
export PATH=$PATH:${JAVA_PATH}
```

