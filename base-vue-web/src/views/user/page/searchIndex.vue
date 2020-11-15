<template>
    <div class="content100">
<!--        搜索条件-->
        <div style="width: 100%;height: 400px;background: #76b8fc;position: relative;padding: 0 10%">
            <a-row style="width: 100%;padding-top: 50px;height: 100px;" :gutter="12">
                <a-col :span="8">
                    <a-select
                            v-model="searchData.type"
                            size="large"
                            default-value="10"
                            style="width: 40%;float: right;"
                    >
                        <a-select-option v-for="item in dict.searchType" :key="item.value" :value="item.value">
                            {{item.text}}
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
            <div style="width: 100%;background: red;height: 300px;font-size: 72px;">
                高级搜索
            </div>
        </div>
<!--        查询结果-->
        <div style="font-size: 72px;padding: 0 10%;background: #1890ff">
            查询结果
            <div></div>
        </div>
    </div>
</template>

<script>
  import {Dict} from "@/utils/dict";

  export default {
    data(){
      return{
        searchData: {
          type: '10',
          search:''
        },
        dict: {
          searchType: Dict.SEARCHTYPE
        },
      }
    },
    mounted() {
      this.searchData = {...this.$route.params}
    }
  }
</script>

<style scoped>

</style>