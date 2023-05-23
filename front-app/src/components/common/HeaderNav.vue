<template>
  <div class="frame">
    <h1>
      <a href="/">home</a>
    </h1>
    <div class="frame__links">
      <router-link v-if="loginUser === null" to="/user/login">로그인</router-link>
      <router-link v-if="loginUser === null" to="/user/regist">회원가입</router-link>
      <a href="/" v-if="loginUser !== null" @click="logout">로그아웃</a>
      <router-link to="/user/mypage">마이페이지</router-link>  
      <router-link to="/video">비디오검색</router-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HeaderNav",
  data() {
    return { 
    };
  },
  computed: {
    ...mapState(["loginUser"]),
  },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
    },
  },
};
</script>

<style></style>
