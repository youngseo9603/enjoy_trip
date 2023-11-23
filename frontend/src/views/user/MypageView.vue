<template>
	<div class="flex" style="width: 100%; height: 1500px">
		<div class="bg-gray-800 menu" style="height: 700px; width: 220px">
			<ul>
				<router-link
					to="/mypage"
					:class="{ selected: $route.path === '/mypage' }"
				>
					<li>
						<img src="@/assets/icon/info.svg" alt="SVG Icon" />
						<p :class="{ info: $route.path === '/mypage' }">내 정보</p>
					</li></router-link
				>
				<hr />
				<router-link
					to="/mypage/mytrip"
					:class="{ selected: $route.path === '/mypage/mytrip' }"
				>
					<li>
						<img src="@/assets/icon/earth.svg" alt="SVG Icon" />
						<p :class="{ plan: $route.path === '/mypage/mytrip' }">
							내 여행계획
						</p>
					</li>
				</router-link>
				<hr />

				<router-link
					to="/mypage/mycomment"
					:class="{ selected: $route.path === '/mypage/mycomment' }"
				>
					<li>
						<img src="@/assets/icon/comment.svg" alt="SVG Icon" />
						<p :class="{ wishlist: $route.path === '/mypage/mycomment' }">
							내 위시리스트
						</p>
					</li>
				</router-link>
				<hr />

				<router-link
					to="/mypage/mypost"
					:class="{ selected: $route.path === '/mypage/mypost' }"
				>
					<li>
						<img src="@/assets/icon/pencil.svg" alt="SVG Icon" />
						<p :class="{ written: $route.path === '/mypage/mypost' }">
							내가 쓴 글
						</p>
					</li>
				</router-link>
				<hr />
			</ul>
		</div>
		<div class="mypageview">
			<router-view></router-view>
		</div>
	</div>
</template>

<script setup>
import memberAPI from '@/api/member.js';
import store from '@/stores/index';
import { ref } from 'vue';

var member = ref({});

const startMyPage = () => {
	memberAPI.getMyInfo(
		store.state.account.memberIndex,
		({ data }) => {
			member.value = data.data;
		},
		() => {
			console.log('마이 데이터 정보 불러오기 실패');
		},
	);
};

startMyPage();
</script>

<style lang="scss" scoped>
.menu {
	position: sticky;
	top: 100px;
}
ul {
	list-style-type: none;
	font-size: 16px;
	padding: 0;
	margin: 0;
	color: white;
}

li {
	margin: 20px;
	margin-left: 30px;
	padding: 10px;
	border-radius: 5px;
	align-items: center;
	display: flex;
}

a {
	text-decoration: none;
}

img {
	width: 25px;
	margin-right: 5px;
}

.mypageview {
	padding: 50px;
}
.selected p {
	font-weight: bold;
}
h2 {
	font-size: 20px;
}
td {
}
</style>
