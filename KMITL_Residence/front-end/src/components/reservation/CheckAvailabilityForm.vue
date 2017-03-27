<template>
<form class="ui large form" @submit.prevent>
    <div class="field">
        <div class="fields">
            <div class="four input wide field">
                <label>Check-in date</label>
                <input type="date" placeholder="Choose check-in date" :min="currentDate" v-model="checkInDate" id="checkInDate">
            </div>

            <div class="four input wide field">
                <label>Check-out date</label>
                <input type="date" placeholder="Choose check-out date" :min="currentDate" v-model="checkOutDate" id="checkOutDate">
            </div>

            <div class="two wide field">
                <label>Adults</label>
                <select class="ui fluid selection dropdown" v-model="adults" id="adults">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </div>

            <div class="two wide field">
                <label>Children</label>
                <select class="ui fluid selection dropdown" v-model="children" id="children">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                </select>
            </div>

            <div class="four wide field">
                <label>​&nbsp;</label>
                <button class="ui button fluid large luxury-primary" @click="checkAvailability" ref="submitBtn" id="checkAvailabilityBtn">
                    Check Availability
                </button>
            </div>
        </div>
    </div>
</form>
</template>

<script>
import axios from 'axios'
import moment from 'moment'

export default {
    data: () => ({
        checkInDate: '',
        checkOutDate: '',
        adults: 1,
        children: 0
    }),
    created () {
        let stayingInformation = this.getStayingInformation()
        this.checkInDate = stayingInformation.checkInDate
        this.checkOutDate = stayingInformation.checkOutDate
        this.adults = stayingInformation.adults
        this.children = stayingInformation.children

    },
    mounted () {
        $('select.dropdown').dropdown()
    },
    methods: {
        checkAvailability(){
            if(this.checkInDate == '' || this.checkOutDate ==''){
                return
            }
            $(this.$refs.submitBtn).addClass('loading')
            axios.get(`http://localhost:9000/reservation/availableSearch?checkin=${this.checkInDate}&checkout=${this.checkOutDate}&adults=${this.adults}&children=${this.children}`)
                .then((res) => this.getAvailableRoomsCallback(res))
                .catch(function(err){
                    console.log(err)
                })
        },
        getAvailableRoomsCallback(res){
            let totalAvailableRooms = res.data
            if(totalAvailableRooms[0].total > 0 || totalAvailableRooms[1].total > 0 || totalAvailableRooms[2].total > 0){
                this.$router.push({path: '/reservation'})
                this.$store.dispatch('nextStep')
                this.$store.dispatch('setTotalAvailableRooms', totalAvailableRooms)
                let stayingInformation = this.getStayingInformation()
                stayingInformation.checkInDate = this.checkInDate
                stayingInformation.checkOutDate = this.checkOutDate
                stayingInformation.adults = this.adults
                stayingInformation.children = this.children
                this.$store.dispatch('setStayingInformation', stayingInformation)
            }
            $(this.$refs.submitBtn).removeClass('loading')
        },
        getStayingInformation(){
            return this.$store.getters.getStayingInformation
        }
    },
    computed: {
        currentDate() {
            return moment().format('YYYY-MM-DD')
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