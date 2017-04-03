import axios from 'axios'

const confirm = (reservationId, hashId) => {
    return axios.put(`http://localhost:9000/reservation/${reservationId}/confirm?id=${hashId}`)
}

const cancel = (reservationId, hashId) => {
    return axios.put(`http://localhost:9000/reservation/${reservationId}/cancel?id=${hashId}`)
}

export default {
    confirm,
    cancel
}