import { createStore } from 'vuex';
import memberAPI from '@/api/member.js';
import { ref } from 'vue';

const store = createStore({
	state() {
		return {
			account: {
				memberIndex: 0,
				memberNickName: '',
			},
			wholePlan: {
				planTitle: '',
				startDate: '',
				endDate: '',
				accomodation: '',
				planDay: [],
			},
			planList: {
				daysList: {},
				wishList: {},
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
		setPlanTitle(state, title) {
			state.wholePlan.planTitle = title;
		},
		setAccomodation(state, accomodation) {
			state.wholePlan.accomodation = accomodation;
		},
		setStartDate(state, startTime) {
			state.wholePlan.startDate = startTime;
		},
		setEndDate(state, startTime) {
			state.wholePlan.endDate = startTime;
		},
		setWishList(state, wishList) {
			console.log('wishlist  ', wishList);
			state.planList.wishList = wishList;
		},
		setDaysList(state, list) {
			console.log('list   ', list);
			state.planList.daysList = list;
		},
	},
});

export default store;
