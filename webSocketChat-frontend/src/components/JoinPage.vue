<template>
  <div class="h-screen flex justify-center items-center bg-slate-100">
    <div
      class="flex flex-col justify-center items-center w-[400px] bg-white rounded-2xl p-3 shadow-md"
    >
      <h1 class="text-3xl font-bold">Join Chat</h1>
      <input
        id="nicknameInput"
        class="p-1 my-3 h-10 border-gray-400 border-2 w-full rounded-lg"
        type="text"
        v-model="nickname"
        placeholder="Enter your nickname"
        @keydown.enter="join"
      />
      <button
        id="joinBtn"
        class="p-1 text-xl bg-cyan-200 w-full rounded-lg"
        :class="{ 'hover:bg-cyan-300 transition duration-300': !isDisabled }"
        @click="join"
      >
        Join
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "JoinPage",
  data() {
    return {
      nickname: "",
      isDisabled: false,
    };
  },
  methods: {
    join() {
      //비활성화
      this.isDisabled = true;
      document.querySelector("#nicknameInput").disabled = true;
      document.querySelector("#joinBtn").disabled = true;

      //닉네임 저장
      this.$store.commit("setNickname", this.nickname);

      //접속 시도
      this.$store.dispatch("connect");
    },
  },
  mounted() {},
};
</script>

<style scope>
div {
  -webkit-user-select: none; /* Safari */
  -moz-user-select: none; /* Firefox */
  -ms-user-select: none; /* IE10+/Edge */
  user-select: none; /* Standard */
  cursor: default;
}
</style>
