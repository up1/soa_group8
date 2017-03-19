<template>
    <div class="content-container side-padding-content" ref="menucontainer">
        <div class="ui secondary menu inverted">
            <a class="item logo">
                <div>KMITL</div>RESIDENCE
            </a>
            <div class="right menu">
                <router-link to="/" class="item" active-class="active" exact>HOME</router-link>

                <router-link to="/reservation" class="item" active-class="active">RESERVATION</router-link>
                <a class="item">
                    ADMINISTRATOR
                </a>
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
        }
    }
}
</script>

<style scoped>

div.content-container {
    position: absolute;
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
    transition: color .1s ease-in 0s, padding .15s ease-in .12s;
    font-weight: 500 !important;
}

div.ui.secondary.menu .item:first-child {
    margin-left: 0px;
}

div.ui.secondary.menu .item:last-child {
    margin-right: 1.619px;
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