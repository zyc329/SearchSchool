import {axios} from '@/utils/request'

const api = {
	userAdd: '/user/userAdd',
	userFindPage: '/user/userFindPage',
	userUpdateStatus:'/user/userUpdateStatus',
	resetPW:'/user/resetPW',
	userDelete:'/user/userDelete',
	userUpdate:'/user/userUpdate'
}

export function userUpdate(parameter) {
	return axios({
		url: api.userUpdate,
		method: 'post',
		dataType: 'json',
		data: parameter,
	})
}

export function userDelete(parameter) {
	return axios({
		url: api.userDelete,
		method: 'post',
		dataType: 'json',
		data: parameter,
	})
}

export function resetPW(parameter) {
	return axios({
		url: api.resetPW,
		method: 'post',
		dataType: 'json',
		params: parameter,
	})
}

export function userAdd(parameter) {
	return axios({
		url: api.userAdd,
		method: 'post',
		dataType: 'json',
		data: parameter,
	})
}

export function userFindPage(parameter) {
	return axios({
		url: api.userFindPage,
		method: 'post',
		dataType: 'json',
		data: parameter,
	})
}

export function userUpdateStatus(parameter) {
	return axios({
		url: api.userUpdateStatus,
		method: 'post',
		dataType: 'json',
		data: parameter,
	})
}
