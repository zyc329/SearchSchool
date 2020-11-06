<template>
  <div class="TableList">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item label="账号">
            <a-input
              v-decorator="['account']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="用户名">
            <a-input
              v-decorator="['userName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="年龄">
            <a-input
              v-decorator="['age']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="性别">
            <a-select style="width: 100%">
              <a-select-option v-for="item in []" :key="item" :value="item" v-decorator="['sex']">
                {{}}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item>
            <a-button class="ml20" type="primary" @click="$refs.userModule.showModule(undefined,10)">新增</a-button>
            <a-button class="ml20" type="primary" @click="queryAll()">查询</a-button>
            <a-button class="ml20" @click="resetFieldsQueryAll()">清空条件</a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>

    <a-table
      :columns="columns"
      :data-source="tableData"
      :pagination="pagination"
      @change="handleTableChange"
    >
      <span slot="action" slot-scope="text, record">
        <a-button type="link" @click="editItem(record)">编辑</a-button>
        <a-button type="link" @click="">重置密码</a-button>
        <a-button type="link" @click="">{{record.state===0?'冻结':'解冻'}}</a-button>
        <a-button type="link " @click="deleteItem(record)">删除</a-button>
      </span>
    </a-table>
    <user-module ref="userModule" @closeModule="queryAll"></user-module>
  </div>
</template>

<script>
  import UserModule from "@/views/admin/user/module/UserModule"
  export default {
    components:{
      UserModule
    },
    data() {
      return {
        form: this.$form.createForm(this),
        columns: [
          {
            title: '账号',
            dataIndex: 'account',
            align: 'center'
          },
          {
            title: '用户名',
            dataIndex: 'userName',
            align: 'center'
          },
          {
            title: '性别',
            dataIndex: 'sex',
            align: 'center'
          },
          {
            title: '年龄',
            dataIndex: 'age',
            align: 'center'
          },
          {
            title: '状态',
            dataIndex: 'state',
            align: 'center'
          },
          {
            title: '操作',
            align: 'center',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'},
          }
        ],
        tableData: [
          {
            account: '1',
            userName: 'John Brown',
            age: 32,
            state: 0,
          },
          {
            account: '2',
            userName: 'Jim Green',
            age: 42,
            state: 0,
          },
          {
            account: '3',
            userName: 'Joe Black',
            age: 32,
            state: 1,
          }
        ],
        pagination: {
          current: 1,
          showTotal: (total) => {
            return `共 ${total} 条`
          },
          showSizeChanger: true,
          pageSize: 10
        },
      }
    },
    mounted() {
      this.queryAll()
    },
    methods: {
      queryAll() {
        const queryParam = {}
        const {form: {validateFields}} = this
        validateFields((errors, values) => {
          if (!errors) {
            Object.assign(queryParam, values, {page: this.pagination.current, size: this.pagination.pageSize})
          }
          //api请求
          // const pagination = { ...this.pagination }
          // pagination.total = res.data.total
          // const pagination = { ...this.pagination }
          // if (!res.code) {
          //   this.tableData = res.data.rows
          //   pagination.total = res.data.total
          // } else {
          //   this.tableData = []
          //   pagination.total = 0
          //   this.$message.error(res.description)
          // }
          // this.pagination = pagination

        })
      },
      resetFieldsQueryAll() {
        this.form.resetFields()
        this.queryAll()
      },
      handleTableChange(pagination, filters, sorter) {
        const pager = {...this.pagination}
        pager.current = pagination.current
        pager.pageSize = pagination.pageSize
        this.pagination = pager
        this.queryAll()
      },
      editItem(data) {

      },
      deleteItem(data) {

      }
    }
  }
</script>

<style scoped>

</style>