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

export default {
    createCliente,
    loginCliente
};

