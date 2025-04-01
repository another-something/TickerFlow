<template>
  <div class="register-wrapper">
    <div class="register-box">
      <h2 class="register-title">회원가입</h2>

      <div class="input-group">
        <label for="userId">아이디</label>
        <!--<input v-model="userId" id="userId" type="text" @keyup="checkId($event)" placeholder="영어 소문자, 숫자만 허용됩니다." />-->
        <input v-model="userId" id="userId" type="text" @input="checkUserId($event)" placeholder="영어 소문자, 숫자만 허용됩니다.(6~15 글자 이내 입력)" />
        <label for="idMessage" :style="{ color : idMessageColor }">{{ idMessage }}</label>
      </div>

      <div class="input-group">
        <label for="userPass">비밀번호</label>
        <input v-model="userPass" id="userPass" type="password" @input="checkPassword($event)" placeholder="영어 + 숫자 + 특수문자 포함 10자리 이상 입력해주세요" />
        <label for="passMessage" :style="{ color : messageColor }">{{ passMessage }}</label>
      </div>

      <div class="input-group">
        <label for="userPassConfirm">비밀번호 확인</label>
        <input v-model="userPassConfirm" id="userPassConfirm" type="password" @input="checkSamePassword($event)" placeholder="영어 + 숫자 + 특수문자 포함 10자리 이상 입력해주세요" />
        <label for="sameMessage" :style="{ color : sameMessageColor }">{{ sameMessage }}</label>
      </div>

      <div class="input-group">
        <label for="email">이메일</label>
        <input v-model="email" id="email" type="text" @input="checkEmail($event)" placeholder="example@email.com" />
        <label for="emailMessage" :style="{ color : emailMessageColor }">{{ emailMessage }}</label>
      </div>

      <button class="register-button" type="button" @click="join">회원가입</button>

      <p class="login-text">
        이미 계정이 있으신가요? <a href="/login">로그인</a>
      </p>
    </div>

    <!-- ✅ 토스트 알림 -->
    <Toast :message="toastMessage" :visible="toastVisible" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { join as joinRequest } from '@/service/authService'
import Toast from '@/components/Toast.vue'
import axios from 'axios';
import { debounce } from "lodash";

const userId = ref('')
const userPass = ref('')
const userPassConfirm = ref('')
const toastMessage = ref('')
const toastVisible = ref(false)
const email = ref('')
const router = useRouter()
const idMessage = ref('');
const idMessageColor = ref('');
const passMessage = ref('');
const messageColor = ref('');
const sameMessage = ref('');
const sameMessageColor = ref('');
const emailMessage = ref('');
const emailMessageColor = ref('');

const showToast = (msg) => {
  toastMessage.value = msg
  toastVisible.value = true
  setTimeout(() => {
    toastVisible.value = false
  }, 3000)
}

/* 아이디 형식 맞는지 확인*/
const checkUserId = debounce (async (event) => {
  const userId = event.target.value;
  try {
    const res = await axios.post("/api/user/check-id", {
      userId: userId,
    });
    const { valid, message } = res.data;
    idMessage.value = message;
    idMessageColor.value = valid ? "green" : "red";
  } catch (err) {
    console.log(err);
    idMessageColor.value = "서버 오류가 발생하였습니다.";
    idMessageColor.value = "red";
  }
}, 500);

/* 패스워드 형식 맞는지 확인 */
const checkPassword = debounce(async (event) => {

  const userPass = event.target.value;

  try {
    const res = await axios.post("/api/user/check-pw", {
      userPass: userPass,
    });
    const {valid, message } = res.data;
    passMessage.value = message;
    messageColor.value = valid ? "green" : "red";

  } catch (err) {
    console.log(err);
    passMessage.value = "서버 오류가 발생하였습니다.";
    messageColor.value = "red";
  }

}, 500);

const checkEmail = debounce( async (event) => {
  const userEmail = event.target.value;
  try {
    const res = await axios.post("/api/user/check-email", {
      email: userEmail,
    });
    const {valid, message } = res.data;
    emailMessage.value = message;
    emailMessageColor.value = valid ? "green" : "red";

  } catch (err) {

  }
}, 500);

const checkSamePassword = debounce(async () => {

  try {
    const res = await axios.post("/api/user/check-same-pw", {
      userPass: userPass.value,
      userPassConfirm: userPassConfirm.value
    });
    const {valid, message } = res.data;
    sameMessage.value = message;
    sameMessageColor.value = valid ? "green" : "red";
  } catch (err) {
    console.log(err);
    sameMessage.value = "서버 오류가 발생하였습니다.";
    sameMessageColor.value = "red";
  }
}, 500);

const join = async () => {
  try {
    const res = await axios.post("/api/join", {
      userId: userId.value,
      userPass: userPass.value,
      userPassConfirm: userPassConfirm.value,
      userEmail: email.value
    }, {
      headers: {
        "Content-Type": "application/json"
      }
    });

    if (res.data.success) {
      showToast(res.data.message)
      router.push('/login')
    } else {
      showToast(res.data.message)
    }

  } catch (err) {
    console.error("❌ 회원가입 실패", err);
    showToast('회원가입 중 오류가 발생했습니다.');
  }
};
</script>

<style scoped>
.register-wrapper {
  height: 100vh;
  background-color: #f9f9f9;
  display: flex;
  justify-content: center;
  align-items: center;
}

.register-box {
  width: 360px;
  background-color: #1e1e1e;
  padding: 40px;
  border-radius: 16px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.25);
  color: white;
  text-align: center;
}

.register-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 30px;
}

.input-group {
  text-align: left;
  margin-bottom: 20px;
}

.input-group label {
  font-size: 14px;
  color: #ccc;
  margin-bottom: 8px;
  display: block;
}

.input-group input {
  width: 100%;
  padding: 10px;
  background-color: #2a2a2a;
  border: none;
  border-radius: 6px;
  color: white;
  font-size: 14px;
  box-sizing: border-box;
}

.input-group input::placeholder {
  color: #aaa;
}

.register-button {
  width: 100%;
  padding: 12px;
  background-color: #3478f6;
  color: white;
  border: none;
  border-radius: 6px;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.2s;
  box-sizing: border-box;
}

.register-button:hover {
  background-color: #245fdd;
}

.login-text {
  margin-top: 20px;
  font-size: 14px;
  color: #aaa;
}

.login-text a {
  color: #3478f6;
  text-decoration: none;
  font-weight: 500;
}
</style>