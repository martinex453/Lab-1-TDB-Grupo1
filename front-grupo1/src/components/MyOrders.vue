<template>
    <div class="container-order-summary">
        <div class="order-title">
            <h1>Órdenes de compra</h1>
        </div>
        <div class="order-details">
            <table>
                <thead>
                    <tr>
                        <th>Fecha creación</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Detalles</th>
                        <th v-if="isAdmin">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="order in orders" :key="order.id_orden">
                        <td>{{ formatDate(order.fecha_orden) }}</td>
                        <td>{{ order.estado }}</td>
                        <td>{{ order.total }}</td>
                        <td>
                            <button @click="goToOrderDetail(order.id_orden)">Ir</button>
                        </td>
                        <td v-if="isAdmin">
                            <button v-if="order.estado === 'pagada'" @click="sendOrder(order)">Enviar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="pagination-container">
                <button v-if="page > 1" @click="changePage(1)" class="pageButton">Ant</button>
                <button v-if="orders.length === pageSize" @click="changePage(page + 1)" class="pageButton">Sig</button>
            </div>
        </div>
    </div>
</template>

<script>
import orderService from '@/services/orderService';

export default {
    //Definir las propiedades del componente
    data() {
        return {
            orders: [],
            isAdmin: false,
            page: 1, //Numero de página actual
            pageSize: 12, //Tamaño de la página
            id_user: localStorage.getItem('idUser'),
            token: this.$cookies.get("jwt"),
        };
    },
    methods: {
        async getOrders() {
            //Obtener el token de autenticación y el rol del usuario
            const token = this.$cookies.get("jwt");
            const userRole = localStorage.getItem("userRole");

            //Verificar si el usuario es administrador
            this.isAdmin = userRole === 'admin';
            
            try {
                if (this.isAdmin) {
                    //Obtener las órdenes de compra si el usuario es administrador
                    const response = await orderService.getOrders(this.page, this.pageSize, token);
                    this.orders = response.data;
                } else {
                    //Obtener el id del usuario y buscar sus órdenes de compra
                    const id = localStorage.getItem('idUser');
                    const response = await orderService.getOrderByUserId(id, this.page, this.pageSize, token);
                    this.orders = response.data;
                }
            } catch (error) {
                //Mostrar un mensaje de error si no se pueden obtener las órdenes
                console.error("Error al obtener órdenes:", error);
            }
        },
        async sendOrder(order) {
            try {
                //Cambiar el estado de la orden a 'enviada'
                console.log(order);
                order.estado = 'enviada';
                await orderService.updateOrder(order.id_orden, order, this.id_user, this.token);
                //Actualizar la lista de órdenes
                this.getOrders();
                alert('Orden enviada correctamente');
            } catch (error) {
                //Mostrar un mensaje de error si no se puede enviar la orden
                console.error("Error al enviar la orden:", error);
                alert('Error al enviar la orden');
            }
        },
        goToOrderDetail(orderId) {
            //Redirigir a la página de detalles de la orden
            this.$router.push(`/order/${orderId}`);
        },
        formatDate(date) {
            //Formatear la fecha de la orden
            const newDate = new Date(date);
            return newDate.toLocaleDateString("es-ES", {
                day: "2-digit",
                month: "2-digit",
                year: "numeric",
            });
        },
        async changePage(newPage) {
            //Cambiar la página actual y obtener las órdenes de la nueva página
            this.page = newPage;
            await this.getOrders();
        }
    },
    mounted() {
        //Llamar al método para obtener las órdenes de compra
        this.getOrders();
    }
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
    color: #000000;
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

button {
    background-color: #3b82f6;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 5px 10px;
    cursor: pointer;
}

button:hover {
    background-color: #2563eb;
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