<template>
    <div class="main">
        <a-form id="formLogin" class="user-layout-login" ref="formLogin" :form="form" @submit="handleSubmit">
            <a-alert v-if="isLoginError" type="error" showIcon style="margin-bottom: 24px" message="账户或密码错误"/>
            <a-form-item>
                <a-input
                        size="large"
                        type="text"
                        placeholder="账户: admin or super"
                        v-decorator="[
                'account',
                { rules: [{ required: true, message: '请输入账号/手机号/邮箱号' }, { validator: handleUsernameOrEmail }], validateTrigger: 'change' },
              ]"
                >
                    <a-icon slot="prefix" type="username" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>

            <a-form-item>
                <a-input
                        size="large"
                        type="password"
                        autocomplete="false"
                        placeholder="密码: 和账户名一致"
                        v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }], validateTrigger: 'blur' }]"
                >
                    <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>

            <a-form-item>
                <!--        <a-checkbox v-decorator="['rememberMe', { valuePropName: 'checked' }]" class="user-select-none">自动登录-->
                <!--        </a-checkbox>-->
                <a class="forge-password" style="float: right" @click="$refs.userModule.showModule(undefined,10,100)">注册账号</a>
            </a-form-item>

            <a-form-item style="margin-top: 24px">
                <a-button size="large" type="primary" htmlType="submit" class="login-button" :loading="state.loginBtn"
                          :disabled="state.loginBtn"
                >确定
                </a-button
                >
            </a-form-item>
        </a-form>

        <user-module ref="userModule"></user-module>
    </div>
</template>

<script>
  import md5 from 'md5'
  // import TwoStepCaptcha from '@/components/tools/TwoStepCaptcha'
  import {mapActions} from 'vuex'
  import {timeFix} from '@/utils/util'
  import {getSmsCaptcha, get2step} from '@/api/user'
  import UserModule from "@/views/admin/user/module/UserModule"

  export default {
    name: 'userLogin',
    components: {
      UserModule
    },
    data() {
      return {
        rememberMe: true,
        loginBtn: false,
        // login type: 0 email, 1 username, 2 telephone
        loginType: 0,
        isLoginError: false,
        requiredTwoStepCaptcha: false,
        stepCaptchaVisible: false,
        form: this.$form.createForm(this),
        state: {
          time: 60,
          loginBtn: false,
          // login type: 0 email, 1 username, 2 telephone
          loginType: 0,
          smsSendBtn: false,
        },
      }
    },
    created() {
      get2step({})
        .then((res) => {
          this.requiredTwoStepCaptcha = res.result.stepCode
        })
        .catch(() => {
          this.requiredTwoStepCaptcha = false
        })
      // this.requiredTwoStepCaptcha = true
    },
    methods: {
      ...mapActions(['Login', 'Logout']),
      // handler
      handleUsernameOrEmail(rule, value, callback) {
        const {state} = this
        const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
        if (regex.test(value)) {
          state.loginType = 0
        } else {
          state.loginType = 1
        }
        callback()
      },
      handleSubmit(e) {
        e.preventDefault()
        const {
          form: {validateFields},
          state,
          Login,
        } = this

        state.loginBtn = true

        const validateFieldsKey = ['account', 'password']

        validateFields(validateFieldsKey, {force: true}, (err, values) => {
          if (!err) {
            const loginParams = {...values}
            Login(loginParams)
              .then((res) => this.loginSuccess(res))
              .catch((err) => this.requestFailed(err))
              .finally(() => {
                state.loginBtn = false
              })
          } else {
            setTimeout(() => {
              state.loginBtn = false
            }, 600)
          }
        })
      },
      loginSuccess(res) {
        this.$router.push({path: '/'})
        // 延迟 1 秒显示欢迎信息
        setTimeout(() => {
          this.$notification.success({
            message: '欢迎',
            description: `${timeFix()}，欢迎回来`,
          })
        }, 1000)
        this.isLoginError = false
      },
      requestFailed(err) {
        this.isLoginError = true
        this.$notification['error']({
          message: '错误',
          description: ((err.response || {}).data || {}).message || '请求出现错误，请稍后再试',
          duration: 4,
        })
      },
    },
  }
</script>

<style lang="less" scoped>
    .user-layout-login {
        label {
            font-size: 14px;
        }

        .getCaptcha {
            display: block;
            width: 100%;
            height: 40px;
        }

        .forge-password {
            font-size: 14px;
        }

        button.login-button {
            padding: 0 15px;
            font-size: 16px;
            height: 40px;
            width: 100%;
        }

        .user-select-none {
            user-select: none;
        }
    }
</style>
