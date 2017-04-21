import axios from 'axios'

const getRoomTypeData = (roomTypeId) => {
    return axios(`http://localhost:9001/rooms/type/${roomTypeId}`)
}

export default {
    getRoomTypeData
}