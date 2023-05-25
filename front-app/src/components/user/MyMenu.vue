<template>
  <div class="container scroll-container">
    <div style="text-align: center;">
      <h1>오늘의 식단 추천</h1>
    </div>
    <div class="row">
      <div class="col">
        <div class="card mb-4">
          <div class="card-body text-center">
            <h4 class="card-title">
              {{ loginUser.nickName }} 님의 키와 몸무게 정보입니다
            </h4>
          </div>
        </div>
        <div class="card">
          <h5
            class="card-body"
            style="display: flex; flex-direction: column; text-align: center;"
          >
            <p>키: {{ loginUser.height }} cm</p>
            <p>몸무게: {{ loginUser.weight }} kg</p>
          </h5>
        </div>
      </div>
      <div>
        <div style="text-align: center">
          <h4 class="card-title">
            {{ loginUser.nickName }} 님의 하루 적정체중은 {{ betterWeight }} kg
            입니다.
          </h4>
          <h4 class="card-title">
            {{ loginUser.nickName }} 님의 하루 권장 섭취 칼로리는
            {{ eatTotalCal }} kcal 입니다.
          </h4>

          <h4 class="card-title">
            한 끼에 {{ eatCal }} kcal 이하 섭취를 권장해요!
          </h4>
        </div>
      </div>
      <div class="col">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title" style="width: 500px">
              버튼을 눌러 랜덤 식단 재료를 추천받아보세요!!
              <b-button @click="getRandomMenu" class="btn-btn"
                >랜덤 식단 추천</b-button
              >
            </h5>

            <ul class="list-group">
              <li class="list-group-item">
                <span class="badge bg-danger">탄수화물</span>
                {{ randomValue.carb }}
              </li>
              <li class="list-group-item">
                <span class="badge bg-primary">단백질</span>
                {{ randomValue.prot }}
              </li>
              <li class="list-group-item">
                <span class="badge bg-success text-dark">기본 채소</span>
                {{ randomValue.veg1 }}
              </li>
              <li class="list-group-item">
                <span class="badge bg-info text-dark">보충 채소</span>
                {{ randomValue.veg2 }}
              </li>
              <li class="list-group-item">
                <span class="badge bg-warning text-dark">간식</span>
                {{ randomValue.snack }}
              </li>
              <li class="list-group-item">
                <span class="badge bg-secondary">과일</span>
                {{ randomValue.fruit }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  data() {
    return {
      randomValue: {},
    };
  },
  computed: {
    ...mapState(["loginUser"]),
    betterWeight() {
      return Math.round(
        this.loginUser.height * 0.01 * (this.loginUser.height * 0.01) * 22
      );
    },
    eatTotalCal() {
      return this.betterWeight * 35;
    },
    eatCal() {
      return Math.round(this.eatTotalCal / 3);
    },
  },
  methods: {
    getRandomMenu() {
      axios({
        method: "GET",
        url: "http://localhost:9999/foodapi/food",
        headers: {
          "Content-Type": "application/json",
          Authorization: sessionStorage.getItem("Authorization"),
        },
      })
        .then((response) => {
          this.randomValue = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style scoped>
.scroll-container {
  height: 100vh;
  overflow: auto;
  scrollbar-width: thin;
  scrollbar-color: transparent transparent;
}

.scroll-container::-webkit-scrollbar {
  width: 6px;
  background-color: transparent;
}

.scroll-container::-webkit-scrollbar-thumb {
  background-color: transparent;
}

.scroll-container {
  scrollbar-color: transparent transparent;
  scrollbar-width: thin;
}

.scroll-container:hover {
  scrollbar-color: rgba(0, 0, 0, 0.3) rgba(0, 0, 0, 0.3);
}

.scroll-container:hover::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.3);
}

.card {
  background-color: #1f2028;
  border-color: rgb(55, 102, 104);
  color: whitesmoke;
  width: 100%;
  justify-self: center;
}

.list-group-item {
  background-color: #1f2028;
  color: #c4c3c9;
}
.badge{
  margin-right: 20pt;
  width: 50px;
}

.container {
  max-width: 800px;
  margin: 0 auto;
}
.container {
  color: #b2366f;
  max-width: 800px;
  margin: 0 auto;
}
.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}
.btn-btn {
  width: auto;
  color: wheat;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  background-color: rgb(170, 170, 69);
  padding: 5px;
}
</style>
