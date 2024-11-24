<template>
    <div class="container-order-summary">
        <div class="order-title">
            <h1>Resumen orden de compra</h1>
        </div>
        <div class="order-details">
            <table>
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item, index) in totalOrderDetail" :key="index">
                        <td>{{ item.name }}</td>
                        <td>{{ item.cantidad }}</td>
                        <td>${{ item.precio_unitario }} CLP</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order-summary">
            <h1>Total a pagar:</h1>
            <h2>${{ this.totalPrice }} CLP</h2>
            <button class="order-button" @click="submitOrder">Ordenar</button>
        </div>
    </div>
</template>

<script>
import productService from '@/services/productService';
import orderService from '@/services/orderService';
import orderDetailService from '@/services/orderDetailService';

export default {
    //Definir las propiedades del componente
    data() {
        return {
            token: this.$cookies.get("jwt"),
            totalOrderDetail: [],
            totalPrice: 0,
            idUser: localStorage.getItem("idUser")
        };
    },
    methods: {
        async submitOrder(){
            //Convertir el carrito de compras a un arreglo JSON
            const carritoJson = [];
            for(let i = 0; i < this.$carrito.length; i++){
                const productoCarrito = {
                    id_producto: parseInt(this.$carrito[i][0]),
                    id_producto: Number(this.$carrito[i][0]),
                    cantidad: this.$carrito[i][1],
                    precio_unitario: this.$carrito[i][2]
                }
                carritoJson.push(productoCarrito);
            }
            console.log(carritoJson);
            console.log(JSON.stringify(carritoJson));
            console.log(this.token);
            console.log(this.idUser);
            //Enviar la orden de compra al servidor
            await orderService.submitOrder(carritoJson, this.idUser, this.token);
            alert("Orden realizada con éxito");
            //Limpiar el carrito de compras
            this.$carrito.splice(0, this.$carrito.length);
            this.updateCarrito();
            console.log(this.$carrito);
            this.loadCart();
            //Obtener los nombres de los productos y calcular el precio total
            await this.fetchProductNames();
            this.calculateTotalPrice();
        },
        loadCart() {
            //Obtener el carrito de compras del almacenamiento local
            const carrito = JSON.parse(localStorage.getItem("carrito")) || [];
            //Convertir el carrito de compras a un arreglo de productos
            this.totalOrderDetail = carrito.map(item => {
                return {
                    id: item[0],
                    cantidad: item[1],
                    precio_unitario: item[2],
                    name: ''
                };
            });
        },
        async fetchProductNames() {
            //Obtener los nombres de los productos en el carrito
            for (const detail of this.totalOrderDetail) {
                const productResponse = await productService.getProductById(detail.id, this.token);
                console.log(productResponse.data.nombre);
                detail.name = productResponse.data.nombre;
            }
        },
        calculateTotalPrice() {
            //Calcular el precio total de la orden de compra
            this.totalPrice = this.totalOrderDetail.reduce((total, item) => {
                return total + (item.precio_unitario * item.cantidad);
            }, 0);
        },
    },
    async created() {
        //Cargar el carrito de compras, obtener los nombres de los productos y calcular el precio total al cargar el componente
        this.loadCart();
        await this.fetchProductNames();
        this.calculateTotalPrice();
    }
};
</script>

<style>
.container-order-summary {
    display: grid;
    grid-template-columns: 2fr 1fr;
    grid-template-rows: auto 1fr;
    gap: 20px;
    padding: 20px;
    height: 100vh;
    width: 100%;
    background-color: #f0f0f0;
    overflow: hidden;
}

.order-title {
    grid-column: span 2;
    text-align: center;
    background-color: #fff;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-size: 15px;
}

.order-details {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    color: #000000;
}

.order-summary {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    grid-row: span 2;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
}

thead {
    background-color: #3b82f6;
    color: white;
}

th, td {
    text-align: center;
    padding: 10px;
    border-bottom: 1px solid #ddd;
}

tbody tr:hover {
    background-color: #f9f9f9;
}

h1, h2 {
    text-align: center;
    color: #333;
}

button {
    width: 100%;
    padding: 12px;
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

button:hover {
    background-color: #2563eb;
}
</style>
