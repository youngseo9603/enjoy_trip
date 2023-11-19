<script setup>
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import TheFooter from '@/layouts/TheFooter.vue';
</script>

<template>
	<TheHeader> </TheHeader>
	<TheView> </TheView>
	<TheFooter> </TheFooter>
</template>

<style scoped>
@font-face {
	font-family: 'NanumBarunGothic';
	src: url('assets/fonts/NanumBarunGothic.ttf') format('truetype');
	font-weight: 400;
}

@media (min-width: 1024px) {
	.logo {
		margin: 0 2rem 0 0;
	}

	header .wrapper {
		display: flex;
		place-items: flex-start;
		flex-wrap: wrap;
	}
}

container {
	max-width: 100%;
	margin: 0 auto;
	padding: 0 1rem;
}
</style>
