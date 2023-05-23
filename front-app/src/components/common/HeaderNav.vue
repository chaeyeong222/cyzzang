<template>
  <div class="frame">
    <h1>
      <a href="/">home</a>
    </h1>
    <div class="frame__links">
      <router-link to="/login">로그인</router-link> 
      <router-link to="/regist">회원가입</router-link>
      <router-link to="/mypage">마이페이지</router-link>  
 
      <a href="#">검색하기</a>  

    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderNav",
  data() {
    return { 
    };
  },
};
</script>

<style></style>
