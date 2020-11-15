<template>
    <div class="TableList">
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
            <a-row>
                <a-col :span="8">
                    <a-form-item label="学校名称">
                        <a-input
                                v-decorator="['schoolName']"
                        />
                    </a-form-item>
                </a-col>
                <a-col :span="8">
                    <a-form-item label="学校类型">
                        <a-select v-decorator="['schoolType']">
                            <a-select-option value="0">
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
                        <a-date-picker v-decorator="['schoolTime']"/>
                    </a-form-item>
                </a-col>
                <a-col :span="16">
                    <a-form-item>
                        <a-button class="ml30" type="primary" @click="$refs.schoolModule.showModule(undefined,10)">新增
                        </a-button>
                        <a-button class="ml30" type="primary" @click="queryAll()">查询</a-button>
                        <a-button class="ml30" @click="resetFieldsQueryAll()">清空条件</a-button>
                    </a-form-item>
                </a-col>
            </a-row>
        </a-form>
        <a-spin :spinning="loading">
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
        </a-spin>
        <school-module ref="schoolModule" @closeModule="queryAll"></school-module>
    </div>
</template>

<script>
  import SchoolModule from "@/views/admin/school/modules/SchoolModule"
  import moment from 'moment'
  import {schoolFindPage} from '@/api/admin/school'

  export default {
    components: {
      SchoolModule
    },
    data() {
      return {
        moment,
        form: this.$form.createForm(this),
        loading: false,
        columns: [
          {
            key: 'schoolName',
            title: '学校名称',
            dataIndex: 'schoolName',
            align: 'center'
          },
          {
            key: 'schoolSrc',
            title: '学校具体地址',
            dataIndex: 'schoolSrc',
            align: 'center'
          },
          {
            key: 'schoolType',
            title: '学校类型',
            dataIndex: 'schoolType',
            align: 'center',
            scopedSlots: {customRender: 'schoolType'}
          },
          {
            key: 'schoolUrl',
            title: '学校官网网址',
            dataIndex: 'schoolUrl',
            align: 'center',
            scopedSlots: {customRender: 'schoolUrl'}
          },
          {
            key: 'schoolTel',
            title: '招生热线',
            dataIndex: 'schoolTel',
            align: 'center'
          },
          {
            key: 'schoolHead',
            title: '招生办负责人',
            dataIndex: 'schoolHead',
            align: 'center'
          },
          {
            key: 'schoolTime',
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
            key: '',
            title: '操作',
            align: 'center',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'}
          }
        ],
        tableData: [],
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
      this.form = this.$form.createForm(this)
      this.queryAll()
    },
    methods: {
      queryAll() {
        let queryParam = {page: this.pagination.current, size: this.pagination.pageSize}
        const {form: {validateFields}} = this
        validateFields((errors, values) => {
          if (!errors) {
            this.loading = true
            values.schoolTime && this.moment(values.schoolTime).format('yyyy-MM-DD')
            queryParam = Object.assign(values, queryParam)
            schoolFindPage(queryParam).then(res => {
              this.tableData = res.data.list
              this.loading = false
            }).catch(err => {
              this.$message.error(err.message)
            })
          }
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