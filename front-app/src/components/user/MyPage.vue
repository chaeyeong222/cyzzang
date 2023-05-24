<template>
  <div class="mypage">
    <div
      class="card mb-4"
      style="
        width: 600px;
        background-color: #1f2028;
        color: palevioletred;
        margin: 20px;
      ">
      <div class="left-section">
        <h2>개인정보 수정</h2>
        <div>
          <div class="form-group">
            <label for="userId">아이디</label>
            <input
              type="text"
              id="userId"
              v-model="loginUser.userId"
              disabled
              style="background-color: #1f2028; color: #b1b1b3" />
          </div>
          <div class="form-group">
            <label for="nickName">닉네임</label>
            <input
              style="background-color: #b1b1b3"
              type="text"
              id="nickName"
              v-model="loginUser.nickName" />
          </div>
          <div class="form-group">
            <label for="emailAdress">이메일</label>
            <input
              type="email"
              id="emailAdress"
              v-model="loginUser.emailAdress"
              disabled
              style="background-color: #1f2028; color: #b1b1b3" />
          </div>
          <div class="form-group">
            <label for="height">키</label>
            <input
              type="number"
              id="height"
              v-model="loginUser.height"
              required
              style="background-color: #b1b1b3" />
          </div>
          <div class="form-group">
            <label for="weight">몸무게</label>
            <input
              type="number"
              id="weight"
              v-model="loginUser.weight"
              required
              style="background-color: #b1b1b3" />
          </div>
          <div style="display: flex; flex-direction: column">
            <div style="text-align: right">
              <b-button
                @click="updateUserInfo"
                style="background-color: #f6bf50"
                class="btn-btn"
                >수정하기</b-button
              >
            </div>
          </div>
        </div>
      </div>
      <router-link to="/user/myMenu" style="align-self: end"
        ><b>오늘의 식단 구성하기</b></router-link
      >
    </div>
    <div
      class="card mb-4"
      style="width: 600px; background-color: #1f2028; margin: 20px">
      <div class="right-section">
        <h2 style="color: palevioletred">찜 목록</h2>
        <div class="scroll-container">
          <table class="table">
            <thead>
              <tr style="color: white">
                <th>제목</th>
                <th>채널</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(zzim, index) in zzimList"
                :key="index"
                style="color: white">
                <td>
                  <a
                    @click="goDetail(zzim.videoId)"
                    style="color: white; margin: 30px 10px 30px 10px"
                    >{{ zzim.title }}</a
                  >
                </td>
                <td>{{ zzim.channelName }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      user: {},
    };
  },
  created() {
    this.$store.commit("SET_LOGIN_USER");
    this.$store.dispatch("setZzimList");
  },
  computed: {
    ...mapState(["loginUser", "zzimList"]),
  },
  methods: {
    goDetail(videoId) {
      this.$store.dispatch("setVideoReviews", videoId);
      this.$router.push(`/video/${videoId}`);
    },
    updateUserInfo() {
      let user = {
        userId: this.loginUser.userId,
        nickName: this.loginUser.nickName,
        emailAdress: this.loginUser.emailAdress,
        height: this.loginUser.height,
        weight: this.loginUser.weight,
      };
      alert("개인정보가 업데이트되었습니다.");
      this.$store.dispatch("updateUser", user);
    },
  },
};
</script>

<style scoped>
.scroll-container {
  height: 70vh;
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
.mypage {
  display: flex;
  justify-content: space-between;
}

.left-section,
.right-section {
  flex: 1;
  padding: 40px;
  margin: 10px;
}

form {
  margin-bottom: 20px;
}

.form-group {
  margin: 30px 10px 30px 10px;
}

label {
  display: block;
}

input {
  width: 100%;
  padding: 5px;
  font-size: 14px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 5px;
}
.btn-btn {
  width: fit-content;
  padding: 8px;
}
</style>
