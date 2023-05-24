import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import http from "@/util/http";
import router from "@/router";

Vue.use(Vuex);

const URL = "http://localhost:9999/";

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
      let token = sessionStorage.getItem("Authorization");
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
      sessionStorage.removeItem("Authorization");
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
        .post("userapi/regist", user)
        .then(() => {
          commit("CREATE_USER", user);
          alert("회원가입 완료");
          router.push("user/login"); //로그인 화면으로 이동하기
        })
        .catch((err) => {
          console.log(err);
        });
    },
    videoSearch({ commit }, word) {
      const apiKey = "AIzaSyDZVBVC_rfXXmVOC4gp5pc0dUxiPpsyyHE";
      axios
        .get(
          `https://www.googleapis.com/youtube/v3/search?part=snippet&q=${word + " 운동"
          }&key=${apiKey}&maxResults=50&type=video&videoSyndicated=true`
        )
        .then((res) => {
          const he = require("he");
          const videoItems = res.data.items;
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
      console.log();
      axios({
        method: "GET",
        url: URL + `reviewapi/video/${videoId}`,
        headers: {
          'Authorization': sessionStorage.getItem("Authorization"),
          'Content-Type': 'application/json'
        },
      })
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
          alert("로그인을 먼저 해주세요");
        });
    },
    setLoginUser(context, user) {
      http
        .post("userapi/login", user)
        .then((res) => {
          if (res.status == 204) {
            alert("아이디 또는 비밀번호를 확인하세요.");
          } else {
            alert(res.data);
            sessionStorage.setItem("Authorization", res.data["Authorization"]);
            context.commit("SET_LOGIN_USER");
            context.dispatch("setZzimList")
            router.push("/");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addReview(context, newReview) {
      axios({
        method: "POST",
        url: URL + "reviewapi/review",
        headers: {
          'Authorization': sessionStorage.getItem("Authorization"),
          'Content-Type': 'application/json'
        },
        data: newReview,
      })
        .then(() => {
          context.dispatch("setVideoReviews", newReview.videoId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteZzim(context, zzimNum) {
      axios({
        method: "DELETE",
        url: URL + `zzimapi/zzim/${zzimNum}`,
        headers: {
          'Authorization': sessionStorage.getItem("Authorization"),
          'Content-Type': 'application/json'
        },
      })
        .delete(`zzimapi/zzim/${zzimNum}`)
        .then(() => {
          context.dispatch("setZzimList")
        })
        .catch((err) => {
          console.log(err);
        });
    },
    addZzim(context, newZzim) {
      axios({
        method: "POST",
        url: URL + `zzimapi/zzim`,
        headers: {
          'Authorization': sessionStorage.getItem("Authorization"),
          'Content-Type': 'application/json'
        },
        data: newZzim,
      })
        .then(() => {
          context.dispatch("setZzimList")
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setZzimList(context) {
      axios({
        method: "GET",
        url: URL + `zzimapi/user/${context.state.loginUser.userId}`,
        headers: {
          'Authorization': sessionStorage.getItem("Authorization"),
          'Content-Type': 'application/json'
        },
      })
        .then((res) => {
          if (res.status == 200) context.commit("SET_ZZIMLIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateUser({ commit }, user) {
      axios({
        method: "put",
        url: URL + `userapi/user`,
        headers: {
          'Authorization': sessionStorage.getItem("Authorization"),
          'Content-Type': 'application/json'
        },
        data: user,
      })
        .then((res) => {
          if (res.status == 200)
            sessionStorage.setItem("Authorization", res.data["Authorization"]);

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
