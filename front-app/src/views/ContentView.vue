<template>
  <div data-scroll-container>
    <router-view></router-view>
  </div>
</template>

<script>
import LocomotiveScroll from 'locomotive-scroll';
export default {
  name: "MainContent",
  mounted(){
    const lscroll = new LocomotiveScroll({
      el: this.$el.querySelector('[data-scroll-container]'),
      smooth: true,
    });
    lscroll.update();
  },
};
</script>
