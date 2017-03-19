<template>
    <div class="ui grid">
        <div class="sixteen wide column">
            <h1 class="ui center aligned header section-header">Choose Room Type</h1>
        </div>
        <RoomTypeCards/>
    </div>
</template>

<script>
import RoomTypeCards from './RoomTypeCard.vue'
export default {
    components: {
        RoomTypeCards
    }
}
</script>