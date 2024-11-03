<script setup>
import RegisterLogin from './components/RegisterLogin.vue';
</script>

<template>
  <main>
    <div class="wrapper">
      <RegisterLogin />
    </div>
  </main>
</template>

<style scoped>
@media (min-width: 1024px) {

.wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
    width: 100vw;
  }
}
</style>
