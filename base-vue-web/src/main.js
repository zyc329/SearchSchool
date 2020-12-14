// IE10兼容 with polyfills
import 'core-js/stable'
import 'regenerator-runtime/runtime'

import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store/'
import { VueAxios } from '@/utils/request'
import ECharts from 'vue-echarts'

// --- 注意 ---- mockjs不支持IE，正式项目中请不要在 production ENV中使用
import '@/mock'

import setStoreWidthConfig from '@/core/set_store_width_config'
import '@/core/lazy_use'
import '@/permission' // permission control
import '@/utils/filter' // global filter
import '@/style/global.less'
import '@/assets/styles/index.css'

import 'echarts/lib/chart/line'
import 'echarts/lib/chart/bar'
import 'echarts/lib/component/title'
import 'echarts/lib/component/legend'
import 'echarts/lib/component/tooltip'


// 给 axios Promise 扩展 finally
import promiseFinally from 'promise.prototype.finally'
promiseFinally.shim()

Vue.config.productionTip = false

Vue.use(VueAxios)



new Vue({
  router,
  store,
  created: setStoreWidthConfig,
  render: (h) => h(App),
}).$mount('#app')
