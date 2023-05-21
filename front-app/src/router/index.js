import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import VideoSearch from "@/components/VideoSearch.vue";

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
    children: [{ path: "", name: "list", component: VideoSearch }],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
