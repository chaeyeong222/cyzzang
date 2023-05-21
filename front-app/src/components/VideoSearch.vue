<template>
  <div>
    <div class="card-container">
      <b-card v-for="(video, index) in paginatedVideos" :key="index" class="card">
        <template #header>
          <img :src="getThumbnailUrl(video.videoId)" style="width: 100%;" />
        </template>
        <p class="card-text">{{ video.title }}</p>
        <p class="card-text">{{ video.channelTitle }}</p>
      </b-card>
    </div>

    <div class="pagination-container">
      <b-pagination
        v-model="currentPage"
        :total-rows="videoSize()"
        :per-page="perPage"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "SearchResult",
  data() {
    return {
      currentPage: 1,
      perPage: 15,
    };
  },
  computed: {
    ...mapState(["videos"]),
    paginatedVideos() {
      const startIndex = (this.currentPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      return this.videos.slice(startIndex, endIndex);
    },
  },
  methods: {
    videoSize() {
      return this.videos.length;
    },
    getThumbnailUrl(videoId) {
      return `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`;
    },
  },
};
</script>

<style>
.card-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }

  .card {
    width: calc(100% / 6);
    margin: 10px;
  }

  @media (max-width: 768px) { 
    .card {
      width: calc(100% / 4);
    }
  }

  @media (max-width: 480px) { 
    .card {
      width: 100% - 20px;
    }
  }
  .pagination-container {
    display: flex;
    justify-content: center; 
    margin-top: 20px;
  }
</style>
