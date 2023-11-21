import { localAxios } from '../utils/http-commons';
const boardAPI = localAxios();
const getList = (sucess, fail) => {
	boardAPI.get('/board/list').then(sucess).catch(fail);
};

const getDetailBoard = (boardIndex, success, fail) => {
	boardAPI.get(`/board/${boardIndex}`).then(success).catch(fail);
};

const removeBoard = (boardIndex, success, fail) => {
	boardAPI.delete(`/board/${boardIndex}`).then(success).catch(fail);
};

const updateBoard = (board, success, fail) => {
	board.createTime = new Date();
	boardAPI.put(`/board/${board.boardIndex}`, board).then(success).catch(fail);
};

const searchBoards = (query, success, fail) => {
	boardAPI.get(`/board/search?query=${query}`).then(success).catch(fail);
};

const registerBoard = (board, success, fail) =>{
	boardAPI
		.post(
			'/board/register', board
		)
		.then(success)
		.catch(fail);
};
export default {
	getList,
	getDetailBoard,
	removeBoard,
	updateBoard,
	searchBoards,
	registerBoard,
};
