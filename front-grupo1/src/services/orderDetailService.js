import httpClient from '../http-common';

const makeOrderDetail = (orderDetail, token, idCliente) => { 
    return httpClient.post(`/detalleOrden/crear`, orderDetail, {
        params: {
            id_cliente: idCliente,
        },
        headers: {
            Authorization: `Bearer ${token}`, 
        },
    });
};

const makeOrderDetails = (order, clientId, token) => { 
    return httpClient.post(`/detalleOrden/crear?id_cliente=${clientId}`, order, {
        headers: {
            Authorization: `Bearer ${token}`, 
        },
    });
};

const getOrderDetailByOrderId = (id_orden, token) => { 
    return httpClient.get(`/detalleOrden/getbyOrdenid/${id_orden}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};



export default{
    makeOrderDetails,
    getOrderDetailByOrderId,
    makeOrderDetail
};