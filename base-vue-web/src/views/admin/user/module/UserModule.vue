<template>
    <a-modal
            :title="title"
            width="30%"
            :visible="visible"
            :destroyOnClose="true"
            :closable="false"
    >
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
            <a-form-item label="账号">
                <a-input
                        v-decorator="['account',{rules:rules.account}]"
                />
            </a-form-item>
            <a-form-item label="密码">
                <a-input  type="password"
                        v-decorator="['password',{rules:rules.password}]"
                />
            </a-form-item>
            <a-form-item label="用户名">
                <a-input
                        v-decorator="['userName',{rules:rules.userName}]"
                />
            </a-form-item>
            <a-form-item label="头像" v-if="false">
                <a-input
                        v-decorator="['imgSrc',{rules:rules.imgSrc}]"
                />
            </a-form-item>
            <a-form-item label="性别">
                <a-select style="width: 100%" v-decorator="['sex',{rules:rules.sex}]">
                    <a-select-option v-for="item in dirs.sexDirs" :key="item.code" :value="item.code">
                        {{item.text}}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="年龄">
                <a-input
                        v-decorator="['age',{rules:rules.age}]"
                />
            </a-form-item>
            <a-form-item label="角色" v-if="operationType!==10">
                <a-select style="width: 100%">
                    <a-select-option v-for="item in dirs.roleDirs" :key="item.code" :value="item.code" v-decorator="['role',{rules:rules.role}]">
                        {{item.text}}
                    </a-select-option>
                </a-select>
            </a-form-item>
        </a-form>
        <div slot="footer">
            <a-button type="primary" :loading="loading" @click="save">
                保存
            </a-button>
            <a-button @click="close" :loading="loading">关闭</a-button>
        </div>
    </a-modal>
</template>

<script>
  import {userAdd} from '@/api/admin/user/index'
  export default {
    data() {
      return {
        operationId: '',
        // 0查看 10新增 20修改
        operationType: '',
        title: '新增',
        visible: false,
        loading: false,
        lineData: {},
        form: this.$form.createForm(this),
        rules: {
          account:[{
            'message': '账号必须输入',
            'required': true
          }],
          password:[{
            'message': '密码必须设置',
            'required': true
          }],
          userName:[{
            'message': '请填写用户名',
            'required': true
          }],
          imgSrc:[{
            'message': '请上传用户头像',
            'required': true
          }],
          sex:[{
            'message': '请选择性别',
            'required': true
          }],
          age:[{
            'message': '账号必须输入',
            'required': true
          }],
        },
        dirs: {
          sexDirs:[{code:1,text:'男'},{code:0,text:'女'},{code:2,text:'保密'}],
          roleDirs:[{code:'admin',text:'管理员'},{code:'user',text:'用户'}]
        },
        //100 注册 200管理员页面 300用户页面
        pageType: '',
      }
    },
    mounted() {

    },
    methods: {
      showModule(data, type, pageType) {
        this.visible = true
        this.operationType = type
        this.pageType=pageType
        if (type === 10) {
          this.title = '新增'
        } else {
          this.title = '修改'
          this.lineData = {...data}
          this.operationId = data.IIIIDDDD
        }
        this.pageType ===100 && (this.title = '注册')
      },
      save() {
        const { form: { validateFields } } = this
        this.loading =true
        validateFields((errors, values) => {
          if (!errors) {
            values.role = this.pageType !== 200 ? 'user' : 'admin'
            userAdd(values).then(res=>{
              this.close()
              this.$message.success('用户添加成功');
            }).catch(err=>{
              this.$message.error(err.message)
              this.loading=false
            })
          } else {
            this.loading = false
          }
        })
      },
      close() {
        this.form.resetFields()
        this.lineData = {}
        this.operationId = ''
        this.operationType = ''
        this.pageType=''
        this.visible = false
      }
    }
  }
</script>

<style scoped>
    /deep/ .ant-modal-body {
        max-height: 650px;
        overflow-y: auto;
    }

    /* 设置滚动条的样式 */
    /deep/ .ant-modal-body::-webkit-scrollbar {
        width: 5px;
        background-color: #8e8e8e;
    }

    /* 滚动槽 */
    /deep/ .ant-modal-body::-webkit-scrollbar-track {
        border-radius: 10px;
    }

    /* 滚动条滑块 */
    /deep/ .ant-modal-body::-webkit-scrollbar-thumb {
        border-radius: 10px;
        background: #3283d5;
    }
</style>