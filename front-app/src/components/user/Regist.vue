<template>
  <div class="section">
    <div class="container scroll-container">
      <div class="row full-height justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="section pb-5 pt-5 pt-sm-2 text-center">
            <div class="card-3d-wrap mx-auto">
              <div class="card-3d-wrapper">
                <div class="card-front">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <h3 class="mb-4 pb-3">회원가입</h3>
                      <div class="sssss">
                        <div class="form-group mt-2">
                          <label for="userId"> 아이디</label>
                          <input
                            :readonly="idCC"
                            type="text"
                            id="userId"
                            name="userId"
                            class="form-style"
                            placeholder="아이디를 입력하세요"
                            v-model="userId"
                            autocomplete="off"
                            ref="idDupCheck" />
                          <i class="input-icon uil uil-user"></i>
                          <button class="btn" @click="idDuplicateCheck(userId)">
                            중복확인
                          </button>
                        </div>
                        <div class="form-group mt-2">
                          <label for="nickName">닉네임</label>
                          <input
                            type="text"
                            name="nickName"
                            class="form-style"
                            placeholder="닉네임을 입력하세요"
                            v-model="nickName"
                            autocomplete="off" />
                          <i class="input-icon uil uil-user"></i>
                        </div>
                        <div class="form-group mt-2">
                          <label for="emailAdress">이메일</label>
                          <input
                            :readonly="emailCC"
                            type="email"
                            name="emailAdress"
                            class="form-style"
                            placeholder="이메일을 입력하세요"
                            v-model="emailAdress"
                            autocomplete="off"
                            ref="emailDupCheck" />
                          <i class="input-icon uil uil-at"></i>
                          <button
                            class="btn"
                            @click="emailDuplicateCheck(emailAdress)">
                            중복확인
                          </button>
                        </div>
                        <div v-if="emailSendCheck" class="form-group mt-2">
                          <label for=""></label>
                          <input
                            type="text"
                            name="emailAuthentic"
                            v-model="emailAuthentic"
                            class="form-style"
                            placeholder="인증번호 입력"
                            ref="emailAuthCheck" />
                          <button
                            class="btn"
                            @click="authenNumCheck(emailAuthentic)">
                            인증번호확인
                          </button>
                        </div>
                        <div class="form-group mt-2">
                          <label for="password">비밀번호</label>
                          <input
                            type="password"
                            id="password"
                            name="password"
                            class="form-style"
                            placeholder="비밀번호를 입력하세요"
                            v-model="password"
                            autocomplete="off" />
                          <i class="input-icon uil uil-lock-alt"></i>
                        </div>
                        <div class="form-group mt-2">
                          <label for="password2">비밀번호 확인</label>
                          <input
                            type="password"
                            id="password2"
                            name="password2"
                            class="form-style"
                            placeholder="비밀번호를 한번 더 입력하세요"
                            v-model="password2"
                            autocomplete="off" />
                          <i class="input-icon uil uil-lock-alt"></i>
                        </div>
                        <p v-if="isPasswordMatch">비밀번호가 일치합니다.</p>
                        <p v-else>비밀번호가 일치하지 않습니다.</p>
                      </div> 
                    </div>
                    <button
                      v-if="isRegistPossible"
                      class="btn"
                      style="width: 120px"
                      @click="regist">
                      가입하기
                    </button>
                    
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http";

