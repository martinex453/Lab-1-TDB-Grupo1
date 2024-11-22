import httpClient from '../http-common';

// Nombres usados en el código
// makeOrder, getOrderById, updateOrder, getOrderByUserId

const makeOrder = (order, clientId) => {
    const token = localStorage.getItem("token"); 
    return httpClient.post(`/orden/crear?id_cliente=${clientId}`, order, {
        headers: {
            Authorization: `Bearer ${token}`, 
        },
    });
};

const getOrderByUserId = (id) => {
    const token = localStorage.getItem("token"); 
    return httpClient.get(`/orden/get/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const getOrderById = (id) => {
    const token = localStorage.getItem("token"); 
    return httpClient.get(`/orden/getOrderid/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}

const updateOrder = (id, order, clientId) => {
    const token = localStorage.getItem("token"); 
    return httpClient.post(`/orden/update/${id}?id_cliente=${clientId}`, order, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}


export default {
    makeOrder,
    getOrderByUserId,
    getOrderById, 
    updateOrder
};