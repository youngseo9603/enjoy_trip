<script setup>
import TheHeader from '@/layouts/TheHeader.vue'
import TheView from '@/layouts/TheView.vue'
import TheFooter from '@/layouts/TheFooter.vue'
</script>

<template>
  <TheHeader> </TheHeader>
  <TheView> </TheView>
  <TheFooter> </TheFooter>
</template>

<style scoped>
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
