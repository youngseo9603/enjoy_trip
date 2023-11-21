import { localAxios } from '../utils/http-commons';
const memberAPI = localAxios();

const signup = (member, success, fail) => {
    memberAPI.post('/user/join', member).then(success).catch(fail);
}

const login = (LoginRequest, success, fail) => {
    memberAPI.post('/user/login', LoginRequest).then(success).catch(fail);
}

const getNickName = (memberIndex, success, fail) =>{
    console.log("memberIndex",memberIndex);
	memberAPI
		.get(`user/nickname?memberIndex=${memberIndex}`)
		.then(success)
		.catch(fail);
}

export default{signup, login, getNickName}