<template>
    <div>
        <div class="content-container extra-top-menubar-padding">
            <div class="card-padding">
                <reservation-progress/>
            </div>
        </div>

        <div class="content-container">
            <div class="card-padding">
                <div class="card padding-content">
                    <CheckAvailabilitySection v-if="getCurrentStep() == 1"/>
                    <ChooseRoomTypeSection v-else-if="getCurrentStep() == 2"/>
                    <BasicInformationSection v-else-if="getCurrentStep() == 3"/>
                </div>
            </div>
        </div>

    </div>
</template>

<script>

import ReservationProgress from './ReservationProgress'
import CheckAvailabilitySection from './CheckAvailabilitySection'
import ChooseRoomTypeSection from './ChooseRoomTypeSection'
import BasicInformationSection from './BasicInformationSection'

export default {
    name: 'reservation',
    components: {
        ReservationProgress,
        CheckAvailabilitySection,
        ChooseRoomTypeSection,
        BasicInformationSection
    },
    beforeRouteLeave(to, from, next){
        this.$store.dispatch('clearReservationState')
        next()
    },
    methods: {
        getCurrentStep() {
            return this.$store.getters.getCurrentStep
        }
    }
}

</script>

<style>

</style>