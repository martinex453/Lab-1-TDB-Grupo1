import httpClient from "../http-common";

const getAll = (token) => {
    return httpClient.get("/categoria/All", {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

export default {
    getAll,
};