<template>
  <div class="TableList">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item label="学校名称">
            <a-input
              v-decorator="['schoolId']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="学校类型">
            <a-select v-decorator="['schoolType']">
              <a-select-option value="0" >
                专科
              </a-select-option>
              <a-select-option value="1">
                本科
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="创校时间">
            <a-date-picker v-decorator="['schoolTime']" />
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item>
            <a-button class="ml30" type="primary" @click="$refs.schoolModule.showModule(undefined,10)">新增</a-button>
            <a-button class="ml30" type="primary" @click="queryAll()">查询</a-button>
            <a-button class="ml30" @click="resetFieldsQueryAll()">清空条件</a-button>
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
        <a-button type="link " @click="deleteItem(record)">删除</a-button>
      </span>
    </a-table>
    <school-module ref="schoolModule" @closeModule="queryAll"></school-module>
  </div>
</template>

<script>
  import SchoolModule from "@/views/admin/school/modules/SchoolModule"
  export default {
    components:{
      SchoolModule
    },
    data() {
      return {
        form: this.$form.createForm(this),
        columns: [
          {
            title: 'name',
            dataIndex: 'name',
            align: 'center'
          },
          {
            title: 'Age',
            dataIndex: 'age',
            align: 'center'
          },
          {
            title: 'Address',
            dataIndex: 'address',
            align: 'center'
          },
          {
            title: 'Tags',
            dataIndex: 'tags',
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
            key: '1',
            name: 'John Brown',
            age: 32,
            address: 'New York No. 1 Lake Park, New York No. 1 Lake Park',
          },
          {
            key: '2',
            name: 'Jim Green',
            age: 42,
            address: 'London No. 2 Lake Park, London No. 2 Lake Park',
          },
          {
            key: '3',
            name: 'Joe Black',
            age: 32,
            address: 'Sidney No. 1 Lake Park, Sidney No. 1 Lake Park',
          },
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