export default {
  name: "UserList",
  data() {
    return {
      userId: "",
      password: "",
      nickName: "",
      emailAdress: "",
      password2: "",
      emailSendCheck: false,
      emailAuthentic: "", // 인증번호
      idCC: false, //아이디 중복확인 완료 시
      emailCC: false, //이메일 중복확인 완료 시
      authenticNumCC: false, //인증번호 확인 환료시
      isRegistPossible: false,
      isPasswordMatch: false,
    };
  },
  methods: {
    regist() {
      if (
        this.password === "" ||
        this.password2 === "" ||
        this.isPasswordMatch === false
      ) {
        alert("비밀번호를 확인해주세요");
        return;
      }

      let user = {
        userId: this.userId,
        password: this.password,
        nickName: this.nickName,
        emailAdress: this.emailAdress,
      };

      alert("완료");
      this.$store.dispatch("createUser", user);
    },

    idDuplicateCheck(userId) {
      http.get(`userapi/dupli/${userId}`).then((res) => {
        if (res.data) {
          alert("사용가능한 아이디 입니다.");
          this.idCC = true;
          this.registPossible();
        } else {
          alert("사용 불가능한 아이디 입니다. ");
          this.$refs.idDupCheck.value = "";
        }
      });
    },

    emailDuplicateCheck(emailAdress) {
      http.get(`userapi/email/${emailAdress}`).then((res) => {
        if (res.data === "") {
          alert("이미 등록된 이메일입니다. ");
          this.$refs.emailDupCheck.value = "";
        } else {
          alert("사용가능한 이메일입니다.");
          this.authenticNum = res.data; //넘어온 인증번호
          alert(res.data);
          this.emailSendCheck = true;
          this.emailCC = true;
          this.registPossible();
        }
      });
    },

    authenNumCheck() {
      if (this.authenticNum === this.emailAuthentic) {
        alert("인증완료!!");
        this.authenticNumCC = true;
        this.registPossible();
      } else {
        alert("인증번호가 일치하지 않습니다");
        this.$refs.emailAuthCheck.value = "";
      }
    },
    registPossible() {
      if (this.idCC == true && this.emailCC == true && this.authenticNumCC) {
        this.isRegistPossible = true;
      }
    },
  },
  watch: {
    password() {
      if (this.password !== "" && this.password === this.password2) {
        this.isPasswordMatch = true;
      }
    },
    password2() {
      if (this.password !== "" && this.password === this.password2) {
        this.isPasswordMatch = true;
      }
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
body {
  font-family: "Poppins", sans-serif;
  font-weight: 300;
  font-size: 15px;
  line-height: 1.7;
  color: #c4c3ca;
  background-color: #1f2029;
  overflow-x: hidden;
}
label {
  width: 60px;
  display: flex;
  align-items: center;
}
.sssss {
  display: flex;
  align-items: flex-start;
  flex-direction: column;
}
a {
  cursor: pointer;
  transition: all 200ms linear;
}
a:hover {
  text-decoration: none;
}
.link {
  color: #c4c3ca;
}
.link:hover {
  color: #ffeba7;
}
p {
  font-weight: 500;
  font-size: 14px;
  line-height: 1.7;
  width: 90%;
}
h4 {
  font-weight: 600;
}
h6 span {
  padding: 0 20px;
  text-transform: uppercase;
  font-weight: 700;
}
.section {
  position: relative;
  width: 100%;
  display: block;
}
.full-height {
  min-height: 100vh;
}
[type="checkbox"]:checked,
[type="checkbox"]:not(:checked) {
  position: absolute;
  left: -9999px;
}
.checkbox:checked + label,
.checkbox:not(:checked) + label {
  position: relative;
  display: block;
  text-align: center;
  width: 60px;
  height: 16px;
  border-radius: 8px;
  padding: 0;
  margin: 10px auto;
  cursor: pointer;
  background-color: #ffeba7;
}
.checkbox:checked + label:before,
.checkbox:not(:checked) + label:before {
  position: absolute;
  display: block;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  color: #ffeba7;
  background-color: #102770;
  font-family: "unicons";
  content: "\eb4f";
  z-index: 20;
  top: -10px;
  left: -10px;
  line-height: 36px;
  text-align: center;
  font-size: 24px;
  transition: all 0.5s ease;
}
.checkbox:checked + label:before {
  transform: translateX(44px) rotate(-270deg);
}

.card-3d-wrap {
  position: relative;
  width: 500px;
  max-width: 100%;
  height: 600px;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  perspective: 800px;
  margin-top: 60px;
}
.card-3d-wrapper {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  transition: all 600ms ease-out;
}
.card-front,
.card-back {
  width: 100%;
  height: 100%;
  background-color: #2a2b38;
  background-image: url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/1462889/pat.svg");
  background-position: bottom center;
  background-repeat: no-repeat;
  background-size: 300%;
  position: absolute;
  border-radius: 6px;
  left: 0;
  top: 0;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -o-backface-visibility: hidden;
  backface-visibility: hidden;
}
.card-back {
  transform: rotateY(180deg);
}
.checkbox:checked ~ .card-3d-wrap .card-3d-wrapper {
  transform: rotateY(180deg);
}
.center-wrap {
  position: absolute;
  width: 100%;
  padding: 0 35px;
  top: 50%;
  left: 0;
  transform: translate3d(0, -50%, 35px) perspective(100px);
  z-index: 20;
  display: block;
} 
.form-group {
  position: relative;
  display: flex;
  margin: 0;
  padding: 0;
}
.form-style {
  padding: 0px 20px 0px 45px;
  height: 48px;
  /* width: 100%; */
  width: 280px;
  font-weight: 500;
  border-radius: 4px;
  font-size: 14px;
  line-height: 22px;
  letter-spacing: 0.5px;
  outline: none;
  color: #c4c3ca;
  background-color: #1f2029;
  border: none;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.form-style:focus,
.form-style:active {
  border: none;
  outline: none;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.input-icon {
  position: absolute;
  top: 0;
  left: 18px;
  height: 48px;
  font-size: 24px;
  line-height: 48px;
  text-align: left;
  color: #ffeba7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}

.form-group input:-ms-input-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input::-moz-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:-moz-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input::-webkit-input-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus:-ms-input-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus::-moz-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus:-moz-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus::-webkit-input-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}

.btn {
  width: 70px;
  margin: 0 0 0 10px !important;
  border-radius: 4px;
  height: 42px;
  font-size: 13px;
  font-weight: 600;
  text-transform: uppercase;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  padding: 0 5px;
  letter-spacing: 1px;
  display: -webkit-inline-flex;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-align-items: center;
  -moz-align-items: center;
  -ms-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  -moz-justify-content: center;
  -ms-justify-content: center;
  justify-content: center;
  -ms-flex-pack: center;
  text-align: center;
  border: none;
  background-color: #ffeba7;
  color: #102770;
  box-shadow: 0 8px 24px 0 rgba(255, 235, 167, 0.2);
}
.btn:active,
.btn:focus {
  background-color: #102770;
  color: #ffeba7;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}
.btn:hover {
  background-color: #102770;
  color: #ffeba7;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}
</style>
