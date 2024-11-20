import httpClient from "../http-common";

const createCliente = (cliente) => {
    return httpClient.post("/cliente/crear", cliente);
};

const loginCliente = (email, contrasena) => {
    return httpClient.post(`/cliente/login?email=${email}&contrasena=${contrasena}`);
};

export default {
    createCliente,
    loginCliente
};

