<template>
  <div>
    <form class="ui large form" @submit.prevent>
      <div class="field">
        <label>Username</label>
        <input type="text" name="username" placeholder="Username">
      </div>
      <div class="field">
        <label>Password</label>
        <input type="password" name="password" placeholder="Password">
      </div>
      <div class="ui center aligned">
        <button class="fluid ui blue large button" type="submit">Login</button>
      </div>
    </form>
  </div>
</template>

<script>

</script>

<style scoped>
  button.fluid.ui.blue.large.button {
        padding: 1em !important;
        margin-top: 19px !important;
  }
</style>