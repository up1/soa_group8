<template>
    <div class="sixteen wide column">
        <div class="ui " ref="cardContainer">
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
    },
    mounted () {
        let length = ''
        switch(this.getTotalAvailableRooms.length){
            case 1: length = 'one'
                    break
            case 2: length = 'two'
                    break
            case 3: length = 'three'
                    break
        }
        $(this.$refs.cardContainer).addClass(length+" column grid")
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