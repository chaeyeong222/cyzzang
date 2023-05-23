<template>
  <div data-scroll-container>
    <router-view></router-view>
  </div>
</template>

<script>
import LocomotiveScroll from "locomotive-scroll";
export default {
  name: "MainContent",
  created() {
    document.body.classList.remove("loading");
  },
  mounted() {
    const lscroll = new LocomotiveScroll({
      el: this.$el.querySelector("[data-scroll-container]"),
      smooth: true,
    });
    lscroll.update();
  },
};
</script>
