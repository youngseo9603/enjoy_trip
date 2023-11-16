<template>
	<header>
		<nav
			class="navbar navbar-expand-lg bg-body-tertiary header"
			style="width: 100%"
		>
			<div class="container-fluid">
				<RouterLink
					class="navbar-brand"
					style="font-family: RixInooAriDuriR; font-size: 25px"
					to="/"
					>Foodtrip</RouterLink
				>
				<button @click="isOpen = !isOpen" class="lg:hidden focus:outline-none">
					<svg
						class="w-6 h-6 text-white"
						fill="none"
						stroke="currentColor"
						viewBox="0 0 24 24"
						xmlns="http://www.w3.org/2000/svg"
					>
						<path
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="M4 6h16M4 12h16m-7 6h7"
						></path>
					</svg>
				</button>
				<!-- <div
					class="collapse navbar-collapse"
					id="navbarSupportedContent"
					style="font-family: 'TheJamsil5Bold'"
				> -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 mx-3">
					<li class="nav-item">
						<RouterLink class="nav-link" to="/search">여행지</RouterLink>
					</li>
					<li class="nav-item">
						<RouterLink class="nav-link" to="/plan">여행계획하기</RouterLink>
					</li>
					<li class="nav-item">
						<RouterLink class="nav-link" to="/post?page=1">게시판</RouterLink>
					</li>
				</ul>
				<ul class="navbar-nav mb-2 mb-lg-0 mx-3">
					<li class="nav-item">
						<RouterLink class="nav-link" to="/login">로그인</RouterLink>
					</li>
					<li class="nav-item">
						<RouterLink class="nav-link" to="/signup">회원가입</RouterLink>
					</li>
				</ul>
			</div>
		</nav>
	</header>
</template>

<script setup></script>

<style lang="scss" scoped>
header {
	position: sticky;
	top: 0;
	z-index: 100;
}
</style>
