<template>
  <div class="content100">
    <!--        搜索条件-->
    <div style="width: 100%; height: 400px; background: #76b8fc; position: relative; padding: 0 10%">
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
          <a-button type="primary" icon="search" size="large" @click="">搜索</a-button>
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
                  <a-checkbox-group v-model="checkData" :options="checkShowData" />
                </a-col>
                <a-col :span="2">
                  <span>热门专业：</span>
                </a-col>
                <a-col :span="22" class="mb20">
                  <a-checkbox-group v-model="checkData2" :options="checkShowData2" />
                </a-col>
                <a-col :span="2">
                  <span>排序：</span>
                </a-col>
                <a-col :span="22">
                  <a-radio-group v-model="radioData">
                    <a-radio-button value="1"> 按创效时间排序 </a-radio-button>
                    <a-radio-button value="2"> 按浏览量排序 </a-radio-button>
                    <a-radio-button value="3"> 按热门排名排序 </a-radio-button>
                  </a-radio-group>
                </a-col>
              </a-row>
            </div>
          </a-collapse-panel>
        </a-collapse>
      </div>
    </div>
    <!--        查询结果-->
    <div style="padding: 0 10%; background: #1890ff">
      <div class="w100">
        <a-row>
          <a-col span="8">
            <img style="width: 100px; height: 100px" src="/download?picId=5fb4014bf100f969991c6a88" alt="" />
          </a-col>
          <a-col span="16">
            a
            <a-button @click="aa">下载图片</a-button>
          </a-col>
        </a-row>
      </div>
    </div>
  </div>
</template>

<script>
import { Dict } from '@/utils/dict'

export default {
  data() {
    return {
      searchData: {
        type: '10',
        search: '',
      },
      dict: {
        searchType: Dict.SEARCHTYPE,
      },
      activeKey: ['1'],
      checkData: [],
      checkShowData: ['本科', '专科'],
      checkData2: [],
      checkShowData2: ['土木', '计算机', '文学', '数学', '英语', '物理', '考古', '医学'],
      radioData: '',
    }
  },
  mounted() {
    this.searchData = { ...this.$route.params }
  },
  methods: {
    aa() {
      let u = 'http://' + location.host + '/download?picId=5fb4014bf100f969991c6a88'
      window.open(encodeURI(u))
    },
  },
}
</script>

<style scoped></style>
