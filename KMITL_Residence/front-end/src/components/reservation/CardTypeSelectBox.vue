<template>
    <div class="ui fluid selection dropdown">
        <input type="hidden" ref="cardType">
        <div class="default text">Type</div>
        <i class="dropdown icon"></i>
        <div class="menu">
            <div class="item" data-value="Visa">
                <i class="visa icon"></i>
                Visa
            </div>
            <div class="item" data-value="Mastercard">
                <i class="mastercard icon"></i>
                Mastercard
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['value'],
    data: () => ({
        cardType: ''
    }),
    created(){
        this.cardType = this.value
    },
    mounted(){
        $(this.$refs.cardType).on('change', () => {
            this.cardType = $(this.$refs.cardType).val()
            this.$emit('input', this.cardType)
        })
    }
}
</script>