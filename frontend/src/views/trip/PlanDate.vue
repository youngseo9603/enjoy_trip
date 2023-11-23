<template>
	<div class="flex flex-col back items-center">
		<p class="py-3" style="font-size: 30px">여행 계획하기</p>
		<div style="width: 30%">
			<!-- <form @submit.prevent="gonext()" class="group"> -->
			<form @submit.prevent="gonext()" class="group">
				<div class="flex flex-col justify-center items-center">
					<div style="width: 100%">
						<input
							type="text"
							name="planname"
							id="planname"
							class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-white rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
							pattern=".{1,}"
							placeholder="여행이름"
							v-model.lazy="wholePlan.title"
							required
						/>
						<br />
						<input
							type="text"
							name="accommodation"
							id="accommodation"
							class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-white rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
							style="background-color: white"
							pattern=".{1,}"
							placeholder="숙소주소"
							v-model.lazy="wholePlan.accommodation"
							required
						/>

						<br />

						<p>여행날짜</p>

						<a-range-picker
							v-model:value="datevalue"
							style="width: 100%"
							required
						/>
					</div>
					<br />
					<button
						@click="gonext()"
						type="submit"
						class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
						style="width: 100px"
					>
						다음
					</button>
				</div>
			</form>
		</div>
	</div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import store from '@/stores/index';

import dayjs from 'dayjs';
import { ref } from 'vue';

const route = useRoute();
const router = useRouter();

const dateFormat = 'YYYY/MM/DD';
var wholePlan = ref({});
//stores에 입력받고 그값을 반환
const datevalue = ref([
	dayjs('2023/10/11', dateFormat),
	dayjs('2023/10/12', dateFormat),
]);

const next = () => {
	// goplanlocation();
};

const gonext = () => {
	store.commit('setPlanTitle', wholePlan.value.title);
	store.commit('setAccomodation', wholePlan.value.accommodation);

	store.commit('setStartDate', datevalue.value[0].$d);
	store.commit('setEndDate', datevalue.value[1].$d);

	router.push({ name: 'planLocation' });
};
</script>

<style lang="scss" scoped>
.back {
	width: 100%;
	height: 600px;
}
</style>
