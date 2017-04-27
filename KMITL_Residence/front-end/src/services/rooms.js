import axios from 'axios'

const API_URL = process.env.API_URL

const getRoomTypeData = (roomTypeId) => {
    return axios(`${API_URL}:9001/rooms/type/${roomTypeId}`)
}

const getCheckInInfo = (reservationId, _token) => {
    return axios(`${API_URL}:9001/checkinfo?id=${reservationId}`, {
        headers: {
            'authenticate-token': _token
        }
    })
}

export default {
    getRoomTypeData,
    getCheckInInfo
}