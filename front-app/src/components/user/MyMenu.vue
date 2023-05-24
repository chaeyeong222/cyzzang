<template>
  <div class="container">
    <div>
      <h1>오늘의 식단 추천</h1>
    </div>
    <div class="row">
      <div class="col-md-4">
        <div class="card mb-4">
          <div class="card-body text-center">
            <h4 class="card-title">{{ nickName }}</h4>
            <p class="card-text">님의 키와 몸무게 정보입니다</p>
          </div>
        </div>
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">About Me</h5>
            <p>키: {{ height }} cm</p>
            <p>몸무게: {{ weight }} kg</p>
          </div>
        </div>
      </div>
      <div>
        <h4 class="card-title">
          {{ nickName }} 님의 하루 권장 소비 칼로리는 {{ wasteCal }} kcal
          입니다.
        </h4>
        <h4 class="card-title">
          {{ nickName }} 님의 하루 권장 섭취 칼로리 는 {{ eatCal }} kcal 입니다.
        </h4>
      </div>
      <div class="col-md-8">
        <div class="card" style="width: 600px">
          <div class="card-body">
            <h5 class="card-title" style="width: 500px">
              아래의 재료를 곁들여 먹는 걸 추천해요!
              <button class="btn" @click="getRandomMenu" style="width: 100px">
                랜덤 식단 추천
              </button>
            </h5>

            <ul class="list-group">
              <!-- <div v-for="category in categories" :key="category.id">
                  <h2>{{ category.name }}</h2>
                  <p>{{ category.randomData }}</p>
              </div> -->

              <li class="list-group-item">
                <span class="badge bg-success">탄수화물</span>
                {{ randomValue }}
              </li>
              <li class="list-group-item">
                <span class="badge bg-primary">단백질</span>
                Order #5678 - Widget Y
              </li>
              <li class="list-group-item">
                <span class="badge bg-warning text-dark">채소</span>
                Order #9012 - Widget Z
              </li>
              <li class="list-group-item">
                <span class="badge bg-warning text-dark">간식</span>
                Order #9012 - Widget Z
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http.js";

export default {
  data() {
    return {
      userId: "testtest",
      nickName: "냠냠냠",
      height: 190,
      weight: 80,
      wasteCal: 999,
      eatCal: 888,
      randomValue: null,
      // categories: [
      //   { id: 1, name: "카테고리 1", randomData: null },
      //   { id: 2, name: "카테고리 2", randomData: null },
      //   { id: 3, name: "카테고리 3", randomData: null },
      //   // 추가적인 카테고리들...
      // ]
    };
  },

  methods: {
    getRandomMenu() {
      // this.categories.forEach(category => {
      http
        .get(`/foodapi/food/`)
        .then((response) => {
          this.randomValue = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
      // }
      //  );
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}
</style>
