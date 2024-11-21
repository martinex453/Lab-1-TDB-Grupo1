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
                    <tr v-for="detail in totalOrderDetail" :key="detail.id_detalle">
                        <td>{{ detail.name }}</td>
                        <td>{{ detail.cantidad }}</td>
                        <td>{{ detail.precio_unitario }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order-summary">
            <h1>Total a pagar:</h1>
            <h2>${{ this.totalPrice }} CLP</h2>
        </div>
    </div>
</template>

<script>
import productService from '@/services/productService';
import orderService from '@/services/orderService';
import orderDetailService from '@/services/orderDetailService';

export default {
    data() {
        return {
            orderId: localStorage.getItem("orderId"),
            totalOrderDetail: {},
            totalPrice: 0
        };
    },
    methods: {
        async getTotalOrderDetail() {
            if (!this.orderIdExists) {
                alert("No se ha encontrado una orden de compra.");
                return;
            }
            try {
                const response = await orderDetailService.getOrderDetailByOrderId(this.orderId);
                this.totalOrderDetail = response.data;

                for (const detail of this.totalOrderDetail) {
                    const productResponse = await productService.getProductById(detail.id_detalle);
                    this.$set(detail, 'name', productResponse.data.nombre);
                }

                const total = await orderService.getOrderById(this.orderId);
                this.totalPrice = total.data.total;
            } catch (error) {
                alert('Error al obtener detalle de orden');
            }
        }
    },
    created() {
        this.getTotalOrderDetail();
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
