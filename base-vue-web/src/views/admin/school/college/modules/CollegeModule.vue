<template>
  <a-modal :title="title" width="40%" :visible="visible" :destroyOnClose="true" :closable="false">
    <a-row>
      <a-col>
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
          <a-form-item label="学校名称">
            <a-select style="width: 100%" v-decorator="['schoolId']">
              <a-select-option v-for="item in dirs.schoolDirs" :key="item.value" :value="item.value">
                {{ item.text }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="学院名称">
            <a-input v-decorator="['collegeName', { rules: rules }]" />
          </a-form-item>
          <a-form-item label="招生办负责人">
            <a-input v-decorator="['collegeHead', { rules: rules }]" />
          </a-form-item>
          <a-form-item label="学院介绍">
            <a-textarea :auto-size="{ minRows: 3, maxRows: 5 }" v-decorator="['collegeIntroduction', { rules: rules }]" />
          </a-form-item>
        </a-form>
      </a-col>
    </a-row>

    <div slot="footer">
      <a-button type="primary" :loading="loading" @click="save"> 保存 </a-button>
      <a-button @click="close" :loading="loading">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
import _ from 'lodash'
import { Dict } from '@/utils/dict'
import { schoolAll } from '@/api/admin/school'
import { collegeAdd, collegeUpdate } from '@/api/admin/college'

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
      rules: [],
      dirs: {
        schoolDirs: [],
      },
    }
  },
  mounted() {},
  methods: {
    showModule(data, type) {
      this.visible = true
      this.getSchool()
      if (type === 10) {
        this.title = '新增'
        this.operationType = type
      } else {
        this.title = '修改'
        this.lineData = { ...data }
        this.operationType = type
        this.operationId = data.collegeId
        this.$nextTick(() => {
          this.form.setFieldsValue({ ...data })
        })
      }
    },
    getSchool() {
      schoolAll()
        .then((res) => {
          let school = []
          for (let item of res.data) {
            school.push({ value: item.schoolId, text: item.schoolName })
          }
          this.dirs.schoolDirs = school
        })
        .catch((err) => {
          this.$message.error(err.message)
        })
    },
    save() {
      const {
        form: { validateFields },
      } = this
      let api = collegeAdd
      this.operationType === 20 && (api = collegeUpdate)
      this.loading = true
      validateFields((errors, values) => {
        if (!errors) {
          let params = values
          this.operationType === 20 && (params = Object.assign(values, { collegeId: this.operationId }))
          api(params)
            .then((res) => {
              this.close()
              let message = this.operationType === 20 ? '学院信息修改成功' : '学院信息添加成功'
              this.$message.success(message)
              this.loading = false
            })
            .catch((err) => {
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
    },
  },
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
