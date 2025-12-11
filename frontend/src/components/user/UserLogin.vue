<template>
  <div class="container">
    <h2>로그인</h2>
    <div class="form-box">
      <div class="input-group">
        <label for="id">ID</label>
        <input type="text" id="username" v-model="loginObj.username"/>
      </div>
      <div class="input-group">
        <label for="password">PW</label>
        <input type="password" id="password" v-model="loginObj.password"/>
      </div>
      <div class="btn-group">
        <button class="login-btn" @click="login">로그인</button>
      </div>
      <div class="home-link">
        <router-link :to="{ name: 'Home' }">홈으로 돌아가기</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {useUserStore} from '@/stores/userStore';
import {useRouter} from "vue-router";

const store = useUserStore();
const router = useRouter();

const loginObj = ref({
  username: '',
  password: '',
});

const login = async () => {
  if (!loginObj.value.username || !loginObj.value.password) {
    alert("아이디와 비밀번호를 모두 입력해주세요.");
    return;
  }
  try {
    const isSuccess = await store.login(loginObj.value);
    if (isSuccess) {
      router.push({name: "Home"});
    }
  } catch (error) {
    console.log("로그인 중 에러 발생");
  }
};
</script>

<style scoped>
.container {
  width: 80%;
  margin: 20px auto;
  text-align: center;
}

.form-box {
  border: 1px solid #ccc;
  padding: 20px;
  border-radius: 5px;
  max-width: 400px;
  margin: 0 auto;
}

.input-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

label {
  width: 50px;
  font-weight: bold;
}

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.btn-group {
  margin-top: 20px;
}

.login-btn {
  background-color: rgb(243, 169, 32);
  color: white;
  border: none;
  padding: 10px 30px;
  cursor: pointer;
  border-radius: 5px;
  font-weight: bold;
}

.login-btn:hover {
  background-color: chocolate;
}

.home-link {
  margin-top: 20px;
  font-size: 0.9rem;
}

.home-link a {
  text-decoration: none;
  color: #666;
}

.home-link a:hover {
  color: black;
  font-weight: bold;
}
</style>
