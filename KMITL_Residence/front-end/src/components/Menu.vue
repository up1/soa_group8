<template>
    <div class="content-container" ref="menucontainer">
        <div class="ui secondary menu inverted">
            <a class="item logo">
                <div>KMITL</div>RESIDENCE
            </a>
            <div class="right menu">
                <router-link to="/" class="item">HOME</router-link>

                <router-link to="/reservation" class="item">RESERVATION</router-link>
                <a class="item">
                    ADMINISTRATOR
                </a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    mounted(){
        this.changeMenuStyle()
    },

    watch: {
        $route(){
            this.changeMenuStyle()
        }
    },

    methods: {
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
    top: 25px;
}

div.ui.secondary.menu {
    font-family: 'Montserrat';
    background-color: transparent;
}

div.ui.secondary.menu * {
    font-size: 10pt;
}

div.ui.secondary.menu .item {
    border-radius: 0px;
    margin: 0px 20px;
    transition: color .1s ease-in 0s;
    font-weight: 500 !important;
}

div.ui.secondary.menu .item:first-child {
    margin-left: 0px;
}

div.ui.secondary.menu .item:last-child {
    margin-left: 0px;
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