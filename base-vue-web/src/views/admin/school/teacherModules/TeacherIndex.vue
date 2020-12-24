<template>
  <div class="TableList">
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item label="学校名称">
            <a-select style="width: 100%" v-decorator="['schoolId']" show-search option-filter-prop="children"
                      :filter-option="filterOption">
              <a-select-option v-for="item in dirs.schoolDirs" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="学院名称">
            <a-select style="width: 100%" v-decorator="['collegeId']" show-search option-filter-prop="children"
                      :filter-option="filterOption">
              <a-select-option v-for="item in dirs.collegeDirs" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="授课专业">
            <a-select style="width: 100%" v-decorator="['professionalId']" show-search option-filter-prop="children"
                      :filter-option="filterOption">
              <a-select-option v-for="item in dirs.professionalDirs" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="职称">
            <a-input
              v-decorator="['title']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="工龄">
            <a-input
              v-decorator="['teachingAge']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :wrapper-col="{ span: 24 }">
            <a-button class="ml30" type="primary" @click="$refs.teacherModule.showModule(undefined,10)">新增</a-button>
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
      <span slot="schoolId" slot-scope="text, record">
        {{utils.parse_str(text,dirs.schoolDirs)}}
      </span>
      <span slot="collegeId" slot-scope="text, record">
        {{utils.parse_str(text,dirs.collegeDirs)}}
      </span>
      <span slot="professionalId" slot-scope="text, record">
        {{utils.parse_str(text,dirs.professionalDirs)}}
      </span>
      <span slot="action" slot-scope="text, record">
        <a-button type="link" @click="editItem(record)">编辑</a-button>
        <a-button type="link " @click="deleteItem(record.teacherId)">删除</a-button>
      </span>
    </a-table>
    <teacher-module ref="teacherModule" @closeModule="queryAll()"></teacher-module>
  </div>
</template>

<script>
  import TeacherModule from "@/views/admin/school/teacherModules/modules/TeacherModule"
  import {schoolAll, schoolDelete} from "@/api/admin/school";
  import {professionalFindAll} from "@/api/admin/specialty"
  import {collegeFindAll} from "@/api/admin/college"
  import {teacherFindPage, teacherDelete} from "@/api/admin/teacher"
  import * as utils from "@/utils/utilZengh"

  export default {
    components: {
      TeacherModule
    },
    data() {
      return {
        utils,
        form: this.$form.createForm(this),
        dirs: {
          schoolDirs: [],
          professionalDirs: [],
          collegeDirs: []
        },
        columns: [
          {
            key: 'teacherName',
            title: '教师姓名',
            dataIndex: 'teacherName',
            align: 'center'
          },
          {
            key: 'professionalId',
            title: '授课专业',
            dataIndex: 'professionalId',
            scopedSlots: {customRender: 'professionalId'},
            align: 'center'
          },
          {
            key: 'title',
            title: '职称',
            dataIndex: 'title',
            align: 'center'
          },
          {
            key: 'teachingAge',
            title: '教学工龄',
            dataIndex: 'teachingAge',
            align: 'center'
          },
          {
            key: 'schoolId',
            title: '学校名称',
            dataIndex: 'schoolId',
            scopedSlots: {customRender: 'schoolId'},
            align: 'center'
          },
          {
            key: 'collegeId',
            title: '学院名称',
            dataIndex: 'collegeId',
            scopedSlots: {customRender: 'collegeId'},
            align: 'center'
          },
          {
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
          pageSize: 10,
          total:0
        },
      }
    },
    mounted() {
      this.queryAll()
      this.getDirs()
    },
    methods: {
      filterOption(input, option) {
        return (
          option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
        )
      },
      getDirs() {
        schoolAll().then(res => {
          let school = []
          for (let item of res.data) {
            school.push({value: item.schoolId, text: item.schoolName})
          }
          this.dirs.schoolDirs = school
        }).catch(err => {
          this.$message.error(err.message)
        })

        collegeFindAll().then(res => {
          let college = []
          for (let item of res.data) {
            college.push({value: item.collegeId, text: item.collegeName})
          }
          this.dirs.collegeDirs = college
        }).catch(err => {
          this.$message.error(err.message)
        })

        professionalFindAll().then(res => {
          let professional = []
          for (let item of res.data) {
            professional.push({value: item.professionalId, text: item.professionalName})
          }
          this.dirs.professionalDirs = professional
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
            teacherFindPage(queryParam).then(res => {
              this.tableData = res.data.list
              this.pagination.total=res.data.total
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
        this.$refs.teacherModule.showModule(data, 20)
      },
      deleteItem(teacherId) {
        let _this = this
        _this.loading = true
        this.$confirm({
          title: '提示',
          content: '是否确定删除该数据',
          onOk() {
            teacherDelete({teacherId: teacherId}).then(() => {
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