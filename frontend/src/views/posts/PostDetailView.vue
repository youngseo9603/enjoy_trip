<template>
	<div>
		<h2>제목: {{ board.boardTitle }}</h2>
		<br />
		<p>내용: {{ board.boardContent }}</p>
		<p>작성자 : {{ memberNickName }}</p>
		<p class="text-muted">{{ board.createTime }}</p>
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
			<div class="col-auto" v-if="isEqual">
				<button class="btn btn-outline-dark" @click="goEditpage">수정</button>
			</div>
			<div class="col-auto" v-if="isEqual">
				<button class="btn btn-outline-dark" @click="removeBoard">삭제</button>
			</div>
		</div>
	</div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import boardAPI from '@/api/board.js';
import memberAPI from '@/api/member.js';
import { ref } from 'vue';
import store from '@/stores/index';


const route = useRoute();
const router = useRouter();
const id = route.params.id;
const goListpage = () => router.push({ name: 'PostList' });
const goEditpage = () => router.push({ name: 'PostEdit', params: { id } });
const board = ref({});
const memberNickName = ref('');
const isEqual = ref(false);

const getDetailBoard = () => {
	boardAPI.getDetailBoard(
		id,
		({ data }) => {
			board.value = data.data;
			board.value.memberIndex = data.data.memberIndex;
			console.log(data.message);
			console.log(data.data);

			getNickName();
			decideEqual();
		},
		() => {
			console.log('게시물 데이터 조회에 실패했습니다.');
		},
	);
};

const getNickName = () =>{
	memberAPI.getNickName(
		board.value.memberIndex,
		({ data }) => {
			memberNickName.value = data.data;
		},
		() => {
			console.log("사용자 정보 불러오기 실패");
		},
	);
};

const removeBoard = () => {
	boardAPI.removeBoard(
		id,
		({ data }) => {
			console.log(data);
			goListpage();
		},
		() => {
			console.log('삭제 실패');
		},
	);
};

const decideEqual = () => {
	var tmp = store.state.account.memberIndex;

	if (tmp != 0) {
		if (tmp == board.value.memberIndex) {
			isEqual.value = true;
		}
	}
}

getDetailBoard();
</script>

<style lang="scss" scoped></style>
