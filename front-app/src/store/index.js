import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import http from "@/util/http.js";
import router from "@/router";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    videos: [],
    videoReviews: [],
    videoId: "",
    video: {},
    users: [],
    user: {},
    loginUser: null,
    zzimList: [],
  },
  getters: {},
  mutations: {
    SET_VIDEO_LIST(state, videos) {
      state.videos = videos;
    },
    SET_VIDEO_REVIEWS(state, reviews) {
      state.videoReviews = reviews;
    },
    SET_VIDEO(state, video) {
      state.video = video;
    },
    CREATE_USER(state, user) {
      state.users.push(user);
    },
    SET_USER(state, user) {
      state.user = user;
    },
    SET_USERS(state, users) {
      state.users = users;
    },
    SET_LOGIN_USER(state) {
      let token = sessionStorage.getItem("access-token");
      if (token) {
        let base64Payload = token.split(".")[1];
        let payload = new TextDecoder().decode(
          new Uint8Array([...atob(base64Payload)].map((c) => c.charCodeAt(0)))
        );
        let result = JSON.parse(payload.toString());
        state.loginUser = result;
      }
    },
    LOGOUT(state) {
      state.loginUser = null;
      sessionStorage.removeItem("access-token");
    },
    DELETE_ZZIM(state, zzimNum) {
      for (let zzim of state.zzimList) {
        if (zzim.zzimNum == zzimNum) {
          let idx = state.ZzimList.indexOf(zzim);
          ZzimList.splice(idx, 1);
        }
      }
    },
    ADD_ZZIM(state, newZzim) {
      state.zzimList.push(newZzim);
    },
    SET_ZZIMLIST(state, zzimList) {
      state.zzimList = zzimList;
    },
  },
  actions: {
    createUser({ commit }, user) {
      console.log(user);
      http
        .post("userapi/user", user)
        .then(() => {
          commit("CREATE_USER", user);
          alert("회원가입 완료");
          router.push("/login"); //로그인 화면으로 이동하기
        })
        .catch((err) => {
          console.log(err);
        });
    },
    videoSearch({ commit }, word) {
      const apiKey = "AIzaSyBCemuYfu5PQsgPVL_oTEudlK9GnsKZ4is";
      axios
        .get(
          `https://www.googleapis.com/youtube/v3/search?part=snippet&q=${
            word + " 운동"
          }&key=${apiKey}&maxResults=50&type=video&videoSyndicated=true`
        )
        .then((response) => {
          const he = require("he");
          const videoItems = response.data.items;
          const videos = videoItems.map((item) => {
            const videoId = item.id.videoId;
            const title = he.decode(item.snippet.title);
            const channelTitle = he.decode(item.snippet.channelTitle);
            return {
              videoId,
              title,
              channelTitle,
            };
          });
          commit("SET_VIDEO_LIST", videos); // mutation 호출
        })
        .catch((error) => {
          console.error("API 요청 실패", error);
        });
    },
    setVideoReviews({ commit }, videoId) {
      http
        .get(`reviewapi/video/${videoId}`)
        .then((res) => {
          commit("SET_VIDEO_REVIEWS", res.data);
          for (let video of this.state.videos) {
            if (video.videoId === videoId) {
              commit("SET_VIDEO", video);
              router.push(`/video/${videoId}`);
              break;
            }
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setLoginUser({ commit }, user) {
      http
        .post("userapi/login", user)
        .then((res) => {
          sessionStorage.setItem("access-token", res.data["access-token"]);
          commit("SET_LOGIN_USER");
          this.actions.setZzimList(user.userId);
          router.push("/");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addReview({ commit }, newReview) {
      http
        .post("reviewapi/review", newReview)
        .then(() => {
          http
            .get(`reviewapi/video/${newReview.videoId}`)
            .then((res) => {
              console.log(res.data);
              commit("SET_VIDEO_REVIEWS", res.data);
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteZzim({ commit }, zzimNum) {
      http
        .delete(`zzimapi/zzim/${zzimNum}`)
        .then(() => {
          commit("DELETE_ZZIM", zzimNum);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setZzimList({ commit }) {
      let token = sessionStorage.getItem("access-token");
      let base64Payload = token.split(".")[1];
      let payload = new TextDecoder().decode(
        new Uint8Array([...atob(base64Payload)].map((c) => c.charCodeAt(0)))
      );
      let result = JSON.parse(payload.toString());
      http
        .get(`zzimapi/user/${result.userId}`)
        .then((res) => {
          console.log(res.data);
          commit("SET_ZZIMLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    addZzim({ commit }) {
      let newZzim = {
        userId: this.state.loginUser.userId,
        videoId: this.state.video.videoId,
        title: this.state.video.title,
        channelName: this.state.video.channelTitle,
      };
      http
        .post("zzimapi/zzim", newZzim)
        .then(() => {
          commit("ADD_ZZIM", newZzim);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateUser({commit}, user){ 
      http.put(`userapi/user`, user) 
      .then(() => {
        commit("SET_USER", user);
        router.push("/"); //일단 메인으로 이동
       // router.push(`/mypage/{loginUser}`); //마이페이지로 이동, 로그인 되면 뒤에 로그인유저 붙이기
      })
      .catch((err) => {
        console.log(err);
      });

    }
  },
  modules: {},
});
