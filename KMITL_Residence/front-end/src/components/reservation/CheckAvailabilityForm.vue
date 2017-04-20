<template>
<div>
    <FormErrorMsg :errors="errors.all()" />
    <form class="ui large form" @submit.prevent>
        <div class="field">
            <div class="fields">
                <div class="four input wide field">
                    <label>Check-in date</label>
                    <div class="ui calendar" ref="checkInDate">
                        <div class="ui input left icon">
                            <i class="calendar icon"></i>
                            <input type="text" placeholder="Choose check-in date" v-validate="'required|date_format:DD-MM-YYYY'" name="Check-in date" data-vv-name="Check-in date">
                        </div>
                    </div>
                </div>

                <div class="four input wide field">
                    <label>Check-out date</label>
                    <div class="ui calendar" ref="checkOutDate">
                        <div class="ui input left icon">
                            <i class="calendar icon"></i>
                            <input type="text" placeholder="Choose check-in date" v-validate="'required|date_format:DD-MM-YYYY|after:Check-in date'" name="checkOutDate" data-vv-name="Check-out date">
                        </div>
                    </div>
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
</div>
</template>

<script>
import moment from 'moment'
import FormErrorMsg from './FormErrorMsg'
import { Reservation } from '@/services'

export default {
    components: {
        FormErrorMsg
    },
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
        this.initialCalendarInput()
    },
    watch: {
      checkInDate(){
      },
      checkOutDate(){
      }
    },
    methods: {
        checkAvailability(){
            this.$validator.validateAll()
            if(this.errors.any()){
                return
            }
            $(this.$refs.submitBtn).addClass('loading')
            Reservation.availableSearch(this.checkInDate, this.checkOutDate, this.adults, this.children)
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
        },
        initialCalendarInput(){
            let today = new Date()
            $(this.$refs.checkInDate).calendar({
                type: 'date',
                minDate: new Date(today.getFullYear(), today.getMonth(), today.getDate()),
                formatter: {
                    date: function (date, settings) {
                        if (!date) return ''
                        let day = date.getDate()
                        let month = date.getMonth() + 1
                        let year = date.getFullYear()
                        return moment(`${day}/${month}/${year}`, 'D/M/YYYY').format('DD-MM-YYYY')
                    }
                },
                onChange: (date, text, mode) => {
                    this.checkInDate = moment(text, 'DD-MM-YYYY').format('YYYY-MM-DD')
                }
            })
            $(this.$refs.checkOutDate).calendar({
                type: 'date',
                minDate: new Date(today.getFullYear(), today.getMonth(), today.getDate()+1),
                formatter: {
                    date: function (date, settings) {
                        if (!date) return ''
                        let day = date.getDate()
                        let month = date.getMonth() + 1
                        let year = date.getFullYear()
                        return moment(`${day}/${month}/${year}`, 'D/M/YYYY').format('DD-MM-YYYY')
                    }
                },
                onChange: (date, text, mode) => {
                    this.checkOutDate = moment(text, 'DD-MM-YYYY').format('YYYY-MM-DD')
                }
            })
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