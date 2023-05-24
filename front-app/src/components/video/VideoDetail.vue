<template>
  <div
    class="container"
    :style="{ width: containerWidth, maxWidth: '90vw', maxHeight: '50vw' }"
  >
    <h2 class="text-center">{{ video.title }}</h2>
    <div class="messaging">
      <div class="inbox_review">
        <div class="inbox_video">
          <div class="favorite-icon" @click="toggleFavorite">
            <i :class="['fa', 'fa-heart', { active: favorite }]"></i>
          </div>
          <div class="embedded-video">
            <b-embed type="iframe" :src="embedURL()" allowfullscreen></b-embed>
          </div>
        </div>
        <div class="mesgs">
          <div class="review_history">
            <div class="review_scroll">
              <div
                class="incoming_review"
                v-for="(review, index) in videoReviews"
                :key="review.id"
                :class="index % 2 === 0 ? 'received_review' : 'sent_review'"
              >
                <div class="message">
                  <div class="message_content">
                    <p>{{ review.content }}</p>
                    <span class="time_date"
                      >{{ review.writeTime }}
                      <i
                        v-for="n in review.rate"
                        :key="n"
                        class="fa fa-star"
                        aria-hidden="true"
                      ></i
                    ></span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="type_review">
            <div class="input_review_write">
              <span class="star-rating">
                <i
                  v-for="n in 5"
                  :key="n"
                  :class="['fa', 'fa-star', { active: n <= selectedRating }]"
                  @click="selectRating(n)"
                ></i>
              </span>
              <input
                type="text"
                class="write_review"
                placeholder="Type a review"
                v-model="newReviewText"
              />
              <button class="review_send_btn" type="button" @click="sendReview">
                <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "videoDetail",
  data() {
    return {
      videoId: "",
      newReviewText: "",
      selectedRating: 0,
      favorite: false,
      zzimNum: 0,
    };
  },
  computed: {
    ...mapState(["videoReviews", "video", "zzimList", "loginUser"]),
    containerWidth() {
      const viewportWidth =
        window.innerWidth || document.documentElement.clientWidth;
      return `${viewportWidth * 0.9}px`;
    },
    isFavorite() {
      return this.video.favorite;
    },
  },
  created() {
    this.$store.commit("SET_LOGIN_USER");
    this.$store.dispatch("setZzimList");
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    this.videoId = id;
    this.$store.dispatch("setVideoReviews", id);
    for (let zzim of this.zzimList) {
      if (zzim.videoId === this.videoId) {
        this.favorite = true;
        this.zzimNum = zzim.zzimNum;
        break;
      }
    }
  },
  methods: {
    toggleFavorite() {
      if (this.favorite) this.$store.dispatch("deleteZzim", this.zzimNum);
      else {
        let newZzim = {
          userId: this.loginUser.userId,
          videoId: this.video.videoId,
          title: this.video.title,
          channelName: this.video.channelTitle,
        };
        this.$store.dispatch("addZzim", newZzim);
      }

      this.favorite = !this.favorite;
    },
    selectRating(rating) {
      this.selectedRating = rating;
    },
    embedURL() {
      return `https://www.youtube.com/embed/${this.videoId}?rel=0`;
    },
    adjustContainerHeight() {
      const container = this.$el;
      const windowHeight = window.innerHeight;
      const containerTop = container.getBoundingClientRect().top;
      const containerHeight = windowHeight - containerTop;
      container.style.height = `${containerHeight}px`;
    },
    sendReview() {
      if (this.newReviewText && this.selectedRating > 0) {
        let videoReview = {
          videoId: this.videoId,
          userId: this.$store.state.loginUser.userId,
          content: this.newReviewText,
          rate: this.selectedRating,
        };
        this.$store.dispatch("addReview", videoReview);
        this.newReviewText = "";
        this.selectedRating = 0;
      } else {
        alert("리뷰 내용과 별점을 선택해주세요.");
      }
    },
    scrollToBottom() {
      const container = this.$el.querySelector(".review_scroll");
      container.scrollTop = container.scrollHeight;
    },
  },
  mounted() {
    this.$nextTick(() => {
      this.scrollToBottom();
    });
    window.addEventListener("resize", this.adjustContainerHeight);
    this.adjustContainerHeight();
    this.$watch("videoReviews", () => {
      this.$nextTick(() => {
        this.scrollToBottom();
      });
    });
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.adjustContainerHeight);
  },
};
</script>

<style>
@import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css");
.favorite-icon {
  top: 10px;
  right: 10px;
  z-index: 2;
  cursor: pointer;
  color: #ccc;
}

.favorite-icon .fa-heart {
  font-size: 24px;
}

.favorite-icon .fa-heart.active {
  color: red;
}

.time_date i {
  color: #fcd12a;
}

.star-rating i.active {
  color: #fcd12a;
}

.review_history {
  background-color: transparent;
  overflow-y: auto;
  max-height: 100%-48pt;
}

.review_history::-webkit-scrollbar {
  width: 0;
  background: transparent;
}

.review_scroll {
  padding-right: 15px;
}

.container {
  max-width: 1170px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.inbox_video {
  overflow: hidden;
  width: 100%;
  border-right: 1px solid #c4c4c4;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60vw;
}

.embedded-video {
  position: relative;
  padding-bottom: 56.25%;
  width: 100%;
}

.embedded-video iframe {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.video-embed {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.inbox_review {
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
  display: flex;
  justify-content: center;
}

.mesgs {
  padding: 30px 15px 0 25px;
  width: 100%;
  height: 60vw;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}

.review_history {
  background-color: transparent;
}

.incoming_review {
  margin-bottom: 15px;
}

.received_review {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
}

.sent_review {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

.sent_review .message {
  text-align: right;
}

.sent_review .message_content p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 12px 5px 10px;
  width: 100%;
}

.message {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
}

.message_content p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}

.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}

.type_review {
  border-top: 1px solid #c4c4c4;
  position: relative;
}

.input_review_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.review_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}

.messaging {
  padding: 0 0 30px 0;
  width: 90vw;
  height: 50vw;
}

.text-center {
  color: palevioletred;
}
</style>
