<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Payment Information
                <div class="sub header">Enter your payment information</div>
            </h1>
        </div>
        <div class="sixteen wide column">
            <PaymentInformationForm v-model="paymentInformation" @next="next"/>
        </div>
    </div>
</template>

<script>
import PaymentInformationForm from './PaymentInformationForm'

export default {
    data: () => ({
        paymentInformation: {

        }
    }),
    components: {
        PaymentInformationForm
    },
    methods: {
        next(){
            this.$store.dispatch('setPaymentInformation', this.paymentInformation)
            this.$store.dispatch('nextStep')
        }
    }
}
</script>