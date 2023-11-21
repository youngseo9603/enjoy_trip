<template>
	<div>내 정보</div>
	<div>{{ member.name }}</div>
	<div>{{ member.nickname }}</div>
	<div>{{ member.email }}</div>
	<div>{{ member.loginId }}</div>
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
}

startMyPage();
</script>

<style lang="scss" scoped></style>
