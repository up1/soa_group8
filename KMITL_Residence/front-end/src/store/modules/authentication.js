const state = {
    isAuthen: false,
    userInfo: {
        username: '',
        role: ''
    }
    
}

const getters = {
    getAuthenState: (state) => state.isAuthen,
    getUserInfo: (state) => state.getUserInfo
}

const actions = {
    setAuthenState({commit}, val){
        commit('setAuthenState', val)
    },

    setAuthenticated({commit, dispatch}){
        dispatch('setAuthenState', true)
    },

    setUnauthenticated({commit, dispatch}){
        dispatch('setAuthenState', false)
    },

    setUserInfo({commit}, val){
        commit('setUserInfo', val)
    },

    reset({dispatch}){
        dispatch('setUnauthenticated')
        dispatch('setUserInfo', {
            username: '',
            role: ''
        })
    }
}

const mutations = {
    setAuthenState(state, val){
        state.isAuthen = val
    },

    setUserInfo(state, val){
        state.userInfo = val
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}