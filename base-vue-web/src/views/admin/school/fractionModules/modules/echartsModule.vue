<template>
  <a-modal
    width="70%"
    title="历年分数线"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
  >
    <div style="width: 100%;height: 400px;">
      <div style="width: 100%;">
        <a-select style="width: 20%;" v-model="queryYear" @change="selectQuery">
          <a-select-option v-for="item in [5,10]" :key="item" :value="item">
            近{{item}}年分数线
          </a-select-option>
        </a-select>
      </div>
      <div>
        <a-spin :spinning="loading">
          <v-chart :options="options"/>
        </a-spin>
      </div>
    </div>
    <div>

    </div>
    <div slot="footer">
      <a-button @click="close">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
  import ECharts from 'vue-echarts'
  import {scoreByColleges} from "@/api/admin/score"

  export default {
    components: {
      'v-chart': ECharts
    },
    data() {
      return {
        visible: false,
        schoolId: '',
        professionalId: '',
        // 查询近几年
        queryYear: 10,
        loading: false,
        options: {},
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
      showModule(schoolId, professionalId) {
        this.visible = true
        this.schoolId = schoolId
        this.professionalId = professionalId
        this.queryAll()
      },
      selectQuery() {

      },
      queryAll() {
        this.loading = true
        let param = {
          year: this.queryYear,
          professionalIds: this.professionalId,
          schoolId: this.schoolId
        }
        scoreByColleges(param).then(res => {
          console.log(res)
          debugger
        }).catch(err=>{
          this.$message.error(err.message)
        }).finally(()=>{
          this.loading = false
        })
      }
    }
  }
</script>

<style scoped>

</style>