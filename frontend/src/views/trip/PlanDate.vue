<template>
	<div class="flex back">
		<div>
			<p>여행 계획하기</p>
			<form action="">
				<input
					type="text"
					name="planname"
					id="planname"
					class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
					pattern=".{1,}"
					placeholder="여행이름"
					required
				/>
				<input
					type="text"
					name="accommodation"
					id="accommodation"
					class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
					pattern=".{1,}"
					placeholder="숙소주소"
					required
				/>

				<p>여행날짜</p>

				<a-range-picker v-model:value="value1" required />
				<router-link to="/planlocation">
					<button
						type="submit"
						class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
					>
						다음
					</button>
				</router-link>
			</form>
		</div>
	</div>
</template>

<script>
import { ref } from 'vue';
const value1 = ref();
</script>

<style lang="scss" scoped>
// .back {
// 	opacity: 0.8;
// 	background: #141e46;
// }
</style>
