<template>
	<h2>내 글 목록</h2>
	<br />
	<div id="board-list">
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th scope="col" class="th-num">번호</th>
						<th scope="col" class="th-title">제목</th>
						<th scope="col" class="th-date">등록일</th>
					</tr>
				</thead>
				<tbody>
					<template v-for="board in boards" :key="board.id">
						<tr @click="goPage(board.boardIndex)">
							<td>{{ board.boardIndex }}</td>
							<td>
								{{ board.boardTitle }}
							</td>
							<td>{{ board.createTime }}</td>
						</tr>
					</template>
				</tbody>
			</table>
		</div>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import boardAPI from '@/api/board.js';
import store from '@/stores/index';

const router = useRouter();
const boards = ref([]);

const goPage = id => {
	router.push({
		name: 'PostDetail',
		params: { id },
	});
};

const startFunc = () => {
	boardAPI.searchBoardsByMemberIndex(
		store.state.account.memberIndex,
		({ data }) => {
			boards.value = data.data;
			console.log(boards.value);
		},
		() => {
			console.log('게시판 찾기 실패');
		},
	);
};

startFunc();
</script>

<style lang="scss" scoped>
h2 {
	font-size: 20px;
}
</style>
