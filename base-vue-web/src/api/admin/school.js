import {axios} from '@/utils/request'

const api = {
  schoolFindPage: '/school/schoolFindPage',
  schoolAdd: '/school/schoolAdd',
  schoolDelete:'/school/schoolDelete',
  schoolUpdate:'/school/schoolUpdate',
  schoolAll:'school/schoolAll'
}

export function schoolAll() {
  return axios({
    url: api.schoolAll,
    method: 'post',
    dataType: 'json',
  })
}

export function schoolUpdate(parameter) {
  return axios({
    url: api.schoolUpdate,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}

export function schoolDelete(parameter) {
  return axios({
    url: api.schoolDelete,
    method: 'post',
    dataType: 'json',
    params: parameter,
  })
}

export function schoolAdd(parameter) {
  return axios({
    url: api.schoolAdd,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}

export function schoolFindPage(parameter) {
  return axios({
    url: api.schoolFindPage,
    method: 'post',
    dataType: 'json',
    data: parameter,
  })
}

export function upload(parameter) {
  return axios({
    url: 'upload',
    method: 'post',
    data: parameter,
  })
}

export function deletePic(parameter) {
  return axios({
    url: 'deletePic',
    method: 'post',
    params: parameter,
  })
}
