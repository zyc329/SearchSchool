import {axios} from '@/utils/request'

const api = {
  scoreFindPage: '/score/scoreFindPage',
  scoreByColleges: '/score/scoreByColleges',
  scoreAdd:'/score/scoreAdd',
  scoreUpdate:'/score/scoreUpdate',
  scoreDelete:'/score/scoreDelete'
}
export function scoreDelete(params) {
  return axios({
    url: api.scoreDelete,
    method: 'post',
    dataType: 'json',
    params: params
  })
}
export function scoreAdd(params) {
  return axios({
    url: api.scoreAdd,
    method: 'post',
    dataType: 'json',
    data: params
  })
}
export function scoreUpdate(params) {
  return axios({
    url: api.scoreUpdate,
    method: 'post',
    dataType: 'json',
    data: params
  })
}
export function scoreFindPage(params) {
  return axios({
    url: api.scoreFindPage,
    method: 'post',
    dataType: 'json',
    data: params
  })
}
export function scoreByColleges(params) {
  return axios({
    url: api.scoreByColleges,
    method: 'post',
    dataType: 'json',
    params: params
  })
}

