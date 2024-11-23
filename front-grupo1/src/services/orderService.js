import httpClient from '../http-common';

const makeOrder = (order, clientId, token) => { 
    console.log("Enviando orden");
    return httpClient.post(`/orden/crear?id=${clientId}`, order, {
        headers: {
            Authorization: `Bearer ${token}`, 
        },
    });
};

const getOrderByUserId = (id, nroPag, tamanioPag, token) => { 
    return httpClient.get(`/orden/pagina/user/${id}/${nroPag}/${tamanioPag}`, {
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

const orderByTimestamp = (token) => {
    return httpClient.get("/orden/timestamp", {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}

const submitOrder = (carrito, idUser, token) => {
    return httpClient.post(`/crearOrdenCompra/${idUser}`, carrito, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}

const getOrders = (nroPag, tamanioPag, token) => {
    return httpClient.get(`/orden/pagina/${nroPag}/${tamanioPag}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

export default {
    makeOrder,
    getOrderByUserId,
    getOrderById, 
    updateOrder,
    orderByTimestamp,
    submitOrder,
    getOrders
};