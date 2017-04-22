<template>
  <div>
    <FormErrorMsg :errors="errors.all()" />
    <form class="ui large form" name="loginForm" @submit.prevent>
      <div class="field" :class="{'error':errors.has('Password')}">
        <label>Username</label>
        <input type="text" name="username" placeholder="Username" v-model="authenData.username" v-validate="'required'" data-vv-name="Username" data-vv-validate-on="none" >
      </div>
      <div class="field" :class="{'error':errors.has('Password')}">
        <label>Password</label>
        <input type="password" name="password" placeholder="Password" v-model="authenData.password" v-validate="'required'" data-vv-name="Password" data-vv-validate-on="none" >
      </div>
      <div class="ui center aligned">
        <button class="fluid ui blue large button" type="submit" @click="login" ref="loginBtn">Login</button>
      </div>
    </form>
  </div>
</template>

<script>

import { Validator } from 'vee-validate'
import FormErrorMsg from "@/components/reservation/FormErrorMsg"
import { User } from "@/services"

export default {
    components: {
        FormErrorMsg
    },
    data: () => ({
        authenData: {
            username: "",
            password: ""
        }
    }),
    methods: {
        login(){
            this.$validator.validateAll()
            if(!this.errors.any()){
                $(this.$refs.loginBtn).addClass("loading")
                this.$validator.validate('authen_data', this.authenData)
                    .then(res => {
                        $(this.$refs.loginBtn).removeClass("loading")
                        console.log(res)
                    })
            }
        }
    },
    created() {
        this.$validator.remove("authen_user")
        this.$validator.detach("authen_data")
        Validator.extend('authen_user', {
            getMessage: (field) => `Username or password is incorrect.`,
            validate: (value) => new Promise(resolve => {
                User.authen(value)
                    .then((res) => {
                        resolve({ 
                            valid : {
                                result: true
                            }
                        })
                    })
                    .catch((err) => {
                        reject({ valid : false })
                    })
            }) 
        })

        this.$validator.attach("authen_data", 'authen_user')
    }
}
</script>

<style scoped>
  button.fluid.ui.blue.large.button {
        padding: 1em !important;
        margin-top: 19px !important;
  }
</style>