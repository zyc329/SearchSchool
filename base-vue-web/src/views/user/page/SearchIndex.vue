<template>
  <div class="content100">
    <!--        搜索条件-->
    <div style="width: 100%; height: 400px; background: #76b8fc; position: relative; padding: 0 10%;margin-bottom: 20px;">
      <a-row style="width: 100%; padding-top: 50px; height: 100px" :gutter="12">
        <a-col :span="8">
          <a-select v-model="searchData.type" size="large" default-value="10" style="width: 40%; float: right">
            <a-select-option v-for="item in dict.searchType" :key="item.value" :value="item.value">
              {{ item.text }}
            </a-select-option>
          </a-select>
        </a-col>
        <a-col :span="8">
          <a-input size="large" v-model="searchData.search"></a-input>
        </a-col>
        <a-col :span="8">
          <a-button type="primary" icon="search" size="large" @click="queryExample">搜索</a-button>
        </a-col>
      </a-row>
      <div style="width: 100%; height: 300px">
        <a-collapse v-model="activeKey">
          <a-collapse-panel key="1" header="筛选">
            <div>
              <a-row>
                <a-col :span="2">
                  <span>学校类型：</span>
                </a-col>
                <a-col :span="22" class="mb20">
                  <a-radio-group v-model="checkData" :options="checkShowData"/>
                </a-col>
                <a-col :span="2">
                  <span>热门专业：</span>
                </a-col>
                <a-col :span="22" class="mb20">
                  <a-checkbox-group v-model="checkData2" :options="checkShowData2"/>
                </a-col>
                <a-col :span="2">
                  <span>排序：</span>
                </a-col>
                <a-col :span="22">
                  <a-radio-group v-model="radioData">
                    <a-radio-button :value="0"> 按创校时间排序</a-radio-button>
                    <a-radio-button :value="1"> 按浏览量排序</a-radio-button>
                    <a-radio-button :value="2"> 按热门排名排序</a-radio-button>
                  </a-radio-group>
                </a-col>
              </a-row>
            </div>
            <div style="text-align: right;width: 100%;">
              <a-button type="primary" @click="remove">清除筛选</a-button>
            </div>
          </a-collapse-panel>
        </a-collapse>
      </div>
    </div>
    <!--        查询结果-->
    <div v-if="resultData.length>0" style="padding: 0 10%;" >
      <a-card class="w100" style="margin-bottom: 20px;" v-for="item in resultData">
        <span slot="title" class="title-name">{{item.schoolName}}
          <a style="margin-left: 50px;" :href="item.schoolUrl"
             target="_blank">{{item.schoolUrl}}</a></span>
        <a-button @click="lookItem(item.schoolId)" slot="extra">查看详情</a-button>
        <div class="w100">
          <p>学校地址：{{item.schoolSrc}}</p>
          <a-row>
            <a-col :span="18">
              <p>学校简介：{{item.schoolIntroduction}}</p>
            </a-col>
            <a-col :span="6">
              <div class="w100" style="text-align: right">
                <p>学校负责人：{{item.schoolHead}}</p>
                <p>联系电话：{{item.schoolTel}}</p>
                <p>创校时间：{{isEmpty(item.schoolTime)?'':moment(item.schoolTime).format('yyyy-MM-DD')}}</p>
              </div>
            </a-col>
          </a-row>
        </div>
        <div class="w100">
          <span style="float: left">相关图片：</span>
          <div v-for="pic in item.schoolPic.split(',').slice(0,7)" style="float: left;height: 50px;margin-right: 15px;">
            <img style="height: 50px;" :src="`/download?picId=${pic}`">
          </div>
          <span style="float: right;">浏览次数：{{item.schoolSeachSize}}</span>
        </div>
      </a-card>
    </div>
    <div v-else style="padding: 0 10%;width: 100%;height: 100px;">
      <a-empty />
    </div>
    <div style="height: 60px"></div>
  </div>
</template>

<script>
  import {Dict} from '@/utils/dict'
  import {selectItem,addCount} from '@/api/admin/school'
  import {isEmpty} from "@/utils/utilZengh"
  import moment from 'moment'
  import SchoolDetail from "@/views/user/page/SchoolDetail"

  export default {
    components:{
      SchoolDetail
    },
    data() {
      return {
        moment,
        isEmpty,
        searchData: {
          type: '10',
          search: '',
        },
        dict: {
          searchType: Dict.SEARCHTYPE,
        },
        activeKey: ['1'],
        checkData: '',
        checkShowData: [{label: '本科', value: '1'}, {label: '专科', value: '0'}],
        checkData2: [],
        checkShowData2: ['土木', '计算机', '文学', '数学', '英语', '物理', '考古', '医学'],
        radioData: '',
        resultData: []
      }
    },
    mounted() {
      this.searchData = {...this.$route.params}
      if (isEmpty(this.searchData.type)) {
        this.searchData.type = '10'
      }
      this.firstQuery()
    },
    methods: {
      aa() {
        let u = 'http://' + location.host + '/download?picId=5fb4014bf100f969991c6a88'
        window.open(encodeURI(u))
      },
      remove() {
        this.checkData = ''
        this.checkData2 = []
        this.radioData = ''
      },
      firstQuery() {
        let params = {}
        switch (this.searchData.type) {
          case '10':
            params = {
              example: {schoolName: this.searchData.search}
            }
            break
          case '20':
            params = {
              example: {professionalName: this.searchData.search}
            }
            break
          case '30':
            params = {
              example: {schoolSrc: this.searchData.search}
            }
            break
        }
        selectItem(params).then(res => {
          this.resultData = res.data
        })
      },
      queryExample() {
        let params = {}
        switch (this.searchData.type) {
          case '10':
            params = {
              example: {schoolName: this.searchData.search}
            }
            break
          case '20':
            params = {
              example: {professionalName: this.searchData.search}
            }
            break
          case '30':
            params = {
              example: {schoolSrc: this.searchData.search}
            }
            break
        }
        if (!isEmpty(this.checkData)) {
          params.example.schoolType = this.checkData
        }
        if (!isEmpty(this.checkData2)) {
          params.roleNameList = this.checkData2
        }
        if (!isEmpty(this.radioData)) {
          params.example.order = this.radioData
        }
        selectItem(params).then(res => {
          this.resultData = res.data
        })
      },
      lookItem(schoolId){
        addCount({schoolId:schoolId}).then(res=>{
          this.$router.push(
            {
              name:'SchoolDetail',
              query:{schoolId:schoolId}
            })
        })
      }
    },
  }
</script>

<style scoped>
  .title-name {
    font-size: 22px;
    font-weight: bolder
  }
</style>
