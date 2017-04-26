<template>
    <div>
        <div class="content-container extra-top-menubar-padding">
            <div class="card-padding">
                <div class="normal-padding">
                    <div class="ui grid">
                        <div class="sixteen wide column">
                            <h1 class="ui header">
                                Manage Customer
                            </h1>
                        </div>
                        <div class="row">
                            <div class="sixteen wide column">
                                <div class="ui large secondary pointing menu">
                                    <router-link to="/administrator/customer/checkin" class="item" active-class="active">Check-In</router-link>
                                    <router-link to="/administrator/customer/checkout" class="item" active-class="active">Check-Out</router-link>
                                    <router-link to="/administrator/customer/edit" class="item" active-class="active">Edit</router-link>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="sixteen wide column">
                                <div class="ui large icon fluid input" ref="searchContainer">
                                    <input type="text" data-vv-name="Reservation Id" placeholder="Seach by Reservation ID" v-model="reservInput" data-vv-validate-on="none" v-validate="'numeric'">
                                    <i class="search icon"></i>
                                </div>
                            </div>
                        </div>
                        <CustomerInfo :mode="mode" :reservationId="reservationId" :err="errors.all()"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CustomerInfo from './CustomerInfo'

export default {
    components: {
        CustomerInfo
    },
    data: () => ({
        reservInput: '',
        reservationId: '',
        mode: ''
    }),
    created(){
        this.mode = this.getMode()
    },
    watch: {
        reservInput(){
            $(this.$refs.searchContainer).addClass('loading')
            this.fetchJSON()
        },
        '$route': function(){
            this.mode = this.getMode()
        }
    },
    methods: {
        fetchJSON: _.debounce(function(){
                $(this.$refs.searchContainer).removeClass('loading')
                this.$validator.validateAll()
                this.reservationId = this.reservInput
            }, 1000),
        getMode(){
            let splitedPath = this.$route.path.split('/')
            return splitedPath[splitedPath.length - 1]
        }
    }
}
</script>