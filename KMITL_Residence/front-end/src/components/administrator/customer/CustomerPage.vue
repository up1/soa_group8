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
                                    <input type="text" data-vv-name="Reservation Id" placeholder="Search by Reservation ID" v-model="reservInput" data-vv-validate-on="none" v-validate="'numeric'">
                                    <i class="search icon"></i>
                                </div>
                            </div>
                        </div>
                        <CustomerInfo :mode="mode" :reservationData="reservationData" :reservationId="reservationId" :loading="loading" @refresh="fetchJSON" :err="errors.all()"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CustomerInfo from './CustomerInfo'
import { Validator } from 'vee-validate'
import { Rooms } from '@/services'

export default {
    components: {
        CustomerInfo
    },
    data: () => ({
        reservInput: '',
        reservationId: '',
        reservationData: {},
        loading: false,
        mode: ''
    }),
    created(){
        this.mode = this.getMode()
        this.$validator.remove('reservationId_validate')
        this.$validator.detach('reservationId')
        Validator.extend('reservationId_validate', {
            getMessage: (field, params, data) => data.message,
            validate: (value) => new Promise(resolve => {
                Rooms.getCheckInInfo(value, this.$cookie.get('_token'))
                    .then((res) => {
                        let errMsg = ''
                        if(this.mode == 'checkin' && res.data.checkInStatus == 'yes'){
                            errMsg = 'This reservation has already checked-in'
                        } else if(this.mode == 'checkout' && res.data.checkInStatus == 'no'){
                            errMsg = 'This reservation hasn\'t checked-in yet'
                        } else if(res.data.status == 'waiting'){
                            errMsg = 'This reservation hasn\'t confirmed by customer yet'
                        } else if(res.data.status == 'cancel'){
                            errMsg = 'This reservation has cancelled by customer'
                        }
                        if(errMsg == ''){
                            this.reservationData = res.data
                            resolve({
                                valid: true
                            })
                        } else {
                            this.reservationData = {}
                            resolve({
                                valid: false,
                                data: {
                                    message: errMsg
                                }
                            })
                        }
                    })
                    .catch(err => {
                        this.reservationData = {}
                        resolve({
                            valid : false,
                            data: {
                                message: 'Reservation ID not found.'
                            }
                        })
                    })
            })
        })
        this.$validator.attach('reservationId', 'reservationId_validate')
    },
    watch: {
        reservInput(){
            $(this.$refs.searchContainer).addClass('loading')
            this.loading = true
            this.fetchJSON()
        },
        '$route': function(){
            this.mode = this.getMode()
        },
        mode(){
            this.reservationData = {}
            this.loading = true
            this.fetchJSON()
        }
    },
    methods: {
        fetchJSON: _.debounce(function(){
                this.$validator.validateAll()
                this.reservationId = this.reservInput
                this.$validator.validate('reservationId', this.reservationId)
                    .then(res => {
                        $(this.$refs.searchContainer).removeClass('loading')
                        this.loading = false
                    })
            }, 1000),
        getMode(){
            let splitedPath = this.$route.path.split('/')
            return splitedPath[splitedPath.length - 1]
        }
    }
}
</script>