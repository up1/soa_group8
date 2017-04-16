<template>
  <div class="ui raised centered card">
  	<div class="content">
  		<login-form/>
  	</div>
  </div>
</template>

<script>
import LoginForm from './LoginForm'
export default {
    components: {
        LoginForm
    }
}
</script>