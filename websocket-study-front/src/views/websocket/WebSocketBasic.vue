<script>
import {defineComponent} from 'vue'
let ws
export default defineComponent({
  name: "WebSocketBasic",
  data() {
    return {
      isConnected: false,
      message : "",
      logs : []
    }
  },
  methods: {
    connect() {
      ws = new WebSocket("ws://" + "localhost:7777" + "/chat")
      ws.onopen = () => {
        this.isConnected = true;

        ws.onmessage = ({data}) => {
          this.logs.push({event: '메시지 수신', data});
          console.log(data)
        }
      }
    },
    disconnect() {
      ws.close();
      this.isConnected=false;

    },
    sendMessage() {
      ws.send(this.message)
      this.message=''
    }
  },
  beforeDestroy() {
    ws.close();
  }

})
</script>

<template>
<div>
  <button @click="connect">connect</button>
    <button @click="disconnect">disconnect</button>
    <input type="text" v-model="message"/>
    <button @click="sendMessage">send</button>
    <p v-if="isConnected">connected!</p>
    <p v-else> not connected</p>
    <p v-for="(log, i) in logs" :key="i">{{log}}</p>
</div>
</template>

<style scoped>

</style>