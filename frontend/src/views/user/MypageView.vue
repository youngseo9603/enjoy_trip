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

<style lang="scss" scoped>
ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
	background-color: #f1f1f1; /* Optional background color */
}

li {
	margin: 5px;
	padding: 10px;
	border-radius: 5px;
	background-color: #ddd; /* Optional background color */
}

a {
	text-decoration: none;
	color: #333; /* Optional text color */
}

li:hover {
	background-color: #bbb; /* Optional hover background color */
}
</style>
