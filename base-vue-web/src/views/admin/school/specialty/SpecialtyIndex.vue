<template>
  <a-modal
    title="专业管理"
    width="60%"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
  >
    <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
      <a-row>
        <a-col :span="8">
          <a-form-item v-show="false">
            <a-input
              v-decorator="['schoolId']"
            />
          </a-form-item>
          <a-form-item  v-show="false">
            <a-input
              v-decorator="['collegeId']"
            />
          </a-form-item>
          <a-form-item label="专业名称">
            <a-input
              v-decorator="['professionalName']"
            />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item :wrapper-col="{ span: 24 }">
            <a-button class="ml30" type="primary" @click="$refs.specialtyModule.showModule(lineData,10)">新增</a-button>
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
        <a-button type="link " @click="deleteItem(record.professionalId)">删除</a-button>
      </span>
    </a-table>
    <specialty-module ref="specialtyModule" @closeModule="queryAll"></specialty-module>
    <div slot="footer">
      <a-button @click="close" :loading="loading">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
  import SpecialtyModule from "@/views/admin/school/specialty/module/module/SpecialtyModule"
  import {professionalFindPage,professionalDelete} from "@/api/admin/specialty"
  import * as utils from '@/utils/utilZengh'
  // 专业
  export default {
    components: {SpecialtyModule},
    data() {
      return {
        utils,
        visible: false,
        form: this.$form.createForm(this),
        columns: [
          {
            key: 'professionalName',
            title: '专业名称',
            dataIndex: 'professionalName',
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
          schoolDirs: [],
          collegeDirs: []
        },
        lineData: {}
      }
    },
    mounted() {
    },
    methods: {
      close() {
        this.form.resetFields()
        this.operationId = ''
        this.$emit('closeModule')
        this.visible = false
      },
      showModule(data) {
        this.visible = true
        this.lineData = {...data}
        this.$nextTick(() => {
          this.form.setFieldsValue({...data})
          this.queryAll()
        })
      },
      queryAll() {
        const {form: {validateFields}} = this
        let queryParam = {page: this.pagination.current, size: this.pagination.pageSize}
        validateFields((errors, values) => {
          if (!errors) {
            this.loading = true
            queryParam = Object.assign(values, queryParam)
            professionalFindPage(queryParam).then(res => {
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
        this.$refs.specialtyModule.showModule(data, 20)
      },
      deleteItem(professionalId) {
        let _this = this
        _this.loading = true
        this.$confirm({
          title: '提示',
          content: '是否确定删除该数据',
          onOk() {
            professionalDelete({professionalId: professionalId}).then(() => {
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