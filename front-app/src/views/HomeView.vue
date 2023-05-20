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
          data-scroll-speed="1">
          <div class="gallery__item-img">
            <div
              class="gallery__item-imginner"
              :style="`background-image: url(${item.image})`"></div>
          </div>
          <figcaption class="gallery__item-caption">
            <h2 class="gallery__item-title">{{ item.title }}</h2>
            <span class="gallery__item-number">{{ item.number }}</span>
            <p class="gallery__item-tags">
              <span v-for="tag in item.tags" :key="tag">#{{ tag }}</span>
            </p>
            <a class="gallery__item-link">explore</a>
          </figcaption>
        </figure>
        <div class="gallery__text">
          <span
            class="gallery__text-inner"
            data-scroll
            data-scroll-speed="-4"
            data-scroll-direction="vertical"
            >{{ thirdText }}</span
          >
          <span
            data-scroll
            data-scroll-speed="3"
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
  data() {
    return {
      firstText: "SSAFIT",
      secondText: "chajong",
      items: [
        {
          image: "img/12.jpg",
          title: "Funambulist",
          number: "01",
          tags: ["house", "green", "chair"],
        },
        {
          image: "img/11.jpg",
          title: "Omophagy",
          number: "02",
          tags: ["love", "hug", "people"],
        },
        {
          image: "img/10.jpg",
          title: "Conniption",
          number: "03",
          tags: ["hike", "nature", "rain"],
        },
        {
          image: "img/9.jpg",
          title: "Xenology",
          number: "04",
          tags: ["free", "wood", "fire"],
        },
        {
          image: "img/8.jpg",
          title: "Lycanthropy",
          number: "05",
          tags: ["cloud", "lake", "frog"],
        },
        {
          image: "img/7.jpg",
          title: "Mudlark",
          number: "06",
          tags: ["tent", "flower", "love"],
        },
        {
          image: "img/6.jpg",
          title: "Illywhacker",
          number: "07",
          tags: ["water", "bottle", "hand"],
        },
        {
          image: "img/5.jpg",
          title: "Disenthral",
          number: "08",
          tags: ["night", "stars", "moon"],
        },
        {
          image: "img/4.jpg",
          title: "Abaya",
          number: "09",
          tags: ["sun", "light", "air"],
        },
        {
          image: "img/3.jpg",
          title: "Hallux",
          number: "10",
          tags: ["vital", "fog", "close"],
        },
        {
          image: "img/2.jpg",
          title: "Lablab",
          number: "11",
          tags: ["cover", "bed", "window"],
        },
        {
          image: "img/1.jpg",
          title: "Momisom",
          number: "12",
          tags: ["sad", "mouth", "tear"],
        },
      ],
      thirdText: "Hexed",
      fourthText: "kambu",
    };
  },
};

</script>

<style>

</style>
