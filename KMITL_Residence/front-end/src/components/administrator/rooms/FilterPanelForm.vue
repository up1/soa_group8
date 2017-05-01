<template>
    <form class="ui large form">
        <div class="two fields">
            <div class="field">
                <label>Availibility</label>
                <select class="ui fluid dropdown" v-model="roomsFilter.availabilityType">
                    <option value="9">All</option>
                    <option value="1">Available</option>
                    <option value="0">Not available</option>
                    <option value="-1">Under reconstruction</option>
                </select>
            </div>
            <div class="field">
                <label>
                    Type
                </label>
                <select class="ui fluid dropdown" v-model="roomsFilter.roomsType">
                    <option value="9">All</option>
                    <option value="1">Deluxe Room</option>
                    <option value="2">Premiere Room</option>
                    <option value="3">Suite Room</option>
                </select>
            </div>
        </div>
    </form>
</template>

<script>
export default {
    props: ['value'],
    data: () => ({
        roomsFilter: {
            availabilityType: 9,
            roomsType: 9
        }
    }),
    created(){
        this.roomsFilter.availabilityType = this.value.availabilityType
        this.roomsFilter.roomsType = this.value.roomsType
    },
    watch: {
        'roomsFilter.availabilityType': function(){
            this.$emit('input', this.roomsFilter)
        },
        'roomsFilter.roomsType': function(){
            this.$emit('input', this.roomsFilter)
        }
    }
}
</script>