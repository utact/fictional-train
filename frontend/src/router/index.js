import {createRouter, createWebHistory} from 'vue-router'
import UserCreate from "@/components/user/UserCreate.vue";
import HomeView from "@/views/HomeView.vue";
import UserView from "@/views/UserView.vue";
import UserLogin from "@/components/user/UserLogin.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView,
    },
    {
      path: '/user',
      component: UserView,
      children: [
        {
          path: 'regist',
          name: 'Regist',
          component: UserCreate,
        },
        {
          path: 'login',
          name: 'Login',
          component: UserLogin,
        },
      ],
    },
  ],
});

export default router
