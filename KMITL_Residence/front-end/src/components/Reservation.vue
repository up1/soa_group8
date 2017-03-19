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
                    <CheckAvailabilitySection/>
                </div>
            </div>
        </div>

        <div class="content-container">
            <div class="card-padding">
                <div class="card padding-content">
                    <ChooseRoomTypeSection/>
                </div>
            </div>
        </div>


    </div>
</template>

<script>

import ReservationProgress from './ReservationProgress'
import CheckAvailabilitySection from './CheckAvailabilitySection'
import ChooseRoomTypeSection from './ChooseRoomTypeSection'

export default {
    name: 'reservation',
    components: {
        ReservationProgress,
        CheckAvailabilitySection,
        ChooseRoomTypeSection
    }
}

</script>

<style>

</style>