<template>
    <input type="text" name="CardNumber" placeholder="Card Number" ref="cardnumber">
</template>

<script>
export default {
    props: ['value'],
    data: () => ({
        cardNumber: ''
    }),
    created () {
        this.cardNumber = this.value
    },
    mounted () {
        let cardNumberInput = $(this.$refs.cardnumber)
        cardNumberInput.val(this.cardNumber)
        cardNumberInput.on('keyup', () => this.cardNumberInputChanged())
        cardNumberInput.formatter({
            'pattern': '{{9999}} {{9999}} {{9999}} {{9999}}'
        })
    },
    methods: {
        cardNumberInputChanged(){
            let txt = $(this.$refs.cardnumber).val().split(" ").join("")
            this.cardNumber = txt
            this.$emit('input', txt)
        },
    }
}
</script>