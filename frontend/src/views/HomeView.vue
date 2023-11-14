<script setup>
import { ref } from 'vue';
const value = ref('');
const onSearch = searchValue => {
	console.log('use value', searchValue);
	console.log('or use this.value', value.value);
};
</script>

<template>
	<main>
		<div class="d-flex flex-column align-items-center position-relative">
			<img src="@/assets/background.png" alt="" class="" style="width: 100%" />
			<div
				class="d-flex flex-column align-items-center position-absolute"
				style="top: 30%"
			>
				<h2 class="text-white">나만의 여행 플래너 어스토리</h2>
				<h4 class="text-white">쉽고 빠르게 계획하세요.</h4>
				<a-space direction="vertical">
					<a-input-search
						v-model:value="value"
						placeholder="여행지를 검색해주세요"
						size="large"
						@search="onSearch"
						style="width: 500px"
					>
						<template #enterButton>
							<a-button>검색</a-button>
						</template>
					</a-input-search>
				</a-space>
			</div>
		</div>
		<div
			class="d-flex flex-column align-items-center my-5"
			style="height: 500px"
		>
			<h3>추천 여행지</h3>
			<h5>여행지에 맞는 여행 계획을 추천해드려요</h5>
		</div>
	</main>
</template>
