<template>
  <div class="container">
    <h2>사용자 등록</h2>
    <div class="form-box">
      <div class="input-group">
        <label for="id">ID</label>
        <input type="text" id="username" name="username" v-model="user.username"/>
      </div>
      <div class="input-group">
        <label for="password">PW</label>
        <input type="password" id="password" name="password" v-model="user.password"/>
      </div>

      <div class="btn-group">
        <button class="regist-btn" @click="regist">등록</button>
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

const user = ref({
  username: '',
  password: '',
});

const regist = async () => {
  if (!user.value.username || !user.value.password) {
    alert('항목을 모두 작성해주세요.');
    return;
  }

  try {
    const isSuccess = await store.createUser(user.value);

    if (isSuccess) {
      router.push({name: 'Home'});
    }
  } catch (error) {
    console.log("가입 실패");
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
  max-width: 500px;
  margin: 0 auto;
}

.input-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

label {
  margin-right: 10px;
  font-weight: bold;
  width: 60px;
  text-align: right;
}

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ddd;
}

.btn-group {
  text-align: center;
  margin-top: 20px;
}

.regist-btn {
  background-color: #35495e;
  color: white;
  border: none;
  padding: 10px 30px;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
  font-size: 1rem;
}

.regist-btn:hover {
  background-color: #2c3e50;
}

.home-link {
  margin-top: 20px;
}

.home-link a {
  text-decoration: none;
  color: #888;
  font-size: 0.9rem;
}

.home-link a:hover {
  color: #333;
  text-decoration: underline;
}
</style>
