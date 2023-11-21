<template>
	<div class="grid grid-cols-4 gap-4" style="width: 100%; height: 1500px">
		<div class="col-span-1" style="height: 100%">
			<br />
			<br />
			<br />
			<br />
			<div class="text-center">좌소연</div>
			<div class="text-center">jwajwa@jwawjwa.com</div>
			<br />
			<br />
			<ul>
				<router-link to="/mypage"
					><li>
						<img src="@/assets/icon/info.svg" alt="SVG Icon" />내 정보
					</li></router-link
				>
				<router-link to="/mypage/mytrip"
					><li>
						<img src="@/assets/icon/earth.svg" alt="SVG Icon" />내 여행계획
					</li></router-link
				>
				<router-link to="/mypage/mypost"
					><li>
						<img src="@/assets/icon/pencil.svg" alt="SVG Icon" />내가 쓴 글
					</li></router-link
				>
				<router-link to="/mypage/mycomment"
					><li>
						<img src="@/assets/icon/comment.svg" alt="SVG Icon" />내가 쓴 댓글
					</li></router-link
				>
			</ul>
		</div>
		<div class="col-span-3"><router-view></router-view></div>
	</div>
</template>

<script setup></script>

<style lang="scss" scoped>
ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

li {
	margin: 20px;
	padding: 10px;
	border-radius: 5px;
	justify-content: center;
	align-items: center;
	display: flex;
}

a {
	text-decoration: none;
}

img {
	width: 25px;
	margin-right: 5px;
}
</style>
