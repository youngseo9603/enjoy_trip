<template>
	<div class="card">
		<div class="card-body flex justify-between">
			<h5 class="card-title">{{ title }}</h5>
			<p class="text-muted">{{ createdAt }}</p>
		</div>
	</div>
</template>

<script setup>
defineProps({
	title: {
		type: String,
		required: true,
	},
	content: {
		type: String,
	},
	createdAt: {
		type: [String, Date, Number],
	},
});
</script>

<style lang="scss" scoped></style>
