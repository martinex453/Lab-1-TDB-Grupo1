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

//Nombres usados en el codigo
//makeOrderDetail, getOrderDetailByOrderId




export default{};