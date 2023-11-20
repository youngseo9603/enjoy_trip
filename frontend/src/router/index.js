import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import PostListView from '@/views/posts/PostListView.vue';
import PostDetailView from '@/views/posts/PostDetailView.vue';
import PostEditView from '@/views/posts/PostEditView.vue';
import LoginView from '@/views/user/LoginView.vue';
import SignupView from '@/views/user/SignupView.vue';
import MyPageView from '@/views/user/MyPageView.vue';
import SearchLocView from '@/views/trip/SearchLoc.vue';
import PlanDateView from '@/views/trip/PlanDate.vue';
import PlanLocationView from '@/views/trip/PlanLocation.vue';
import PlanTripView from '@/views/trip/PlanTrip.vue';
import PlanMapPathView from '@/views/trip/PlanMapPath.vue';
import PostCreateView from '@/views/posts/PostCreateView.vue';

// import PlanMapSearchView from '@/views/trip/PlanMapSearch.vue';
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
			path: '/mypage',
			name: 'mypage',
			component: MyPageView,
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
			component: PlanTripView,
			children: [
				{
					path: '',
					name: 'PlanMapPath',
					component: PlanMapPathView,
				},
			],
		},
		{
			path: '/plandate',
			name: 'planDate',
			component: PlanDateView,
		},
		{
			path: '/planlocation',
			name: 'planLocation',
			component: PlanLocationView,
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
		{
			path: '/create',
			name: 'postCreate',
			component : PostCreateView
		},
	],
});

export default router;
