<script setup>
import RegisterLogin from './components/RegisterLogin.vue';
import PucharseOrder from './components/PucharseOrder.vue';
import Products from './components/Products.vue';
import totalOrderDetail from './components/totalOrderDetail.vue';
</script>

<template>
  <main>
    <div class="wrapper">
      <totalOrderDetail />
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
