import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import PostListView from '@/views/posts/PostListView.vue';
import PostDetailView from '@/views/posts/PostDetailView.vue';
import PostEditView from '@/views/posts/PostEditView.vue';
import PostCreateView from '@/views/posts/PostCreateView.vue';
import LoginView from '@/views/user/LoginView.vue';
import SignupView from '@/views/user/SignupView.vue';
import MyPageView from '@/views/user/MyPageView.vue';
import SearchLocView from '@/views/trip/SearchLoc.vue';
import PlanView from '@/views/Plan.vue';
import PlanDateView from '@/views/trip/PlanDate.vue';
import PlanLocationView from '@/views/trip/PlanLocation.vue';
import PlanTripView from '@/views/trip/PlanTrip.vue';
import PlanMapPathView from '@/views/trip/PlanMapPath.vue';
import InfoView from '@/views/user/mypage/Info.vue';
import MycommentView from '@/views/user/mypage/MyComment.vue';
import MypostView from '@/views/user/mypage/MyPost.vue';
import MytripView from '@/views/user/mypage/MyTrip.vue';

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
			children: [
				{
					path: '',
					name: 'info',
					component: InfoView,
				},
				{
					path: 'mypost',
					name: 'mypost',
					component: MypostView,
				},
				{
					path: 'mycomment',
					name: 'mycomment',
					component: MycommentView,
				},
				{
					path: 'mytrip',
					name: 'mytrip',
					component: MytripView,
				},
			],
		},
		{
			path: '/post',
			name: 'PostList',
			component: PostListView,
		},
		{
			path: '/postCreate',
			name: 'PostCreate',
			component: PostCreateView,
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
			component: PlanView,
			children: [
				{
					path: 'wholeplan',
					name: 'wholeplan',
					component: PlanTripView,
				},
				{
					path: '',
					name: 'planDate',
					component: PlanDateView,
					meta: {
						next: 'planlocation',
					},
				},
				{
					path: 'planlocation',
					name: 'planLocation',
					component: PlanLocationView,
					meta: {
						next: 'wholeplan',
						prev: 'planDate',
					},
				},
				{
					path: 'path/:id',
					name: 'path',
					component: PlanMapPathView,
				},
			],
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
