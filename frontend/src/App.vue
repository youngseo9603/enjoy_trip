<script setup>
import { ref, watch } from 'vue';
import TheHeader from '@/layouts/TheHeader.vue';
import TheView from '@/layouts/TheView.vue';
import TheFooter from '@/layouts/TheFooter.vue';

const transitionName = ref('');

watch('$route', (to, from) => {
	if (to.meta.page == null || from.meta.page == null) {
		transitionName.value = 'fade';
	} else {
		transitionName.value = to.meta.page > from.meta.page ? 'next' : 'prev';
	}
	console.log(transitionName.value);
});
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
header {
	position: sticky;
	top: 0;
	z-index: 100;
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
