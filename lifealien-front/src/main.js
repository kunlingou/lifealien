import Vue from "vue";
import VueRouter from "vue-router";
import axios from "axios"
import iView from 'iview'
import 'iview/dist/styles/iview.css'

import App from "./App.vue";
import userList from "./components/userList";
import HelloWorld from "./components/HelloWorld";
import iconList from "./components/iconList";
// import functionTree from "./components/functionTree";

Vue.use(VueRouter);
Vue.use(iView);

var rootUrl = 'http://localhost:8000'

Vue.config.productionTip = false;
Vue.prototype.$http = axios;
Vue.prototype.$rootUrl = rootUrl;

var routes = [
  { path: "/hello",name: "hello", component: HelloWorld },
  { path: "/userList",name: "userList", component: userList },
  { path: "/iconList",component: iconList },
]

var router = new VueRouter({
  routes
});

new Vue({
  router,
  render: h => h(App)
  // render: function(h){
  //   return h(App);
  // }
}).$mount("#app");
