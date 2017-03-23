<template>
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
            <div class="seven wide field">
                <input type="text" name="firstname" placeholder="Firstname" v-model="personalInformation.firstName">
            </div>
            <div class="seven wide field">
                <input type="text" name="lastname" placeholder="Lastname" v-model="personalInformation.lastName">
            </div>
        </div>
        <div class="two fields">
            <div class="field">
                <label>Nationality</label>
                <Nationality v-model="personalInformation.nation"/>
            </div>
            <div class="field">
                <label>Country</label>
                <Country v-model="personalInformation.country"/>
            </div>
        </div>
        <div class="two fields">
            <div class="field">
                <label>E-mail</label>
                <input type="text" name="email" placeholder="Your e-mail" v-model="personalInformation.email">
            </div>
            <div class="field">
                <label>Phone Number</label>
                <input type="text" name="tel" placeholder="Your Phone Number" v-model="personalInformation.tel">
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
import Nationality from './NationalitySelectBox'
import Country from './CountrySelectBox'

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
        Country
    },
    mounted () {
        $('select.dropdown').dropdown()
    },
    methods: {
        next() {
            this.$emit('input', this.personalInformation)
            this.$emit('next')
        }
    }

}
</script>

<style scoped>
    button.fluid.ui.blue.large.button {
        padding: 1em !important;
        margin-top: 15px !important;
    }
</style>