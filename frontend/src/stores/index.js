import { createStore } from 'vuex';
import memberAPI from '@/api/member.js';

const store = createStore({
	state() {
		return {
			account: {
				memberIndex: 0,
				memberNickName: '',
			},
		};
	},
	mutations: {
		setAccount(state, memberIndex) {
			state.account.memberIndex = memberIndex;

			memberAPI.getNickName(
				memberIndex,
				({ data }) => {
					state.account.memberNickName = data.data;
				},
				() => {
					console.log('nickname 불러오기 실패');
				},
			);
		},
		deleteAccount(state) {
			state.account.memberIndex = 0;
			state.account.memberNickName = '';
		},
	},
});

export default store;
