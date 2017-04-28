<template>
    <div class="ui small modal" id="chooseRoomModal">
        <i class="close icon"></i>
        <div class="header">
            Choose Room
        </div>
        <div class="content">
            <div class="description">
                <div class="ui segment" ref="tableSegment">
                    <table class="ui single line table">
                        <thead>
                            <tr>
                                <th style="width:35%">Room ID</th>
                                <th style="width:35%">Room Type</th>
                                <th style="width:30%">Check In</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="room in availableRooms">
                                <td>{{ room.roomId }}</td>
                                <td>{{ room.roomTypeId | matchRoomType }}</td>
                                <td>
                                    <button class="ui fluid button" @click="chooseRoom(room.roomId)">
                                        Choose
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="actions">
            <div class="ui black deny button">
                Cancel
            </div>
        </div>
    </div>
</template>

<script>
import { Rooms } from '@/services'

export default {
    props: ['reservationData'],
    data: () => ({
        state: 'loading',
        availableRooms: []
    }),
    created(){
        this.fetchData()
        this.state = 'fuck'
    },
    mounted(){
        this.fetchData()
    },
    watch: {
        reservationData(){
            this.fetchData()
        }
    },
    methods: {
        fetchData(){
            Rooms.getAvailableRoomsByRoomType(this.reservationData.roomType)
                .then(res => {
                    this.state = 'completed'
                    this.availableRooms = res.data
                })
                .catch(err => {
                    this.state = 'error'
                })
        },
        chooseRoom(roomId){
            $(this.$refs.tableSegment).addClass('loading')
            Rooms.checkIn(this.reservationData.id, roomId, this.$cookie.get('_token'))
                .then(res => {
                    $(this.$refs.tableSegment).removeClass('loading')
                    $('#chooseRoomModal').modal('hide')
                    this.reservationData.checkInStatus = 'yes'
                    $(this.$emit('refresh'))
                })
                .catch(err => {
                    console.log(err)
                })
        }
    }
}


</script>