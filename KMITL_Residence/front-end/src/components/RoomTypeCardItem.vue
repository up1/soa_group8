<template>
    <div class="column">
        <div class="ui link card">
            <div class="ui fluid card">
                <div class="ui slide masked reveal image">
                    <img src="/images/avatar/large/jenny.jpg" class="visible content">
                    <img src="/images/avatar/large/elliot.jpg" class="hidden content">
                </div>
                <div class="content">
                    <div class="header">{{ data.typeNameEn }}</div>
                    <div class="description">
                        {{ data.typeDetailsTh}}
                    </div>
                </div>
                <div class="extra content">
                    <span>Price : {{ +data.typePrice | THBformat }}</span>
                    <span class="right floated">Night : {{ +getTotalNights | atLeastOne }}</span>
                </div>
                <div class="ui bottom attached button">
                    <i class="add icon"></i>
                        Book for {{ (+data.typePrice * +getTotalNights) | THBformat }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
import numeral from 'numeral'

export default {
    props: ['id'],
    data: () => ({
        data: {}
    }),
    mounted () {
        this.getRoomTypeData()
    },
    methods: {
        getRoomTypeData(){
            axios.get(`http://localhost:9001/rooms/type/${this.id}`)
                .then((res) => this.getRoomTypeDataCallback(res))
                .catch((err) => {
                    console.log(err)
                })
        },
        getRoomTypeDataCallback(res){
            this.data = res.data
        }
    },
    computed: {
        getTotalNights(){
            let checkInDate = moment(this.getStayingInformation.checkInDate, "YYYY-M-D")
            let checkOutDate = moment(this.getStayingInformation.checkOutDate, "yyyy-M-D")
            return checkOutDate.diff(checkInDate, 'days')
        },
        getStayingInformation(){
            return this.$store.getters.getStayingInformation
        }
    },
    filters: {
        atLeastOne(val){
            if(val < 1){
                return 1
            }
            return val
        },
        THBformat(val){
            return numeral(+val).format('0,0.00') + ' THB'
        }
    }

}
</script>
