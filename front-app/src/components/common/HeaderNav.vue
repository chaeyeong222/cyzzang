<template>
  <div class="frame">
    <h1>
      <a href="/">home</a>
    </h1>
    <div class="frame__links">
      <router-link v-if="loginUser === null" to="/login">로그인</router-link>
      <router-link v-if="loginUser === null" to="/regist">회원가입</router-link>
      <router-link v-if="loginUser" @click="logout()">로그아웃</router-link>
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
      modalShow: false,
      sendMail: false,
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
