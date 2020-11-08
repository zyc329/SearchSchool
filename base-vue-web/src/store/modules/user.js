import Vue from 'vue'
import {login, getInfo, logout} from '@/api/user'
import {TOKEN_NAME} from '@/config/index'
import {welcome} from '@/utils/util'

const user = {
  state: {
    token: '',
    name: '',
    welcome: '',
    avatar: '',
    roles: [],
    info: {},
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, {name, welcome}) => {
      state.name = name
      state.welcome = welcome
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_INFO: (state, info) => {
      state.info = info
    },
  },

  actions: {
    // 登录
    Login({commit}, userInfo) {
      return new Promise((resolve, reject) => {
        login(userInfo)
          .then((response) => {
            const result = response
            Vue.ls.set(TOKEN_NAME, result.token, 7 * 24 * 60 * 60 * 1000)
            commit('SET_TOKEN', result.token)
            resolve()
          })
          .catch((error) => {
            reject(error)
          })
      })
    },

    // 获取用户信息
    GetInfo({commit}) {
      return new Promise((resolve, reject) => {
        const token = Vue.ls.get(TOKEN_NAME)
        getInfo({token})
          .then((response) => {
            const result = response.userInfo

            if (result) {
              commit('SET_INFO', result)
            } else {
              reject(new Error('获取用户失败!'))
            }
            commit('SET_NAME', {name: result.userName, welcome: welcome()})

            commit('SET_AVATAR', 'https://gw.alipayobjects.com/zos/rmsportal/jZUIxmJycoymBprLOUbT.png')
            // commit('SET_AVATAR', result.imgSrc)
            resolve(response)
          })
          .catch((error) => {
            reject(error)
          })
      })
    },

    // 登出
    Logout({commit, state}) {
      return new Promise((resolve) => {
        logout(state.token)
          .then(() => {
            resolve()
          })
          .catch(() => {
            resolve()
          })
          .finally(() => {
            commit('SET_TOKEN', '')
            commit('SET_ROLES', [])
            Vue.ls.remove(TOKEN_NAME)
          })
      })
    },
  },
}

export default user
