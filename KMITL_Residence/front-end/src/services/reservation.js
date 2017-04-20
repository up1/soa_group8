import axios from 'axios'
const API_URL = process.env.API_URL

const add = (reservationData) => {
    return axios.post(`${API_URL}:9000/reservation/add`, reservationData)
}

const confirm = (reservationId, hashId) => {
    return axios.put(`${API_URL}:9000/reservation/${reservationId}/confirm?id=${hashId}`)
}

const cancel = (reservationId, hashId) => {
    return axios.put(`${API_URL}:9000/reservation/${reservationId}/cancel?id=${hashId}`)
}

const availableSearch = (checkInDate, checkOutDate, adults, children) => {
    return axios.get(`${API_URL}:9000/reservation/availableSearch?checkin=${checkInDate}&checkout=${checkOutDate}&adults=${adults}&children=${children}`)
}

export default {
    add,
    confirm,
    cancel,
    availableSearch
}