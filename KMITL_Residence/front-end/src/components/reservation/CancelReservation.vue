<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header" v-if="success == 0">
                Loading..
            </h1>
            <h1 class="ui center aligned header section-header" v-else-if="success == 1">
                The Reservation has been cancelled!
                <div class="sub header">
                    Your reservation has been cancelled, anyway, We’d love to see you again next time. Have a nice day!
                </div>
            </h1>
            <h1 class="ui center aligned header section-header" v-else-if="success == 2" >
                Error !
                <div class="sub header">
                    {{ errMsg }}
                </div>
            </h1>
        </div>
        <div class="sixteen wide column" style="text-align:center;">
            <router-link to="/" class="ui center aligned link large">Go to Homepage</router-link>
        </div>
    </div>
</template>

<script>
import { Reservation } from '@/services'

export default {
    props: ['reservationId', 'hashId'],
    data: () => ({
        success: 0,
        errMsg: ''
    }),
    created() {
        Reservation.cancel(this.reservationId, this.hashId)
            .then(() => {
                this.success = 1
            }).catch((err) => {
                this.success = 2
                this.errMsg = err.response.data.message
            })
    }
}
</script>

