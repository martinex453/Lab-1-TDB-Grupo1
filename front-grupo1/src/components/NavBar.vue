<template>
    <nav class="navbar">
      <div class="navbar-container">
        <div class="navbar-links">
          <router-link to="/" class="navbar-link">Salir</router-link>
          <router-link to="/products" class="navbar-link">Productos</router-link>
          <router-link to="/myOrders" class="navbar-link">Mis Ordenes</router-link>
          <router-link to="/order" class="navbar-link">Orden</router-link>
        </div>
      </div>
    </nav>
  </template>
  
  
<style scoped>
.navbar {
  background-color: #ffffff;
  color: #333;
  display: flex;
  justify-content: center;
  height: 40px;
}
  
.navbar-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}
  
.navbar-links {
  display: flex;
  gap: 20px;
}
  
.navbar-link {
  color: #333;
  text-decoration: none;
  font-size: 1rem;
}
</style>
  