<template>
  <div id="app">
    <header-nav />
    <router-view />
    <svg class="cursor" width="20" height="20" viewBox="0 0 20 20">
      <circle class="cursor__inner" cx="10" cy="10" r="5" />
    </svg>
  </div>
</template>

<script>
import HeaderNav from "./components/common/HeaderNav.vue";

export default {
  name: "App",
  components: {
    HeaderNav,
  },
};


</script>

<style>

</style>
