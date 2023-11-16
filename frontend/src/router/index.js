import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import PostListView from '@/views/posts/PostListView.vue';
import PostDetailView from '@/views/posts/PostDetailView.vue';
import PostEditView from '@/views/posts/PostEditView.vue';
import LoginView from '@/views/user/LoginView.vue';
import SignupView from '@/views/user/SignupView.vue';
import SearchLocView from '@/views/trip/SearchLoc.vue';
import PlanMapPathView from '@/views/trip/PlanMapPath.vue';
import PlanMapSearchView from '@/views/trip/PlanMapSearch.vue';
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
			//콜론으로 동적 매핑
			path: '/post/:id',
			name: 'PostDetail',
			component: PostDetailView,
		},
		{
			//콜론으로 동적 매핑
			path: '/post/:id/edit',
			name: 'PostEdit',
			component: PostEditView,
		},
		{
			path: '/search',
			name: 'search',
			component: SearchLocView,
		},
		{
			path: '/plan',
			name: 'plan',
			component: PlanMapSearchView,
		},
		{
			path: '/path',
			name: 'path',
			component: PlanMapPathView,
		},
		{
			path: '/login',
			name: 'login',
			component: LoginView,
		},
		{
			path: '/signup',
			name: 'signUp',
			component: SignupView,
		},
	],
});

export default router;
