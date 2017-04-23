<template>
<div>
    <FormErrorMsg :err="errors.all()"/>
    <form class="ui large form" @submit.prevent>
        <div class="field">
            <label>Name </label>
            <div class="fields">
                <div class="two wide field">
                    <select class="ui fluid search dropdown" v-model="personalInformation.titleName" value="Mr.">
                        <option value="Mr.">Mr.</option>
                        <option value="Mrs.">Mrs.</option>
                        <option value="Miss">Miss</option>
                    </select>
                </div>
                <div class="seven wide field" :class="{'error': errors.has('firstname')}">
                    <input v-validate="'required|alpha'" data-vv-validate-on="none" type="text" data-vv-name="firstname" placeholder="Firstname" v-model="personalInformation.firstName">
                </div>
                <div class="seven wide field" :class="{'error': errors.has('lastname')}">
                    <input v-validate="'required|alpha'" data-vv-validate-on="none" type="text" data-vv-name="lastname" placeholder="Lastname" v-model="personalInformation.lastName">
                </div>
            </div>
            <div class="two fields">
                <div class="field" :class="{'error': errors.has('nationality')}">
                    <label>Nationality</label>
                    <Nationality v-model="personalInformation.nation" v-validate="'required'" data-vv-name="nationality"/>
                </div>
                <div class="field" :class="{'error': errors.has('country')}">
                    <label>Country</label>
                    <Country v-model="personalInformation.country" v-validate="'required'" data-vv-name="country"/>
                </div>
            </div>
            <div class="two fields">
                <div :class="{'field': true, 'error':errors.has('email')}">
                    <label>E-mail</label>
                    <input  data-vv-name="email" v-validate="'required|email'" data-vv-validate-on="none" type="text" placeholder="Your e-mail" v-model="personalInformation.email">
                </div>
                <div class="field" :class="{'error': errors.has('phone number')}">
                    <label>Phone Number</label>
                    <input type="text" data-vv-name="phone number" placeholder="Your Phone Number" data-vv-validate-on="none" v-validate="'required|numeric|min:8|max:10'" v-model="personalInformation.tel" maxlength="10">
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
import Nationality from './NationalitySelectBox'
import Country from './CountrySelectBox'
import FormErrorMsg from './FormErrorMsg'

export default {
    data: () => ({
        personalInformation: {
            titleName: 'Mr.',
            firstName: '',
            lastName: '',
            email: '',
            tel: '',
            country: '',
            nation: ''
        }
    }),

    components: {
        Nationality,
        Country,
        FormErrorMsg
    },
    created () {
        
    },
    mounted () {
        $('select.dropdown').dropdown()
    },
    methods: {
        next() {
            this.personalInformation.firstName = _.capitalize(this.personalInformation.firstName)
            this.personalInformation.lastName = _.capitalize(this.personalInformation.lastName)
            this.$emit('input', this.personalInformation)
            this.$validator.validateAll()
            if(!this.errors.any()){
                this.$emit('next')
            }
        }
    }

}
</script>

<style scoped>
    button.fluid.ui.blue.large.button {
        padding: 1em !important;
        margin-top: 19px !important;
    }
</style>