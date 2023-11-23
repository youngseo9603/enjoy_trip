<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { getPosts } from '@/api/posts';
import MainItem from '@/components/posts/MainItem.vue';
const router = useRouter();
const posts = ref([]);

const fetchPosts = () => {
	posts.value = getPosts();
};
fetchPosts();
const goPage = id => {
	router.push({
		name: 'PostDetail',
		params: { id },
	});
};
</script>

<template>
	<h2>내 여행 목록</h2>
	<br />
	<div class="row g-5" style="width: 1000px">
		<div v-for="post in posts" :key="post.id" class="col-4">
			<MainItem
				:title="post.title"
				:content="post.content"
				:createdAt="post.createdAt"
				@click="goPage(post.id)"
			></MainItem>
		</div>
	</div>
</template>

<style lang="scss">
h2 {
	font-size: 20px;
	font-weight: bold;
}
</style>
