import Vue from 'vue'
import router from './router'
import store from './store'

import NProgress from 'nprogress' // 进度条
import '@/components/NProgress/nprogress.less' // 自定义进度条样式
import notification from 'ant-design-vue/es/notification'
import { setDocumentTitle, domTitle } from '@/utils/domUtil'

import { TOKEN_NAME, openPermission } from '@/config/index'
import { defaultRootRoutePath, whiteList } from '@/router/router.config'

if (openPermission) {
  NProgress.configure({ showSpinner: false }) // 进度条配置
}

router.beforeEach((to, from, next) => {
  if (openPermission && !store.getters.multiTabList.includes(to.fullPath)) {
    NProgress.start() // 开始加重进度条
  }

  to.meta && typeof to.meta.title !== 'undefined' && setDocumentTitle(`${to.meta.title} - ${domTitle}`)

  // 请求带有 redirect 重定向时，登录自动重定向到该地址
  const redirect = decodeURIComponent(from.query.redirect || to.path)
  if (Vue.ls.get(TOKEN_NAME)) {
    if (to.path === '/user/login') {
      // next({ path: defaultRootRoutePath })
      next()
      if (openPermission) {
        NProgress.done()
      }
    } else {
      if (store.getters.roles.length === 0) {
        store
          .dispatch('GetInfo')
          .then((res) => {
            if (res.userInfo.role==='user'){
              if (to.path){
                next()
              }else{
                next({path:'/homeIndex'})
              }
            }else{
              if (to.path){
                next()
              }else{
                next({path:'/user-manage'})
              }
            }
          })
          .catch(() => {
            notification.error({
              message: '错误',
              description: '请求用户信息失败，请重试',
            })
            store.dispatch('Logout').then(() => {
              next({ path: '/user/login', query: { redirect: to.fullPath } })
            })
          })
      } else {
        next()
      }
    }
  } else {
    // 无 token
    if (whiteList.includes(to.name)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next({ path: '/user/login', query: { redirect: to.fullPath } })
      if (openPermission) {
        NProgress.done()
      }
    }
  }
})

router.afterEach(() => {
  if (openPermission) {
    NProgress.done() // 结束进度条加载
  }
})
