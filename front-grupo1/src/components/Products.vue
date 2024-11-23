<template>
    <div class="products-container">
        <h1 class="title">Lista de Productos</h1>
        <div class="products-list">
            <div class="product-item" v-for="product in products" :key="product.id">
                <img class="product-img" src="./images/caja.png" alt="Producto" />
                <h2>{{ product.nombre }}</h2>
                <p>{{ product.descripcion }}</p>
                <h3>Precio: ${{ product.precio }} CLP</h3>
                <h4>Stock: {{ product.stock }}</h4>
                <button class="purchase-button" @click="productDetails(product)">Comprar</button>
                <button v-if="this.isAdmin" class="edit-button" @click="editProduct(product.id_producto)">Editar</button>
            </div>
        </div>
        <div class="pagination-container">
            <button v-if="showPrevButton" @click="changePage(page - 1)" class="pageButton">Ant</button>
            <button v-if="showNextButton" @click="changePage(page + 1)" class="pageButton">Sig</button>
        </div>
    </div>
</template>

<script>
import productService from '../services/productService.js';

export default {
    data() {
        return {
            products: [],
            page: 1,
            pageSize: 12,
            isAdmin: localStorage.getItem("userRole") === "admin",
        };
    },
    computed: {
        showPrevButton() {
            return this.page > 1;
        },
        showNextButton() {
            return this.products.length === this.pageSize;
        },
    },
    methods: {
        async getProducts() {
            try {
                const token = this.$cookies.get("jwt");
                const response = await productService.getPoductsForPages(this.page, this.pageSize, token);
                this.products = response.data;
            } catch (error) {
                console.error(error);
            }
        },
        productDetails(product) {
            this.$router.push(`/purchase/${product.id_producto}`);
        },
        async changePage(newPage) {
            if (newPage < 1) return;
            this.page = newPage;
            this.getProducts();
        },
        editProduct(productId) {
            this.$router.push(`/edit-product/${productId}`);
        },
    },
    mounted() {
        this.getProducts();
    },
};
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
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    justify-content: center;
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
    height: 100%;
    min-height: 300px;
}

.product-item:hover {
    transform: translateY(-5px);
}

.product-img {
    width: 100%;
    max-height: 150px;
    object-fit: cover;
    margin-bottom: 15px;
}

.purchase-button,
.edit-button {
    padding: 12px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 14px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-top: 10px;
}

.purchase-button:hover,
.edit-button:hover {
    background-color: #2563eb;
}


.product-img {
    width: 100%;
    max-height: 150px;
    object-fit: cover;
    margin-bottom: 15px;
}

.product-item h2,
.product-item p,
.product-item h3,
.product-item h4 {
    margin: 0;
    text-align: center;
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

.product-item h3,
.product-item h4 {
    font-size: 16px;
    color: #333;
    margin-bottom: 10px;
}

.pagination-container {
    display: flex;
    justify-content: center;
    gap: 10px;
    margin-top: 20px;
}

.pageButton {
    padding: 12px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    width: 70px;
    font-size: 14px;
    transition: background-color 0.3s;
}
</style>
