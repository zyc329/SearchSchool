<template>
  <a-modal :title="title" width="80%" :visible="visible" :destroyOnClose="true" :closable="false">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col span="8">
          <span class="title">证件照：</span>
          <a-upload
            name="avatar"
            list-type="picture-card"
            class="avatar-uploader"
            :show-upload-list="false"
            :customRequest="
              (data) => {
                customRequest(data, 'profilePicture')
              }
            "
            :remove="handleRemove"
          >
            <img style="width: 240px; height: 300px" v-if="imageUrl" :src="'/download?picId=' + profilePicture" alt="avatar" />
            <div v-else>
              <a-icon :type="loading ? 'loading' : 'plus'" />
              <div class="ant-upload-text">Upload</div>
            </div>
          </a-upload>
        </a-col>
        <a-col span="8">
          <span class="title">学校信息：</span>
          <a-form-item label="学校名称">
            <a-select style="width: 100%" v-decorator="['schoolId']" @change="getCollege">
              <a-select-option v-for="item in dirs.schoolDirs" :key="item.value" :value="item.value">
                {{ item.text }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="学院名称" v-if="isCollege">
            <a-select style="width: 100%" v-decorator="['collegeId']" @change="getProfessional">
              <a-select-option v-for="item in dirs.collegeDirs" :key="item.value" :value="item.value">
                {{ item.text }}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="授课专业" v-if="isProfessional">
            <a-select style="width: 100%" v-decorator="['professionalId']">
              <a-select-option v-for="item in dirs.professionalDirs" :key="item.value" :value="item.value">
                {{ item.text }}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col span="8">
          <span class="title">老师信息：</span>
          <a-form-item label="教师名称">
            <a-input v-decorator="['teacherName']" />
          </a-form-item>
          <a-form-item label="职称">
            <a-input v-decorator="['title', { rules: [] }]" />
          </a-form-item>
          <a-form-item label="教师工龄">
            <a-input v-decorator="['teachingAge', { rules: [] }]" />
          </a-form-item>
          <a-form-item label="教师简历">
            <a-textarea placeholder="" :auto-size="{ minRows: 3, maxRows: 5 }" v-decorator="['teacherIntroduction']" />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <div slot="footer">
      <a-button type="primary" :loading="loading" @click="save"> 保存 </a-button>
      <a-button @click="close" :loading="loading">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
import { deletePic, schoolAll, upload } from '@/api/admin/school'
import { collegeFindList } from '@/api/admin/college'
import { professionalFindList } from '@/api/admin/specialty'
import { teacherAdd, teacherUpdate } from '@/api/admin/teacher'
import _ from 'lodash'

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
        professionalDirs: [],
        collegeDirs: [],
      },
      isCollege: false,
      isProfessional: false,
      imageUrl: false,
      pic: [],
      profilePicture: '',
    }
  },
  mounted() {},
  methods: {
    handleRemove(data, type) {
      let param = {
        deletePicId: data.id,
      }
      deletePic(param).then((res) => {
        this.pic = []
        this.imageUrl = false
        this.$message.success('请记得重新上传照片！')
      })
    },
    customRequest(data, picType) {
      let file = data.file
      let fileType = file.type
      if (this.pic.length >= 1) {
        this.$message.warn('只能上传一个文件')
        return false
      }
      if (fileType.indexOf('image') < 0) {
        this.$message.warn('请上传图片！')
        return false
      }
      if (file.size / 1024 / 1024 > 50) {
        this.$message.warn('文件大小不能超过50M！')
        return false
      }
      let idx = _.findIndex(this.pic, (o) => {
        return o.name == file.name
      })

      if (idx > -1) {
        this.$message.warn(file.name + '已存在，请重新选择', 1)
        return false
      }

      const formData = new FormData()
      formData.append('file', file)
      formData.append('type', picType)
      upload(formData)
        .then((res) => {
          file.id = res.data
          this.pic = [...this.pic, file]
          this.profilePicture = res.data
          this.imageUrl = true
          this.$message.success('上传成功！')
        })
        .catch((err) => {
          this.$message.error('上传失败！')
        })
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
    getCollege() {
      this.form.resetFields(['collegeId'])
      collegeFindList({ schoolId: this.form.getFieldValue('schoolId') })
        .then((res) => {
          let college = []
          for (let item of res.data) {
            college.push({ value: item.collegeId, text: item.collegeName })
          }
          this.dirs.collegeDirs = college
          this.isCollege = true
        })
        .catch((err) => {
          this.$message.error(err.message)
        })
    },
    getProfessional() {
      this.form.resetFields(['professionalId'])
      professionalFindList({ collegeId: this.form.getFieldValue('collegeId') })
        .then((res) => {
          let professional = []
          for (let item of res.data) {
            professional.push({ value: item.professionalId, text: item.professionalName })
          }
          this.dirs.professionalDirs = professional
          this.isProfessional = true
        })
        .catch((err) => {
          this.$message.error(err.message)
        })
    },
    showModule(data, type) {
      this.visible = true
      this.operationType = type
      this.getSchool()
      if (type === 10) {
        this.title = '新增'
      } else {
        this.title = '修改'
        this.lineData = { ...data }
        this.operationId = data.teacherId
        this.$nextTick(() => {
          this.form.setFieldsValue({ ...data })
          this.imageUrl = true
          this.profilePicture = this.lineData.profilePicture
        })
      }
    },
    save() {
      const {
        form: { validateFields },
      } = this
      let api = teacherAdd
      this.operationType === 20 && (api = teacherUpdate)
      this.loading = true
      validateFields((errors, values) => {
        if (!errors) {
          let params = values
          this.operationType === 10 && (params = Object.assign(values, { profilePicture: this.profilePicture }))
          this.operationType === 20 && (params = Object.assign(values, { teacherId: this.operationId }))
          api(params)
            .then((res) => {
              this.close()
              let message = this.operationType === 20 ? '修改老师信息成功' : '添加老师信息成功'
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
      this.imageUrl = false
      this.pic = []
      this.form.resetFields()
      this.lineData = {}
      this.operationId = ''
      this.operationType = ''
      this.isCollege = false
      this.isProfessional = false
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

.title {
  font-size: 16px;
  font-weight: bolder;
}
</style>
