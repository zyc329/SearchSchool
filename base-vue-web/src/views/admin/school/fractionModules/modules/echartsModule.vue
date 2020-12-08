<template>
  <a-modal width="70%" title="历年分数线" :visible="visible" :destroyOnClose="true" :closable="false">
    <div style="width: 100%; height: 400px">
      <div style="width: 100%">
        <a-select style="width: 20%" v-model="queryYear" @change="queryAll">
          <a-select-option v-for="item in [5, 10]" :key="item" :value="item"> 近{{ item }}年分数线</a-select-option>
        </a-select>
      </div>
      <a-spin :spinning="loading" style="margin:50px;">
        <div style="width: 100%; height: 300px">
          <v-chart :options="options"/>
        </div>
      </a-spin>
    </div>
    <div slot="footer">
      <a-button @click="close">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
  import ECharts from 'vue-echarts'
  import {scoreByColleges} from '@/api/admin/score'
  import {parse_str} from "@/utils/utilZengh";
  import {professionalFindList} from "@/api/admin/specialty";

  export default {
    components: {
      'v-chart': ECharts,
    },
    data() {
      return {
        options: {},
        visible: false,
        schoolId: '',
        professionalId: '',
        // 查询近几年
        queryYear: 10,
        loading: false,
        // 专业
        professionalDirs: []
      }
    },
    mounted() {

    },
    methods: {
      close() {
        this.visible = false
        this.schoolId = ''
        this.professionalId = ''
      },
      async showModule(schoolId, professionalId) {
        this.visible = true
        this.schoolId = schoolId
        this.professionalId = professionalId
        await this.getProfessional()
        await this.queryAll()
      },
      getProfessional() {
        professionalFindList({professionalId: this.professionalId}).then(res => {
          let professional = []
          for (let item of res.data) {
            professional.push({value: item.professionalId, text: item.professionalName})
          }
          this.professionalDirs = professional
        }).catch(err => {
          this.$message.error(err.message)
        })
      },
      initCharts(data) {
        let arr = [...data]
        let xAxis = []
        for (let item of arr) {
          xAxis.push(item.year)
        }
        // 处理有几个专业
        let legendIds = []
        let {year, ...leg} = arr[0]
        legendIds = Object.keys(leg)
        let specialtyName = []
        let series = []
        for (let specialty of legendIds) {
          let name = parse_str(specialty, this.professionalDirs)
          specialtyName.push(name)
          let seriesData = {
            name: name,
            type: 'line',
            stack: '分数',
            data: [],
          }
          for (let item of arr) {
            seriesData.data.push(Number(item[specialty]))
          }
          series.push(seriesData)
        }

        let options = {
          title: {
            text: '专业分数线',
          },
          tooltip: {
            trigger: 'axis',
          },
          legend: {
            data: specialtyName,
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true,
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxis,
          },
          yAxis: {
            type: 'value',
          },
          series: series
        }
        console.log(options)
        return options
      },
      queryAll() {
        this.loading = true
        let param = {
          year: this.queryYear,
          professionalIds: this.professionalId,
          schoolId: this.schoolId,
        }
        scoreByColleges(param)
          .then((res) => {
            if (res.data) {
              this.options = this.initCharts(res.data)
            }
          })
          .catch((err) => {
            this.$message.error(err.message)
          })
          .finally(() => {
            this.loading = false
          })
      },
    },
  }
</script>

<style scoped>
  .echarts {
    width: 100%;
    height: 100%;
  }
</style>
