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
          <a-form-item label="创校年份">
            <a-date-picker
              mode="year"
              format="yyyy"
              :open="pickerShow"
              @panelChange="handlePanelChange"
              @openChange="handleOpenChange"
              v-decorator="['schoolTime']"/>
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
        <span slot="schoolType" slot-scope="text,record">
          {{utils.parse_str(text,dict.schoolType)}}
        </span>
        <a slot="schoolUrl" slot-scope="text,record">
          {{text}}
        </a>
        <span slot="action" slot-scope="text, record">
          <a-button type="primary" @click="scoreManage(record.schoolId)">分数管理</a-button>
        </span>
      </a-table>
    </a-spin>

    <a-modal
      width="30%"
      title="专业选择"
      :visible="visible"
      :destroyOnClose="true"
      :closable="false"
      :footer="null"
    >
      <div style="width: 100%;height: 200px;position: relative;">
        <a-row>
          <a-col :span="6" style="text-align: right;">
            专业选择：
          </a-col>
          <a-col :span="18">
            <a-select style="width: 80%" v-model="professionalId">
              <a-select-option v-for="item in dict.professionalDirs" :key="item.value" :value="item.value">
                {{item.text}}
              </a-select-option>
            </a-select>
          </a-col>
        </a-row>
        <div style="position: relative;left: 50%;transform: translate(-10%,0);margin-top: 100px;">
          <a-button type="primary" @click="openScoreModule()" style="margin-right: 20px">查询</a-button>
          <a-button type="primary" @click="openEcharts()" style="margin-right: 20px">数据对比</a-button>
          <a-button @click="closeOper">关闭</a-button>
        </div>
      </div>
    </a-modal>

    <a-modal
      width="60%"
      title="分数查询"
      :visible="scoreVisible"
      :destroyOnClose="true"
      :closable="false"
    >
      <a-form :form="form1" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
        <a-row>
          <a-col :span="8">
            <a-form-item label="年份">
              <a-date-picker
                mode="year"
                format="yyyy"
                :open="pickerShow1"
                @panelChange="handlePanelChange1"
                @openChange="handleOpenChange1"
                v-decorator="['year']"/>
            </a-form-item>
          </a-col>
          <a-col :span="16">
            <a-form-item>
              <a-button type="primary" @click="">新增</a-button>
              <a-button class="ml20" type="primary" @click="queryScoreAll()">查询</a-button>
              <a-button class="ml20" @click="resetFieldScoreQueryAll()">清空条件</a-button>
            </a-form-item>
          </a-col>
        </a-row>
        <a-spin :spinning="loading1">
          <a-table
            :columns="columns1"
            :data-source="tableData1"
            :pagination="pagination1"
            @change="handleTableChange1"
          >
          </a-table>
        </a-spin>
      </a-form>
      <div slot="footer">
        <a-button @click="scoreVisible=false">关闭</a-button>
      </div>
    </a-modal>

    <echarts-module ref="echartsModule" @closeModule=""></echarts-module>
  </div>
</template>

<script>
  import moment from 'moment'
  import {schoolFindPage} from '@/api/admin/school'
  import {scoreFindPage} from "@/api/admin/score"
  import * as utils from "@/utils/utilZengh"
  import {Dict} from "@/utils/dict";
  import {professionalFindList} from "@/api/admin/specialty"
  import echartsModule from "@/views/admin/school/fractionModules/modules/echartsModule"

  export default {
    components: {
      echartsModule
    },
    data() {
      return {
        moment,
        utils,
        visible: false,
        scoreVisible: false,
        form: this.$form.createForm(this),
        form1: this.$form.createForm(this),
        loading: false,
        loading1: false,
        dict: {
          schoolType: Dict.SCHOOLTYPE,
          professionalDirs: []
        },
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
            key: 'action',
            title: '操作',
            align: 'center',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'}
          }
        ],
        tableData: [],
        columns1: [
          {
            key: 'year',
            title: '年份',
            dataIndex: 'year',
            align: 'center'
          },
          {
            key: 'score',
            title: '分数',
            dataIndex: 'score',
            align: 'center'
          }
        ],
        tableData1: [],
        pagination: {
          current: 1,
          showTotal: (total) => {
            return `共 ${total} 条`
          },
          showSizeChanger: true,
          pageSize: 10
        },
        pagination1: {
          current: 1,
          showTotal: (total) => {
            return `共 ${total} 条`
          },
          showSizeChanger: true,
          pageSize: 10
        },
        schoolTime: null,
        pickerShow: false,
        pickerShow1: false,
        schoolId: '',
        professionalId: ''
      }
    },
    mounted() {
      this.form = this.$form.createForm(this)
      this.form1 = this.$form.createForm(this)
      this.queryAll()
    },
    methods: {
      openEcharts() {
        if (this.utils.isEmpty(this.professionalId)) {
          this.$message.warn('请先选择专业！')
          return
        }
        this.$refs.echartsModule.showModule(this.schoolId, this.professionalId)
      },
      closeOper() {
        this.visible = false
      },
      openScoreModule() {
        if (this.utils.isEmpty(this.professionalId)) {
          this.$message.warn('请先选择专业！')
          return
        }
        this.closeOper()
        this.scoreVisible = true
        this.queryScoreAll()
      },
      getProfessional() {
        let _this = this
        professionalFindList({schoolId: this.schoolId}).then(res => {
          let professional = []
          for (let item of res.data) {
            professional.push({value: item.professionalId, text: item.professionalName})
          }
          _this.dict.professionalDirs = professional
        }).catch(err => {
          this.$message.error(err.message)
        })
      },
      queryAll() {
        let _this = this
        let queryParam = {page: this.pagination.current, size: this.pagination.pageSize}
        const {form: {validateFields}} = this
        validateFields((errors, values) => {
          if (!errors) {
            this.loading = true
            values.schoolTime && (values.schoolTime = this.moment(_this.schoolTime).format('yyyy-MM-DD'))
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
      queryScoreAll() {
        let _this = this
        let queryParam = {
          page: this.pagination1.current,
          size: this.pagination1.pageSize,
          schoolId: this.schoolId,
          professionalId: this.professionalId
        }
        const {form1: {validateFields}} = this
        validateFields((errors, values) => {
          if (!errors) {
            this.loading1 = true
            queryParam = Object.assign(values, queryParam)
            scoreFindPage(queryParam).then(res => {
              this.tableData1 = res.data.list
              this.loading1 = false
            }).catch(err => {
              this.$message.error(err.message)
            })
          }
        })
      },
      resetFieldScoreQueryAll() {
        this.form1.resetFields()
        this.queryScoreAll()
      },
      handlePanelChange(value) {
        this.schoolTime = {...value}
        let time = moment(value).format('yyyy')
        this.form.setFieldsValue({'schoolTime': time})
        this.pickerShow = false
      },
      handleOpenChange(status) {
        this.pickerShow = status
      },
      handlePanelChange1(value) {
        this.schoolTime = {...value}
        let time = moment(value).format('yyyy')
        this.form1.setFieldsValue({'year': time})
        this.pickerShow1 = false
      },
      handleOpenChange1(status) {
        this.pickerShow1 = status
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
        this.queryScoreAll()
      },
      handleTableChange1(pagination, filters, sorter) {
        const pager = {...this.pagination1}
        pager.current = pagination.current
        pager.pageSize = pagination.pageSize
        this.pagination1 = pager
        this.queryAll()
      },
      scoreManage(id) {
        this.visible = true
        this.dict.professionalDirs = []
        this.professionalId = ''
        this.schoolId = id
        this.getProfessional()
      }
    }
  }
</script>

<style scoped>

</style>