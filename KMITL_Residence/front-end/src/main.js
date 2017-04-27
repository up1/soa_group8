// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import '!script-loader!jquery'
import '!script-loader!semantic-ui/dist/semantic.min.js'
import '!script-loader!semantic-ui-calendar/dist/calendar.min.js'
import '!script-loader!formatter.js/dist/jquery.formatter.min.js'
import '!script-loader!lodash/lodash.min.js'
import '!style-loader!css-loader!semantic-ui/dist/semantic.min.css'
import '!style-loader!css-loader!semantic-ui-calendar/dist/calendar.min.css'
import '!style-loader!css-loader!./assets/css/custom.css'

import store from './store'
import App from './App'
import router from './router'

import moment from 'moment'
import VeeValidate, {Validator} from 'vee-validate'
import VueCookie from 'vue-cookie'

Vue.config.productionTip = false

Validator.installDateTimeValidators(moment);
Vue.use(VeeValidate)
Vue.use(VueCookie)

/* eslint-disable no-new */


Vue.filter('matchRoomType', (val) => {
    let roomType = ["None", "Deluxe Room", "Premiere Room", "Suite Room"]
    return roomType[val]
})

Vue.filter('confirmStatusInterpret', (status) => {
    let statusType = ['waiting', 'completed', 'cancel']
    let interpretList = ['Unconfirmed', 'Confirmed', 'Cancelled']
    return interpretList[statusType.indexOf(status)]
})

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})