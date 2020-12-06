import {axios} from '@/utils/request'

const api = {
  scoreFindPage: '/score/scoreFindPage'
}

export function scoreFindPage(params) {
  return axios({
    url: api.scoreFindPage,
    method: 'post',
    dataType: 'json',
    data: params
  })
}
