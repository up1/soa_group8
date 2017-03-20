<template>
<form class="ui form" @submit.prevent>
    <div class="fields">
        <div class="ui large input four wide field">
            <label>Check-in date</label>
            <input type="date" placeholder="Choose check-in date" v-model="checkInDate">
        </div>

        <div class="ui large input four wide field">
            <label>Check-out date</label>
            <input type="date" placeholder="Choose check-out date" v-model="checkOutDate">
        </div>

        <div class="ui large input two wide field">
            <label>Adults</label>
            <select class="ui dropdown" v-model="adults">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
        </div>

        <div class="ui large input two wide field">
            <label>Children</label>
            <select class="ui dropdown" v-model="children">
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
        </div>

        <div class="ui large input four wide field">
            <label>​&nbsp;</label>
            <button class="ui button fluid luxury-primary" @click="checkAvailability" ref="submitBtn">
                Check Availability
            </button>
        </div>
    </div>
</form>
</template>

<script>
import axios from 'axios'

export default {
    data: () => ({
        checkInDate: '',
        checkOutDate: '',
        adults: 1,
        children: 0
    }),
    methods: {
        checkAvailability(){
            $(this.$refs.submitBtn).addClass('loading')
            axios.get(`http://localhost:9000/reservation/availableSearch?checkin='${this.checkInDate}'&checkout='${this.checkOutDate}'&adults=${this.adults}&children=${this.children}`)
                .then((res) => this.getAvailableRoomsCallback(res))
                .catch(function(err){
                    console.log(err)
                })
            //this.$router.push({path: '/reservation'})
        },
        getAvailableRoomsCallback(res){
            let roomType = res.data
            console.log(roomType)
            $(this.$refs.submitBtn).removeClass('loading')
        }
    }
}
</script>

<style scoped>
div.input {
  display: block;
}

div.large.input.field > select, div.large.input.field > button{
  height: 43.11px;
}
</style>