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

const getAvailableRoomsByRoomType = (roomTypeId) => {
    return axios(`${API_URL}:9001/rooms/available/${roomTypeId}`)
}

const checkIn = (reservationId, roomId, _token) => {
    return axios.post(`${API_URL}:9001/rooms/${roomId}/checkin/${reservationId}`, {}, {
        headers: {
            'authenticate-token': _token
        }
    })
}

const checkOut = (reservationId, _token) => {
    return axios.put(`${API_URL}:9001/rooms/checkout?id=${reservationId}`, {}, {
        headers: {
            'authenticate-token': _token
        }
    })
}

const changeRoom = (reservationId, roomId, _token) => {
    return axios.put(`${API_URL}:9001/rooms/change?reservationId=${reservationId}&roomId=${roomId}`, {}, {
        headers: {
            'authenticate-token': _token
        }
    })
}

export default {
    getRoomTypeData,
    getCheckInInfo,
    getAvailableRoomsByRoomType,
    checkIn,
    checkOut,
    changeRoom
}