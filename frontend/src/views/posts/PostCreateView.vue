<template>
	<div style="width: 70%">
		<h2>게시글 등록</h2>
		<br />
		<hr />
		<form @submit.prevent="registerBoard()">
			<div class="my-3">
				<label for="title" class="ml-1 form-label">제목</label>
				<input
					type="text"
					class="form-control"
					id="title"
					v-model.lazy="board.boardTitle"
				/>
			</div>
			<div class="mb-3">
				<label for="content" class="ml-1 form-label">내용</label>
				<input
					class="form-control"
					id="content"
					rows="3"
					style="height: 300px"
					v-model.lazy="board.boardContent"
				/>
			</div>
			<div class="flex justify-end">
				<button
					class="btn btn-outline-dark me-2 border border-gray-300"
					@click="goListPage"
				>
					목록
				</button>
			</div>
			<div class="flex justify-center">
				<button
					type="submit"
					class="btn btn-outline-dark"
					style="font-size: 15px"
				>
					등록하기
				</button>
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
