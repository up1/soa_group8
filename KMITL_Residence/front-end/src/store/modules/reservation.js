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
    },
    clearReservationState({commit, state}) {
        commit('clearReservationState')
        commit('setTotalAvailableRooms', [])
    },
    setTotalAvailableRooms({commit, state}, totalAvailableRooms){
        commit('setTotalAvailableRooms', totalAvailableRooms)
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
    clearReservationState(state) {
        state.step = 1
        state.data = {
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
    },
    setTotalAvailableRooms(state, totalAvailableRooms){
        state.totalAvailableRooms = totalAvailableRooms
    } 

}

export default {
    state,
    getters,
    actions,
    mutations
}