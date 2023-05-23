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
    loginUser: {},
    ZzimList: [],
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
    SET_LOGIN_USER(state, user) {
      state.loginUser = user;
    },
    LOGOUT(state) {
      state.loginUser = null;
    },
    DELETE_ZZIM(state, zzimNum) {
      for (let zzim of state.ZzimList) {
        if (zzim.zzimNum == zzimNum) {
          let idx = state.ZzimList.indexOf(zzim);
          ZzimList.splice(idx, 1);
        }
      }
    },
  },
  actions: {
    createUser({ commit }, user) {
      console.log(user);
      http
        .post("userapi/user/regist", user)
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
      console.log(word);
      const apiKey = "AIzaSyBCemuYfu5PQsgPVL_oTEudlK9GnsKZ4is";
      axios
        .get(
          `https://www.googleapis.com/youtube/v3/search?part=snippet&q=${word + " 운동"
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
      this.state.loginUser.userId = "qwer";
      http
        .get(`reviewapi/video/${videoId}`)
        .then((res) => {
          console.log(res.data);
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
    addReview({ commit }, newReview) {
      console.log(newReview);
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
          // 여기부분 로그인하면서 만드는 찜 목록 불러오기를 추가 실행
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
});
