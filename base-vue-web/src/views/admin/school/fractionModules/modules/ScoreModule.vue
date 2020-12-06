<template>
  <a-modal
    :title="title"
    width="30%"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
  >
    <a-row>
      <a-col>
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
          <a-form-item v-show="false">
            <a-input
              v-decorator="['schoolId']"
            />
          </a-form-item>
          <a-form-item v-show="false">
            <a-input
              v-decorator="['collegeId']"
            />
          </a-form-item>
          <a-form-item label="专业名称">
            <a-input
              v-decorator="['professionalName',{rules:rules}]"
            />
          </a-form-item>
        </a-form>
      </a-col>
    </a-row>

    <div slot="footer">
      <a-button type="primary" :loading="loading" @click="save">
        保存
      </a-button>
      <a-button @click="close" :loading="loading">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
  import {professionalAdd,professionalUpdate} from "@/api/admin/specialty"
  export default {
    data() {
      return {
        operationId: '',
        // 10新增 20修改
        operationType: '',
        title: '新增',
        visible: false,
        loading: false,
        lineData: {},
        form: this.$form.createForm(this),
        rules: [],
      }
    },
    mounted() {

    },
    methods: {
      showModule(data, type) {
        this.visible = true
        this.operationType = type
        if (type === 10) {
          this.title = '新增'
        } else {
          this.title = '修改'
          this.operationId = data.professionalId
        }
        this.$nextTick(() => {
          this.form.setFieldsValue({...data})
        })
      },
      save() {
        const {form: {validateFields}} = this
        let api = professionalAdd
        this.operationType === 20 && (api = professionalUpdate)
        this.loading = true
        validateFields((errors, values) => {
          if (!errors) {
            let params = values
            this.operationType === 20 && (params= Object.assign(values, {professionalId: this.operationId}))
            api(params).then(res => {
              this.close()
              let message = this.operationId === 20 ? '专业修改成功' : '专业信息添加成功'
              this.$message.success(message);
              this.loading = false
            }).catch(err => {
              this.$message.error(err.message)
              this.loading = false
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
        this.$emit('closeModule')
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