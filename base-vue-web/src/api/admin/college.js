import {axios} from '@/utils/request'

const api = {
  collegeFindPage: '/college/collegeFindPage',
  collegeAdd:'/college/collegeAdd',
  collegeUpdate:'/college/collegeUpdate',
  collegeDelete:'/college/collegeDelete',
  collegeFindAll:'/college/collegeFindAll',
  collegeFindList:'/college/collegeFindList'
}

export function collegeFindList(parameter) {
  return axios({
    url: api.collegeFindList,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}

export function collegeFindAll() {
  return axios({
    url: api.collegeFindAll,
    method: 'post',
    dataType: 'json',
  })
}

export function collegeDelete(parameter) {
  return axios({
    url: api.collegeDelete,
    method: 'post',
    dataType: 'json',
    params: parameter,
  })
}

export function collegeUpdate(parameter) {
  return axios({
    url: api.collegeUpdate,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}

export function collegeAdd(parameter) {
  return axios({
    url: api.collegeAdd,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}

export function collegeFindPage(parameter) {
  return axios({
    url: api.collegeFindPage,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}
