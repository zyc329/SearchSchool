import request from '@/utils/request' // 引入封装好的 axios 请求
const userApi = {
    demo: '/api/text',
    Login: '/api/auth/login',
    Logout: '/api/auth/logout',
    ForgePassword: '/api/auth/forge-password',
    Register: '/api/auth/register',
    twoStepCode: '/api/auth/2step-code',
    SendSms: '/api/account/sms',
    SendSmsErr: '/api/account/sms_err',
    // get my info
    UserInfo: '/api/user/info',
    UserMenu: '/api/user/nav'
}

export function loginText(params) { // 测试
    debugger
    return request({
        url: userApi.demo,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: params
    })
}

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
    return request({
        url: userApi.Login,
        method: 'post',
        data: parameter
    })
}

export function getSmsCaptcha(parameter) {
    return request({
        url: userApi.SendSms,
        method: 'post',
        data: parameter
    })
}

export function getInfo() {
    return request({
        url: userApi.UserInfo,
        method: 'get',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}

export function getCurrentUserNav() {
    return request({
        url: userApi.UserMenu,
        method: 'get'
    })
}

export function logout() {
    return request({
        url: userApi.Logout,
        method: 'post',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
}

/**
 * get user 2step code open?
 * @param parameter {*}
 */
export function get2step(parameter) {
    return request({
        url: userApi.twoStepCode,
        method: 'post',
        data: parameter
    })
}
