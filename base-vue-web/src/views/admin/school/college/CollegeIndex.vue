<template>
  <div class="TableList">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item label="所属学校">
            <a-select style="width: 100%" v-decorator="['schoolId']">
              <a-select-option v-for="item in dirs.schoolDirs" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="学院名称">
            <a-input
              v-decorator="['collegeName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="学院负责人">
            <a-input
              v-decorator="['collegeHead']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :wrapper-col="{ span: 24 }">
            <a-button class="ml30" type="primary" @click="$refs.collegeModule.showModule(undefined,10)">新增</a-button>
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
      <span slot="schoolName" slot-scope="text,record">
        {{utils.parse_str(record.schoolId,dirs.schoolDirs)}}
      </span>
      <span slot="action" slot-scope="text, record">
        <a-button type="link" @click="editItem(record)">编辑</a-button>
        <a-button type="link " @click="deleteItem(record.collegeId)">删除</a-button>
        <a-button type="link" @click="$refs.specialtyIndex.showModule(record)">专业管理</a-button>
      </span>
    </a-table>
    <college-module ref="collegeModule" @closeModule="queryAll"></college-module>
    <specialty-index ref="specialtyIndex" @closeModule="queryAll"></specialty-index>
  </div>
</template>

<script>
  import CollegeModule from "@/views/admin/school/college/modules/CollegeModule";
  import {schoolAll} from '@/api/admin/school'
  import SpecialtyIndex from "@/views/admin/school/specialty/SpecialtyIndex";
  import {collegeFindPage,collegeDelete} from '@/api/admin/college'
  import * as utils from '@/utils/utilZengh'
  // 学院
  export default {
    components: {CollegeModule,SpecialtyIndex},
    data() {
      return {
        utils,
        form: this.$form.createForm(this),
        columns: [
          {
            key: 'schoolName',
            title: '学校名称',
            dataIndex: 'schoolName',
            align: 'center',
            scopedSlots: {customRender: 'schoolName'},
          },
          {
            key: 'collegeName',
            title: '学院名称',
            dataIndex: 'collegeName',
            align: 'center'
          },
          {
            key: 'collegeHead',
            title: '学院负责人',
            dataIndex: 'collegeHead',
            align: 'center'
          },
          {
            key: 'action',
            title: '操作',
            align: 'center',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'}
          }
        ],
        rules: {},
        tableData: [],
        pagination: {
          current: 1,
          showTotal: (total) => {
            return `共 ${total} 条`
          },
          showSizeChanger: true,
          pageSize: 10
        },
        dirs: {
          schoolDirs: []
        }
      }
    },
    mounted() {
      this.queryAll()
      this.getSchool()
    },
    methods: {
      getSchool() {
        schoolAll().then(res => {
          let school = []
          for (let item of res.data) {
            school.push({value: item.schoolId, text: item.schoolName})
          }
          this.dirs.schoolDirs = school
        }).catch(err => {
          this.$message.error(err.message)
        })
      },
      queryAll() {
        const {form: {validateFields}} = this
        let queryParam = {page: this.pagination.current, size: this.pagination.pageSize}
        validateFields((errors, values) => {
          if (!errors) {
            this.loading = true
            queryParam = Object.assign(values, queryParam)
            collegeFindPage(queryParam).then(res => {
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
        this.$refs.collegeModule.showModule(data, 20)
      },
      deleteItem(collegeId) {
        let _this = this
        _this.loading = true
        this.$confirm({
          title: '提示',
          content:'是否确定删除该数据',
          onOk() {
            collegeDelete({collegeId:collegeId}).then(() => {
              _this.$message.success('删除成功！')
              _this.queryAll()
              _this.loading = false
            }).catch(err => {
              _this.$message.error(err.message)
              _this.loading = false
            }).finally(() => {
              _this.loading = false
            })
          },
          onCancel() {
            _this.queryAll()
            _this.loading = false
          },
        })
      }
    }
  }
</script>

<style scoped>

</style>