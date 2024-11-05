<script>
export default {
    data() {
        return {
            name: "Caja de carton",
            description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
            stock: 1,
            state: "Disponible",
            price: 100,
            amount: 1
        }
    },
    computed: {
        totalPrice() {
            return this.price * this.amount;
        }
    }
}
</script>

<template>
    <div class="pucharce-order-container">
        <div class="image">
            <img class="product-img" src="./images/caja.png" alt="Producto" />
        </div>
        <div class="product-information">
            <h1>{{ name }}</h1>
            <h2>Descripcion del producto:</h2>
            <br>
            <p>{{ description }}</p>

        </div>
        <div class="order-form-container">
            <h1 class="order-title">Detalles de la orden</h1>
            <div class="pucharse-information">
                <h1>Precio: ${{price}} clp</h1>
                <h1>Cantidad</h1>
                <input type="number" min="1" step="1" v-model="amount" class="amount-selection">
                <h1>Stock: {{stock}}</h1>
                <h1>Estado: {{state}}</h1>
                <h1>Precio total: ${{totalPrice}} clp</h1>
                <br>
            </div>
            <button v-if="stock>=1" class="pucharse-button">Realizar orden</button>
            <h1 v-else class="no-stock-text">No hay stock del producto</h1>
        </div>
    </div>
</template>

<style>
.pucharce-order-container {
    display: grid;
    grid-template-areas:
        "image button"
        "product-information button";
    grid-template-columns: 1.5fr 1fr;
    grid-template-rows: auto 1fr;
    width: 100%;
    height: 100vh;
    gap: 20px;
    padding: 20px;
    box-sizing: border-box;
    background-color: #1E3A8A;
    color: white;
}

.image {
    grid-area: image;
    display: flex;
    align-items: center;
    justify-content: center;
    max-height: 450px;
    border-radius: 15px;
    background-color: #3B82F6;
}

.product-img {
    width: 100%;
    height: auto;
    max-height: 100%;
    object-fit: contain;
}

.product-information {
    grid-area: product-information;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 20px;
    box-sizing: border-box;
    border-radius: 15px;
    background-color: #3B82F6;
}

.order-form-container {
    grid-area: button;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px;
    border-radius: 15px;
    background-color: #3B82F6;
}

.pucharse-button, .no-stock-text {
    font-size: 1.5rem;
    width: 100%;
    max-width: 20rem;
    height: 3rem;
    padding: 10px;
    border-radius: 5px;
    text-align: center;
    color: white;
}

.pucharse-button {
    background-color: rgb(45, 140, 184);
}

.no-stock-text {
    background-color: rgb(241, 76, 112);
}

.amount-selection {
    width: 100%;
    max-width: 20rem;
    height: 3rem;
    padding: 10px;
    border-radius: 5px;
    text-align: center;
    font-size: 1.5rem;
    margin-bottom: 10px;
    background-color: white;
    color: black;
}

.pucharse-information {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 20px;
    border-radius: 15px;
    background-color: #3B82F6;
}

.order-title {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 20px;
    background-color: rgb(45, 140, 184);
    padding: 8px;
    border-radius: 10px;
}
</style>
