import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import PostListView from '@/views/posts/PostListView.vue';
import LoginView from '@/views/user/LoginView.vue';
import SignupView from '@/views/user/SignupView.vue';
const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: '/',
			name: 'home',
			component: HomeView,
		},
		{
			path: '/about',
			name: 'about',
			component: () => import('../views/AboutView.vue'),
		},
		{
			path: '/post',
			name: 'PostList',
			component: PostListView,
		},
		{
			path: '/login',
			name: 'Login',
			component: LoginView,
		},
		{
			path: '/signup',
			name: 'SignUp',
			component: SignupView,
		},
	],
});

export default router;
