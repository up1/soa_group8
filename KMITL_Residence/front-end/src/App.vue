<template>
  <div id="app">
    <menu-bar/>
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
