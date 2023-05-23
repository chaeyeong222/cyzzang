<template>
  <div data-scroll-container>
    <div class="content">
      <div class="gallery" id="gallery">
        <div class="gallery__text">
          <span
            class="gallery__text-inner"
            data-scroll
            data-scroll-speed="3"
            data-scroll-direction="vertical"
            >{{ firstText }}</span
          >
          <span
            data-scroll
            data-scroll-speed="-4"
            data-scroll-direction="vertical"
            class="gallery__text-inner"
            >{{ secondText }}</span
          >
        </div>
        <figure
          v-for="item in items"
          :key="item.number"
          class="gallery__item"
          data-scroll
          data-scroll-speed="1"
        >
          <div class="gallery__item-img">
            <div
              class="gallery__item-imginner"
              :style="`background-image: url(${item.image})`"
            ></div>
          </div>
          <figcaption class="gallery__item-caption">
            <h2 class="gallery__item-title">{{ item.engtitle }}</h2>
            <span class="gallery__item-number">{{ item.number }}</span>
            <p class="gallery__item-tags">
              <span v-for="tag in item.tags" :key="tag"
                ><a @click="videoSearch(tag)">#{{ tag }}</a></span
              >
            </p>
            <a @click="videoSearch(item.title)" class="gallery__item-link">{{
              item.title
            }}</a>
          </figcaption>
        </figure>
        <div class="gallery__text_d">
          <span
            class="gallery__text-inner"
            data-scroll
            data-scroll-speed="-1"
            data-scroll-direction="vertical"
            >{{ thirdText }}</span
          >
          <span
            data-scroll
            data-scroll-speed="1"
            data-scroll-direction="vertical"
            class="gallery__text-inner"
            >{{ fourthText }}</span
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  methods: {
    videoSearch(word) {
      this.$store.dispatch("videoSearch", word);
      this.$router.push("/video");
    },
  },
  data() {
    return {
      firstText: "SSAFIT",
      secondText: "chajong",
      items: [
        {
          image: "img/12.jpg",
          engtitle: "WHOLE  BODY",
          title: "전신",
          number: "01",
          tags: ["house", "green", "chair"],
        },
        {
          image: "img/11.jpg",
          engtitle: "Shoulder",
          title: "어깨",
          number: "02",
          tags: ["love", "hug", "people"],
        },
        {
          image: "img/10.jpg",
          engtitle: "Chest",
          title: "가슴",
          number: "03",
          tags: ["hike", "nature", "rain"],
        },
        {
          image: "img/9.jpg",
          engtitle: "Arms",
          title: "팔",
          number: "04",
          tags: ["free", "wood", "fire"],
        },
        {
          image: "img/8.jpg",
          engtitle: "Back",
          title: "등",
          number: "05",
          tags: ["cloud", "lake", "frog"],
        },
        {
          image: "img/7.jpg",
          engtitle: "Abdomen",
          title: "복근",
          number: "06",
          tags: ["tent", "flower", "love"],
        },
        {
          image: "img/6.jpg",
          engtitle: "Lower Body",
          title: "하체",
          number: "07",
          tags: ["water", "bottle", "hand"],
        },
        {
          image: "img/5.jpg",
          engtitle: "Shoulder",
          title: "Disenthral",
          number: "08",
          tags: ["night", "stars", "moon"],
        },
        {
          image: "img/4.jpg",
          engtitle: "Shoulder",
          title: "Abaya",
          number: "09",
          tags: ["sun", "light", "air"],
        },
        {
          image: "img/3.jpg",
          engtitle: "Shoulder",
          title: "Hallux",
          number: "10",
          tags: ["vital", "fog", "close"],
        },
        {
          image: "img/2.jpg",
          engtitle: "Shoulder",
          title: "Lablab",
          number: "11",
          tags: ["cover", "bed", "window"],
        },
        {
          image: "img/1.jpg",
          engtitle: "Shoulder",
          title: "Momisom",
          number: "12",
          tags: ["sad", "mouth", "tear"],
        },
      ],
      thirdText: "Health is not valued",
      fourthText: "till sickness comes",
    };
  },
};
</script>

<style>
/*! locomotive-scroll v4.0.4 | MIT License | https://github.com/locomotivemtl/locomotive-scroll */
html.has-scroll-smooth {
  overflow: hidden;
}

html.has-scroll-dragging {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.has-scroll-smooth body {
  overflow: hidden;
}

.has-scroll-smooth [data-scroll-container] {
  min-height: 100vh;
}

[data-scroll-direction="horizontal"] [data-scroll-container] {
  white-space: nowrap;
  height: 100vh;
  display: inline-block;
  white-space: nowrap;
}

[data-scroll-direction="horizontal"] [data-scroll-section] {
  display: inline-block;
  vertical-align: top;
  white-space: nowrap;
  height: 100%;
}

.c-scrollbar {
  position: absolute;
  right: 0;
  top: 0;
  width: 11px;
  height: 100%;
  transform-origin: center right;
  transition: transform 0.3s, opacity 0.3s;
  opacity: 0;
}
.c-scrollbar:hover {
  transform: scaleX(1.45);
}
.c-scrollbar:hover,
.has-scroll-scrolling .c-scrollbar,
.has-scroll-dragging .c-scrollbar {
  opacity: 1;
}
[data-scroll-direction="horizontal"] .c-scrollbar {
  width: 100%;
  height: 10px;
  top: auto;
  bottom: 0;
  transform: scaleY(1);
}
[data-scroll-direction="horizontal"] .c-scrollbar:hover {
  transform: scaleY(1.3);
}

.c-scrollbar_thumb {
  position: absolute;
  top: 0;
  right: 0;
  background-color: black;
  opacity: 0.5;
  width: 7px;
  border-radius: 10px;
  margin: 2px;
  cursor: -webkit-grab;
  cursor: grab;
}
.has-scroll-dragging .c-scrollbar_thumb {
  cursor: -webkit-grabbing;
  cursor: grabbing;
}
[data-scroll-direction="horizontal"] .c-scrollbar_thumb {
  right: auto;
  bottom: 0;
}
</style>
