<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Check Availability</h1>
        </div>
        <div class="sixteen wide column">
            <reservation-form/>
        </div>
    </div>
</template>

<script>
import ReservationForm from './ReservationForm'

export default {
    components: {
        ReservationForm
    }
}
</script>