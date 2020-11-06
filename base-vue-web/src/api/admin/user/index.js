import { axios } from '@/utils/request'
const api = {
  userAdd:'/user/userAdd'
}
export function userAdd(parameter) {
  return axios({
    url: api.userAdd,
    method: 'post',
    params: parameter,
  })
}
