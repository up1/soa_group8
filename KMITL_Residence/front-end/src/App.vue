<template>
  <div id="app">
    <MenuBar/>
    <router-view></router-view>
  </div>
</template>

<script>
import MenuBar from './components/Menu'

export default {
  components: {
    MenuBar
  }
}
</script>

<style>

</style>
