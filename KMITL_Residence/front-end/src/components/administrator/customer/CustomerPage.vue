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
                                <div class="ui secondary pointing menu">
                                    <router-link to="/administrator/customer/checkin" class="item" active-class="active">Check-In</router-link>
                                    <router-link to="/administrator/customer/checkout" class="item" active-class="active">Check-Out</router-link>
                                    <router-link to="/administrator/customer/edit" class="item" active-class="active">Edit</router-link>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="sixteen wide column">
                                <div class="ui icon fluid input" ref="searchContainer">
                                    <input type="text" placeholder="Seach by Reservation ID" v-model="reservationId">
                                    <i class="search icon"></i>
                                </div>
                            </div>
                        </div>
                        <router-view/> 
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data: () => ({
        reservationId: ''
    }),
    watch: {
        reservationId(){
            $(this.$refs.searchContainer).addClass('loading')
            this.fetchJSON()
        }
    },
    methods: {
        fetchJSON: _.debounce(function(){
                $(this.$refs.searchContainer).removeClass('loading')
            }, 1000)
    }
}
</script>