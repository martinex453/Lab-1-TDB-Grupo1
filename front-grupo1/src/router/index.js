import { createRouter, createWebHistory } from 'vue-router';
import RegisterLogin from '../components/RegisterLogin.vue';
import Products from '../components/Products.vue';
import PurchaseOrder from '../components/PucharseOrder.vue';
import totalOrderDetail from '../components/totalOrderDetail.vue';
import MyOrders from '../components/MyOrders.vue';

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
        path: '/product',
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