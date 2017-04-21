import axios from 'axios'

const API_URL = process.env.API_URL

const getRoomTypeData = (roomTypeId) => {
    return axios(`http://${API_URL}:9001/rooms/type/${roomTypeId}`)
}

export default {
    getRoomTypeData
}