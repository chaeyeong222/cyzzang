<template>
  <div style="justify-content: center">
    <br />
    <br />
    <div class="header-container">
      <input
        type="text"
        class="search-input"
        @keyup.enter="videoSearch"
        v-model="searchWord"
        ref="search"
        placeholder="검색어를 입력하세요"
      />
      <button class="search-button" @click="videoSearch">검색</button>
    </div>
    <div class="card-container">
      <b-card
        v-for="(video, index) in paginatedVideos"
        :key="index"
        class="card"
        @click="goDetail(video.videoId)"
      >
        <template #header>
          <img :src="getThumbnailUrl(video.videoId)" style="width: 100%" />
        </template>
        <p class="card-text">{{ truncateTitle(video.title) }}</p>
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
export default {
  name: "SearchResult",
  data() {
    return {
      currentPage: 1,
      perPage: 0,
      searchWord: "",
    };
  },

  computed: {
    videos() {
      return this.$store.state.videos;
    },
    paginatedVideos() {
      const startIndex = (this.currentPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      return this.videos.slice(startIndex, endIndex);
    },
  },
  methods: {
    goDetail(videoId) {
      this.$store.dispatch("setVideoReviews", videoId);
      this.$router.push(`/video/${videoId}`)
    },
    videoSearch() {
      this.$store.dispatch("videoSearch", this.searchWord);
      this.$refs.search.value = "";
    },
    videoSize() {
      return this.videos.length;
    },
    getThumbnailUrl(videoId) {
      return `https://img.youtube.com/vi/${videoId}/hqdefault.jpg`;
    },
    updatePerPage() {
      const screenWidth = window.innerWidth;
      if (screenWidth >= 768) {
        this.perPage = 10;
      } else if (screenWidth >= 480) {
        this.perPage = 6;
      } else {
        this.perPage = 1;
      }
    },
    truncateTitle(title) {
      const maxLength = 20;
      if (title.length <= maxLength) {
        return title;
      } else {
        return title.slice(0, maxLength) + "...";
      }
    },
  },
  created() {
    this.updatePerPage();
    window.addEventListener("resize", this.updatePerPage);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.updatePerPage);
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

.header-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
  width: 100%;
}

.search-input {
  flex: 0.7;
  margin-right: 10px;
}

.search-button {
  width: 80px;
}

@media (max-width: 768px) {
  .card {
    width: calc(100% / 4);
  }
}

@media (max-width: 480px) {
  .card {
    width: 100%;
  }
}
.pagination-container {
  display: flex;
  justify-content: center;
}
</style>