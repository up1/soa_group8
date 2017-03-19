<template>
  <div>
    <div class="background-homepage-container">
      <div class="content-container side-padding-content hero-middle-vertical">
        <div class="ui grid">
          <div class="ten wide column">
            <h1>MAKE YOUR VACATION BE FULFILLED WITH HAPPINESS</h1>
          </div>
          <div class="six wide column">
          </div>
          <div class="sixteen wide column">
            <button class="ui white basic button radiusless">READ MORE</button>
          </div>
        </div>
      </div>
    </div>

    <div class="content-container padding-content">
      <div class="ui grid">
        <div class="sixteen wide column">
          <h1 class="ui center aligned header section-header">Check Availability</h1>
        </div>
        <div class="sixteen wide column">
          <reservation-form/>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import ReservationForm from './ReservationForm'

export default {
    name: 'home',
    components: {
      ReservationForm
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

div.hero-middle-vertical {
  min-height: 45vh;
  display: flex;
  align-items: center;
  vertical-align: middle;
}


</style>
