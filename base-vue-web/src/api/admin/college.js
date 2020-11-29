import {axios} from '@/utils/request'

const api = {
  collegeFindPage: '/college/collegeFindPage',
  collegeAdd:'/college/collegeAdd',
  collegeUpdate:'/college/collegeUpdate',
  collegeDelete:'/college/collegeDelete'
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
