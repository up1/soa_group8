const state = {
    step: 0,
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

}

const actions = {

}

const mutations = {


}

export default {
    state,
    getters,
    actions,
    mutations
}