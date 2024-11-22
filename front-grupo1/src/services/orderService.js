import httpClient from '../http-common';

// Nombres usados en el código
// makeOrder, getOrderById, updateOrder, getOrderByUserId

const makeOrder = (order, clientId, token) => { 
    console.log("Enviando orden");
    return httpClient.post(`/orden/crear?id=${clientId}`, order, {
        headers: {
            Authorization: `Bearer ${token}`, 
        },
    });
};

const getOrderByUserId = (id, token) => { 
    return httpClient.get(`/orden/get/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const getOrderById = (id, token) => {
    return httpClient.get(`/orden/getOrderid/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}

const updateOrder = (id, order, clientId, token) => {
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