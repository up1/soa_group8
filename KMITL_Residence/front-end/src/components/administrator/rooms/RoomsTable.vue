<template>
    <div class="row">
        <div class="sixteen wide column">
            <div class="card card-padding normal-padding">
                <div class="ui active loader" v-if="loading"/>
                <table class="ui compact celled definition table" v-else>
                    <thead class="full-width">
                        <tr>
                            <th style="width:15%">Room ID</th>
                            <th style="width:40%">Room Type</th>
                            <th style="width:40%">Available</th>
                            <th style="width:5%">Closing</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="room in filteredRoomsData">
                            <td>{{ room.roomId }}</td>
                            <td>{{ room.roomTypeId | matchRoomType }}</td>
                            <td>
                                <i class="large green checkmark icon" v-if="room.roomAvailability"/>
                                <i class="large red close icon" v-else/>
                            </td>
                            <td class="collapsing">
                                <div class="ui fitted slider checkbox" :class="{ disabled : room.roomAvailability == 0}">
                                    <input type="checkbox" :checked="room.roomAvailability == 1" :disabled="room.roomAvailability == 0" v-on:change="updateRoom(room)"> <label></label>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import { Rooms } from '@/services'
export default {
    props: ['roomsFilter'],
    data: () => ({
        loading: true,
        roomsData: []
    }),
    created () {
        Rooms.getAllRoomsData(this.$cookie.get('_token'))
            .then(res => {
                this.roomsData = res.data
                this.loading = false
            })
            .catch(err => {
                alert("System can\'t fetch data from server")
            })
    },
    computed: {
        filteredRoomsData(){
            let roomsData = this.roomsData
            if(this.roomsFilter.availabilityType != 9){
                roomsData = roomsData.filter(room => room.roomAvailability == this.roomsFilter.availabilityType)
            }
            if(this.roomsFilter.roomsType != 9){
                roomsData = roomsData.filter(room => room.roomTypeId == this.roomsFilter.roomsType)
            }
            return roomsData
        }
    },
    methods: {
        updateRoom(room){
            if(room.roomAvailability == 1){
                Rooms.closeRoom(room.roomId, this.$cookie.get('_token'))
                    .then(res => {
                        console.log("Room ID " + room.roomId + " is now closed.")
                    })
                    .catch(err => {
                        console.log("Can\'t close the room ID " + room.roomId + ". maybe the server is terminated.")
                    })
            }else if(room.roomAvailability == -1){
                Rooms.openRoom(room.roomId, this.$cookie.get('_token'))
                    .then(res => {
                        console.log("Rooms ID " + room.roomId + " is now opened.")
                    })
                    .catch(err => {
                        console.log("Can\'t open the room ID " + room.roomId + ". maybe the server is terminated.")
                    })
            }
        }
    }
}
</script>