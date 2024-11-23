import { createRouter, createWebHistory } from 'vue-router';
import RegisterLogin from '../components/RegisterLogin.vue';
import Products from '../components/Products.vue';
import PurchaseOrder from '../components/PucharseOrder.vue';
import totalOrderDetail from '../components/totalOrderDetail.vue';
import OrderDetail from '../components/OrderDetail.vue';
import MyOrders from '../components/MyOrders.vue';
import EditProduct from '../components/EditProduct.vue';

const routes = [
    {
        path: '/',
        name: 'register-login',
        component: RegisterLogin,
        meta: { requiresAuth: false}
    },
    {
        path: '/products',
        name: 'products',
        component: Products,
        meta: { requiresAuth: true}
    },
    {
        path: '/purchase/:id',
        name: 'pucharse-order',
        component: PurchaseOrder,
        meta: { requiresAuth: true}
    },
    {
        path: '/order',
        name: 'totalOrderDetail',
        component: totalOrderDetail,
        meta: { requiresAuth: true}
    },
    {
        path: '/order/:id',
        name: 'orderDetail',
        component: OrderDetail,
        meta: { requiresAuth: true}
    },
    {
        path: '/edit-product/:id',
        name: 'editProduct',
        component: EditProduct,
        meta: { requiresAuth: true}
    },
    {
        path: '/myOrders',
        name: 'MyOrders',
        component: MyOrders,
        meta: { requiresAuth: true}
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
  export default router;