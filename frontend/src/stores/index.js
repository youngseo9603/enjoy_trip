import { createStore } from "vuex";

const store =  createStore({
    state() {
        return {
            account: {
                memberIndex: 0
            }
        }
    },
    mutations: {
        setAccount(state, memberIndex) {
            state.account.memberIndex = memberIndex;
        }
    }
})

export default store;