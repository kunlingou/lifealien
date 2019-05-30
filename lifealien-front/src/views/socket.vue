<template>
  <div class="email">
    <form>
        <div>socket</div>
      <button class="send" type="submit" @click.stop.prevent="send(formData)">发送邮件</button>
    </form>
  </div>
</template>
<script>
// import email from "emailjs";
var websock;
export default {
  name: "email",
  data() {
    return {
      formData: {
        name: "",
        phone: ""
      }
    };
  },
  created(){
      this.initWebpack();
  },
  methods: {
    initWebpack(){
        const wsuri = "ws://localhost:8000/socketServer/"+Math.floor(Math.random()*10);
        this.websock = new WebSocket(wsuri);
        this.websock.onopen = this.websocketopen;
        this.websock.onerror = this.websocketonerror;
        this.websock.onmessage = this.websocketonmessage;
        this.websock.onclose = this.websocketclose;
    },
    websocketopen(){
        console.log("open");
    },
    websocketonmessage(e){
        console.log(e);
    },
    websocketonerror(e){
        console.log(e);
    },
    websocketclose(e){
         console.log("关闭"+e);
    },
    websocketsend(agentData){//数据发送 
　　　　this.websock.send(agentData); 
　　　　　},
    send(data) {
        this.websocketsend({
            name:123,
            phone:178888
        });
    //   if (!data.name) {
    //     alert("请输入的你的姓名");
    //     return false;
    //   } else if (!data.phone) {
    //     alert("请输入的你的电话");
    //     return false;
    //   } else {
    //     // axios({
    //     //   method: 'post',
    //     //   url: '/api/email',  // 后面会讲解
    //     //   data: data,
    //     // });
    //     // var email = require("emailjs");
    //     // debugger
    //     // var email = require("emailjs/email");
    //     // var server = email.server.connect({
    //     //   user: "1162729917@qq.com", // 你的QQ用户
    //     //   password: "wrxnxhxtbxuxgfia", // 注意，不是QQ密码，而是刚才生成的授权码
    //     //   host: "smtp.qq.com", // 主机，不改
    //     //   ssl: false // 使用ssl
    //     // });

    //     // //开始发送邮件
    //     // server.send(
    //     //   {
    //     //     text: "邮件内容", //邮件内容
    //     //     from: "1162729917@qq.com", //谁发送的
    //     //     to: "kunlingou@foxmail.com", //发送给谁的
    //     //     cc: "17888835939@163.com",
    //     //     subject: "邮件主题" //邮件主题
    //     //   },
    //     //   function(err, message) {
    //     //     //回调函数
    //     //     console.log(err || message);
    //     //   }
    //     // );
    //   }
    }
  }
};
</script>
<style lang="scss" scoped>
.input {
  display: flex;
  .label {
    width: 20rem;
    height: 3rem;
  }
  .value {
    flex: 1;
    border: 1px solid #dfdfdf;
  }
}
</style>