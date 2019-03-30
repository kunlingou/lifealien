<template>
  <div id="app" class="leftMenuBody">
    <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
    <Row class="leftMenuBody">
      <Layout class="leftMenuBody">
        <Header :style="{background: '#fff'}">
          <Card title="生活是一种态度" icon="ios-options" :padding="0" shadow style="width: 300px;height: 100%"/>
        </Header>
        <Layout>
          <Sider hide-trigger :style="{background: '#fff'}">
            <functionTree/>
          </Sider>
          <Content>
            <router-view/>
          </Content>
        </Layout>
        <Footer>Copyright © kunlinr</Footer>
      </Layout>
    </Row>
  </div>
</template>
<script>
import functionTree from "./components/functionTree.vue";
export default {
  components: {
    functionTree
  }
};
// style="height: 800px;"
</script>

<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.leftMenuBody{
    height:100%;
    width:100%;
}
</style>
