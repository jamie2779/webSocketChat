<template>
  <ChatFrame v-if="$store.state.isJoin"/>
  <join-page v-else/>
</template>

<script>
import ChatFrame from './components/ChatFrame.vue'
import JoinPage from './components/JoinPage.vue'

export default {
  name: 'App',
  components: {
    ChatFrame,
    JoinPage,
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
