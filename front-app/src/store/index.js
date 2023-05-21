import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "@/router";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    videos: [],
  },
  getters: {},
  mutations: {
    SET_VIDEO_LIST(state, videos) {
      state.videoList = videos;
    },
  },
  actions: {
    videoSearch({commit},word){
      console.log(word);
      const apiKey = 'AIzaSyBCemuYfu5PQsgPVL_oTEudlK9GnsKZ4is';
      axios.get(`https://www.googleapis.com/youtube/v3/search?part=snippet&q=${word}&key=${apiKey}`)
      .then(response => {
        const videoItems = response.data.items;
        console.log(videoItems);
        const videos = videoItems.map(item => {
          const videoId = item.id.videoId;
          const title = item.snippet.title;
          const channelId = item.snippet.channelId;
          const channelTitle = item.snippet.channelTitle;
          return {
            videoId,
            title,
            channelId,
            channelTitle
          };
        });

        commit('SET_VIDEO_LIST', videos); // mutation 호출
        router.push("/video")
      })
      .catch(error => {
        console.error('API 요청 실패', error);
      });
    },

  },
  modules: {},
});
