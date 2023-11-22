<template>
	<div class="back items-center">
		<router-view v-slot="{ Component }"
			><transition name="slide" mode="out-in"
				><component :is="Component"></component> </transition
		></router-view>
	</div>
</template>

<script setup></script>

<style lang="scss">
.back {
	width: 100%;
	height: 600px;
	opacity: 1;
	// background: #141e46;
	z-index: 0;
}
.slide-enter-active,
.slide-leave-active {
	transition:
		opacity 1s,
		transform 1s;
}

.slide-leave-to {
	opacity: 0;
	transform: translateX(-50%);
}
.slide-enter-from {
	opacity: 0;
	transform: translateX(50%);
}
</style>
