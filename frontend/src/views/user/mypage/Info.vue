<template>
	<h2>내 정보</h2>
	<br />
	<table>
		<tr>
			<th>이름</th>
			<td>{{ member.name }}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>{{ member.nickname }}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>{{ member.email }}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>{{ member.loginId }}</td>
		</tr>
	</table>

	<!-- <div>{{ member.name }}</div>
	<div>{{ member.nickname }}</div>
	<div>{{ member.email }}</div>
	<div>{{ member.loginId }}</div> -->
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

<style></style>
