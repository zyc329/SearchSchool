<template>
  <a-modal
    :title="title"
    width="80%"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
  >
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
    <div slot="footer">
      <a-button type="primary" :loading="loading" @click="save">
        保存
      </a-button>
      <a-button @click="close" :loading="loading">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
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

        ]
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