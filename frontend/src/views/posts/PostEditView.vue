<template>
  <div style="width: 70%">
    <h2>게시글 수정</h2>
    <hr />
    <form @submit.prevent>
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" class="form-control" id="title" v-model="board.boardTitle" />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <textarea
          class="form-control"
          id="content"
          rows="3"
          v-model="board.boardContent"
        ></textarea>
      </div>
      <div>
        <button class="btn btn-outline-dark me-2" @click="goDetailPage">취소</button>
        <button class="btn btn-outline-dark" @click="updateBoard()">수정</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref } from 'vue'
import boardAPI from '@/api/board.js'

const route = useRoute()
const router = useRouter()
const id = route.params.id
const board = ref({})

const goDetailPage = () => router.push({ name: 'PostDetail', params: { id } })

const getDetailBoard = () => {
  boardAPI.getDetailBoard(
    id,
    ({ data }) => {
      board.value = data.data

      console.log(data.message)
      console.log(data.data)
    },
    () => {
      console.log('게시물 데이터 조회에 실패했습니다.')
    }
  )
}

const updateBoard = () => {
  boardAPI.updateBoard(
    board.value,
    ({ data }) => {
      console.log(board.value)
      console.log(data.message)

      goDetailPage()
    },
    () => {
      console.log(board)
      console.log('게시물 수정에 실패했습니다.')
    }
  )
}

getDetailBoard()
</script>

<style lang="scss" scoped></style>
