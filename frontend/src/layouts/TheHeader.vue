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
				<button
					class="navbar-toggler"
					type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div
					class="collapse navbar-collapse"
					id="navbarSupportedContent"
					style="font-family: 'TheJamsil5Bold'"
				>
					<ul class="navbar-nav me-auto mb-2 mb-lg-0 mx-3">
						<li class="nav-item">
							<RouterLink class="nav-link" to="/about">여행지</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink class="nav-link" to="/about">여행계획하기</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink class="nav-link" to="/post">게시판</RouterLink>
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
