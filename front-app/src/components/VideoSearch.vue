<template>
  <div>
    <h1>검색</h1>
    <div v-for="(video, index) in videos" :key="index">
      <h1>{{ video.title }}</h1>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "SearchResult",
  computed: {
    ...mapState(["videos"]),
  },
  created() {
    console.log(this.$store.state.videos);
  },
};
</script>

<style></style>
