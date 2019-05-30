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
  created() {
    this.initWebpack();
  },
  beforeDestroy(){
      this.websock.close();
  },
  methods: {
    initWebpack() {
      const wsuri =
        "ws://localhost:8000/socketServer/" + Math.floor(Math.random() * 10);
      this.websock = new WebSocket(wsuri);
      this.websock.onopen = this.websocketopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onclose = this.websocketclose;
    },
    websocketopen() {
      console.log("open");
    },
    websocketonmessage(e) {
      console.log(e);
    },
    websocketonerror(e) {
      console.log(e);
    },
    websocketclose(e) {
      console.log("close");
    },
    websocketsend(agentData) {
      //数据发送
      this.websock.send(agentData);
    },
    send(data) {
      this.websocketsend(
        JSON.stringify({
          name: 123,
          phone: 178888
        })
      );
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