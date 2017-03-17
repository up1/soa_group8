<template>
    <div class="content-container">
        <div class="ui secondary menu inverted">
            <a class="item logo">
                <div>KMITL</div>RESIDENCE
            </a>
            <div class="right menu">
                <a class="active item">
                    HOME
                </a>
                <a class="item">
                    RESERVATION
                </a>
                <a class="item">
                    ADMINISTRATOR
                </a>
            </div>
        </div>
    </div>
</template>

<script>

</script>

<style>

div.content-container {
    position: absolute;
    left:0;
    right:0;
    top: 45px;
}

div.ui.secondary.menu {
    font-family: 'Montserrat';
    font-weight: 700 !important;
    background-color: transparent;
}

div.ui.secondary.menu * {
    font-size: 12pt;
}

div.ui.secondary.menu.inverted .active.item {
    background-color: #FFFFFF;
    color: #000000 !important;
}

div.ui.secondary.menu.inverted .logo {
    font-size: 22px !important;
    color: rgba(255, 255, 255, .68) !important;
}

div.ui.secondary.menu.inverted .logo > div {
    color: white !important;
    font-size: 22px !important;
}

</style>