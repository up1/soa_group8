<template>
    <div class="sixteen wide column">
        <div class="card card-padding normal-padding">
            <h3 class="ui center aligned header" v-if="err.length || !reservationId">
                {{ headerText }}
                <div class="sub header">
                    {{ statusText }}
                </div>
            </h3>
            <div v-else-if="reservationData.customer">
                <h3 class="ui dividing header clear-bottom">
                    Reservation Information
                </h3>
                <table class="ui definition table">
                    <tbody>
                        <tr>
                            <td class="three wide column">Name</td>
                            <td> {{ `${reservationData.customer.titleName} ${reservationData.customer.fullName}` }}</td>
                        </tr>
                        <tr>
                            <td>E-mail</td>
                            <td> {{ reservationData.customer.email }}</td>
                        </tr>
                        <tr>
                            <td>Phone Number</td>
                            <td> {{ reservationData.customer.tel }} </td>
                        </tr>
                        <tr>
                            <td>Nation</td>
                            <td> {{ reservationData.customer.nation }} </td>
                        </tr>
                        <tr>
                            <td>Country</td>
                            <td> {{ reservationData.customer.country }} </td>
                        </tr>
                        <tr>
                            <td>Check-in date</td>
                            <td> {{ reservationData.checkIn }}</td>
                        </tr>
                        <tr>
                            <td>Check-out date</td>
                            <td> {{ reservationData.checkOut}} </td>
                        </tr>
                        <tr>
                            <td>Room Type</td>
                            <td>{{ reservationData.roomType | matchRoomType}}</td>
                        </tr>
                        <tr>
                            <td>Occupancy</td>
                            <td> {{ `${reservationData.total} Persons` }} </td>
                        </tr>
                        <tr>
                            <td>Confirmation Status</td>
                            <td> {{ reservationData.status | confirmStatusInterpret }} </td>
                        </tr>
                    </tbody>
                    <tfoot class="full-width">
                        <th colspan="2">
                            <CheckerBtn :mode="mode" :reservationData="reservationData" @refresh="refresh"/>
                            <div class="ui right floated large button" @click="refresh" >
                                     Refresh
                            </div>
                        </th>
                    </tfoot>
                </table>
            </div>                
        </div>
    </div>
</template>

<script>
import CheckerBtn from './CheckerBtn'

export default {
    components: {
        CheckerBtn
    },
    props: ['mode', 'err', 'reservationId', 'reservationData'],
    data: () => ({
        headerText: 'Please enter the reservation ID',
        statusText: 'To check the customer in, the reservation ID is required.'
    }),
    watch: {
        mode(){
            this.setStatusText()
        },
        err(){ 
            if(this.err.length > 0) {
                this.headerText = 'Unfortunately, We\'ve got an error!'
                this.statusText = this.err[0]
            } else {
                this.setStatusText()
            }
        }
    },
    methods: {
        setStatusText() {
            let modes = ['checkin', 'checkout', 'edit']
            let statusText = ['To check the customer in, the reservation ID is required.',
                'To check the customer out, the reservation ID is required.',
                'To edit the customer information, the reservation ID is required.'
            ]
            this.headerText = "Please enter the reservation ID"
            this.statusText = statusText[modes.indexOf(this.mode)]
        },
        refresh(){
            this.$emit('refresh')
        }
    }
}
</script>