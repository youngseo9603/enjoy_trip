<template>
	<div>내 위시리스트</div>
	<div>{{ wishlist }}</div>
</template>

<script>
import wishAPI from '@/api/wish';
import store from '@/stores/index';
import { ref } from 'vue';

const wishs = ref([]);

export default {
	data() {
		return {
			wishlist: wishs.value,
		};
	},
	mounted() {
		wishAPI.getWishList(
			store.state.account.memberIndex,
			({ data }) => {
				wishs.value = data.data;
				console.log(data.message);
			},
			() => {
				console.log("위시리스트 불러오기 실패");
			}
		);
	}
}

</script>

<style lang="scss" scoped></style>
