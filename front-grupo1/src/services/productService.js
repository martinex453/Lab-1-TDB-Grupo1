import httpClient from '../http-common';

const getProductById = (id) => {
    const token = localStorage.getItem("token");
    return httpClient.get(`/producto/get/${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const updateProduct = (id, product, clientId) => {
    const token = localStorage.getItem("token");
    return httpClient.put(`/producto/update/${id}id_cliente=${clientId}`, product, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const getAllproducts = () => {
    const token = localStorage.getItem("token");
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
