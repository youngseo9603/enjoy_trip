<template>
  <div class="container pt-5 mx-auto max-w-xs" style="width: 400px; height: 1000px">
    <h3 class="logo-font text-center py-8 dark:text-gray-300">| SIGN UP |</h3>
    <form @submit.prevent="registerMem()" class="group">
      <div class="mb-4 relative">
        <input
          type="text"
          name="username"
          id="id_username"
          v-model.lazy="member.loginId"
          class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
          placeholder=" "
          pattern=".{1,}"
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
          type="text"
          name="email"
          id="id_email"
          v-model.lazy="member.email"
          class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
          placeholder=" "
          pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
          required
        />
        <label
          for="id_email"
          class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
          >이메일</label
        >
      </div>
      <div class="mb-4 relative">
        <input
          type="text"
          name="nickname"
          id="id_nickname"
          v-model.lazy="member.nickname"
          class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
          placeholder=" "
          pattern=".{1,}"
          required
        />
        <label
          for="id_nickname"
          class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
          >닉네임</label
        >
      </div>
      <div class="grid grid-cols-3 gap-4">
        <div class="col-span-2">
          <div class="mb-4 relative">
            <input
              type="text"
              name="firstname"
              id="id_firstname"
              v-model.lazy="member.name"
              class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
              pattern=".{1,}"
              placeholder=" "
            />
            <label
              for="id_firstname"
              class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
              >이름</label
            >
          </div>
        </div>
      </div>
      <div class="mb-1 relative">
        <input
          type="password"
          name="password1"
          id="id_password1"
          v-model.lazy="member.password"
          class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
          placeholder=" "
          pattern=".{8,}"
          required
        />
        <label
          for="id_password1"
          class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
          >패스워드</label
        >
      </div>
      <p class="ml-1 mb-2 text-xs text-gray-500">패스워드는 8글자 이상 작성해주세요.</p>
      <div class="mb-4 relative">
        <input
          type="password"
          name="password2"
          id="id_password2"
          class="block px-2.5 pb-2.5 pt-4 w-full text-sm text-gray-900 bg-transparent rounded-lg border-1 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 invalid:[&:not(:placeholder-shown):not(:focus)]:border-red-500 peer"
          placeholder=" "
          required
        />
        <label
          for="id_password2"
          class="absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-4 scale-75 top-2 z-10 origin-[0] bg-white dark:bg-gray-900 px-2 peer-focus:px-2 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-1/2 peer-placeholder-shown:top-1/2 peer-focus:top-2 peer-focus:scale-75 peer-focus:-translate-y-4 left-1"
          >패스워드 확인</label
        >
      </div>

      <div class="mb-4 relative">
        <img id="imagePreview" class="w-full" style="display: none" />
      </div>

      <button
        type="submit"
        class="mt-1 w-full border border-gray-300 py-3 rounded-lg bg-indigo-950 hover:bg-indigo-950 text-white font-bold group-invalid:pointer-events-none group-invalid:opacity-30"
      >
        회원가입
      </button>
    </form>
    <div class="mt-4 flex justify-center">
      <span class="text-gray-500 text-sm mr-1">이미 아이디가 있으신가요? </span>
      <RouterLink to="/login" style="color: black; text-decoration: none; font-weight: bold"
        >로그인</RouterLink
      >
    </div>
  </div>
</template>

<script setup>
import memberAPI from '@/api/member.js'
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const member = ref({})

const goLoginPage = () => router.push({ name: 'login' })

const registerMem = () => {
  console.log(member.value)
  memberAPI.signup(
    member.value,
    ({ data }) => {
      console.log(data.message)
      alert(data.message)

      console.log(data.status)
      if (data.status == 200) goLoginPage()
    },
    () => {
      console.log('회원 가입에 실패했습니다.')
    }
  )
}
</script>

<style lang="scss" scoped></style>
