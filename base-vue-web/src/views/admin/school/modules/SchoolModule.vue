<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
  >
    <a-row>
      <a-col :span="width==='40%'? 24 : 12">
        <span>基础信息</span>
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
          <a-form-item label="学校名称">
            <a-input
              v-decorator="['schoolName',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="学校类型">
            <a-select style="width: 100%" v-decorator="['schoolType',{rules:rules}]">
              <a-select-option v-for="item in dirs.schoolType" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="学校官网网址">
            <a-input
              v-decorator="['schoolUrl',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="招生热线">
            <a-input
              v-decorator="['schoolTel',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="招生办负责人">
            <a-input
              v-decorator="['schoolHead',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="创校时间">
            <a-date-picker format="yyyy-MM-DD" v-decorator="['schoolTime',{rules:rules}]"/>
          </a-form-item>
          <a-form-item label="学校具体地址">
            <a-textarea
              :auto-size="{ minRows: 3, maxRows: 5 }"
              v-decorator="['schoolSrc',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="学校简介">
            <a-textarea
              :auto-size="{ minRows: 3, maxRows: 5 }"
              v-decorator="['schoolIntroduction',{rules:rules}]"
            />
          </a-form-item>
        </a-form>
      </a-col>
      <a-col :span="12" v-if="operationType===10">
        图片信息
        <a-form :form="form1" :label-col="{ span: 8 }" :wrapper-col="{ span: 12 }">
          <a-form-item label="学校校标">
            <a-upload
              :fileList="fileList1"
              :remove="((data)=>{handleRemove(data,1)})"
              :text="upload.text"
              :customRequest="(data)=>{customRequest(data,'schoolTitle',1)}"
            >
              <a-button>
                <a-icon type="upload"/>
                上传学校校标
              </a-button>
            </a-upload>
          </a-form-item>
          <a-form-item label="学校详情页标题图片">
            <a-upload
              :fileList="fileList2"
              :remove="((data)=>{handleRemove(data,2)})"
              :text="upload.text"
              :customRequest="(data)=>{customRequest(data,'schoolDetail',2)}"
            >
              <a-button>
                <a-icon type="upload"/>
                上传学校详情页
              </a-button>
            </a-upload>
          </a-form-item>
          <a-form-item label="学校照片墙">
            <a-upload
              :fileList="fileList3"
              :remove="((data)=>{handleRemove(data,3)})"
              :text="upload.text"
              :customRequest="(data)=>{customRequest(data,'schoolPhoto',3)}"
            >
              <a-button>
                <a-icon type="upload"/>
                上传学校照片墙
              </a-button>
            </a-upload>
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
  import {upload, deletePic, schoolAdd, schoolUpdate} from '@/api/admin/school'
  import _ from 'lodash'
  import {Dict} from "@/utils/dict";
  import {userAdd, userUpdate} from "@/api/admin/user";

  export default {
    data() {
      return {
        operationId: '',
        // 0查看 10新增 20修改
        operationType: '',
        title: '新增',
        width: '80%',
        visible: false,
        loading: false,
        lineData: {},
        form: this.$form.createForm(this, {name: 'schoolBase'}),
        form1: this.$form.createForm(this, {name: 'schoolPic'}),
        rules: [],
        upload: {
          text: '上传文件',
        },
        fileList1: [],
        fileList2: [],
        fileList3: [],
        schoolPic: [],
        dirs: {
          schoolType: Dict.SCHOOLTYPE
        }
      }
    },
    mounted() {

    },
    methods: {
      showModule(data, type) {
        this.visible = true
        if (type === 10) {
          this.title = '新增'
          this.operationType = type
          this.width = '80%'
        } else {
          this.title = '修改'
          this.width = '40%'
          this.lineData = {...data}
          this.operationType = type
          this.operationId = data.schoolId
          this.$nextTick(() => {
            this.form.setFieldsValue({...data})
          })
        }
      },
      save() {
        const {form: {validateFields}} = this
        let api = schoolAdd
        this.operationType === 20 && (api = schoolUpdate)
        this.loading = true
        validateFields((errors, values) => {
          if (!errors) {
            let params = Object.assign(values, {schoolPic: this.schoolPic.join(',')})
            this.operationType === 20 && Object.assign(values, {schoolId: this.operationId})
            api(params).then(res => {
              this.close()
              let message = this.operationId === 20 ? '学校信息修改成功' : '学校信息添加成功'
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
        this.form1.resetFields()
        this.lineData = {}
        this.operationId = ''
        this.operationType = ''
        this.$emit('closeModule')
        this.visible = false
      },
      handleRemove(data, type) {
        let param = {
          deletePicId: data.id
        }
        deletePic(param).then(res => {
          if (type === 1) {
          }
        })
      },
      customRequest(data, picType, type) {
        let file = data.file
        let fileType = file.type;
        if (fileType.indexOf('image') < 0) {
          this.$message.warn('请上传图片！');
          return false;
        }
        if (file.size / 1024 / 1024 > 50) {
          this.$message.warn('文件大小不能超过50M！');
          return false;
        }
        if (type === 1) {
          if (this.fileList1.length >= 1) {
            this.$message.warn('只能上传一个文件')
            return false
          }
          let idx = _.findIndex(this.fileList1, o => {
            return o.name == file.name
          })

          if (idx > -1) {
            this.$message.warn(file.name + '已存在，请重新选择', 1)
            return false
          }
        } else if (type === 2) {
          if (this.fileList2.length >= 5) {
            this.$message.warn('只能上传五个文件')
            return false
          }
          let idx = _.findIndex(this.fileList2, o => {
            return o.name == file.name
          })

          if (idx > -1) {
            this.$message.warn(file.name + '已存在，请重新选择', 1)
            return false
          }
        } else {
          let idx = _.findIndex(this.fileList3, o => {
            return o.name == file.name
          })

          if (idx > -1) {
            this.$message.warn(file.name + '已存在，请重新选择', 1)
            return false
          }
        }

        const formData = new FormData()
        formData.append('file', file)
        formData.append('type', picType)
        upload(formData).then(res => {
          file.id = res.data
          if (type === 1) {
            this.fileList1 = [...this.fileList1, file]
            console.log(this.fileList1)
            debugger
          } else if (type === 2) {
            this.fileList2 = [...this.fileList2, file]
          } else {
            this.fileList3 = [...this.fileList3, file]
          }
          this.schoolPic.push(res.data)
          this.$message.success('上传成功！')
        }).catch(err => {
          this.$message.error('上传失败！')
        })
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