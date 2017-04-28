<template>
    <div class="ui basic modal" id="checkoutModal">
        <div class="ui icon header">
            <i class="remove user icon"></i>
                Check-out the customer
        </div>
        <div class="content">
            <p>Are you sure to check-out this reservation ? Are the customer right there ?</p>
        </div>
        <div class="actions">
            <div class="ui red basic cancel inverted button">
            <i class="remove icon"></i>
            No
            </div>
            <div class="ui green ok inverted button">
            <i class="checkmark icon"></i>
            Yes
            </div>
        </div>
    </div>

</template>

<script>
export default {
    props: ['reservationData']
}
</script>

<style scoped>
    div.content > p {
        text-align: center;
    }
</style>