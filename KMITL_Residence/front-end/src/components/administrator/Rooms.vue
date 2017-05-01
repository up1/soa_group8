<template>
    <div>
        <div class="content-container extra-top-menubar-padding">
            <div class="card-padding">
                <div class="normal-padding">
                    <div class="ui grid">
                        <div class="sixteen wide column">
                            <h1 class="ui header">
                                Manage Rooms
                            </h1>
                        </div>
                        <div class="row filter-panel">
                            <div class="sixteen wide column">
                                <FilterPanel v-model="roomsFilter"/>
                            </div>
                        </div>
                        <RoomsTable :roomsFilter="roomsFilter"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>


<script>
import FilterPanel from './rooms/FilterPanelForm'
import RoomsTable from './rooms/RoomsTable'

export default {
    data: () => ({
        roomsFilter: {
            availabilityType: 9,
            roomsType: 9
        }
    }),
    components: {
        FilterPanel,
        RoomsTable
    },
    mounted () {
        $('select.dropdown').dropdown()
    }
}
</script>

<style scoped>
.filter-panel {
    padding-top: 24px !important;
}
</style>