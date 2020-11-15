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
            <a-select style="width: 100%" v-decorator="['sex']">
              <a-select-option v-for="item in dict.sex" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="16">
          <a-form-item>
            <a-button class="ml20" type="primary" @click="$refs.userModule.showModule(undefined,10,200)">新增
            </a-button>
            <a-button class="ml20" type="primary" @click="queryAll">查询</a-button>
            <a-button class="ml20" @click="resetFieldsQueryAll">清空条件</a-button>
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
                 <span slot="sex" slot-scope="text,record">
                    {{utils.parse_str(text,dict.sex)}}
                </span>
        <span slot="state" slot-scope="text,record">
                    {{utils.parse_str(text,dict.state)}}
                </span>
        <span slot="action" slot-scope="text, record">
                <a-button type="link" @click="editItem(record)">编辑</a-button>
                <a-button type="link" @click="resetPW(record.userId)">重置密码</a-button>
                <a-button type="link" @click="editState(record)">{{record.state===0?'封号':'解封'}}</a-button>
                <a-button type="link " @click="deleteItem(record)">删除</a-button>
              </span>
      </a-table>
    </a-spin>
    <user-module ref="userModule" @closeModule="queryAll"></user-module>
  </div>
</template>

<script>
  import UserModule from "@/views/admin/user/module/UserModule"
  import {userFindPage, userUpdateStatus, resetPW, userDelete} from '@/api/admin/user/index'
  import * as utils from '@/utils/utilZengh'
  import moment from 'moment'
  import {Dict} from "@/utils/dict";

  export default {
    components: {
      UserModule
    },
    data() {
      return {
        utils,
        moment,
        loading: false,
        form: this.$form.createForm(this),
        columns: [
          {
            key: 'account',
            title: '账号',
            dataIndex: 'account',
            align: 'center'
          },
          {
            key: 'userName',
            title: '用户名',
            dataIndex: 'userName',
            align: 'center'
          },
          {
            key: 'sex',
            title: '性别',
            dataIndex: 'sex',
            align: 'center',
            scopedSlots: {customRender: 'sex'},
          },
          {
            key: 'age',
            title: '年龄',
            dataIndex: 'age',
            align: 'center'
          },
          {
            key: 'state',
            title: '状态',
            dataIndex: 'state',
            align: 'center',
            scopedSlots: {customRender: 'state'}
          },
          {
            key: 'action',
            title: '操作',
            align: 'center',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'}
          }
        ],
        tableData: [],
        dict: {
          sex: Dict.SEX,
          state: Dict.STATE
        },
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
        let params = {}
        const {form: {validateFields}} = this
        validateFields((errors, values) => {
          if (!errors) {
            this.loading = true
            params = Object.assign(values, {role:'admin',page: this.pagination.current, size: this.pagination.pageSize})
            userFindPage(params).then(res => {
              this.tableData = res.data.list
              this.loading = false
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
        this.$refs.userModule.showModule(data, 20, 200)
      },
      deleteItem(data) {
        let _this = this
        _this.loading = true
        this.$confirm({
          title: '提示',
          content:'是否确定删除该数据',
          onOk() {
            userDelete(data).then(() => {
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
      },
      editState(data) {
        this.loading = true
        userUpdateStatus(data).then(() => {
          this.$message.success('状态修改成功！')
          this.queryAll()
          this.loading = false
        }).catch(err => {
          this.$message.error(err.message)
          this.loading = false
        })
      },
      resetPW(id) {
        debugger
        this.loading = true
        resetPW({userId: id}).then(() => {
          this.$message.success('重置密码成功！')
          this.queryAll()
          this.loading = false
        }).catch(err => {
          this.$message.error(err.message)
          this.loading = false
        })
      }
    }
  }
</script>

<style scoped>

</style>