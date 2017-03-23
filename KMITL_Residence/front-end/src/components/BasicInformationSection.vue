<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Basic Information
                <div class="sub header">Enter your personal information</div>
            </h1>
        </div>
        <div class="sixteen wide column">
            <BasicInformationForm v-model="personalInformation" @next="next"/>
        </div>
    </div>
</template>

<script>
import BasicInformationForm from './BasicInformationForm'

export default {
    data: () => ({
        personalInformation: {}
    }),
    components: {
        BasicInformationForm
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