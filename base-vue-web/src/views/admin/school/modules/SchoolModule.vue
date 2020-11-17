<template>
  <a-modal
    :title="title"
    width="80%"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
  >
    <a-row>
      <a-col :span="12">
        <span>基础信息</span>
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
          <a-form-item label="学校名称">
            <a-input
              v-decorator="['schoolName',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="学校具体地址">
            <a-input
              v-decorator="['schoolSrc',{rules:rules}]"
            />
          </a-form-item>
          <a-form-item label="学校类型">
            <a-select style="width: 100%">
              <a-select-option v-for="item in []" :key="item" :value="item" v-decorator="['schoolType',{rules:rules}]">
                {{}}
              </a-select-option>
            </a-select>
          </a-form-item>
          <a-form-item label="学校简介">
            <a-input
              v-decorator="['schoolIntroduction',{rules:rules}]"
            />
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
            <a-date-picker format="X" v-decorator="['schoolTime',{rules:rules}]"/>
          </a-form-item>
        </a-form>
      </a-col>
      <a-col :span="12">
        图片信息
        <a-form :form="form" :label-col="{ span: 8 }" :wrapper-col="{ span: 12 }">
          <a-form-item label="学校校标">
            <a-upload
              v-decorator="['file']"
              :fileList="fileList"
              :remove="handleRemove"
              :text="upload.text"
              :customRequest="customRequest"
            >
              <a-button> <a-icon type="upload" />上传文件</a-button>
            </a-upload>
          </a-form-item>
          <a-form-item label="学校详情页标题图片">
          </a-form-item>
          <a-form-item label="学校照片墙">
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
  import {upload} from '@/api/admin/school'
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
        rules:[

        ],
        upload:{
          text: '上传文件',
        },
        fileList:[],
      }
    },
    mounted() {

    },
    methods: {
      showModule(data, type) {
        this.visible = true
        if (type === 10) {
          this.title = '新增'
        } else {
          this.title = '修改'
          this.lineData = {...data}
          this.operationType = type
          this.operationId = data.IIIIDDDD
        }
      },
      save() {

      },
      close() {
        this.form.resetFields()
        this.lineData = {}
        this.operationId = ''
        this.operationType = ''
        this.visible = false
      },
      handleRemove (a) {
        debugger
        this.fileList = []

      },
      customRequest(data){
        debugger
        let file = data.file
        let fileType = file.type;
        if(fileType.indexOf('image')<0){
          this.warning('请上传图片！');
          return false;
        }
        if(file.size / 1024 / 1024 > 50) {
          this.warning('文件大小不能超过50M！');
          return false;
        }
        if (this.fileList.length >=1) {
          this.$message.warn('只能上传一个文件')
          return false
        }

        let idx = _.findIndex(this.fileList,o=>{
          return o.name == file.name
        })

        if (idx >-1) {
          this.$message.warn(file.name + '已存在，请重新选择',1)
          return false
        }

        const formData = new FormData()
        formData.append('file',file)
        formData.append('type','st')
        upload(formData).then(res=>{
          this.fileList = [...this.fileList, file]
        }).catch(err =>{
          this.$message.error('上传失败')
        })
      }
    }
  }
</script>

<style scoped>
  /deep/.ant-modal-body{
    max-height: 650px;
    overflow-y: auto;
  }
  /* 设置滚动条的样式 */
  /deep/.ant-modal-body::-webkit-scrollbar {
    width:5px;
    background-color: #8e8e8e;
  }

  /* 滚动槽 */
  /deep/.ant-modal-body::-webkit-scrollbar-track {
    border-radius:10px;
  }

  /* 滚动条滑块 */
  /deep/.ant-modal-body::-webkit-scrollbar-thumb {
    border-radius:10px;
    background: #3283d5;
  }
</style>