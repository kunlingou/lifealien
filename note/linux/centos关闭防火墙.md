### centos7

```
CentOS 7.0默认使用的是firewall作为防火墙
查看防火墙状态：firewall-cmd --state
停止firewall：systemctl stop firewalld.service
禁止firewall开机启动：systemctl disable firewalld.service
```

### centos6

```
关闭防火墙：service iptables stop
启动防火墙：service iptables start
重启防火墙：service iptables restart
查看防火墙状态：service iptables status
永久关闭防火墙：chkconfig iptables off
```

