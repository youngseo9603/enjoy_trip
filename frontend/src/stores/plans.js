import { createStore } from 'vuex';

const store = createStore({
	state() {
		return {
			plan: {
				planTitle: '',
			},
		};
	},
	mutations: {
		// setAccount(state, memberIndex) {
		// 	state.account.memberIndex = memberIndex;
		//     memberAPI.getNickName(
		//         	memberIndex,
		//         	({ data }) => {
		//         		 state.account.memberNickName =  data.data;
		//         	},
		//         	() => {
		//         		console.log("nickname 불러오기 실패");
		//         	},
		//         );
		// },
		// deleteAccount(state){
		//     state.account.memberIndex = 0;
		//     state.account.memberNickName = '';
		// }
	},
});

export default store;
