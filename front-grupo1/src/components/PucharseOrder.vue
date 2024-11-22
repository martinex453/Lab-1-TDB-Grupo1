<script>
import productService from '@/services/productService';
import orderService from '@/services/orderService';
import orderDetailService from '@/services/orderDetailService';
export default {
    data() {
        return {
            //name: "Caja de carton",
            //description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
            //stock: 1,
            //state: "Disponible",
            //price: 100,
            product: null,
            amount: 1,
            token: this.$cookies.get("jwt"),
            idUser: localStorage.getItem("idUser")
        }
    },
    computed: {
        totalPrice() {
            return this.product?.precio * this.amount;
        },
        productStock() {
            return this.product?.stock - 1;
        }
    },
    methods: {
        async getProduct(){
            try {
                const id = this.$route.params.id;
                const response = await productService.getProductById(id, this.token);
                this.product = response.data;
            } catch (error) {
                alert('Error al obtener producto');
            }
        },
        async makeOrder(){
            try {
                const id_prod = this.$route.params.id;
                console.log("Id producto: "+id_prod);
                //Si el cliente tiene no generado un "Carrito"/orden de compra, se genera uno
                //OBS: Considere que tiene 1 carrito a la vez, donde orderId, se seteara en null
                //cuando se haya enviado la solicitud total de compra o se haya cancelado
                if(!localStorage.getItem("orderId")){
                    console.log("No hay orden de compra");
                    const date = new Date();
                    
                    const timestamp = date.toISOString().slice(0, 19);
                    const order = {
                        fecha_orden: timestamp,
                        estado: "pendiente",
                        id_cliente: this.idUser,
                        total: this.totalPrice
                    }
                    console.log("Orden fec: "+order.fecha_orden);
                    console.log("Orden est: "+order.estado);
                    console.log("Orden id: "+order.id_cliente);
                    console.log("Orden t: "+order.total);
                    const response = await orderService.makeOrder(order, userId, this.token);
                    console.log("Orden creada con exito");
                    localStorage.setItem("orderId", response.data.id_orden);
                }
                //obtener la orden de compra
                const orderId = localStorage.getItem("orderId");
                console.log("OrdenId: "+orderId);
                const response1 = await orderService.getOrderById(orderId, this.token);
                console.log("Orden: "+response1.data);
                const order = response1.data;
                //actualizar el precio total de la orden
                const actOrder = {
                    id: orderId,
                    fecha_orden: order.fecha_orden,
                    estado: order.estado,
                    id_cliente: order.id_cliente,
                    total: order.total + this.totalPrice
                }
                console.log("Orden: "+actOrder);
                await orderService.updateOrder(orderId, actOrder, userId,  this.token);
                console.log("Orden actualizada con exito");
                //Se genera el detalle de la orden para el producto seleccionado
                //Se podria buscar por orderId y productId para ver si ya existe un detalle de orden
                //y asi no generar uno nuevo
                const orderDetail = {
                    id_orden: localStorage.getItem("orderId"),
                    id_producto: id_prod,
                    cantidad: this.amount,
                    precio_unitario: this.product.precio
                }
                
                console.log("Orden: "+orderDetail);
                await orderDetailService.makeOrderDetail(orderDetail, this.token, this.idUser);
                console.log("Orden realizada con exito");
                
                const stock = this.product.stock - this.amount;
                
                console.log("Stock: "+stock);
                await productService.updateStock(id, stock, this.token);
                console.log("Stock actualizado con exito");
                //Se vuelve a cargar el producto para actualizar la vista
                this.getProduct();
            } catch (error) {
                alert('Error al realizar orden');
            }
        },
        validateAmount() {
            if (this.amount < 1) {
                this.amount = 0; // Restablecer al mínimo permitido
            }
        }

    },
    mounted() {
        this.getProduct();
    }
}
</script>

<template>
    <div class="pucharce-order-container">
        <div class="image">
            <img class="product-img" src="./images/caja.png" alt="Producto" />
        </div>
        <div class="product-information">
            <h1>{{ this.product?.nombre }}</h1>
            <h2>Descripcion del producto:</h2>
            <br>
            <p>{{ this.product?.descripcion }}</p>

        </div>
        <div class="order-form-container">
            <h1 class="order-title">Detalles de la orden</h1>
            <div class="pucharse-information">
                <h1>Precio: ${{this.product?.precio}} clp</h1>
                <h1>Cantidad</h1>
                <input type="number" min="1" step="1" v-model="amount" @input="validateAmount" class="amount-selection">
                <h1>Stock: {{this.product?.stock}}</h1>
                <h1>Estado: {{this.product?.estado}}</h1>
                <h1>Precio total: </h1>
                <h1>${{totalPrice}} clp</h1>
                <br>
            </div>
            <button v-if="this.product?.stock>=1" class="pucharse-button" @click="makeOrder">Agregar a la orden</button>
            <h1 v-else class="no-stock-text">No hay stock del producto</h1>
        </div>
    </div>
</template>

<style>
.pucharce-order-container {
    display: grid;
    grid-template-columns: 60% 1fr;
    grid-template-rows: auto 1fr;
    gap: 20px;
    padding: 20px;
    width: 100%;
    box-sizing: border-box;
    background-color: #F3F4F6;
    color: #1F2937;
}

.image {
    grid-column: 1 / 2;
    grid-row: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    max-height: 300px;
    border-radius: 15px;
    background-color: #E5E7EB;
    overflow: hidden;
    padding: 10px;
}

.product-img {
    width: 100%;
    max-width: 400px;
    height: auto;
    max-height: 300px;
    object-fit: contain;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.product-information {
    grid-column: 1 / 2;
    grid-row: 2;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    padding: 20px;
    border-radius: 15px;
    background-color: #E5E7EB;
    color: #374151;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.order-form-container {
    grid-column: 2;
    grid-row: 1 / 3;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 20px;
    border-radius: 15px;
    background-color: #E5E7EB;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
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
    margin-top: 20px;
}

.pucharse-button {
    background-color: #60A5FA;
}

.no-stock-text {
    background-color: #EF4444;
}

.amount-selection {
    width: 100%;
    max-width: 15rem;
    height: 2.5rem;
    padding: 10px;
    border-radius: 5px;
    text-align: center;
    font-size: 1rem;
    margin-bottom: 20px;
    background-color: white;
    color: #374151;
    border: 1px solid #D1D5DB;
}

.pucharse-information {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    gap: 10px;
    padding: 20px;
    border-radius: 15px;
    background-color: #E5E7EB;
}

.order-title {
    text-align: center;
    font-size: 1.8rem;
    margin-bottom: 20px;
    color: #1F2937;
    background-color: #F9FAFB;
    padding: 10px;
    border-radius: 10px;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
}
</style>