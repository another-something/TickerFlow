<template>
  <div>
    <h2>대시보드</h2>
    <button @click="goLogin">로그인으로 이동</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const goLogin = () => {
  router.push('/login')
}
</script>