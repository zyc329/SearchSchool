<template>
  <div class="logo">
    <router-link :to="{ path: defaultRootRoutePath }">
      <LogoSvg alt="logo" />
      <h1 v-if="showTitle" style="letter-spacing:30px;">{{ title }}</h1>
    </router-link>
  </div>
</template>

<script>
import LogoSvg from '@/assets/logo.svg?inline'
import { defaultRootRoutePath } from '@/router/router.config'
export default {
  name: 'Logo',
  components: {
    LogoSvg,
  },
  data() {
    return {
      defaultRootRoutePath,
    }
  },
  props: {
    title: {
      type: String,
      default: '榕城查',
      required: false,
    },
    showTitle: {
      type: Boolean,
      default: true,
      required: false,
    },
  },
}
</script>
