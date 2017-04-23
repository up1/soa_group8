import Vue from 'vue'
import Vuex from 'vuex'
import reservation from './modules/reservation'
import authentication from './modules/authentication'

Vue.use(Vuex)

const state = {

}

const mutations = {

}

const actions = {

}

const getters = {

}


export default new Vuex.Store({
    state,
    mutations,
    actions,
    getters,
    modules: {
        reservation,
        authentication
    }
})