<template>
	<header>
		<nav
			class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4"
		>
			<RouterLink
				class="navbar-brand mr-5"
				style="font-family: RixInooAriDuriR; font-size: 25px"
				to="/"
				>Foodtrip</RouterLink
			>

			<!-- 주 메뉴 링크 -->
			<ul v-if="!isMenuOpen" class="flex me-auto mb-2 mb-lg-0 mx-3 space-x-4">
				<li class="nav-item">
					<RouterLink class="nav-link" to="/search">여행지</RouterLink>
				</li>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/planDate">여행계획하기</RouterLink>
				</li>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/post?page=1">게시판</RouterLink>
				</li>
			</ul>

			<!-- 반응형 네비게이션 링크 -->
			<ul
				v-if="isMenuOpen"
				class="lg:hidden flex flex-col mb-2 mb-lg-0 mx-3 space-y-4"
			>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/search">여행지</RouterLink>
				</li>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/plan">여행계획하기</RouterLink>
				</li>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/post?page=1">게시판</RouterLink>
				</li>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/planDate">계획 첫번째</RouterLink>
				</li>
			</ul>

			<!-- 사용자 인증 링크 -->
			<ul
				class="flex mb-2 mb-lg-0 mx-3 space-x-4"
				v-if="$store.state.account.memberIndex === 0"
			>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/login">로그인</RouterLink>
				</li>
				<li class="nav-item">
					<RouterLink class="nav-link" to="/signup">회원가입</RouterLink>
				</li>
			</ul>
			<!-- 로그인했을시 -->
			<div v-else>
				<RouterLink class="nav-link" to="/mypage"
					>안녕하세요 {{ store.state.account.memberNickName }}님</RouterLink
				>
			</div>
		</nav>
	</header>
</template>

<script setup>
import { ref } from 'vue';
import store from '@/stores/index';
import memberAPI from '@/api/member.js';

const isMenuOpen = ref(false);

const toggleMenu = () => {
	isMenuOpen.value = !isMenuOpen.value;
};




</script>

<style lang="scss" scoped>
/* 필요에 따라 기존의 스타일을 조정하세요 */
</style>