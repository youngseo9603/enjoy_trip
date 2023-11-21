<template>
	<div>
		<h2>게시글 등록</h2>
		<hr />
		<form @submit.prevent="registerBoard()">
			<div class="mb-3">
				<label for="title" class="form-label">제목</label>
				<input
					type="text"
					class="form-control"
					id="title"
					v-model.lazy="board.boardTitle"
				/>
			</div>
			<div class="mb-3">
				<label for="content" class="form-label">내용</label>
				<input
					class="form-control"
					id="content"
					rows="3"
					v-model.lazy="board.boardContent"
				/>
			</div>
			<div>
				<button class="btn btn-outline-dark me-2" @click="goListPage">
					목록
				</button>
				<button type="submit" class="btn btn-outline-dark">저장</button>
			</div>
		</form>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import boardAPI from '@/api/board.js';
import store from '@/stores/index';

const router = useRouter();
var board = ref({});

const goListPage = () => router.push({ name: 'PostList' });

const registerBoard = () => {
	console.log(board.value);
	boardAPI.registerBoard(
		board.value,
		({ data }) => {
			console.log(data.message);
			goListPage();
		},
		() => {
			console.log('등록 실패');
		},
	);
};

const decideIndex = () => {
	board.value.memberIndex = store.state.account.memberIndex;
};
decideIndex();
</script>

<style lang="scss" scoped></style>
