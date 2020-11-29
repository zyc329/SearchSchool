import {axios} from '@/utils/request'

const api = {
  teacherFindPage: '/teacher/teacherFindPage',
  teacherAdd:'/teacher/teacherAdd',
  teacherUpdate:'/teacher/teacherUpdate',
  teacherDelete:'/teacher/teacherDelete'
}
export function teacherDelete(params) {
  return axios({
    url: api.teacherDelete,
    method: 'post',
    dataType: 'json',
    params: params
  })
}

export function teacherUpdate(params) {
  return axios({
    url: api.teacherUpdate,
    method: 'post',
    dataType: 'json',
    data: params
  })
}

export function teacherAdd(params) {
  return axios({
    url: api.teacherAdd,
    method: 'post',
    dataType: 'json',
    data: params
  })
}

export function teacherFindPage(params) {
  return axios({
    url: api.teacherFindPage,
    method: 'post',
    dataType: 'json',
    data: params
  })
}