<script setup>
<<<<<<< HEAD
import TheHeader from '@/layouts/TheHeader.vue'
import TheView from '@/layouts/TheView.vue'
import TheFooter from '@/layouts/TheFooter.vue'
import PlanMapPath from './components/PlanMapPath.vue'
</script>

<template>
  <!-- <TheHeader> </TheHeader>
  <TheView> </TheView>
  <TheFooter> </TheFooter> -->
  <PlanMapPath></PlanMapPath>
</template>

<style scoped>
@font-face {
  font-family: 'NanumBarunGothic';
  src: url('assets/fonts/NanumBarunGothic.ttf') format('truetype');
  font-weight: 400;
}
=======
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
>>>>>>> 838ed9415a50b6587801e3a12f517fc046615f7e
header {
	line-height: 1.5;
	max-height: 100vh;
}

.logo {
	display: block;
	margin: 0 auto 2rem;
}

nav {
	width: 100%;
	font-size: 12px;
	text-align: center;
	margin-top: 2rem;
}

nav a.router-link-exact-active {
	color: var(--color-text);
}

nav a.router-link-exact-active:hover {
	background-color: transparent;
}

nav a {
	display: inline-block;
	padding: 0 1rem;
	border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
	border: 0;
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
