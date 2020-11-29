import {axios} from '@/utils/request'

const api = {
  professionalFindPage: '/professional/professionalFindPage',
  professionalUpdate:'/professional/professionalUpdate',
  professionalAdd:'/professional/professionalAdd',
  professionalDelete:'/professional/professionalDelete'
}
export function professionalDelete(params) {
  return axios({
    url: api.professionalDelete,
    method: 'post',
    dataType: 'json',
    params: params
  })
}

export function professionalAdd(params) {
  return axios({
    url: api.professionalAdd,
    method: 'post',
    dataType: 'json',
    data: params
  })
}

export function professionalUpdate(params) {
  return axios({
    url: api.professionalUpdate,
    method: 'post',
    dataType: 'json',
    data: params
  })
}

export function professionalFindPage(params) {
  return axios({
    url: api.professionalFindPage,
    method: 'post',
    dataType: 'json',
    data: params
  })
}