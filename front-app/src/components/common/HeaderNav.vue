<template>
  <div class="frame">
    <h1>
      <a href="/">home</a>
    </h1>
    <div class="frame__links">
      <router-link to="/login">로그인</router-link> 
      <router-link to="/regist">회원가입</router-link> 

      <!-- 여기부터 로그인 모달창-->
      <a @click="modalShow = !modalShow">로그인 모달창</a>
      <b-modal class = "modal" v-model="modalShow">
        로그인 모달창입니다. 
        <form action="" class="was-validated">
          <div class="form-group">
            <label for="userId">아이디 </label>
            <input
              type="text"
              class="form-control"
              id="userId"
              placeholder="Enter ID"
              name="userId"
              required />
            <div class="valid-feedback">입력되었습니다.</div>
            <div class="invalid-feedback">아이디를 입력하세요.</div>
          </div>
          <div class="form-group">
            <label for="userPw">비밀번호 </label>
            <input
              type="password"
              class="form-control"
              id="userPw"
              placeholder="Enter Password"
              name="userPw"
              required />
            <div class="valid-feedback">입력되었습니다.</div>
            <div class="invalid-feedback">비밀번호를 입력하세요</div>
          </div>
          <button
            type="submit"
            class="btn btn-primary"
            style="margin-left: 40%">
            로그인
          </button>
          <button type="button" class="btn btn-secondary">회원가입</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">
            취소
          </button>
          <a href="/email"> 비밀번호를 잊으셨나요?</a>
        </form>
      </b-modal>

      <!-- 여기까지 로그인 모달창 작업-->

      <!--이메일 보내기 모달창 작업-->
      <a @click="sendMail = !sendMail"> 이메일보내기테스트</a>
      <b-modal v-model="sendMail">
        <form method="POST" action="/userapi/email">
          <div>
            <label for="userMail">이메일</label>
            <input
              type="text"
              id="userMail"
              placeholder="이메일을입력하세요"
              name="userMail" />
          </div>
          <button type="submit">보내기</button>
        </form>
      </b-modal>

      <!-- 여기까지 이메일 모달창 작업-->

    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderNav",
  data() {
    return {
      modalShow: false,
      sendMail: false,
    };
  },
};
</script>

<style></style>
