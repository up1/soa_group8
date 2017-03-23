<template>
    <div class="ui five ordered steps">
        <a class="active step" ref="one" @click="setStep(1)">
            <div class="content">
                <div class="title">Check Availability</div>
            </div>
        </a>
        <a class="disabled step" ref="two" @click="setStep(2)">
            <div class="content">
                <div class="title">Choose Room Type</div>
            </div>
        </a>
        <a class="step disabled" ref="three" @click="setStep(3)">
            <div class="content">
                <div class="title">Basic Information</div>
            </div>
        </a>
        <a class="step disabled" ref="four" @click="setStep(4)">
            <div class="content">
                <div class="title">Payment Information</div>
            </div>
        </a>
        <a class="step disabled" ref="five" @click="setStep(5)">
            <div class="content">
                <div class="title">Confirmation</div>
            </div>
        </a>

    </div>
</template>

<script>
export default {
    computed: {
        getCurrentStep() {
            return this.$store.getters.getCurrentStep
        }
    },
    mounted () {
        this.setStepProgression()
    },
    watch : {
        getCurrentStep(){
            this.setStepProgression()
        }
    },
    methods: {
        setStepProgression() {
            let stepDoms = [$(this.$refs.one), $(this.$refs.two), $(this.$refs.three), $(this.$refs.four), $(this.$refs.five)]
            for(let i = 0; i < 5; i++){
                if (i < this.getCurrentStep - 1){
                    this.setCompletedClass(stepDoms[i])
                }else {
                    this.setDisabled(stepDoms[i])
                }
            }
            if (this.getCurrentStep < 6){
                this.setActiveClass(stepDoms[this.getCurrentStep - 1])
            }
        },
        setCompletedClass(dom) {
            dom.removeClass('active disabled').addClass('completed')
        },
        setActiveClass(dom) {
            dom.removeClass('disabled').addClass('active')
        },
        setDisabled(dom) {
            dom.removeClass('completed active').addClass('disabled')
        },
        setStep (step){
            this.$store.dispatch('setStep', step)
        }
    }
}
</script>