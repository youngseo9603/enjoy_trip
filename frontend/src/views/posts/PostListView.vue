<template>
	<div>
		<h2>게시글 목록</h2>
		<hr />
		<div class="row g-3">
			<div v-for="post in posts" :key="post.id" class="col-3">
				<PostItem
					:title="post.title"
					:content="post.content"
					:createdAt="post.createdAt"
					@click="goPage(post.id)"
				></PostItem>
			</div>
		</div>
	</div>
</template>

<script setup>
import PostItem from '@/components/posts/PostItem.vue';
import { getPosts } from '@/api/posts';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const posts = ref([]);

const fetchPosts = () => {
	posts.value = getPosts();
};
fetchPosts();
const goPage = id => {
	// router.push(`/post/${id}`);
	router.push({
		name: 'PostDetail',
		params: { id },
		// query: {
		// 	searchText: 'hello',
		// },
		// hash: '#world',
	});
};
</script>

<style lang="scss" scoped></style>
