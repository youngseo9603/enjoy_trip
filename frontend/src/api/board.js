import { localAxios } from '../utils/http-commons';

const boardAPI = localAxios();
const getList = (sucess, fail) => {
    boardAPI.get('/board/list').then(sucess).catch(fail);
}


export default{getList}