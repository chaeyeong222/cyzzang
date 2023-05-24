import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import MyUser from "../views/MyUser.vue";
import ContentView from "@/views/ContentView.vue";

import VideoSearch from "@/components/video/VideoSearch.vue";
import VideoDetail from "@/components/video/VideoDetail.vue";
import LoginForm from "@/components/user/LoginForm.vue";
import Regist from "@/components/user/Regist.vue";
import MyPage from "@/components/user/MyPage.vue";
import MyMenu from "@/components/user/MyMenu.vue";
import UpdatePassword from "@/components/user/UpdatePassword.vue";

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
        component: VideoSearch,
      },
      {
        path: ":videoId",
        name: "VideoDetail",
        component: VideoDetail,
      },
    ],
  },
  {
    path: "/user",
    component: MyUser,
    children: [
      {
        path: "regist",
        name: "Regist",
        component: Regist,
      },
      {
        path:"mypage",
        name:"MyPage",
        component: MyPage,
      },
      {
        path: "login",
        name: "Login",
        component: LoginForm,
      },
      {
        path: "mymenu",
        name: "MyMenu",
        component: MyMenu,
      }, 
      {
        path: "updatePassword",
        name: "UpdatePassword",
        component: UpdatePassword,
      }  
    ]

  },
 
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
