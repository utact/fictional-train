<template>
  <div class="home-container">
    <h1>Fictional train</h1>

    <div v-if="!store.isLogin" class="menu-box">
      <router-link :to="{ name: 'Login' }" class="big-btn login-link">로그인</router-link>
      <router-link :to="{ name: 'Regist' }" class="big-btn regist-link">회원가입</router-link>
    </div>

    <div v-else class="menu-box">
      <h3>{{ store.loginUser }}님 반갑습니다!</h3>
      <p>로그인이 완료되었습니다.</p>

      <button class="big-btn logout-btn" @click="handleLogout">로그아웃</button>
    </div>
  </div>
</template>

<script setup>
import {useUserStore} from '@/stores/userStore';

const store = useUserStore();
const handleLogout = () => {
  store.logout();
};
</script>

<style scoped>
.home-container {
  text-align: center;
  margin-top: 100px;
}

.menu-box {
  margin-top: 50px;
  display: flex;
  justify-content: center;
  gap: 20px;
  align-items: center;
  flex-direction: column;
}

.big-btn {
  display: inline-block;
  width: 200px;
  padding: 15px 0;
  text-align: center;
  text-decoration: none;
  font-size: 1.2rem;
  font-weight: bold;
  border-radius: 8px;
  cursor: pointer;
  border: none;
  margin: 10px;
}

.login-link {
  background-color: #42b983;
  color: white;
}

.regist-link {
  background-color: #35495e;
  color: white;
}

.big-btn:hover {
  opacity: 0.9;
  transform: scale(1.02);
  transition: 0.2s;
}
</style>
