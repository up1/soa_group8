<template>
    <div class="sixteen wide column">
        <div class="ui three column grid">

            <RoomTypeCardItem/>
            <RoomTypeCardItem/>
            <RoomTypeCardItem/>
        </div>
    </div>
</template>

<script>

import RoomTypeCardItem from './RoomTypeCardItem'

export default {
    components: {
        RoomTypeCardItem
    }
}
</script>

<style scoped>

div.ui.three.column.grid {
    display: flex;
    align-items: center;
    justify-content: center !important;
}

</style>