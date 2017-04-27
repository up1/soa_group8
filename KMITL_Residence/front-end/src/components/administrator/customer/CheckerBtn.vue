<template>
    <div class="ui right floated large primary button" ref="btn">
        {{ btnTxt }}
        <div class="ui basic modal">
            <div class="ui icon header">
                <i class="archive icon"></i>
                Archive Old Messages
            </div>
            <div class="content">
                <p>Your inbox is getting full, would you like us to enable automatic archiving of old messages?</p>
            </div>
            <div class="actions">
                <div class="ui red basic cancel inverted button">
                <i class="remove icon"></i>
                No
                </div>
                <div class="ui green ok inverted button">
                <i class="checkmark icon"></i>
                Yes
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
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
                    this.enabel = false
                }
            }else{
                this.enable = false
            }
        }
    }
}
</script>