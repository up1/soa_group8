<template>
    <div>
        <FormErrorMsg :errors="errors.all()" />
        <form class="ui large form" @submit.prevent>
            <div class="field">
                <div class="fields">
                    <div class="four wide field" :class="{'error':errors.has('card type')}">
                        <label>Card type</label>
                        <CardTypeSelect v-model="paymentInformation.creditCardType" v-validate="'required'" data-vv-name="card type"/>
                    </div>
                    <div class="nine wide field" :class="{'error': errors.has('card number')}">
                        <label>Card Number</label>
                        <CreditCardInput v-model="paymentInformation.creditCardId" v-validate="'required|credit_card'" data-vv-name="card number" :value="paymentInformation.creditCard" data-vv-validate-on="none" />
                    </div>
                    <div class="three wide field" :class="{'error': errors.has('cvv')}">
                        <label>CVC</label>
                        <input type="text" name="cvv" placeholder="CVC" v-validate="'required|numeric|min:3'" maxlength="3" v-model="paymentInformation.creditCardCvc" data-vv-validate-on="none" />
                    </div>
                </div>
                <div class="fields">
                    <div class="eight wide field" :class="{'error': errors.has('card holder\'s name')}">
                        <label>Card Holder's Name</label>
                        <input type="text" name="cardHolder" placeholder="Card Holder's Name" v-model="paymentInformation.creditCardHolder" v-validate="'required|alpha_spaces'" data-vv-name="card holder's name"/>
                    </div>
                    <div class="eight wide field" :class="{'error': errors.has('expired month')}">
                        <label> Expiration</label>
                        <div class="two fields">
                            <div class="field" :class="{'error': errors.has('expired month')}">
                                <select class="ui fluid selection dropdown" name="card[expire-month]" v-model="expiredMonth" v-validate="'required'" data-vv-name="expired month">
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
                            <div class="field" :class="{'error': errors.has('expired year')}">
                                <select name="year"class="ui fluid selection dropdown" v-model="expiredYear" v-validate="'required'" data-vv-name="expired year">
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
    </div>
</template>

<script>
import FormErrorMsg from './FormErrorMsg'
import CardTypeSelect from './CardTypeSelectBox'
import CreditCardInput from './CreditCardInput'

export default {
    data: () => ({
        expiredMonth: "",
        expiredYear: "",
        yearRange: [],
        paymentInformation: {
            creditCardId: '',
            creditCardExp: '',
            creditCardCvc: '',
            creditCardType: '',
            creditCardHolder: ''
        }
    }),
    components: {
        FormErrorMsg,
        CardTypeSelect,
        CreditCardInput
    },
    created () {
        let personalInformation = this.$store.getters.getPersonalInformation
        this.paymentInformation.creditCardHolder = personalInformation.firstName + " " + personalInformation.lastName
        this.yearRange = _.range(2014, 2100)
    },
    mounted () {
        $('.ui.selection.dropdown').dropdown()
        $(this.$refs.cardType).on('change', () => this.cardTypeChanged())
    },
    watch: {
        expiredMonth () {
            this.paymentInformation.creditCardExp = this.expiredMonth + "/" + this.expiredYear
        },
        expiredYear () {
            this.paymentInformation.creditCardExp = this.expiredMonth + "/" + this.expiredYear
        }
    },
    methods: {
        next(){
            this.$emit('input', this.paymentInformation)
            this.$validator.validateAll()
            if(!this.errors.any()){
                this.$emit('next')
            }
        },
        cardTypeChanged(){
            alert('card type changed')
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
