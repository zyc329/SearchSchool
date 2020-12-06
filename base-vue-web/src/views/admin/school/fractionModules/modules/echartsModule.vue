<template>
  <a-modal
    width="30%"
    title="历年分数线"
    :visible="visible"
    :destroyOnClose="true"
    :closable="false"
    :footer="null"
  >
    <div style="width: 100%;height: 200px;">
      <div>
        <a-select v-model="queryYear" @change="selectQuery">
          <a-select-option v-for="item in [5,10]" :key="item" :value="item">
            近{{item}}年分数线
          </a-select-option>
        </a-select>
      </div>
    </div>
    <div slot="footer">
      <a-button @click="close">关闭</a-button>
    </div>
  </a-modal>
</template>

<script>
  export default {
    data() {
      return {
        visible: false,
        schoolId: '',
        professionalId: '',
        // 查询近几年
        queryYear: ''
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
      },
      selectQuery(){

      },
      queryAll() {
        // let params={
        //   schoolId:this.schoolId
        //   专业:
        //   近几年：this.queryYear
        // }
        // 接口(params).then(res=>{
        //
        // })
      }
    }
  }
</script>

<style scoped>

</style>