import { localAxios } from '../utils/http-commons';
const memberAPI = localAxios();

const getMember = (memberIndex, success, fail) => {
    memberAPI.post('/user/mypage',memberIndex).then(success).then(fail)
}

export default{getMember}