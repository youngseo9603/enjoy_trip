<template>
	<h2>내 위시리스트</h2>
	<br />
	<div id="board-list">
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">장소</th>
						<th scope="col">주소</th>
						<th scope="col">카테고리</th>
					</tr>
				</thead>
				<tbody>
					<template v-for="wish in wishs" :key="wish.id">
						<tr>
							<td>{{ wish.wishIndex }}</td>
							<td>
								{{ wish.placeName }}
							</td>
							<td>{{ wish.address.addr1 }}</td>
							<td>{{ wish.category }}</td>
						</tr>
					</template>
				</tbody>
			</table>
		</div>
	</div>
</template>

<script setup>
import wishAPI from '@/api/wish';
import store from '@/stores/index';
import { ref } from 'vue';

const wishs = ref([]);
const startFunc = () => {
	wishAPI.getWishList(
		store.state.account.memberIndex,
		({ data }) => {
			wishs.value = data.data;
			console.log(wishs.value);
			console.log(data.message);
		},
		() => {
			console.log('위시리스트 불러오기 실패');
		},
	);
};

startFunc();
</script>

<style lang="scss"></style>
