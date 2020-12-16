<template>
  <div class="user-wrapper">
    <div class="content-box">
      <!-- notice：信息通知组件 -->
      <!-- <notice-icon class="action" /> -->
      <a-dropdown>
        <span class="action ant-dropdown-link user-dropdown-menu">
          <a-avatar class="avatar" size="small" :src="avatar"/>
          <span>{{ nickname }}</span>
        </span>
        <a-menu slot="overlay" class="user-dropdown-menu-wrapper">
          <a-menu-item key="2" @click="visible = true">
            <a-icon type="setting"/>
            <span>修改密码</span>
          </a-menu-item>
          <a-menu-divider/>
          <a-menu-item key="3">
            <a href="javascript:;" @click="handleLogout">
              <a-icon type="logout"/>
              <span>退出登录</span>
            </a>
          </a-menu-item>
        </a-menu>
      </a-dropdown>
    </div>
    <a-modal title="修改密码" width="30%" :visible="visible" :destroyOnClose="true" :closable="false">
      <a-row>
        <a-col :span="6" style="text-align: right;">
          新密码：
        </a-col>
        <a-col :span="16">
          <a-input v-model="newPw" type="password"></a-input>
        </a-col>
      </a-row>
      <div slot="footer">
        <a-button type="primary" @click="save">保存</a-button>
        <a-button @click="close()">关闭</a-button>
      </div>
    </a-modal>
  </div>
</template>

<script>
  // import NoticeIcon from '@/components/NoticeIcon'
  import {mapActions, mapGetters} from 'vuex'
  import {updatePW} from '@/api/admin/user/index'
  import {isEmpty} from "@/utils/utilZengh";

  export default {
    name: 'UserMenu',
    components: {
      // NoticeIcon,
    },
    computed: {
      ...mapGetters(['nickname', 'avatar']),
    },
    data() {
      return {
        visible: false,
        newPw: ''
      }
    },
    methods: {
      ...mapActions(['Logout']),
      save() {
        if (isEmpty(this.newPw)) {
          this.$message.error('新密码不允许为空')
          return
        }
        if (this.newPw.length < 8) {
          this.$message.error('密码长度不能小于8位数')
          return
        }
        updatePW({
          userId: this.$store.getters.userInfo.userId,
          newPw: this.newPw
        }).then(res => {
          this.$success({
            title: '提示',
            content: '密码修改成功，即将重新登陆！',
            onOk: () => {
              return this.Logout({})
                .then(() => {
                  setTimeout(() => {
                    window.location.reload()
                  }, 16)
                })
                .catch((err) => {
                  this.$message.error({
                    title: '错误',
                    description: err.message,
                  })
                })
            },
          })
        }).catch(err=>{
          this.$message.error({
            title: '错误',
            description: err.message,
          })
        })
      },
      close() {
        this.newPw=''
        this.visible=false
      },
      handleLogout() {
        this.$confirm({
          title: '提示',
          content: '真的要注销登录吗 ?',
          onOk: () => {
            return this.Logout({})
              .then(() => {
                setTimeout(() => {
                  window.location.reload()
                }, 16)
              })
              .catch((err) => {
                this.$message.error({
                  title: '错误',
                  description: err.message,
                })
              })
          },
        })
      },
    },
  }
</script>
