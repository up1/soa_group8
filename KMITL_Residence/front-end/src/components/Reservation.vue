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
                <transition name="fade" mode="out-in">
                    <CheckAvailabilitySection v-if="getCurrentStep() === 1"/>
                    <ChooseRoomTypeSection v-else-if="getCurrentStep() === 2"/>
                    <BasicInformationSection v-else-if="getCurrentStep() === 3"/>
                    <PaymentInformationSection v-else-if="getCurrentStep() === 4"/>
                    <ConfirmationSection v-else-if="getCurrentStep() === 5"/>
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
import BasicInformationSection from './BasicInformationSection'
import PaymentInformationSection from './PaymentInformationSection'
import ConfirmationSection from './ConfirmationSection'

export default {
    name: 'reservation',
    components: {
        ReservationProgress,
        CheckAvailabilitySection,
        ChooseRoomTypeSection,
        BasicInformationSection,
        PaymentInformationSection,
        ConfirmationSection
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
    .fade-transition {
    opacity: 1;
    transition: all .45s linear;
    }

    .fade-enter, .fade-leave {
    opacity: 0;
    }

</style>