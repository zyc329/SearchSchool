<template>
    <div class="w100">
        <img v-if="imgShow===1" src="~@/assets/img/b1.jpg" class="w100" alt="">
        <img v-if="imgShow===2" src="~@/assets/img/b2.jpg" class="w100" alt="">
        <img v-if="imgShow===3" src="~@/assets/img/b3.jpg" class="w100" alt="">
        <div class="search">
            <span class="search-title">榕城查</span>
            <a-row style="position: absolute;top: 50%;width: 100%" :gutter="12">
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
                    <a-button type="primary" icon="search" size="large" @click="jumpSearch">搜索</a-button>
                </a-col>
            </a-row>
        </div>
        <div class="btn-left" @click="imgBtn(0)">
        </div>
        <div class="btn-right" @click="imgBtn(1)">
        </div>

        <div class="footer">
            管理员联系方式：(tel/vx:15477845214;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ:1154274698)
        </div>
    </div>
</template>

<script>
  import {Dict} from "@/utils/dict";

  export default {
    data() {
      return {
        imgShow: 1,
        searchData: {
          type: '10',
          search:''
        },
        dict: {
          searchType: Dict.SEARCHTYPE
        },
        timer:null
      }
    },
    mounted() {
      this.imgShowMethods()
    },
    beforeDestroy() {
      if(this.timer) { //如果定时器还在运行 或者直接关闭，不用判断
        clearInterval(this.timer); //关闭
      }
    },
    methods: {
      imgBtn(type) {
        if (type === 0) {
          this.imgShow--
          if (this.imgShow === 0) {
            this.imgShow = 3
          }
        } else {
          this.imgShow++
          if (this.imgShow === 4) {
            this.imgShow = 1
          }
        }
      },
      imgShowMethods() {
        this.timer=setInterval(() => {
          if (this.imgShow === 3) {
            this.imgShow = 1
          } else {
            this.imgShow++
          }
        }, 4000)
      },
      jumpSearch(){
        this.$router.push({name:'searchIndex',params: this.searchData})
      }
    }
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
        position: fixed;
        bottom: 15px;
        font-size: 18px;
        font-family: 黑体;
        width: 100%;
        text-align: center;
    }

    .search-title {
        position: absolute;
        top: 20%;
        left: 50%;
        font-family: 华文琥珀;
        font-size: 72px;
        transform: translate(-50%, 0)
    }
</style>