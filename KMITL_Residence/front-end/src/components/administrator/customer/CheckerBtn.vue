<template>
    <div class="ui right floated large primary button" ref="btn" @click="showModal">
        {{ btnTxt }}
        <ChooseRoomModal :reservationData="reservationData" @refresh="refresh"/>
        <CheckOutModal :reservationData="reservationData" @refresh="refresh"/>
    </div>
</template>

<script>
import ChooseRoomModal from './ChooseRoomModal'
import CheckOutModal from './CheckOutModal'

export default {
    components: {
        ChooseRoomModal,
        CheckOutModal
    },
    props: ['mode', 'reservationData'],
    data: () => ({
        btnTxt: '',
        enable: true
    }),
    created(){
        this.setBtnTxt()
        this.setEnableState()
    },
    watch: {
        enable(){
            if(this.enable){
                $(this.$refs.btn).removeClass('disabled')
            }else{
                $(this.$refs.btn).addClass('disabled')
            }
        },
        mode(){
            this.setBtnTxt()
            this.setEnableState()
        },
        reservationData(){
            this.setEnableState()
        }
    },
    methods: {
        setBtnTxt(){
            let modes = ["checkin", "checkout", "edit"]
            let btnTxts = ["Check-In", "Check-Out", "Edit"]
            this.btnTxt = btnTxts[modes.indexOf(this.mode)]
        },
        setEnableState(){
            let nagativeStatus = ["waiting", "cancel"]
            if(nagativeStatus.every(x => this.reservationData.status != x)){
                this.enable = true
                if(this.mode == "checkout" && this.reservationData.checkInStatus == "no"){
                    this.enable = false
                }
                if(this.mode == "checkin" && this.reservationData.checkInStatus == "yes"){
                    this.enable = false
                }
            }else{
                this.enable = false
            }
        },
        showModal(){
            if(this.mode == 'checkin'){
                $('#chooseRoomModal').modal('show')
            }else if(this.mode == 'checkout'){
                $('#checkOutModal').modal('show')
            }
        },
        refresh(){
            this.$emit('refresh')
        }
    }
}
</script>