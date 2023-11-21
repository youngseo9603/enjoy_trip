<template>
	<div class="container pt-5 mx-auto max-w-xs" style="width: 400px">
		<h1 class="logo-font text-center py-8 dark:text-gray-300">| LOGIN |</h1>
		<form @submit.prevent="login()" class="group">
			<div class="mb-4 relative">
				<input
					type="text"
					name="username"
					id="id_username"
					class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
					pattern=".{1,}"
					placeholder=" "
					v-model.lazy="member.loginId"
					required
				/>
				<label
					for="id_username"
					class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
					>아이디</label
				>
			</div>
			<div class="mb-4 relative">
				<input
					type="password"
					name="password"
					id="id_password"
					class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
					pattern=".{8,}"
					placeholder=" "
					v-model.lazy="member.password"
					required
				/>
				<label
					for="id_password"
					class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
					>패스워드</label
				>
			</div>
			<button
				type="submit"
				class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
			>
				로그인
			</button>
		</form>

		<div class="mt-4 flex justify-center items-center">
			<span class="text-gray-500 text-sm mr-2">아이디가 없으신가요? </span>
			<RouterLink
				to="/signup"
				style="color: black; text-decoration: none; font-weight: bold"
				>회원가입</RouterLink
			>
		</div>
	</div>
</template>

<script setup>
import { reactive, computed, ref } from 'vue';
import memberAPI from '@/api/member.js';
import { useRoute, useRouter } from 'vue-router';
import store from '@/stores/index';
const route = useRoute();
const router = useRouter();

const goMainPage = () => router.push({ name: 'home' });

const member = ref({});
const formState = reactive({
	username: '',
	password: '',
	remember: true,
});
const onFinish = values => {
	console.log('Success:', values);
};
const onFinishFailed = errorInfo => {
	console.log('Failed:', errorInfo);
};

const login = () => {
	console.log(member.value);
	memberAPI.login(
		member.value,
		({ data }) => {
			console.log(data.message);
			alert(data.message);
			if (data.status == 200) {
				sessionStorage.setItem('memberIndex', data.data.memberIndex);
				store.commit('setAccount', data.data.memberIndex);
				goMainPage();
			}
		},
		() => {
			console.log('로그인 실패');
		},
	);
};
</script>

<style lang="scss" scoped>
#components-form-demo-normal-login .login-form {
	max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
	float: right;
}
#components-form-demo-normal-login .login-form-button {
	width: 100%;
}
</style>
