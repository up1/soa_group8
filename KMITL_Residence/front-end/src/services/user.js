import axios from 'axios'

const API_URL = process.env.API_URL

const authen = (authenData) => {
    return axios.post(`${API_URL}:9004/users/authentication`, authenData)
}

const isAuthenticated = (token) => {
    return axios.get(`${API_URL}:9004/users/validate?token=${token}`)
}

export default {
    authen,
    isAuthenticated
}