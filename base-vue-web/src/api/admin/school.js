import {axios} from '@/utils/request'

const api = {
 schoolFindPage:'/school/schoolFindPage'
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
