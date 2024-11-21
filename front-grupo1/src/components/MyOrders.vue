<template>
    <div class="container-order-summary">
        <div class="order-title">
            <h1>Mis órdenes de compra</h1>
        </div>
        <div class="order-details">
            <table>
                <thead>
                    <tr>
                        <th>Fecha creacion</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Detalles</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="order in orders" :key="order.id_orden">
                        <td>{{ order.fecha_orden }}</td>
                        <td>{{ order.estado }}</td>
                        <td>{{ order.total }}</td>
                        <td>
                            <router-link :to="{ name: 'totalOrderDetail', params: { id: order.id_orden } }">
                                Ir
                            </router-link>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import orderService from '@/services/orderService';

export default {
  data() {
    return {
      orders: [],
    };
  },
  methods: {
    async getOrders() {
      try {
        const id = localStorage.getItem('userId');
        const response = await orderService.getOrderByUserId(id);
        this.orders = response.data;
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.container-order-summary {
    display: grid;
    grid-template-columns: 1fr;
    gap: 20px;
    padding: 20px;
    height: 100vh;
    background-color: #f0f0f0;
    overflow: hidden;
}

.order-title {
    text-align: center;
    background-color: #fff;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    font-size: 20px;
    font-weight: bold;
    color: #333;
}

.order-details {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    overflow: auto;
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

h1 {
    text-align: center;
    color: #333;
    font-size: 1.5rem;
}

router-link {
    color: #3b82f6;
    text-decoration: none;
}

router-link:hover {
    text-decoration: underline;
    color: #2563eb;
}
</style>
