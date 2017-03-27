<template>
    <div>
        <div class="content-container extra-top-menubar-padding" v-if="getCurrentStep() !== 6">
            <div class="card-padding">
                <reservation-progress/>
            </div>
        </div>

        <div class="content-container extra-top-menubar-padding">
            <div class="card-padding">
                <div class="card padding-content normal-bottom-padding">
                    <transition name="fade" mode="out-in">
                        <CheckAvailabilitySection v-if="getCurrentStep() === 1"/>
                        <ChooseRoomTypeSection v-else-if="getCurrentStep() === 2"/>
                        <PersonalInformationSection v-else-if="getCurrentStep() === 3"/>
                        <PaymentInformationSection v-else-if="getCurrentStep() === 4"/>
                        <ValidationSection v-else-if="getCurrentStep() === 5"/>
                        <ReservationSuccess v-else-if="getCurrentStep() === 6"/>
                    </transition>
                </div>
            </div>
        </div>

    </div>
</template>

<script>

import ReservationProgress from './reservation/ReservationProgress'
import CheckAvailabilitySection from './reservation/CheckAvailabilitySection'
import ChooseRoomTypeSection from './reservation/ChooseRoomTypeSection'
import PersonalInformationSection from './reservation/PersonalInformationSection'
import PaymentInformationSection from './reservation/PaymentInformationSection'
import ValidationSection from './reservation/ValidationSection'
import ReservationSuccess from './reservation/ReservationSuccess'

export default {
    name: 'reservation',
    components: {
        ReservationProgress,
        CheckAvailabilitySection,
        ChooseRoomTypeSection,
        PersonalInformationSection,
        PaymentInformationSection,
        ValidationSection,
        ReservationSuccess
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