<template>
	<div>
		<h2>제목</h2>
		<br />
		<p>내용</p>
		<p class="text-muted">2020-01-01</p>
		<hr />
		<div class="row">
			<div class="col-auto">
				<button class="btn btn-outline-dark">이전글</button>
			</div>
			<div class="col-auto">
				<button class="btn btn-outline-dark">다음글</button>
			</div>
			<div class="col-auto me-auto"></div>
			<div class="col-auto">
				<RouterLink class="nav-link" to="/post?page=1">게시판</RouterLink>
				<!-- <button class="btn btn-outline-dark" @click="goListpage">목록</button> -->
			</div>
			<div class="col-auto">
				<button class="btn btn-outline-dark" @click="goEditpage">수정</button>
			</div>
			<div class="col-auto">
				<button class="btn btn-outline-dark">삭제</button>
			</div>
		</div>
	</div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const id = route.params.id;
const goListpage = () => router.push({ name: 'PostList' });
const goEditpage = () => router.push({ name: 'PostEdit', params: { id } });
</script>

<style lang="scss" scoped></style>
