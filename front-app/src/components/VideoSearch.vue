<template>
  <div>
    <h1>검색</h1>
    <div v-for="(video, index) in videos" :key="index">
      <div>{{ video.title }}</div>
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
};
</script>

<style></style>
