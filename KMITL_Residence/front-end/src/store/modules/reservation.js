const state = {
    step: 1,
    totalAvailableRooms: [],
    data: {
        stayingInformation: {
            checkInDate: '',
            checkOutDate: '',
            adults: 0,
            children: 0,
            roomType: 0
        },
        personalInformation: {
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
    }
}

const actions = {
    nextStep({commit, state}) {
        commit('nextStep')
    },
    prevStep({commit, state}) {
        commit('prevStep')
    },
    setStep({commit, state}, val) {
        commit('setStep', val)
        if(val < 2){
            commit('clearStayingInformation')
        }
        if(val < 3){
            commit('clearPersonalInformation')
        }
        if(val < 4){
            commit('clearPaymentInformation')
        }
    },
    clearReservationState({commit, state, dispatch}) {
        dispatch('setStep', 1)
        commit('setTotalAvailableRooms', [])
    },
    setTotalAvailableRooms({commit, state}, totalAvailableRooms){
        commit('setTotalAvailableRooms', totalAvailableRooms)
    },
    setStayingInformation({commit, state}, stayingInformation){
        commit('setStayingInformation', stayingInformation)
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
    },

    clearStayingInformation(state){
        state.data.stayingInformation = {
                checkInDate: '',
                checkOutDate: '',
                adults: 0,
                children: 0,
                roomType: 0
        }
    },

    clearPersonalInformation(state){
        state.data.stayingInformation = {
                firstName: '',
                lastName: '',
                email: '',
                tel: '',
                country: '',
                nation: ''
        }
    },

    clearPaymentInformation(state) {
        state.data.paymentInformation = {
                creditCardId: '',
                creditCardExp: '',
                creditCardCvv: '',
                creditCardType: 0
        }
    }

}

export default {
    state,
    getters,
    actions,
    mutations
}