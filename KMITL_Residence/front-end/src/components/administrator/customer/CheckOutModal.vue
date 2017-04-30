<template>
    <div class="ui small modal" id="checkOutModal">
        <i class="close icon"></i>
        <div class="header">
            Check the customer out
        </div>
        <div class="content">
            <div class="description">
                Are you sure you want to check this customer out ?
            </div>
        </div>
        <div class="actions">
            <div class="ui black deny button">
                No
            </div>
            <div class="ui primary positive right labeled icon button" @click="checkOut">
                Yes
                <i class="checkmark icon"></i>
            </div>
        </div>
    </div>
</template>

<script>
import { Rooms } from '@/services'

export default {
    props: ['reservationData'],
    beforeDestroy () {
        $(this.$el).remove()
    },
    methods: {
        checkOut(){
            Rooms.checkOut(this.reservationData.id, this.$cookie.get('_token'))
                .then(res => {
                    alert("Checkout success!")
                    $('#checkOutModal').modal('hide')
                })
                .catch(err => {
                    alert("Checkout failed")
                    console.log(err.response)
                    $('#checkOutModal').modal('hide')
                })
        }
    }
}
</script>

<style scoped>
    div.content > p {
        text-align: center;
    }
</style>