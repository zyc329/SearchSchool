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
      <a slot="schoolUrl" slot-scope="text,record">
        {{text}}
      </a>
      <span slot="action" slot-scope="text, record">
        <a-button type="link" @click="lookItem(record)">查看</a-button>
        <a-button type="link" @click="editItem(record)">编辑</a-button>
        <a-button type="link " @click="deleteItem(record)">删除</a-button>
      </span>
    </a-table>
    <school-module ref="schoolModule" @closeModule="queryAll"></school-module>
  </div>
</template>

<script>
  import SchoolModule from "@/views/admin/school/modules/SchoolModule"
  import moment from 'moment'
  export default {
    components:{
      SchoolModule
    },
    data() {
      return {
        moment,
        form: this.$form.createForm(this),
        columns: [
          {
            title: '学校名称',
            dataIndex: 'schoolName',
            align: 'center'
          },
          {
            title: '学校具体地址',
            dataIndex: 'schoolSrc',
            align: 'center'
          },
          {
            title: '学校类型',
            dataIndex: 'schoolType',
            align: 'center',
            scopedSlots: {customRender: 'schoolType'}
          },
          {
            title: '学校官网网址',
            dataIndex: 'schoolUrl',
            align: 'center',
            scopedSlots: {customRender: 'schoolUrl'}
          },
          {
            title: '招生热线',
            dataIndex: 'schoolTel',
            align: 'center'
          },
          {
            title: '招生办负责人',
            dataIndex: 'schoolHead',
            align: 'center'
          },
          {
            title: '创校时间',
            dataIndex: 'schoolTime',
            align: 'center',
            customRender: function (text, record, index) {
              if (!text) {
                return '-'
              } else {
                return moment(text).format('YYYY-MM-DD')
              }
            }
          },
          {
            title: '操作',
            align: 'center',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'}
          }
        ],
        tableData: [
          {
            key: '1',
            schoolName: '福建工程学院',
            schoolSrc: '福建省福州市闽侯县大学城福建工程学院',
            schoolType: 0,
            schoolUrl:'https://www.ceshi.com',
            schoolTel:'13799365050',
            schoolHead:'吴老师'
          },
          {
            key: '2',
            schoolName: '福州大学',
            schoolSrc: '福建省福州市闽侯县大学城福州大学',
            schoolType: 1,
            schoolUrl:'https://www.ceshi1.com',
            schoolTel:'13799365052',
            schoolHead:'林老师'
          },
          {
            key: '3',
            schoolName: '农林大学',
            schoolSrc: '福建省福州市闽侯县大学城农林大学',
            schoolType: 0,
            schoolUrl:'https://www.ceshi2.com',
            schoolTel:'13799365058',
            schoolHead:'刘老师'
          },
          {
            key: '4',
            schoolName: '福州师范',
            schoolSrc: '福建省福州市闽侯县大学城福州师范',
            schoolType: 1,
            schoolUrl:'https://www.ceshi3.com',
            schoolTel:'13799365059',
            schoolHead:'张老师'
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

      },
      lookItem(data) {

      }
    }
  }
</script>

<style scoped>

</style>