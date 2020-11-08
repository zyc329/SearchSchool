import api from './index'
import { axios } from '@/utils/request'

/**
 * login func
 * parameter: {
 *     username: '',
 *     password: '',
 *     remember_me: true,
 *     captcha: '12345'
 * }
 * @param parameter
 * @returns {*}
 */
export function login(parameter) {
  return axios({
    url: '/userLogin/login',
    method: 'post',
    data: parameter,
  })
}

export function getSmsCaptcha(parameter) {
  return axios({
    url: api.SendSms,
    method: 'post',
    data: parameter,
  })
}

export function getInfo(token) {
  return axios({
    url: '/userLogin/getUserInfo',
    method: 'post',
    data: token,
  })
}

export function getCurrentUserNav(token) {
  return axios({
    url: '/user/nav',
    method: 'post',
    data: token,
  })
}

export function logout() {
  return axios({
    url: '/api/auth/logout',
    method: 'post',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    },
  })
}

/**
 * get user 2step code open?
 * @param parameter {*}
 */
export function get2step(parameter) {
  return axios({
    url: api.twoStepCode,
    method: 'post',
    data: parameter,
  })
}
