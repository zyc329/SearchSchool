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
          v-decorator="['account',{rules:rules}]"
        />
      </a-form-item>
      <a-form-item label="密码">
        <a-input
          v-decorator="['password',{rules:rules}]"
        />
      </a-form-item>
      <a-form-item label="用户名">
        <a-input
          v-decorator="['userName',{rules:rules}]"
        />
      </a-form-item>
      <a-form-item label="头像">
        <a-input
          v-decorator="['imgSrc',{rules:rules}]"
        />
      </a-form-item>
      <a-form-item label="性别">
        <a-select style="width: 100%">
          <a-select-option v-for="item in []" :key="item" :value="item" v-decorator="['sex',{rules:rules}]">
            {{}}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="年龄">
        <a-input
          v-decorator="['age',{rules:rules}]"
        />
      </a-form-item>
      <a-form-item label="角色">
        <a-select style="width: 100%">
          <a-select-option v-for="item in []" :key="item" :value="item" v-decorator="['role',{rules:rules}]">
            {{}}
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
        dirs:[
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