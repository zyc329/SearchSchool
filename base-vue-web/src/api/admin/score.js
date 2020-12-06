import {axios} from '@/utils/request'

const api = {
  scoreFindPage: '/score/scoreFindPage',
  scoreByColleges: '/score/scoreByColleges'
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

