<template>
  <div class="content100">
    <a-carousel :autoplay="true" dots-class="slick-dots slick-thumb">
      <div v-for="index in 3" :key="index" style="width: 100%; height: 100%;">
        <img style="width: 100%;height: 900px;" :src="getImgUrl(index)" class="w100" alt="" />
      </div>
    </a-carousel>
    <div class="search">
      <span class="search-title">榕城查</span>
      <a-row style="position: absolute; top: 50%; width: 100%" :gutter="12">
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
          <a-button type="primary" icon="search" size="large" @click="jumpSearch">搜索</a-button>
        </a-col>
      </a-row>
    </div>

<!--    <div class="footer">管理员联系方式：(tel/vx:15477845214;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ:1154274698)</div>-->
  </div>
</template>

<script>
import { Dict } from '@/utils/dict'

export default {
  data() {
    return {
      imgShow: 1,
      searchData: {
        type: '10',
        search: '',
      },
      dict: {
        searchType: Dict.SEARCHTYPE,
      },
      timer: null,
    }
  },
  mounted() {
  },
  methods: {
    getImgUrl(index){
      return require("@/assets/img/b"+index+".jpg");
    },
    jumpSearch() {
      this.$router.push({ name: 'searchIndex', params: this.searchData })
    },
  },
}
</script>

<style scoped>
.search {
  background: rgba(255, 255, 255, 0.4);
  width: 50%;
  height: 400px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 15%;
}

.btn-left {
  background: rgba(163, 161, 161, 0.6);
  width: 30px;
  height: 30px;
  position: absolute;
  top: 50%;
  left: 50px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
}

.btn-right {
  background: rgba(163, 161, 161, 0.6);
  width: 30px;
  height: 30px;
  position: absolute;
  top: 50%;
  right: 50px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
}

.footer {
  margin-top: 20px;
  position: fixed;
  bottom: 0;
  font-size: 16px;
  font-family: 黑体;
  width: 100%;
  height: 30px;
  text-align: center;
}

.search-title {
  position: absolute;
  top: 20%;
  left: 50%;
  font-family: 华文琥珀;
  font-size: 72px;
  transform: translate(-50%, 0);
}

.ant-carousel >>> .slick-slide {
  text-align: center;
  height: 900px;
  line-height: 900px;
  background: #364d79;
  overflow: hidden;
}

.ant-carousel >>> .slick-slide h3 {
  color: #fff;
}
</style>
