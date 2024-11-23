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
    return httpClient.get(`/`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};


export default {
    createCliente,
    loginCliente
};

