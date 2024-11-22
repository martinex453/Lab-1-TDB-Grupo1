import httpClient from '../http-common';

const getProductById = (id, token) => {
    return httpClient.get(`/producto/get/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const updateProduct = (id, product, clientId, token) => {
    return httpClient.put(`/producto/update/${id}id_cliente=${clientId}`, product, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const getAllproducts = (token) => {
    
    console.log(token);
    return httpClient.get(`/producto/All`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}



export default {
    getProductById,
    updateProduct,
    getAllproducts
};
