<template>
  <div style="justify-content: center">
    <div class="header-container">
      <input
        type="text"
        class="search-input"
        @keyup.enter="videoSearch"
        v-model="searchWord"
        ref="search"
        placeholder="          검색어를 입력하세요" />
      <b-button class="search-button" @click="videoSearch"
        >검색</b-button
      >
    </div>
    <div class="content-container">
      <div class="card-container mt-4 ml-5 mr-5 mb-3">
        <b-card
          bg-variant="dark"
          text-variant="white"
          header="Secondary"
          class="text-center"
          v-for="(video, index) in paginatedVideos"
          :key="index"
          @click="goDetail(video.videoId)">
          <template #header>
            <img :src="getThumbnailUrl(video.videoId)" style="width: 100%" />
          </template>
          <p class="card-text">{{ truncateTitle(video.title) }}</p>
          <p class="card-text">{{ video.channelTitle }}</p>
        </b-card>
      </div>

      <div class="pagination-container">
        <b-pagination
          bg-variant="dark"
          align="center"
          pills
          v-model="currentPage"
          :total-rows="videoSize()"
          :per-page="perPage"
          class="pagination-dark"
          size="lg"></b-pagination>
      </div>
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
      if (sessionStorage.getItem("Authorization")) {
        this.$store.dispatch("setVideoReviews", videoId);
        this.$router.push(`/video/${videoId}`);
      } else {
        alert("로그인이 필요합니다.");
      }
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

<style scoped>
.content-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  max-height: 100vh;
  overflow-y: auto;
  scrollbar-width: thin;
  -webkit-scrollbar-width: thin;
}
.content-container::-webkit-scrollbar {
  width: 0.2rem;
}

.content-container::-webkit-scrollbar-thumb {
  background-color: transparent;
}

.content-container::-webkit-scrollbar-thumb:hover {
  background-color: transparent;
}

.pagination-dark .page-link {
  color: rgb(68, 67, 67);
}

.pagination-dark .page-item.active .page-link {
  background-color: #343a40;
  border-color: #343a40;
  color: antiquewhite;
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.card {
  width: calc(100% / 6);
  margin: 10px;
  border-color: rgb(55, 102, 104);
}

.header-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 5px;
  width: 100%;
}

.search-input {
  border-radius: 10px;
  flex: 0.6;
  margin-right: 10px;
  height: 40px;
}

.search-button {
  width: 80px;
  height: 40px;
  font-size: medium;
  display: flex;
  align-items: center;
  justify-content: center;
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
</style>