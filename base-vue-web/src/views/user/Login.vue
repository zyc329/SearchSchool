<template>
  <div class="main">
    <a-form id="formLogin" class="user-layout-login" ref="formLogin" :form="form" @submit="handleSubmit">
      <a-alert v-if="isLoginError" type="error" showIcon style="margin-bottom: 24px" message="账户或密码错误"/>
      <a-form-item>
        <a-input
          size="large"
          type="text"
          placeholder="请输入账号"
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
          placeholder="请输入密码"
          v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }], validateTrigger: 'blur' }]"
        >
          <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
        </a-input>
      </a-form-item>

      <a-form-item>
        <a class="forge-password" style="float: left" @click="visitor">游客模式</a>
        <a class="forge-password" style="float: right" @click="$refs.userModule.showModule(undefined,10,100)">注册账号</a>
      </a-form-item>
      <a-form-item style="margin-top: 24px">
        <a-row :gutter="16">
          <a-col :span="12">
            <a-button size="large" class="login-button"
                      @click="forgetPW"
            >忘记密码
            </a-button
            >
          </a-col>
          <a-col :span="12">
            <a-button size="large" type="primary" htmlType="submit" class="login-button" :loading="state.loginBtn"
                      :disabled="state.loginBtn"
            >登录
            </a-button
            >
          </a-col>
        </a-row>
      </a-form-item>
    </a-form>
    <a-modal title="忘记密码"
             width="30%"
             :visible="visible"
             :destroyOnClose="true"
             :closable="false">
      <div>
        <span>您的账号？</span><br>
        <a-input v-model="secretSecurity.account" />
      </div>
      <h2>密保问题：</h2>
      <div>
        <span>你的手机号码是多少？</span><br>
        <a-input v-model="secretSecurity.miOne" />
      </div>
      <div>
        <span>你的小学叫什么？</span><br>
        <a-input v-model="secretSecurity.miTwo"/>
      </div>
      <div>
        <span>你的初中叫什么？</span><br>
        <a-input v-model="secretSecurity.miThree"/>
      </div>
      <div slot="footer">
        <a-button type="primary" :loading="loading" @click="verification">验证</a-button>
        <a-button @click="closeForgetPW">关闭</a-button>
      </div>
    </a-modal>
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
  import {forgetPW} from '@/api/admin/user/index'
  import * as utils from '@/utils/utilZengh'

  export default {
    name: 'userLogin',
    components: {
      UserModule
    },
    data() {
      return {
        utils,
        visible:false,
        loading:false,
        rememberMe: true,
        secretSecurity:{
          account:'',
          miOne:'',
          miTwo:'',
          miThree:''
        },
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
      visitor() {
        this.$router.push({path: '/homeIndex'})
      },
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
          description: err.message || '请求出现错误，请稍后再试',
          duration: 4
        })
      },
      forgetPW() {
        this.visible=true
      },
      verification(){
        this.loading=false
        if (this.utils.isEmpty(this.secretSecurity.account)) {
          this.$message.error('请填写忘记密码的账号！');
          this.loading = false
          return
        }
        if (this.utils.isEmpty(this.secretSecurity.miOne)) {
          this.$message.error('请填写密保问题1！');
          this.loading = false
          return
        }
        if (this.utils.isEmpty(this.secretSecurity.miTwo)) {
          this.$message.error('请填写密保问题2！');
          this.loading = false
          return
        }
        if (this.utils.isEmpty(this.secretSecurity.miThree)) {
          this.$message.error('请填写密保问题3！');
          this.loading = false
          return
        }
        forgetPW(this.secretSecurity).then(res=>{
          if (res.code===5){
            this.closeForgetPW()
            this.$message.success(res.message);
          }else {
            this.$message.success(res.message);
          }
        }).catch(err=>{
          this.$message.error(err.message)
          this.loading = false
        }).finally(()=>{
          this.loading = false
        })
      },
      closeForgetPW(){
        this.secretSecurity = {
          account:'' ,
          miOne: '',
          miTwo: '',
          miThree: ''
        }
        this.visible=false
      }
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
      font-size: 16px;
      font-weight: bolder;
      color: #1890ff;
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
