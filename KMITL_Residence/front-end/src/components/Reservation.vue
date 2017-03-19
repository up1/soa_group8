<template>
    <div>
        <div class="content-container extra-top-menubar-padding">
            <div class="card-padding">
                <div class="ui two steps">
                    <a class="active step">
                        <i class="truck icon"></i>
                        <div class="content">
                            <div class="title">Shipping</div>
                            <div class="description">Choose your shipping options</div>
                        </div>
                    </a>
                    <a class="step">
                        <i class="payment icon"></i>
                        <div class="content">
                            <div class="title">Billing</div>
                            <div class="description">Enter billing information</div>
                        </div>
                    </a>
                </div>
                
            </div>
                

        </div>
        <div class="content-container">
            <div class="card-padding">
                <div class="card padding-content">
                    <div class="ui grid">
                        <div class="sixteen wide column">
                            <h1 class="ui center aligned header section-header">RESERVATION</h1>
                        </div>
                        <div class="sixteen wide column">
                            <reservation-form/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ReservationForm from './ReservationForm'

export default {
    name: 'reservation',
    components: {
        ReservationForm
    }
}

</script>

<style>

</style>