<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Basic Information
                <div class="sub header">Enter your personal information</div>
            </h1>
        </div>
        <div class="sixteen wide column">
            <PersonalInformationForm v-model="personalInformation" @next="next"/>
        </div>
    </div>
</template>

<script>
import PersonalInformationForm from './PersonalInformationForm'

export default {
    data: () => ({
        personalInformation: {}
    }),
    components: {
        PersonalInformationForm
    },
    methods: {
        next(){
            this.$store.dispatch('setPersonalInformation', this.personalInformation)
            this.$store.dispatch('nextStep')
        }
    }


} 
</script>

<style>

</style>