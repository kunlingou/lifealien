### GitLab常用命令

```
git checkout -b dev-gkl-datatrans master
// 添加并切换到新分支
git push origin dev-gkl-datatrans:dev-gkl-datatrans
// 推送代码到远程主机

-- GitLab网址
http://nvwa.jiuqi.com.cn/gitlab/zczhglpt
-- 前端脚手架
git clone http://nvwa.jiuqi.com.cn/gitlab/zczhglpt/platform/gams2-cli.git gams2-frontend
-- 前端代码
git clone http://nvwa.jiuqi.com.cn/gitlab/zczhglpt/platform/gams2-frontend.git normal
-- 后端代码
git clone http://nvwa.jiuqi.com.cn/gitlab/zczhglpt/platform/gams2-backend.git
-- 元数据
git clone http://nvwa.jiuqi.com.cn/gitlab/zczhglpt/platform/gams2-metadata.git
-- 宁夏元数据
git clone http://nvwa.jiuqi.com.cn/gitlab/zczhglpt/finances/nxcz/metadata.git gams2-nx-metadata
```

### git pull

```
git pull origin master
```

### 分支管理

#### 查看分支

- 查看本地分支
  - git branch
- 查看远程分支
  - git branch -a
  - git branch -r

#### 添加并切换新分支（从master分支创建新分支）

- git checkout -b dev-gkl-datatrans master

#### 推送代码

- git push <远程主机名> <本地分支名>:<远程分支名>

```bash
git push origin dev-gkl-datatrans
-- 如果远程分支被省略，如上则表示将本地分支推送到与之存在追踪关系的远程分支（通常两者同名），如果该远程分支不存在，则会被新建
git push origin dev-gkl-datatrans:dev-gkl-datatrans
-- 如果省略本地分支名，则表示删除指定的远程分支，因为这等同于推送一个空的本地分支到远程分支，等同于 git push origin --delete master
git push origin
--  如果当前分支与远程分支存在追踪关系，则本地分支和远程分支都可以省略，将当前分支推送到origin主机的对应分支 
git push
-- 如果当前分支只有一个远程分支，那么主机名都可以省略，形如 git push，可以使用git branch -r ，查看远程的分支名
git push -u origin master
-- u 指定默认主机
git push --all origin
-- 本地所有分支推送到远程主机
git push --force origin
-- 强制提交
git push origin --tags
-- 推送标签
```

- git push origin dev-gkl-datatrans:dev-gkl-datatrans

#### GitLab上提交合并请求

#### 切换分支

- 切换远程分支,切换远程的v1分支到本地， 本地分支名称叫v1  
  - git checkout -b v1 origin/v1
- 切换本地分支
  - git checkout 分支名

#### 还原代码至某个版本

- git reset --hard 版本commit号