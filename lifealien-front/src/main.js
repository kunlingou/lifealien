import Vue from "vue";
import VueRouter from "vue-router";
import axios from "axios"
import iView from 'iview'
import 'iview/dist/styles/iview.css'



import App from "./App.vue";
import userList from "./components/userList";
import HelloWorld from "./components/HelloWorld";
import iconList from "./components/iconList";
import mail from "./views/mail";
import socket from "./views/socket";
// import functionTree from "./components/functionTree";

Vue.use(VueRouter);
Vue.use(iView);

// import './server.js'

var rootUrl = 'http://localhost:8000'

Vue.config.productionTip = false;
Vue.prototype.$http = axios;
Vue.prototype.$rootUrl = rootUrl;

var routes = [
  { path: "/hello",name: "hello", component: HelloWorld },
  { path: "/userList",name: "userList", component: userList },
  { path: "/iconList",component: iconList },
  { path: "/mail",component: mail },
  { path: "/socket",component: socket }
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
