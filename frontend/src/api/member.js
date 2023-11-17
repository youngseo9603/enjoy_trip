import { localAxios } from '../utils/http-commons';
const memberAPI = localAxios();

const signup = (member, success, fail) => {
    console.log(member)
    memberAPI.post('/user/join', member).then(success).catch(fail);
}

export default{signup}