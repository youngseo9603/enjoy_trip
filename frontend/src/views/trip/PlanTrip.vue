<template>
	<div class="grid grid-cols-4 gap-4 back items-center">
		<div
			class="col-span-3"
			style="white-space: nowrap; overflow-x: auto; overflow-y: hidden"
		>
			<template v-for="(day, index) in days" :key="day.placeName">
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
								<div class="list-group-item">{{ element.placeName }}</div>
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
				:list="wishlist[0]"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">
						{{ element.placeName }}
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

import { getWishlist, date } from '../../api/wishlist';
//더미데이터
import { toRaw, ref } from 'vue';
import store from '@/stores/index';
import draggable from 'vuedraggable';
// const fetchWishs = () => {
// 	wishs.value = getWishlist();
// };
// fetchWishs();
//더미데이터

export default {
	name: 'travel-lists',
	display: 'travel Lists',
	order: 1,
	components: {
		draggable,
	},
	data() {
		const day =
			(store.state.wholePlan.endDate.getTime() -
				store.state.wholePlan.startDate.getTime()) /
				(1000 * 60 * 60 * 24) +
			1;

		const generateLists = () => {
			console.log('aaa');
			const lists = {};
			for (let i = 1; i <= day; i++) {
				lists[`list${i}`] = [];
			}
			return lists;
		};
		return {
			...generateLists(),
			wishlist: [],
			days: Array.from({ length: day }, (_, index) => ({
				// 생성자로 초기값 설정
				placeName: `Place ${index + 1}`,
			})),
			day,
		};
	},
	created() {
		wishAPI.getWishList(
			store.state.account.memberIndex,
			({ data }) => {
				this.wishlist.push(data.data);
				console.log(data.message);
				store.commit('setWishList', this.wishlist[0]);
			},
			() => {
				console.log('위시리스트 불러오기 실패');
			},
		);
	},
	methods: {
		getList(index) {
			// 각 일자에 해당하는 리스트 반환
			return this[`list${index + 1}`];
		},
		log: function (evt) {
			var list = [];
			for (var i = 0; i < this.day; i++) {
				list[i] = this.getList(i);
			}
			store.commit('setDaysList', list);
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
