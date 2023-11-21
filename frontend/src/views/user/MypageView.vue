<template>
	<div class="grid grid-cols-4 gap-4" style="width: 100%; height: 1500px">
		<div class="col-span-1" style="height: 100%">
			<br />
			<br />
			<br />
			<br />
			<div class="text-center">{{ member.name }}</div>
			<div class="text-center">{{ member.email }}</div>
			<br />
			<br />
			<ul>
				<router-link to="/mypage/" 
					><li>
						<img src="@/assets/icon/info.svg" alt="SVG Icon" />내 정보
					</li></router-link
				>
				<router-link to="/mypage/mytrip"
					><li>
						<img src="@/assets/icon/earth.svg" alt="SVG Icon" />내 여행계획
					</li></router-link
				>
				<router-link to="/mypage/mypost"
					><li>
						<img src="@/assets/icon/pencil.svg" alt="SVG Icon" />내가 쓴 글
					</li></router-link
				>
			</ul>
		</div>
		<div class="col-span-3"><router-view></router-view></div>
	</div>
</template>

<script setup>
import memberAPI from '@/api/member.js';
import store from '@/stores/index';
import { ref } from 'vue';

var member = ref({});

const startMyPage = () =>{
	memberAPI.getMyInfo(
		store.state.account.memberIndex,
		({ data }) => {
			member.value = data.data;
		},
		() => {
			console.log('마이 데이터 정보 불러오기 실패');
		},
	);
}



startMyPage();
</script>

<style lang="scss" scoped>
ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

li {
	margin: 20px;
	padding: 10px;
	border-radius: 5px;
	justify-content: center;
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
</style>
