import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8081",
    withCredentials: true
})

export const login = (data) => API.post("/login", data)

export const join = (data) => API.post("/join", data)

export const logout = () => API.post("/logout")
