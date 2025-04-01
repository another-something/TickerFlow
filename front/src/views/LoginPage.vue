<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { login } from '@/service/authService'
import Toast from '@/components/Toast.vue'

const userId = ref('')
const userPass = ref('')
const router = useRouter();
const toastVisible = ref(false)
const toastMessage = ref('')

const handleLogin = async () => {
  try {
    const result = await login ({ userId: userId.value, pass: userPass.value })

    if(result.data.code)
      showToast(result.data.message)

    if(result.data.code === 200)
      router.push("/dashboard")
  } catch (err) {
    showToast("서버 오류가 발생했습니다.")
  }
}
const showToast = (msg) => {
  toastMessage.value = msg
  toastVisible.value = true
  setTimeout(() => {
    toastVisible.value = false
  }, 3000)
}
</script>

<template>
  <div class="login-wrapper">
    <div class="login-box">
      <h2 class="login-title">로그인</h2>

      <div class="input-group">
        <label for="userId">아이디</label>
        <input v-model="userId" id="userId" type="text" placeholder="아이디 입력" />
      </div>

      <div class="input-group">
        <label for="userPass">비밀번호</label>
        <input v-model="userPass" id="userPass" type="password" placeholder="비밀번호 입력" />
      </div>

      <button class="login-button" @click="handleLogin">로그인</button>

      <p class="signup-text">
        계정이 없으신가요? <a href="/join">회원가입</a>
      </p>
    </div>
  </div>
  <Toast :message="toastMessage" :visible="toastVisible" />
</template>

<style scoped>
/* ✅ 화이트 배경 설정 */
:global(html, body, #app) {
  margin: 0;
  padding: 0;
  background-color: #f9f9f9;
  font-family: 'Pretendard', 'Segoe UI', sans-serif;
  height: 100%;
}

/* ✅ 전체 화면 중앙 정렬 */
.login-wrapper {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* ✅ 어두운 카드 스타일 박스 */
.login-box {
  width: 360px;
  background-color: #1e1e1e; /* ✅ 다크 카드 배경 */
  padding: 40px;
  border-radius: 16px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.25); /* ✅ 부드러운 그림자 */
  text-align: center;
  color: white; /* ✅ 텍스트 흰색으로 반전 */
}

/* ✅ 타이틀 */
.login-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 30px;
  color: #222;
}

/* ✅ 입력 필드 */
.input-group {
  text-align: left;
  margin-bottom: 20px;
}

.input-group label {
  font-size: 14px;
  color: #444;
  margin-bottom: 8px;
  display: block;
}

.input-group input {
  width: 100%;
  padding: 10px;
  background-color: #f2f2f2;
  border: 1px solid #ddd;
  border-radius: 6px;
  color: #333;
  font-size: 14px;
}

.input-group input::placeholder {
  color: #aaa;
}

/* ✅ 로그인 버튼 */
.login-button {
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
}

.login-button:hover {
  background-color: #245fdd;
}

/* ✅ 회원가입 안내 */
.signup-text {
  margin-top: 20px;
  font-size: 14px;
  color: #555;
}

.signup-text a {
  color: #3478f6;
  text-decoration: none;
  font-weight: 500;
}
.login-title {
  color: white;
}

.input-group label {
  color: #ccc;
}

.signup-text {
  color: #aaa;
}
.input-group input,
.login-button {
  width: 100%;
  box-sizing: border-box; /* ✅ padding, border 포함 너비 통일 */
}

</style>