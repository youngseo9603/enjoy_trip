<!-- <template>
	<div class="flex">
		<div
			style="background-color: blue; width: 200px"
			class="drop-zone"
			@drop="onDrop($event, 1)"
			@dragover.prevent
			@dragenter.prevent
		>
			<div
				class="drag-el"
				v-for="item in listOne"
				:key="item.index"
				draggable="true"
				@dragstart="startDrag($event, item)"
			>
				{{ item.place_name }}
			</div>
		</div>
		<div
			style="background-color: red; width: 200px"
			class="drop-zone"
			@drop="onDrop($event, 0)"
			@dragover.prevent
			@dragenter.prevent
		>
			<div
				class="drag-el"
				v-for="item in listTwo"
				:key="item.index"
				draggable="true"
				@dragstart="startDrag($event, item)"
			>
				{{ item.place_name }}
			</div>
		</div>
	</div>
	<button @click="confirmOrder">확정</button>
</template>

<script>
import { getWishlist } from '../../api/wishlist';
import { ref } from 'vue';
const visit = ref([]);
const wishs = ref([]);

const fetchWishs = () => {
	wishs.value = getWishlist();
};
fetchWishs();

export default {
	computed: {
		listOne() {
			return wishs.value.filter(item => item.list !== 0);
		},
		listTwo() {
			return wishs.value.filter(item => item.list === 0);
		},
	},
	mounted() {
		// Log the values of listOne and listTwo
		console.log('listOne:', this.listOne);
		console.log('listTwo:', this.listTwo);
	},
	methods: {
		startDrag(evt, item) {
			evt.dataTransfer.dropEffect = 'move';
			evt.dataTransfer.effectAllowed = 'move';
			evt.dataTransfer.setData('itemID', item.index);
		},
		onDrop(evt, list) {
			console.log('Dropped to list:', list);

			const itemID = evt.dataTransfer.getData('itemID');
			const item = wishs.value.find(item => item.index == itemID);

			// 아이템과 list 속성이 존재하는지 확인 후 업데이트
			if (item && 'list' in item) {
				item.list = list;
				console.log('Item updated:', item);
			}
		},
		confirmOrder() {
			// 아이템의 순서를 확인하여 순서대로 정렬
			wishs.value.sort((a, b) => a.list - b.list);
			console.log('Confirmed order:', wishs.value);
		},
	},
};
</script>

<style scoped>
.drop-zone {
	background-color: #eee;
	margin-bottom: 10px;
	padding: 10px;
}

.drag-el {
	background-color: #fff;
	margin-bottom: 10px;
	padding: 5px;
}
</style> -->
<template>
	<div class="row" style="width: 100%">
		<div class="col-3">
			<h3>몇월며칠날 갈 여행지</h3>
			<draggable
				class="list-group"
				:list="list1"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">{{ element.place_name }}</div>
				</template>
			</draggable>
		</div>
		<div class="col-3">
			<h3>몇월며칠날 갈 여행지</h3>
			<draggable
				class="list-group"
				:list="list2"
				group="people"
				@change="log"
				itemKey="name"
			>
				<template #item="{ element }">
					<div class="list-group-item">{{ element.place_name }}</div>
				</template>
			</draggable>
		</div>

		<div class="col-3">
			<h3>즐겨찾기한 여행지</h3>
			<draggable
				class="list-group"
				:list="list3"
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

		<!-- <rawDisplayer class="col-3" :value="list1" title="List 1" />

		<rawDisplayer class="col-3" :value="list2" title="List 2" /> -->
		<div class="col-3">
			<RouterView></RouterView>
		</div>
	</div>

	<router-link to="/plan">
		<button
			type="submit"
			class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
		>
			다음
		</button>
	</router-link>
</template>
<script>
import { getWishlist } from '../../api/wishlist';
import { toRaw, ref } from 'vue';
const wishs = ref([]);

const fetchWishs = () => {
	wishs.value = getWishlist();
};
fetchWishs();

import draggable from 'vuedraggable';
export default {
	name: 'two-lists',
	display: 'Two Lists',
	order: 1,
	components: {
		draggable,
	},
	data() {
		return {
			list1: [],
			list2: [],
			list3: wishs.value,
		};
	},
	methods: {
		// add: function () {
		// 	this.list.push({ name: 'Juan' });
		// },
		// replace: function () {
		// 	this.list = [{ name: 'Edgard' }];
		// },
		// clone: function (el) {
		// 	return {
		// 		name: el.name + ' cloned',
		// 	};
		// },
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
</style>
