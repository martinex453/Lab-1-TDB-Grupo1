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

<style scoped>
.products-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh; /* Ocupa toda la altura de la pantalla */
    width: 100vw; /* Ocupa todo el ancho de la pantalla */
    background-color: #1E3A8A; /* Color de fondo */
    color: white;
    text-align: center; /* Centra el texto */
}

.products-list {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center; /* Centra los elementos dentro de la lista */
    width: 100%; /* Asegura que la lista ocupe todo el ancho disponible */
    max-height: 80vh; /* Limita la altura máxima de la lista */
    overflow-y: auto; /* Habilita el desplazamiento vertical */
}

.product-item {
    background-color: #3B82F6;
    border-radius: 10px;
    padding: 15px;
    width: 200px;
    text-align: center;
}

.purchase-button {
    background-color: rgb(45, 140, 184);
    color: white;
    border: none;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
}

.purchase-button:hover {
    background-color: rgb(30, 100, 150);
}

.title {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 20px;
    background-color: rgb(45, 140, 184);
    padding: 8px;
    border-radius: 10px;
}
</style>