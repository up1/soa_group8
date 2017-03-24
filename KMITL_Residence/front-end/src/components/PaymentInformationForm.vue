<template>
    <form class="ui large form" @submit.prevent>
        <div class="field">
            <div class="fields">
                <div class="four wide field">
                    <label>Card type</label>
                    <div class="ui fluid selection dropdown">
                        <input type="hidden" name="card[type]" ref="cardType">
                        <div class="default text">Type</div>
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <div class="item" data-value="visa">
                                <i class="visa icon"></i>
                                Visa
                            </div>
                            <div class="item" data-value="mastercard">
                                <i class="mastercard icon"></i>
                                Mastercard
                            </div>
                        </div>
                    </div>
                </div>
                <div class="nine wide field">
                    <label>Card Number</label>
                    <input type="text" name="CardNumber" placeholder="Card Number" ref="cardnumber">
                </div>
                <div class="three wide field">
                    <label>CVC</label>
                    <input type="text" name="cvv" placeholder="CVC" maxlength="3" v-model="paymentInformation.creditCardCvc">
                </div>
            </div>
            <div class="fields">
                <div class="eight wide field">
                    <label>Card Holder's Name</label>
                    <input type="text" name="cardHolder" placeholder="Card Holder's Name" v-model="paymentInformation.creditCardHolder">
                </div>
                <div class="eight wide field">
                    <label> Expiration</label>
                    <div class="two fields">
                        <div class="field">
                            <select class="ui fluid selection dropdown" name="card[expire-month]" v-model="expiredMonth">
                                <option value="">Month</option>
                                <option value="1">January</option>
                                <option value="2">February</option>
                                <option value="3">March</option>
                                <option value="4">April</option>
                                <option value="5">May</option>
                                <option value="6">June</option>
                                <option value="7">July</option>
                                <option value="8">August</option>
                                <option value="9">September</option>
                                <option value="10">October</option>
                                <option value="11">November</option>
                                <option value="12">December</option>
                            </select>
                        </div>
                        <div class="field">
                            <select name="year"class="ui fluid selection dropdown" v-model="expiredYear">
                                <option value="">Year</option>
                                <option :value="y" v-for="y in yearRange">{{ y }} </option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="fields">
                <div class="sixteen wide field">
                    <button class="fluid ui blue large button" id="nextbtn" @click="next">Next</button>
                </div>
            </div>
        </div>
    </form>
</template>

<script>

export default {
    data: () => ({
        cardNumber: "",
        expiredMonth: "",
        expiredYear: "",
        cardType: "",
        yearRange: [],
        paymentInformation: {
            creditCardId: '',
            creditCardExp: '',
            creditCardCvc: '',
            creditCardType: '',
            creditCardHolder: ''
        }
    }),
    created () {
        let personalInformation = this.$store.getters.getPersonalInformation
        this.paymentInformation.creditCardHolder = personalInformation.firstName + " " + personalInformation.lastName
        this.yearRange = _.range(2014, 2100)
    },
    mounted () {
        $('.ui.selection.dropdown').dropdown()
        let cardNumberInput = $(this.$refs.cardnumber)
        cardNumberInput.on('keyup', () => this.cardNumberInputChanged())
        cardNumberInput.formatter({
            'pattern': '{{9999}} {{9999}} {{9999}} {{9999}}'
        })
        $(this.$refs.cardType).on('change', () => this.cardTypeChanged())
    },
    watch: {
        expiredMonth () {
            this.paymentInformation.creditCardExp = this.expiredMonth + "/" + this.expiredYear
        },
        expiredYear () {
            this.paymentInformation.creditCardExp = this.expiredMonth + "/" + this.expiredYear
        },
        cardNumber(){
            this.paymentInformation.creditCardId = this.cardNumber
        },
        cardType(){
            this.paymentInformation.creditCardType = this.cardType
        }
    },
    methods: {
        cardNumberInputChanged(){
            let txt = $(this.$refs.cardnumber).val().split(" ").join("")
            this.cardNumber = txt
        },
        cardTypeChanged(){
            let cardType = $(this.$refs.cardType).val()
            this.cardType = cardType
        },
        next(){
            this.$emit('input', this.paymentInformation)
            this.$emit('next')
        }
    }
}
</script>

<style scoped>
    button.fluid.ui.blue.large.button {
        padding: 1em !important;
        margin-top: 19px !important;
    }
</style>>
