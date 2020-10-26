// eslint-disable-next-line
import {UserLayout, BasicLayout, RouteLayout} from '@/layouts'
import {openPermission} from '@/config/index'

import exampleModule from '@/router/modules/example'

// 自定义 icon引入
// import { bxAnaalyse } from '@/core/icons'

// 默认加载和登陆成功跳转路由
export const defaultRootRoutePath = '/dashboard'

// 不跳转白名单路由名
export const whiteList = ['login', 'register', 'registerResult']

// 前端未找到页面路由（固定不用改）
export const notFoundRouter = {
  path: '*',
  redirect: '/404',
  hidden: true,
}

// 基础路由 固定不变的路由
export const constantRouterMap = [
  {
    path: '/user',
    component: UserLayout,
    redirect: '/user/login',
    hidden: true,
    children: [
      {
        path: 'login',
        name: 'login',
        component: () => import(/* webpackChunkName: "user" */ '@/views/user/Login'),
      },
    ],
  },
  {
    path: '/404',
    component: () => import(/* webpackChunkName: "fail" */ '@/views/exception/404'),
  },
]

// 同步路由 一般为前端写死的路由不通过接口获取
let syncRouterMap = [
  {
    path: '/',
    name: 'index',
    component: BasicLayout,
    meta: {title: '首页'},
    redirect: `${defaultRootRoutePath}/analysis`,
    children: [
      // 默认页
      {
        path: defaultRootRoutePath,
        name: 'dashboard',
        redirect: `${defaultRootRoutePath}/analysis`,
        component: RouteLayout,
        meta: {title: '工作台', keepAlive: true, icon: 'dashboard', permission: ['dashboard']},
        children: [
          {
            path: `${defaultRootRoutePath}/analysis`,
            name: 'analysis',
            component: () => import('@/views/dashboard/Analysis'),
            meta: {title: 'hello', keepAlive: true, permission: ['dashboard']},
          },
        ],
      },
      exampleModule,
      {
        path: '/admin-school',
        name: 'school',
        component: RouteLayout,
        meta: {title: '学校资料管理', keepAlive: true, icon: 'thunderbolt'},
        children: [
          {
            path: '/school-index',
            name: 'SchoolIndex',
            component: () => import('@/views/admin/school/SchoolIndex'),
            meta: {title: '学校列表', keepAlive: true, permission: ['dashboard']},
          },
          {
            path: '/fraction-index',
            name: 'FractionIndex',
            component: () => import('@/views/admin/school/fractionModules/FractionIndex'),
            meta: {title: '历年分数线管理', keepAlive: true, permission: ['dashboard']},
          },
          {
            path: '/teacher-index',
            name: 'TeacherIndex',
            component: () => import('@/views/admin/school/teacherModules/TeacherIndex'),
            meta: {title: '学校老师信息管理', keepAlive: true, permission: ['dashboard']},
          }
          ,
          {
            path: '/college-index',
            name: 'CollegeIndex',
            component: () => import('@/views/admin/school/college/CollegeIndex'),
            meta: {title: '学院管理', keepAlive: true, permission: ['dashboard']},
          }
        ],
      },
      {
        path: '/admin-user',
        name: 'user',
        component: RouteLayout,
        meta: {title: '用户管理', keepAlive: true, icon: 'thunderbolt'},
        children: [
          {
            path: '/user-manage',
            name: 'UserManage',
            component: () => import('@/views/admin/user/UserManage'),
            meta: {title: '用户列表', keepAlive: true, permission: ['dashboard']},
          },
          {
            path: '/admin-manage',
            name: 'AdminManage',
            component: () => import('@/views/admin/user/AdminManage'),
            meta: {title: '管理员列表', keepAlive: true, permission: ['dashboard']},
          }
        ],
      },
      {
        path: '/message-index',
        name: 'message',
        component: RouteLayout,
        meta: {title: '消息通知', keepAlive: true, icon: 'thunderbolt'},
        children: [
          {
            path: '/message-index',
            name: 'MessageIndex',
            component: () => import('@/views/admin/message/MessageIndex'),
            meta: {title: '举报处理', keepAlive: true, permission: ['dashboard']},
          }
        ],
      },
    ],
  },
]

if (!openPermission) {
  syncRouterMap.push(notFoundRouter)
}

export {syncRouterMap}
