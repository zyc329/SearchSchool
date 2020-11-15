<template>
  <div id="userLayout" :class="['user-layout-wrapper', device]">
    <div class="container">
      <div style="padding: 120px;background: rgba(255,255,255,0.6);border-radius: 20px;">
        <div class="top">
          <h1 class="title" style="font-size: 38px;">榕城查</h1>
          <p class="desc" style="color: red;">新用户请点击注册账号</p>
        </div>
        <route-layout />
      </div>
    </div>
  </div>
</template>

<script>
import RouteLayout from './RouteLayout'
import { mixinDevice } from '@/utils/mixin'

export default {
  name: 'UserLayout',
  components: { RouteLayout },
  mixins: [mixinDevice],
  data() {
    return {}
  },
  mounted() {
    document.body.classList.add('userLayout')
  },
  beforeDestroy() {
    document.body.classList.remove('userLayout')
  },
}
</script>

<style lang="less">
#userLayout.user-layout-wrapper {
  height: 100%;
  &.mobile {
    .container {
      .main {
        max-width: 368px;
        width: 98%;
      }
    }
  }
  .container {
    width: 100%;
    min-height: 100%;
    background: #f0f2f5 url(~@/assets/img/xx1.jpg) no-repeat 50%;
    background-size: 100%;
    padding: 110px 0 144px;
    position: relative;
    .main {
      min-width: 260px;
      width: 368px !important;
      margin: 0 auto;
    }
    a {
      text-decoration: none;
    }
    .top {
      text-align: center;
      .title {
        height: 44px;
        line-height: 44px;
      }
      .desc {
        font-size: 14px;
        color: rgba(0, 0, 0, 0.45);
        margin-top: 12px;
        margin-bottom: 40px;
      }
    }
  }
}
</style>
