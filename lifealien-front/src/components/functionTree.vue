<template>
  <div>
    <Col span="40" style="height:100vh">
      <Menu theme="light" width="auto">
        <Submenu name="1">
          <template slot="title">
            <Icon type="ios-paper"/>内容管理
          </template>
          <router-link to="/iconList">
            <MenuItem name="1-1">网站列表</MenuItem>
          </router-link>
          <router-link to="/mail">
            <MenuItem name="1-2">邮件管理</MenuItem>
          </router-link>
          <MenuItem name="1-3">举报管理</MenuItem>
        </Submenu>
        <Submenu name="2">
          <template slot="title">
            <Icon type="ios-people"/>用户管理
          </template>
          <router-link to="/userList">
            <MenuItem name="2-1">用户列表</MenuItem>
          </router-link>
          <router-link to="/hello">
            <MenuItem name="2-2">欢迎页面</MenuItem>
          </router-link>
          <MenuItem name="2-3">新增用户</MenuItem>
          <MenuItem name="2-4">活跃用户</MenuItem>
        </Submenu>
        <Submenu name="3">
          <template slot="title">
            <Icon type="ios-stats"/>统计分析
          </template>
          <MenuGroup title="使用">
            <MenuItem name="3-1">新增和启动</MenuItem>
            <MenuItem name="3-2">活跃分析</MenuItem>
            <MenuItem name="3-3">时段分析</MenuItem>
          </MenuGroup>
          <MenuGroup title="留存">
            <MenuItem name="3-4">用户留存</MenuItem>
            <MenuItem name="3-5">流失用户</MenuItem>
          </MenuGroup>
        </Submenu>
      </Menu>
    </Col>
  </div>
</template>
<script>
export default {
  name: "functionTree"
};
</script>
