<template>
  <div
    class="flex"
    :class="{
      'justify-end': session == $store.state.sessionId,
      'justify-center': session == 'none',
    }"
  >
    <div class="flex flex-col">
      <span v-if="session != 'none'" class="text-md" :class="{'text-left':session != $store.state.sessionId, 'text-right':session == $store.state.sessionId}">{{ sender }}</span>
      <div
        class="text-black rounded-lg text-md w-fit text-left"
        :class="{ 'px-2 py-1': session != 'none', 'bg-blue-200 ml-auto':session == $store.state.sessionId, 'bg-gray-200':session != $store.state.sessionId && session != 'none'}"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MessageBox",
  props: {
    sender: String,
    session: String,
    message: String,
  },
};
</script>

<style></style>
