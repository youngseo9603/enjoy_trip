<template>
	<h2>내 정보</h2>
	<br />
	<table>
		<tr>
			<td>이름</td>
			<td>좌소연</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>짜짜로니소연</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>jwa1234@jwa.jwa</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>jwa1234</td>
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
