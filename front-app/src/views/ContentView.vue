<template>
  <div class="scroll-container">
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
.scroll-container {
  height: 100%;
  overflow: auto;
  scrollbar-width: thin;
  scrollbar-color: transparent transparent;
}

.scroll-container::-webkit-scrollbar {
  width: 6px;
  background-color: transparent;
}

.scroll-container::-webkit-scrollbar-thumb {
  background-color: transparent;
}

.scroll-container {
  scrollbar-color: transparent transparent;
  scrollbar-width: thin;
}

.scroll-container:hover {
  scrollbar-color: rgba(0, 0, 0, 0.3) rgba(0, 0, 0, 0.3);
}

.scroll-container:hover::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
