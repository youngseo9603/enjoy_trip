<template>
  <div class="page-title">
    <h3>자유게시판</h3>
  </div>

  <div id="board-search">
    <div class="container">
      <div class="search-window">
        <form action="">
          <div class="search-wrap">
            <form @submit.prevent="searchBoards">
              <input
                id="search"
                type="search"
                name=""
                placeholder="검색어를 입력해주세요."
                v-model="searchText"
              />
              <button class="btn btn-dark">검색</button>
            </form>
          </div>
        </form>
      </div>
    </div>
  </div>

  <!-- board list area -->
  <div id="board-list">
    <div class="container">
      <table class="board-table">
        <thead>
          <tr>
            <th scope="col" class="th-num">번호</th>
            <th scope="col" class="th-title">제목</th>
            <th scope="col" class="th-date">등록일</th>
          </tr>
        </thead>
        <tbody>
          <template v-for="board in paginatedPosts" :key="board.id">
            <tr @click="goPage(board.boardIndex)">
              <td>{{ board.boardIndex }}</td>
              <td>
                {{ board.boardTitle }}
              </td>
              <td>{{ board.createTime }}</td>
            </tr>
          </template>
        </tbody>
      </table>
    </div>
  </div>
  <div class="grid place-items-center my-5">
    <div class="pagination">
      <button :disabled="currentPage === 1" @click="changePage(currentPage - 1)">이전</button>
      <span class="mx-2">{{ currentPage }} / {{ totalPages }}</span>
      <button :disabled="currentPage === totalPages" @click="changePage(currentPage + 1)">
        다음
      </button>
    </div>
  </div>
  <button @click="goCreatePage">등록하기</button>
</template>

<script setup>
import { getPosts } from '@/api/posts'
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import boardAPI from '@/api/board.js'

const router = useRouter()
const posts = ref([])
const currentPage = ref(1)
const postsPerPage = 10
const boards = ref([])
var searchText = ref('')

const paginatedPosts = computed(() => {
  const startIndex = (currentPage.value - 1) * postsPerPage
  const endIndex = startIndex + postsPerPage
  return boards.value.slice(startIndex, endIndex)
})

const totalPosts = computed(() => boards.value.length)
const totalPages = computed(() => Math.ceil(totalPosts.value / postsPerPage))

const fetchPosts = async () => {
  posts.value = await getPosts()
}

const goPage = (id) => {
  router.push({
    name: 'PostDetail',
    params: { id }
  })
}

const changePage = (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page
    updateRoute()
  }
}

const goCreatePage = () => {
  router.push({
    name: 'postCreate'
  })
}

const updateRoute = () => {
  router.replace({ query: { page: currentPage.value } })
}

const getBoards = () => {
  boardAPI.getList(
    ({ data }) => {
      //data: json 부서 목록
      boards.value = data.data
      console.log(boards.value)
    },
    () => {
      console.log('부서 목록 조회에 실패하였습니다.')
    }
  )
}

const searchBoards = () => {
  boardAPI.searchBoards(
    searchText.value,
    ({ data }) => {
      boards.value = data.data
      console.log(data.message)
    },
    () => {
      console.log('검색에 실패했습니다.')
    }
  )
}

fetchPosts()
getBoards()
</script>

<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
}

.page-title {
  margin-top: 60px;
  margin-bottom: 30px;
}
.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 400;
  text-align: center;
}

#board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}
#board-search .search-window .search-wrap {
  position: relative;
  /*   padding-right: 124px; */
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}
#board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 14px;
  padding: 7px 14px;
  border: 1px solid #ccc;
}
#board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}
#board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

.board-table {
  font-size: 13px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}
.board-table a:hover {
  text-decoration: underline;
}
.board-table th {
  text-align: center;
}

.board-table .th-num {
  width: 100px;
  text-align: center;
}

.board-table .th-date {
  width: 200px;
}

.board-table th,
.board-table td {
  padding: 14px 0;
}

.board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.board-table tbody th p {
  display: none;
}

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}
.board-table tbody tr:hover {
  background-color: #f2f2f2;
  cursor: pointer;
}
/* reset */

* {
  list-style: none;
  text-decoration: none;
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.clearfix:after {
  content: '';
  display: block;
  clear: both;
}
.container {
  width: 1100px;
  margin: 0 auto;
}
.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}
</style>
