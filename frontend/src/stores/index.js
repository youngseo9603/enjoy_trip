import { createStore } from 'vuex';
import memberAPI from '@/api/member.js';

const store = createStore({
	state() {
		return {
			account: {
				memberIndex: 0,
				memberNickName: '',
			},
			wholePlan :{
				planTitle:'',
				startDate:{},
				endDate : {},
				accomodation : '',
				planDay :[]
			}
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
		setPlanTitle(state, title){
			state.wholePlan.planTitle = title;
		},
		setAccomodation(state, accomodation){
			state.wholePlan.accomodation = accomodation;
		},
		setStartDate(state, startTime){
			state.wholePlan.startDate.year = startTime.year;
			state.wholePlan.startDate.month = startTime.month;
			state.wholePlan.startDate.day = startTime.day;
		},
		setEndDate(state, startTime){
			state.wholePlan.endDate.year = startTime.year;
			state.wholePlan.endDate.month = startTime.month;
			state.wholePlan.endDate.day = startTime.day;
		}
	},
});

export default store;
