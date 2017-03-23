<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Check Availability
                <div class="sub header">Enter staying information</div>
            </h1>
        </div>
        <div class="sixteen wide column">
            <check-availability-form/>
        </div>
    </div>
</template>

<script>
import CheckAvailabilityForm from './CheckAvailabilityForm'

export default {
    components: {
        CheckAvailabilityForm
    }
}
</script>