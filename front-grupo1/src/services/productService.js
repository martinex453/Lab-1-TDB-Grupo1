import httpClient from '../http-common';

const getProductById = (id, token) => {
    return httpClient.get(`/producto/get/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const updateProduct = (id, product, clientId, token) => {
    return httpClient.put(`/producto/update/${id}?id_cliente=${clientId}`, product, {
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

const updateStock = (id, stock, token, clientId) => {
    return httpClient.put(`/producto/updateStock/${id}/${stock}?id_cliente=${clientId}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}

const getPoductsForPages = (page, pageSize, token) => {
    return httpClient.get(`/producto/pagina/${page}/${pageSize}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
}



export default {
    getProductById,
    updateProduct,
    getAllproducts,
    updateStock,
    getPoductsForPages
};
