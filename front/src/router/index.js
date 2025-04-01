// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'

import LoginPage from '@/views/LoginPage.vue'
import JoinPage from '@/views/JoinPage.vue'
import DashboardPage from '@/views/DashboardPage.vue'

const routes = [
    { path: '/', redirect: '/login' },
    { path: '/login', component: LoginPage },
    { path: '/join', component: JoinPage },
    { path: '/dashboard', component: DashboardPage },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router