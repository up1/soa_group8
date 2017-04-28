<template>
    <div class="content-container side-padding-content" ref="menucontainer" id="menuBar">
        <div class="ui secondary menu inverted">
            <a class="item logo">
                <div>KMITL</div>RESIDENCE
            </a>
            <div class="right menu">
                <router-link to="/" class="item" active-class="active" exact v-if="!isAuthenticated">HOME</router-link>
                <router-link to="/reservation" class="item" active-class="active" v-if="!isAuthenticated">RESERVATION</router-link>
                <router-link to="/administrator" class="item" active-class="active" v-if="!isAuthenticated">ADMINISTRATOR</router-link>
                <router-link to="/administrator/dashboard" class="item" active-class="active" exact v-if="isAuthenticated">DASHBOARD</router-link>
                <div class="ui dropdown item" v-if="isAuthenticated">
                    MANAGE
                    <i class="dropdown icon"></i>
                    <div class="ui vertical menu">
                        <a class="ui left pointing dropdown item">
                            <i class="dropdown icon"></i>
                                Customer
                            <div class="menu">
                                <router-link to="/administrator/customer/checkin" class="item">Check-in</router-link>
                                <router-link to="/administrator/customer/checkout" class="item">Check-out</router-link>
                                <router-link to="/administrator/customer/changeroom" class="item">Change Room</router-link>
                            </div>
                        </a>
                        <a class="item">Rooms</a>
                        <a class="item">Staff</a>
                    </div>
                </div>
                <div class="ui dropdown item" v-if="isAuthenticated" refs="userDropdownBtn">
                    {{ getUsername }}
                    <i class="dropdown icon"></i>
                    <div class="ui vertical menu">
                        <a class="item">Edit Profile</a>
                        <div class="divider"></div>
                        <a class="item" @click="logout">Log Out</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {

    data: () => ({
        activeStatus: {
            home: false,
            reservation: false
        }
    }),
    mounted(){
        this.changeMenuStyle()
        this.setActive()
    },
    updated () {
        if(this.$store.getters.getAuthenState){
            $('.ui.dropdown').dropdown()
        }
    },
    watch: {
        $route(){
            this.changeMenuStyle()
        }
    },

    methods: {
        setActive(){
            this.activeStatus.home = false
            this.activeStatus.reservation = false
            switch(this.getPath()){
                case "/": this.activeStatus.home = true
                            break
                case "/reservation": this.activeStatus.reservation = true
                       break
            }
        },
        getPath() {
            return this.$route.path
        },
        changeMenuStyle() {
            if(this.getPath() != "/"){
                $(this.$refs.menucontainer).addClass('inverted-menu')
            }else{
                $(this.$refs.menucontainer).removeClass('inverted-menu')
            }
        },
        logout(){
            this.$cookie.delete('_token')
            this.$store.dispatch('authenticationReset')
            this.$router.push('/')
        }
    },
    computed: {
        getUsername() {
            return this.$store.getters.getUserInfo.username.toUpperCase()
        },
        isAuthenticated() {
            return this.$store.getters.getAuthenState
        }
    }
}
</script>

<style scoped>

#menuBar {
    position: absolute;
    z-index: 1;
}

div.content-container {;
    left:0;
    right:0;
    height: 86.69px;
}

div.ui.secondary.menu {
    font-family: 'Montserrat';
    background-color: transparent;
    padding-top: 25px;
}

div.content-container.inverted-menu > div.ui.secondary.menu {
    padding-top:0px;
    left: 0;
    right: 0;
}

div.ui.secondary.menu * {
    font-size: 10pt;
}

div.ui.secondary.menu .item {
    border-radius: 0px;
    margin: 0px 20px;
    padding-left: 16.381px;
    padding-right: 16.381px; 
    transition: color .1s ease-in 0s, padding .15s ease-in .12s;
    font-weight: 500 !important;
}

div.ui.secondary.menu .item:first-child {
    margin-left: 0px !important;
}

div.ui.secondary.menu .item:last-child {
    margin-right: 1.619px !important;
}

div.ui.dropdown.item .menu {
    margin: 20px 20px;
}

div.menu.transition.hidden {
    margin: 4px 0px !important;
}

div.menu.transition {
    margin: 12px 0px !important;
}

div.ui.secondary.menu .active.item {
    border-radius: 0px;
}

div.ui.secondary.menu.inverted .item:hover {
    background-color: transparent;
}


div.ui.secondary.menu.inverted .active.item {
    background-color: #FFFFFF;
    color: #000000 !important;

}

.ui.inverted.menu .item{
    color: #C3C3C3 !important;
}

.ui.secondary.menu.inverted .ui.pointing.dropdown.item:hover {
    color: rgba(0, 0, 0, .95) !important;
}

div.content-container.inverted-menu > div.ui.secondary.menu .item.active {
    padding-top: 24.3525px;
    padding-bottom: 24.3525px;
}

div.ui.secondary.menu.inverted .logo {
    font-size: 24px !important;
    color: rgba(255, 255, 255, .68) !important;
    padding-left: 14px !important;
}


div.ui.secondary.menu.inverted .logo > div {
    color: white !important;
    font-size: 24px !important;
}

</style>