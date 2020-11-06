<template>
  <div class="TableList">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item label="老师名字">
            <a-input
              v-decorator="['teacherName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="所处学校">
            <a-input
              v-decorator="['schoolName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="所处学院">
            <a-input
              v-decorator="['collegeName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="专业">
            <a-input
              v-decorator="['professionalName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item>
            <a-button class="ml30" type="primary" @click="">新增</a-button>
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
        <a-button type="link" @click="lookItem(record)">查看</a-button>
        <a-button type="link" @click="editItem(record)">编辑</a-button>
        <a-button type="link " @click="deleteItem(record)">删除</a-button>
      </span>
    </a-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: this.$form.createForm(this),
        columns: [
          {
            title: '教师姓名',
            dataIndex: 'teacherName',
            align: 'center'
          },
          {
            title: '授课专业',
            dataIndex: 'teachingProfessional',
            align: 'center'
          },
          {
            title: '职称',
            dataIndex: 'title',
            align: 'center'
          },
          {
            title: '教学工龄',
            dataIndex: 'teachingAge',
            align: 'center'
          },
          {
            title: '学校名称',
            dataIndex: 'schoolName',
            align: 'center'
          },
          {
            title: '学院名称',
            dataIndex: 'collegeName',
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
            teacherName: '林老师',
            teachingAge: 4,
            collegeName: '软件学院',
            schoolName:'福建工程学院',
            title:'副教授'
          },
          {
            key: '2',
            teacherName: '林老师',
            teachingAge: 2,
            collegeName: '信息学院',
            schoolName:'福建工程学院',
            title:'副教授'
          },
          {
            key: '3',
            teacherName: '吴老师',
            teachingAge: 5,
            collegeName: '电子学院',
            schoolName:'福建工程学院',
            title:'副教授'
          },
          {
            key: '4',
            teacherName: '曾老师',
            teachingAge: 10,
            collegeName: '软件学院',
            schoolName:'福建工程学院',
            title:'副教授'
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
      editItem(data){

      },
      deleteItem(data){

      },
      lookItem(data){

      }
    }
  }
</script>

<style scoped>

</style>