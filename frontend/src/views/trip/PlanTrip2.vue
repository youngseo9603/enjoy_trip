<template>
	<div class="grid grid-cols-4 gap-4 back items-center px-5">
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
			<br />
			<div id="favorite" style="overflow: scroll; height: 300px">
				<draggable
					class="list-group"
					:list="wishwish"
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
		</div>
		<div class="col-span-4 flex justify-center">
			<router-link to="/mypage/mytrip">
				<button
					type="submit"
					class="mt-1 border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
					style="width: 100px"
				>
					확정하기
				</button>
			</router-link>
		</div>
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
		const day = 3;

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
			list1: [
				{ placeName: '꽃담수제버거', id: 1 },
				{ placeName: '성신어촌계우뭇개식당', id: 2 },
				{ placeName: '성산일출봉해송', id: 3 },
				{ placeName: '선미식당', id: 4 },
			],
			list2: [
				{ placeName: '노라바', id: 5 },
				{ placeName: '애월그때그집', id: 6 },
				{ placeName: '이춘옥의원조고등어쌈밥', id: 7 },
				{ placeName: '고이정 본점', id: 8 },
			],
			list3: [
				{ placeName: '삼양해수욕장', id: 9 },
				{ placeName: '그제바람', id: 10 },
			],
			wishwish: [],
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
h3 {
	font-weight: 600;
	margin-bottom: 5px;
	margin-left: 5px;
}

#favorite::-webkit-scrollbar {
	display: none;
}
</style>
