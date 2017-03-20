// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import '!script-loader!jquery'
import '!script-loader!semantic-ui/dist/semantic.min.js'
import '!style-loader!css-loader!semantic-ui/dist/semantic.min.css'
import '!style-loader!css-loader!./assets/css/custom.css'

import store from './store'
import App from './App'
import router from './router'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
