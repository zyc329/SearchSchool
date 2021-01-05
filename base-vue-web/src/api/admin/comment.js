import {axios} from '@/utils/request'

const api = {
  commentsFindList: '/comments/commentsFindList',
  commentsAdd:'/comments/commentsAdd',
  commentsDelete:'/comments/commentsDelete'
}
export function commentsFindList(params) {
  return axios({
    url: api.commentsFindList,
    method: 'post',
    dataType: 'json',
    data:params
  })
}
export function commentsAdd(params) {
  return axios({
    url: api.commentsAdd,
    method: 'post',
    dataType: 'json',
    data: params
  })
}
export function commentsDelete(params) {
  return axios({
    url: api.commentsDelete,
    method: 'post',
    dataType: 'json',
    params: params
  })
}