import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import http from "@/util/http.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    videos: [],
    users:[],
    user:{},
    loginUser : null,
  },
  getters: {
  },
  mutations: {
    SET_VIDEO_LIST(state, videos) {
      state.videos = videos;
    },
    CREATE_USER: function (state, user){
      state.users.push(user);
    },
    SET_USER: function(state, user){
      state.user = user;
    },
    SET_USERS: function(state, users){
      state.users = users;
    },
    SET_LOGIN_USER: function (state, user) {
      state.loginUser = user;
    },
    LOGOUT: function (state) {
      state.loginUser = null;
    },
 
  },
  actions: { 
    createUser({commit}, user){
      console.log(user)
      http.post("userapi/user", user)
      .then(()=> {
        commit("CREATE_USER", user);
        alert("회원가입 완료");
        router.push("/login"); //로그인 화면으로 이동하기
      })
      .catch((err) => {
        console.log(err);
      });
    }

    ,
    videoSearch({commit},word){
      console.log(word);
      const apiKey = 'AIzaSyBCemuYfu5PQsgPVL_oTEudlK9GnsKZ4is';
      axios.get(`https://www.googleapis.com/youtube/v3/search?part=snippet&q=${word+" 운동"}&key=${apiKey}&maxResults=50&type=video&videoSyndicated=true`)
      .then(response => {
        const he = require("he");
        const videoItems = response.data.items;
        console.log(videoItems);
        const videos = videoItems.map(item => {
          const videoId = item.id.videoId;
          const title = he.decode(item.snippet.title);
          const channelTitle = he.decode(item.snippet.channelTitle);
          return {
            videoId,
            title,
            channelTitle
          };
        });

        commit('SET_VIDEO_LIST', videos); // mutation 호출
      })
      .catch(error => {
        console.error('API 요청 실패', error);
      });
    },

  },
  modules: {},
});
