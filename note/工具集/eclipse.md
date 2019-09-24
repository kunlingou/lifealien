## 简介

- Eclipse 是一个开放[源代码](https://baike.baidu.com/item/%E6%BA%90%E4%BB%A3%E7%A0%81/3969)的、基于[Java](https://baike.baidu.com/item/Java/85979)的可扩展开发平台。就其本身而言，它只是一个框架和一组服务，用于通过插件组件构建开发环境。幸运的是，Eclipse 附带了一个标准的插件集，包括Java[开发工具](https://baike.baidu.com/item/%E5%BC%80%E5%8F%91%E5%B7%A5%E5%85%B7)（Java Development Kit，[JDK](https://baike.baidu.com/item/JDK/1011)）。 

## 安装及配置

### 常用信息

- 下载地址：<https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/2019-06/R/eclipse-jee-2019-06-R-win32-x86_64.zip&mirror_id=75> 
- 官网地址：[https://www.eclipse.org](https://www.eclipse.org/) 
- 文件名：eclipse-jee-2019-06-R-win32-x86_64.zip
- 文件大小：346M

### 配置eclipse.ini文件

- 指定默认jdk：

```
-vm
D:\app\jdk\jdk1.8.0_131\bin\javaw.exe
```

- 指定默认编码：

```
-Dfile.encoding=UTF-8
```

- 最终配置文件

```
-startup
plugins/org.eclipse.equinox.launcher_1.5.400.v20190515-0925.jar
--launcher.library
plugins/org.eclipse.equinox.launcher.win32.win32.x86_64_1.1.1000.v20190125-2016
-product
org.eclipse.epp.package.jee.product
-showsplash
org.eclipse.epp.package.common
--launcher.defaultAction
openFile
--launcher.defaultAction
openFile
--launcher.appendVmargs
-vm
D:\app\jdk\jdk1.8.0_162\bin\javaw.exe
-vmargs
-Dosgi.requiredJavaVersion=1.8
-Dosgi.instance.area.default=@user.home/eclipse-workspace
-XX:+UseG1GC
-XX:+UseStringDeduplication
--add-modules=ALL-SYSTEM
-Dosgi.requiredJavaVersion=1.8
-Dosgi.dataAreaRequiresExplicitInit=true
-Xms256m
-Xmx1024m
--add-modules=ALL-SYSTEM
-Dfile.encoding=UTF-8
```

- 双击eclipse.exe即可启动

### 构建后没有部分路径文件

- 修改图中映射关系即可

![1568773689994](C:\Users\KUNLIN~1\AppData\Local\Temp\1568773689994.png)

### xsd离线约束

- Window-Preference-XML Catalog-User Specified Entries 

## 常用组件

### 阿里p3c

- 代码规范检查插件p3c，是根据《阿里巴巴Java开发手册》转化而成的自动化插件。 
- 参考链接：<https://www.cnblogs.com/lsysy/p/9954785.html> 