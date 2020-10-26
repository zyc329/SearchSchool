<template>
  <div class="TableList">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item label="学校名称">
            <a-input
              v-decorator="['']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="学校类型">
            <a-input
              v-decorator="['']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="创校时间">
            <a-input
              v-decorator="['']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item>
            <a-button class="ml30" type="primary" @click="queryAll()">查询</a-button>
            <a-button class="ml10" @click="resetFieldsQueryAll()">清空条件</a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>

<script>
  export default {
    name: "SchoolIndex",
    data() {
      return {
        form:this.$form.createForm(this)
      }
    },
    mounted() {
      this.queryAll()
    },
    methods: {
      queryAll(){
        const queryParam = {}
        const { form: { validateFields } } = this
        validateFields((errors, values) => {
          if (!errors) {
            Object.assign(queryParam, values)
          }
          //api请求

        })
      },
      resetFieldsQueryAll(){
        this.form.resetFields()
        this.queryAll()
      }
    }
  }
</script>

<style scoped>

</style>