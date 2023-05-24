<template>
  <div class="mypage">
    <div class="left-section">
      <h2>개인정보 수정</h2>
      <div>
        <div class="form-group">
          <label for="userId">아이디</label>
          <input type="text" id="userId" v-model="loginUser.userId" disabled />
        </div>
        <div class="form-group">
          <label for="nickName">닉네임</label>
          <input type="text" id="nickName" v-model="loginUser.nickName" />
        </div>
        <div class="form-group">
          <label for="emailAdress">이메일</label>
          <input
            type="email"
            id="emailAdress"
            v-model="loginUser.emailAdress"
            disabled
          />
        </div>
        <div class="form-group">
          <label for="height">키</label>
          <input
            type="number"
            id="height"
            v-model="loginUser.height"
            required
          />
        </div>
        <div class="form-group">
          <label for="weight">몸무게</label>
          <input
            type="number"
            id="weight"
            v-model="loginUser.weight"
            required
          />
        </div>
        <button @click="updateUserInfo">저장</button>
      </div>
      <router-link to="/user/myMenu">오늘의 식단 구성하기</router-link>
    </div>
    <div class="right-section">
      <div class="scrollable-container">
        <table class="table">
          <thead>
            <tr>
              <th>제목</th>
              <th>채널</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(zzim, index) in zzimList" :key="index">
              <td>
                <a @click="goDetail(zzim.videoId)">{{ zzim.title }}</a>
              </td>
              <td>{{ zzim.channelName }}</td>
            </tr>
          </tbody>
        </table>
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
    this.$store.dispatch("setZzimList")
  },
  computed: {
    ...mapState(["loginUser","zzimList"]),
  },
  methods: {
    goDetail(videoId) {
      this.$store.dispatch("setVideoReviews", videoId);
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
  
<style>
.scrollable-container {
  height: auto;
  overflow-y: scroll;
}
.mypage {
  display: flex;
  justify-content: space-between;
}

.left-section,
.right-section {
  flex: 1;
  padding: 20px;
}

form {
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 10px;
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
</style>
  