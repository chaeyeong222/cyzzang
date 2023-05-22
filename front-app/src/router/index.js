import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import ContentView from "@/views/ContentView.vue";
import VideoSearch from "@/components/video/VideoSearch.vue";
import VideoDetail from "@/components/video/VideoDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/emailcheck",
    name: "email",
    component: HomeView,
  },
  {
    path: "/video",
    component: ContentView,
    children: [
      {
        path: "",
        name: "list",
        component: VideoSearch
      },
      {
        path: ":videoId",
        name: "VideoDetail",
        component: VideoDetail
      }
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
