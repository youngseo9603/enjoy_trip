<template>
	<div class="grid grid-cols-4 gap-4 back items-center">
		<div
			class="col-span-3"
			style="white-space: nowrap; overflow-x: auto; overflow-y: hidden"
		>
			<template v-for="(day, index) in days" :key="day.place_name">
				<div
					style="
						width: 200px;
						height: 200px;
						display: inline-block;
						margin-right: 10px;
					"
				>
					<router-link :to="{ path: '/plan/path/' + (index + 1) }">
						<h3>{{ index + 1 }}일째 여행지</h3>
						<draggable
							class="list-group"
							:list="getList(index)"
							group="people"
							@change="log"
							itemKey="name"
						>
							<template #item="{ element }">
								<div class="list-group-item">{{ element.place_name }}</div>
							</template>
						</draggable>
					</router-link>
				</div>
			</template>
		</div>
		<div class="col-span-1">
			<!-- <h3>즐겨찾기한 여행지</h3>
			<draggable
				class="list-group"
				:list="wishlist"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">
						{{ element.placeName }}
					</div>
				</template>
			</draggable> -->

			<h3>즐겨찾기한 여행지</h3>
			<draggable
				class="list-group"
				:list="wishlist"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">
						{{ element.place_name }}
					</div>
				</template>
			</draggable>
		</div>

		<router-link to="/plan">
			<button
				type="submit"
				class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
			>
				다음
			</button>
		</router-link>
	</div>
</template>

<script>
import wishAPI from '@/api/wish';
import { getWishlist } from '../../api/wishlist';
//더미데이터
import { toRaw, ref } from 'vue';
import store from '@/stores/index';
const wishs = ref([]);
// const fetchWishs = () => {
// 	wishAPI.getWishList(
// 		store.state.account.memberIndex,
// 		({ data }) => {
// 			wishs.value = data.data;
// 			console.log(data.message);
// 		},
// 		() => {
// 			console.log('위시리스트 불러오기 실패');
// 		},
// 	);
// };
// fetchWishs();
const fetchWishs = () => {
	wishs.value = getWishlist();
};
fetchWishs();
//더미데이터

import draggable from 'vuedraggable';
export default {
	name: 'travel-lists',
	display: 'travel Lists',
	order: 1,
	components: {
		draggable,
	},
	data() {
		const days = 6;
		const generateLists = () => {
			const lists = {};
			for (let i = 1; i <= days; i++) {
				lists[`list${i}`] = [];
			}
			return lists;
		};
		return {
			...generateLists(),
			wishlist: wishs.value,
			days: Array.from({ length: days }, (_, index) => ({
				// 생성자로 초기값 설정
				place_name: `Place ${index + 1}`,
			})),
		};
	},
	methods: {
		getList(index) {
			// 각 일자에 해당하는 리스트 반환
			return this[`list${index + 1}`];
		},
		log: function (evt) {
			window.console.log(evt);
		},
	},
};
</script>

<style scoped>
.list-group-item {
	cursor: pointer;
}
.back {
	width: 100%;
	height: 600px;
}
</style>
