<template>
    <div class="sixteen wide column">
        <div class="ui three column grid">
            <RoomTypeCardItem v-for="item in getTotalAvailableRooms" :key="item.roomType" :id="item.roomType"/>
        </div>
    </div>
</template>

<script>

import RoomTypeCardItem from './RoomTypeCardItem'

export default {
    components: {
        RoomTypeCardItem
    },
    computed: {
        getTotalAvailableRooms() {return this.$store.getters.getAvailableRooms}
    }
}
</script>

<style scoped>

div.ui.three.column.grid {
    display: flex;
    align-items: stretch;
    justify-content: center !important;
}


</style>