<template>
    <div>
        <div class="content-container extra-top-menubar-padding">
            <div class="card-padding">
                <reservation-progress/>
            </div>
        </div>

        <div class="content-container">
            <div class="card-padding">
                <div class="card padding-content normal-bottom-padding">
                <transition name="fade" mode="out-in">
                    <CheckAvailabilitySection v-if="getCurrentStep() === 1"/>
                    <ChooseRoomTypeSection v-else-if="getCurrentStep() === 2"/>
                    <PersonalInformationSection v-else-if="getCurrentStep() === 3"/>
                    <PaymentInformationSection v-else-if="getCurrentStep() === 4"/>
                    <ValidationSection v-else-if="getCurrentStep() === 5"/>
                </transition>
                </div>
            </div>
        </div>

    </div>
</template>

<script>

import ReservationProgress from './ReservationProgress'
import CheckAvailabilitySection from './CheckAvailabilitySection'
import ChooseRoomTypeSection from './ChooseRoomTypeSection'
import PersonalInformationSection from './PersonalInformationSection'
import PaymentInformationSection from './PaymentInformationSection'
import ValidationSection from './ValidationSection'

export default {
    name: 'reservation',
    components: {
        ReservationProgress,
        CheckAvailabilitySection,
        ChooseRoomTypeSection,
        PersonalInformationSection,
        PaymentInformationSection,
        ValidationSection
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

<style scoped>

</style>