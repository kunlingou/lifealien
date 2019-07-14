<template>
  <div class="email">
    <form>
      <div class="input">
        <label for="name" class="label">姓名</label>
        <input type="text" name="name" v-model.trim="formData.name" id="name" class="value">
      </div>
      <div class="input">
        <label for="name" class="label">电话</label>
        <input type="text" name="phone" v-model.trim="formData.phone" id="phone" class="value">
      </div>
      <button class="send" type="submit" @click.stop.prevent="send(formData)">发送邮件</button>
    </form>
  </div>
</template>
<script>
// import email from "emailjs";
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
  methods: {
    send(data) {
      if (!data.name) {
        alert("请输入的你的姓名");
        return false;
      } else if (!data.phone) {
        alert("请输入的你的电话");
        return false;
      } else {
        // axios({
        //   method: 'post',
        //   url: '/api/email',  // 后面会讲解
        //   data: data,
        // });
        // var email = require("emailjs");
        debugger
        var email = require("emailjs/email");
        var server = email.server.connect({
          user: "1162729917@qq.com", // 你的QQ用户
          password: "wrxnxhxtbxuxgfia", // 注意，不是QQ密码，而是刚才生成的授权码
          host: "smtp.qq.com", // 主机，不改
          ssl: true // 使用ssl
        });

        //开始发送邮件
        server.send(
          {
            text: "邮件内容", //邮件内容
            from: "1162729917@qq.com", //谁发送的
            to: "kunlingou@foxmail.com", //发送给谁的
            cc: "17888835939@163.com",
            subject: "邮件主题" //邮件主题
          },
          function(err, message) {
            //回调函数
            console.log(err || message);
          }
        );
      }
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