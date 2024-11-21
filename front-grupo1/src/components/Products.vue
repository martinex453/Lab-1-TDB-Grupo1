<template>
    <div class="products-container">
        <h1 class="title">Lista de Productos</h1>
        <div class="products-list">
            <div class="product-item" v-for="product in products" :key="product.id">
                <h2>{{ product.nombre }}</h2>
                <p>{{ product.descripcion }}</p>
                <h3>Precio: ${{ product.precio }} CLP</h3>
                <h4>Stock: {{ product.stock }}</h4>
                <button class="purchase-button">Comprar</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            products: [
                { id: 1, nombre: 'Smartphone', descripcion: 'Teléfono inteligente de última generación', precio: 699.99, stock: 50 },
                { id: 2, nombre: 'Televisor 4K', descripcion: 'Televisor de 50 pulgadas con resolución 4K', precio: 899.99, stock: 30 },
                { id: 3, nombre: 'Camisa', descripcion: 'Camisa de algodón 100%', precio: 29.99, stock: 100 },
                { id: 4, nombre: 'Sofá', descripcion: 'Sofá de tres plazas', precio: 499.99, stock: 15 },
                { id: 5, nombre: 'Muñeco de acción', descripcion: 'Figura de acción para niños mayores de 3 años', precio: 14.99, stock: 200 },
                { id: 6, nombre: 'Laptop', descripcion: 'Laptop de alto rendimiento', precio: 999.99, stock: 20 },
                { id: 7, nombre: 'Cámara DSLR', descripcion: 'Cámara profesional para fotografía', precio: 799.99, stock: 10 },
                { id: 8, nombre: 'Auriculares', descripcion: 'Auriculares inalámbricos', precio: 149.99, stock: 50 },
                { id: 9, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 31, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 32, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 33, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 34, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 35, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 36, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 37, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 38, nombre: 'Reloj inteligente', descripcion: 'Reloj con múltiples funciones', precio: 199.99, stock: 30 },
                { id: 10, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 11, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 12, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 13, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 14, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 15, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 16, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 17, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 18, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 19, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 20, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 21, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 22, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 23, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 24, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 25, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 26, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 27, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 28, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 29, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                { id: 30, nombre: 'Tablet', descripcion: 'Tablet de 10 pulgadas', precio: 299.99, stock: 25 },
                // Agrega más productos según sea necesario
            ]
        };
    }
}
</script>

<style>
.products-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    background-color: #f0f0f0;
    min-height: 100vh;
    width: 100%;
}

.title {
    font-size: 24px;
    color: #333;
    margin-bottom: 20px;
    text-align: center;
}

.products-list {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 20px;
    width: 100%;
    max-width: 1200px;
}

.product-item {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    transition: transform 0.3s;
}

.product-item:hover {
    transform: translateY(-5px);
}

.product-item h2 {
    font-size: 18px;
    color: #333;
    margin-bottom: 10px;
}

.product-item p {
    font-size: 14px;
    color: #666;
    margin-bottom: 10px;
    line-height: 1.4;
}

.product-item h3, .product-item h4 {
    font-size: 16px;
    color: #333;
    margin-bottom: 10px;
}

.purchase-button {
    padding: 12px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.purchase-button:hover {
    background-color: #2563eb;
}
</style>
