const state = {
    step: 1,
    totalAvailableRooms: [],
    data: {
        stayingInformation: {
            checkInDate: '',
            checkOutDate: '',
            adults: 1,
            children: 0,
            roomType: 0
        },
        personalInformation: {
            titleName: '',
            firstName: '',
            lastName: '',
            email: '',
            tel: '',
            country: '',
            nation: ''
        },
        paymentInformation: {
            creditCardId: '',
            creditCardExp: '',
            creditCardCvv: '',
            creditCardType: 0
        }
    }
}

const getters = {
    getCurrentStep: (state) => {
        return state.step
    },
    
    getAvailableRooms: (state) => state.totalAvailableRooms,

    getStayingInformation: (state) => state.data.stayingInformation,

    getPersonalInformation: (state) => state.data.personalInformation
}

const actions = {
    nextStep({commit, state}) {
        commit('nextStep')
    },

    prevStep({commit, state}) {
        commit('prevStep')
    },

    setStep({commit, state, dispatch}, val) {
        commit('setStep', val)
        if(val < 2){
            dispatch('clearStayingInformation')
            dispatch('clearTotalAvailableRooms')
        }
        if(val < 3){
            dispatch('clearPersonalInformation')
        }
        if(val < 4){
            dispatch('clearPaymentInformation')
        }
    },

    clearReservationState({commit, state, dispatch}) {
        dispatch('setStep', 1)
        dispatch('clearTotalAvailableRooms')
        dispatch('clearStayingInformation')
        dispatch('clearPersonalInformation')
        dispatch('clearPaymentInformation')
    },

    setTotalAvailableRooms({commit, state}, totalAvailableRooms){
        commit('setTotalAvailableRooms', totalAvailableRooms)
    },

    clearTotalAvailableRooms({commit, state, dispatch}) {
        dispatch('setTotalAvailableRooms', [])
    },

    setStayingInformation({commit, state}, stayingInformation){
        commit('setStayingInformation', stayingInformation)
    },

    clearStayingInformation({commit, state, dispatch}){
        dispatch('setStayingInformation', {
                checkInDate: '',
                checkOutDate: '',
                adults: 1,
                children: 0,
                roomType: 0
        })
    },

    setPersonalInformation({commit, state}, personalInformation){
        commit('setPersonalInformation', personalInformation)
    },

    clearPersonalInformation({commit, state, dispatch}){
        dispatch('setPersonalInformation', {
                titleName: '',
                firstName: '',
                lastName: '',
                email: '',
                tel: '',
                country: '',
                nation: ''
        })
    },

    setPaymentInformation({commit, state}, paymentInformation){
        commit('setPaymentInformation', paymentInformation)
    },

    clearPaymentInformation({commit, state, dispatch}){
        dispatch('setPaymentInformation', {
                creditCardId: '',
                creditCardExp: '',
                creditCardCvv: '',
                creditCardType: 0
        })
    }
}

const mutations = {
    nextStep(state) {
        state.step++
    },
    prevStep(state) {
        state.step--
    },
    setStep(state, val){
        state.step = val
    },

    setTotalAvailableRooms(state, totalAvailableRooms){
        state.totalAvailableRooms = totalAvailableRooms
    },

    setStayingInformation(state, stayingInformation){
        state.data.stayingInformation = stayingInformation
    },

    setPersonalInformation(state, personalInformation){
        state.data.personalInformation = personalInformation
    },

    setPaymentInformation(state, paymentInformation){
        state.data.paymentInformation = paymentInformation
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}