<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "MainContent",
  created() {
    document.body.classList.remove("loading");
  },
  mounted() {},
};
</script>

<style>

</style>
