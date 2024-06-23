import { createStore } from "vuex";

const store = createStore({
  state() {
    return {
      nickname: "",
      isJoin: false,
      chat: [],
      url: "ws://boring-jam.kro.kr:8080/room",
      session: null,
      sessionId: "",
    };
  },
  mutations: {
    setNickname(state, nickname) {
      state.nickname = nickname;
    },
    setIsJoin(state, isJoin) {
      state.isJoin = isJoin;
    },
    setSessionId(state, sessionId) {
      state.sessionId = sessionId;
    },
    addChat(state, message) {
      state.chat.push(message);
      console.log(state.chat);
    },
  },
  actions: {
    connect({ commit, state }) {
      if (state.session == null) {
        state.session = new WebSocket(state.url);
        state.session.onopen = () => {
          console.log("Connected");
        };
        state.session.onmessage = (event) => {
          const message = event.data;
          if (state.sessionId == "") {
            commit("setSessionId", message);
            console.log("getId",state.sessionId);
            state.session.send(state.nickname);
            commit("setIsJoin", true);


          } else {
            const [sender, session, content] = message.split(":");
            commit("addChat", { sender, session, content });
          }
        };
      }
    },
    sendMessage({ state }, message) {
      state.session.send(message);
    },
    disconnect({ commit, state }) {
      state.session.close();
      state.session = null;
      commit("setIsJoin", false);
    },
  },
});

export default store;
