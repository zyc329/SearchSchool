import {axios} from '@/utils/request'

const api = {
	userAdd: '/user/userAdd',
	userFindPage: '/user/userFindPage'
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
