<template>
  <div class="frame">
    <h1 class="frame__title">
      <a href="/"
        >Home</a
      >
    </h1>
    <div class="frame__demos">
      <a class="frame__demo" href="index.html">Demo__01</a>
      <a class="frame__demo" href="index2.html">Demo__02</a>
      <a class="frame__demo" href="index3.html">Demo__03</a>
      <a class="frame__demo frame__demo--current" href="index4.html"
        >Demo__04</a
      >
      <a class="frame__demo" href="index5.html">Demo__05</a>
    </div>
    <div class="frame__links">
      <a href="/video">검색</a>
      <a href="#" @click="modalShow = !modalShow">로그인</a>

      <b-modal v-model="modalShow">Hello From Modal!</b-modal>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderNav",
  data() {
    return {
      modalShow: false,
    };
  },
};
</script>

<style></style>
