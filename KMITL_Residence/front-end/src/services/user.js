import axios from 'axios'

const API_URL = process.env.API_URL

const authen = (authenData) => {
    return axios.post(`${API_URL}:9004/users/authentication`, authenData)
}

export default {
    authen
}