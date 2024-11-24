import httpClient from "../http-common";

const createCliente = (cliente) => {
    return httpClient.post("/cliente/crear_cuenta", cliente);
};

const loginCliente = (email, contrasena) => {
    const data = {
        email: email,
        contrasena: contrasena
    }
    return httpClient.post(`/authenticate/login`,data, {
        headers: "application/json"
    });
};

const getRole = (id, token) => {
    return httpClient.get(`/cliente/getRol?id=${id}`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const getTopUsers = (token) => {
    return httpClient.get(`/top_usuarios`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

const getTopSpenders = (token) => {
    return httpClient.get("/cliente/getTop5", {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};


export default {
    createCliente,
    loginCliente,
    getRole,
    getTopUsers,
    getTopSpenders
};

