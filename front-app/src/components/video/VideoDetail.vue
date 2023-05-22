<template>
  <div class="container">
    <h2 class="text-center">{{ video.title }}</h2>
    <div class="messaging">
      <div class="inbox_review">
        <div class="inbox_video">
          <b-embed type="iframe" :src="embedURL()"></b-embed>
        </div>
        <div class="mesgs">
          <div class="review_history">
            <div
              class="incoming_review"
              v-for="(review, index) in reviews"
              :key="review.id"
              :class="index % 2 === 0 ? 'received_review' : 'sent_review'"
            >
              <div class="message">
                <div class="message_content">
                  <p>{{ review.text }}</p>
                  <span class="time_date">{{ review.time }}</span>
                  <span class="time_date">{{ review.rate }}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="type_review">
            <div class="input_review_write">
              <input
                type="text"
                class="write_review"
                placeholder="Type a review"
                v-model="newreviewText"
              />
              <button class="review_send_btn" type="button" @click="sendreview">
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
      newreviewText: "",
    };
  },
  computed: {
    ...mapState(["videoReviews", "video"]),
    reviews() {
      // 여기에서 실제로 사용할 메시지 데이터를 반환합니다. 예시로 하드코딩하였습니다.
      return [
        {
          id: 1,
          text: "Test which is a new approach to have all solutions",
          time: "11:01 AM | June 9",
        },
        {
          id: 2,
          text: "Test, which is a new approach to have",
          time: "11:01 AM | Yesterday",
        },
        {
          id: 3,
          text: "Apollo University, Delhi, India Test",
          time: "11:01 AM | Today",
        },
        {
          id: 4,
          text: "We work directly with our designers and suppliers, and sell direct to you, which means quality, exclusive products, at a price anyone can afford.",
          time: "11:01 AM | Today",
        },
      ];
    },
  },
  created() {
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length - 1];
    this.videoId = id;
    if (this.video.videoId !== this.videoId) {
      alert("잘못된 접근입니다.");
      this.$router.push("/");
      this.$router.go(0);
    }
  },
  methods: {
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
    sendreview() {
      if (this.newreviewText) {
        // 메시지 전송 로직을 추가하세요.
        console.log("Send review:", this.newreviewText);
        this.newreviewText = ""; // 입력 필드 비우기
      }
    },
  },
  mounted() {
    window.addEventListener("resize", this.adjustContainerHeight);
    this.adjustContainerHeight();
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.adjustContainerHeight);
  },
};
</script>


<style>
.container {
  max-width: 1170px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.inbox_video {
  overflow: hidden;
  width: 100%; /* 가로 길이를 100%로 조정 */
  border-right: 1px solid #c4c4c4;
  display: flex;
  justify-content: center;
  align-items: center; /* 동영상을 수직으로 가운데 정렬 */
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
  width: 60%;
}

/* Remove the white background */
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
  padding: 0 0 50px 0;
}

.text-center {
  color: palevioletred;
}
</style>
