<template>
	<div class="grid grid-cols-4 gap-4" style="width: 100%; height: 500px">
		<div class="col-span-1">
			<ul>
				<router-link to="/mypage"><li>내 정보</li></router-link>
				<router-link to="/mypage/mytrip"><li>내 여행계획</li></router-link>
				<router-link to="/mypage/mypost"><li>내가 쓴 글</li></router-link>
				<router-link to="/mypage/mycomment"><li>내가 쓴 댓글</li></router-link>
			</ul>
		</div>
		<div class="col-span-3"><router-view></router-view></div>
	</div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
