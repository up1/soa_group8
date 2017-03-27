<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Validation
                <div class="sub header">Please make sure that your reservation information is valid</div>
            </h1>
        </div>
        <div class="sixteen wide column">
            <h3 class="ui dividing header clear-bottom">
                Personal Information
            </h3>
            <table class="ui definition table">
                <tbody>
                    <tr>
                        <td class="three wide column">Name</td>
                        <td>{{ `${getReservationData.personalInformation.titleName} ${getReservationData.personalInformation.firstName} ${getReservationData.personalInformation.lastName}`}}</td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td>{{ getReservationData.personalInformation.email }}</td>
                    </tr>
                    <tr>
                        <td>Phone Number</td>
                        <td>{{ getReservationData.personalInformation.tel }}</td>
                    </tr>
                    <tr>
                        <td>Nation</td>
                        <td>{{ getReservationData.personalInformation.nation }}</td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td>{{ getReservationData.personalInformation.country }}</td>
                    </tr>
                </tbody>
            </table>

            <div class="ui divider hidden"></div>

            <h3 class="ui dividing header clear-bottom">
                Payment Information
            </h3>
            <table class="ui definition table">
                <tbody>
                    <tr>
                        <td class="three wide column">Credit Card ID</td>
                        <td>{{ getReservationData.paymentInformation.creditCardId | divideCCformat }}</td>
                    </tr>
                    <tr>
                        <td>Card Type</td>
                        <td>{{ getReservationData.paymentInformation.creditCardType | capitalize }}</td>
                    </tr>
                    <tr>
                        <td>CVC</td>
                        <td>{{ getReservationData.paymentInformation.creditCardCvc }}</td>
                    </tr>
                    <tr>
                        <td>Card Holder</td>
                        <td>{{ getReservationData.paymentInformation.creditCardHolder }}</td>
                    </tr>
                    <tr>
                        <td>Card Expiration</td>
                        <td>{{ getReservationData.paymentInformation.creditCardExp }}</td>
                    </tr>
                </tbody>
            </table>

            <div class="ui divider hidden"></div>

            <h3 class="ui dividing header clear-bottom">
                Staying Information
            </h3>
            <table class="ui definition table">
                <tbody>
                    <tr>
                        <td class="three wide column">Room Type</td>
                        <td>{{ getReservationData.stayingInformation.roomType | matchRoomType }}</td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td>{{ roomData.typePrice | THBformat }} / Night</td>
                    </tr>
                    <tr>
                        <td>Occupancy</td>
                        <td>{{ `${getReservationData.stayingInformation.adults} adults, ${getReservationData.stayingInformation.children} children` }}</td>
                    </tr>
                    <tr>
                        <td>Check-In Date</td>
                        <td>{{ getReservationData.stayingInformation.checkInDate | thaiFormatDate}}</td>
                    </tr>
                    <tr>
                        <td>Check-Out Date</td>
                        <td>{{ getReservationData.stayingInformation.checkOutDate | thaiFormatDate }}</td>
                    </tr>
                    <tr>
                        <td>Nights</td>
                        <td>{{ getTotalNights }}</td>
                    </tr>
                    <tr>
                        <td>Total Price</td>
                        <td>{{ roomData.typePrice * getTotalNights | THBformat }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="sixteen wide column">
                <button class="fluid ui blue big button" id="nextbtn" @click="next" ref="reserveBtn">Reserve</button>
        </div>
    </div>
</template>

<script>
import moment from 'moment'
import axios from 'axios'
import numeral from 'numeral'

export default {
    data: () => ({
        roomData: {}
    }),
    created () {
      this.getRoomTypeData()
    },
    computed: {
        getReservationData(){
            return this.$store.getters.getReservationData
        },
        getTotalNights(){
            let checkInDate = moment(this.getReservationData.stayingInformation.checkInDate, "YYYY-M-D")
            let checkOutDate = moment(this.getReservationData.stayingInformation.checkOutDate, "yyyy-M-D")
            return checkOutDate.diff(checkInDate, 'days')
        }
    },
    filters: {
        matchRoomType(val){
            let roomType = ["None", "Deluxe Room", "Premiere Room", "Suite Room"]
            return roomType[val]
        },
        THBformat(val){
            return numeral(+val).format('0,0.00') + ' THB'
        },
        thaiFormatDate(val){
            let momentDate = moment(val, "YYYY-M-D")
            return momentDate.format('DD-MM-YYYY')
        },
        capitalize(val) {
            return _.capitalize(val)
        },
        divideCCformat(val){
            return val.match(/.{1,4}/g).join(" ")
        }
    },
    methods: {
        getRoomTypeData(){
            let data = axios.get(`http://localhost:9001/rooms/type/${this.getReservationData.stayingInformation.roomType}`)
                .then((res) => this.setRoomData(res))
            return data
        },
        setRoomData(res){
            this.roomData = res.data
        },
        next(){
            $(this.$refs.reserveBtn).addClass('loading')
            let data = this.getReservationData
            let json = {
                 "checkIn": data.stayingInformation.checkInDate,
                "checkOut": data.stayingInformation.checkOutDate,
                "adults": data.stayingInformation.adults,
                "children": data.stayingInformation.children,
                "roomType": data.stayingInformation.roomType,
                "customer": {
                    "titleName": data.personalInformation.titleName,
                    "fullName": `${data.personalInformation.firstName} ${data.personalInformation.lastName}`,
                    "email": data.personalInformation.email,
                    "tel": data.personalInformation.tel,
                    "country": data.personalInformation.country,
                    "nation": data.personalInformation.nation
                },
                "creditCard": {
                    "number": data.paymentInformation.creditCardId,
                    "holderName": data.paymentInformation.creditCardHolder,
                    "type": data.paymentInformation.creditCardType,
                    "expiredDate": data.paymentInformation.creditCardExp,
                    "cvc": data.paymentInformation.creditCardCvc
                }
            }
            axios.post('http://localhost:9000/reservation/add', json)
                .then((res) => this.reserveCallback(res))
                .catch((err) => this.reserveErrorCallback(err))
            
        },
        reserveCallback(res){
            console.log(res)
            this.$store.dispatch('nextStep')
            this.$store.dispatch('clearReservationState')
            $(this.$refs.reserveBtn).removeClass('loading')
        },
        reserveErrorCallback(err){
            console.log(err)
            $(this.$refs.reserveBtn).removeClass('loading')
        }
    }
}
</script>