<template>
  <div class="div-background mh w100">
    <div class="mh w100 div-content">
      <a-carousel :autoplay="true" dots-class="slick-dots slick-thumb">
        <div v-for="item in schoolDetail">
          <img style="height: 300px;width: 100%;" :src="`/download?picId=${item.picId}`" />
        </div>
      </a-carousel>
      <div class="title w100">
        <h1>{{school.schoolName}}</h1>
      </div>
      <div class="w100 clear plr50 mb30">
        <div style="width: 400px;height: 400px;float: left">
          <p style="color: #1890ff;font-size: 22px;font-family: 楷体;margin-bottom: 0;font-weight: bolder;">校标</p>
          <img style="width: 350px;height: 350px;" :src="`/download?picId=${schoolTitle[0].picId}`">
        </div>
        <div style="float: left;margin-left: 20px">
          <span>
            学校简介：{{school.schoolIntroduction}}
          </span>
        </div>
      </div>
      <div class="plr50 w100">
        <a-tabs default-active-key="1">
          <a-tab-pane key="1" tab="学校信息">
            <table v-if="school" border="1" align="center" width="100%" style="font-size: 16px;font-family: 黑体：">
              <tr>
                <td align="center" width="30%">学校名称</td>
                <td>{{school.schoolName}}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校类型</td>
                <td>{{school.schoolType==='0'?'专科':'本科'}}</td>
              </tr>
              <tr>
                <td align="center" width="30%">创校时间</td>
                <td>{{moment(school.schoolTime).format('yyyy-MM-DD')}}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校地址</td>
                <td>{{school.schoolSrc}}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校负责人</td>
                <td>{{school.schoolHead}}</td>
              </tr>
              <tr>
                <td align="center" width="30%">负责人电话</td>
                <td>{{school.schoolTel}}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校官网</td>
                <td><a :href="school.schoolUrl" target="_blank">{{school.schoolUrl}}</a></td>
              </tr>
            </table>
          </a-tab-pane>
          <a-tab-pane key="2" tab="教师信息">
            Content of Tab Pane 2
          </a-tab-pane>
          <a-tab-pane key="3" tab="专业信息">
            Content of Tab Pane 3
          </a-tab-pane>
          <a-tab-pane key="3" tab="照片墙">
            Content of Tab Pane 3
          </a-tab-pane>
        </a-tabs>
      </div>
      <div style="height: 60px;"></div>

    </div>
  </div>
</template>

<script>
  import {detailFindId} from '@/api/admin/school'
  import moment from 'moment'

  export default {
    data() {
      return {
        moment,
        schoolId: '',
        colleges: [],
        profess: [],
        school: {},
        teachers: [],
        pic: [],
        schoolTitle: [],
        schoolDetail: [],
        schoolPhoto: []
      }
    },
    mounted() {
      this.schoolId = this.$route.query.schoolId
      this.detailFind()
    },
    methods: {
      detailFind() {
        detailFindId({schoolId: this.schoolId}).then(res => {
          //分门别类数据
          this.collapse = res.data.collapse
          this.profess = res.data.profess
          this.school = {...res.data.school}
          this.teachers = res.data.teachers
          this.pic = res.data.pic
          // 图片分类
          for (let item of this.pic) {
            switch (item.picType) {
              case 'schoolTitle':
                this.schoolTitle.push(item)
                break
              case 'schoolDetail':
                this.schoolDetail.push(item)
                break
              case 'schoolPhoto':
                this.schoolPhoto.push(item)
                break
            }
          }
        })
      }
    }
  }
</script>

<style scoped>
  .mh {
    min-height: 1000px;
  }

  .div-background {
    background: rgba(153, 144, 144, 0.15);
    padding: 5% 15%;
  }

  .div-content {
    background: white;
  }

  .title {
    text-align: center;
  }

  .clear:after{
    content: '';
    clear: both;
    display: block;
  }

  .plr50{
    padding: 0 50px;
  }

  .mb30{
    margin-bottom: 30px;
  }

  .ant-carousel >>> .slick-slide {
    text-align: center;
    height: 300px;
    line-height: 300px;
    background: #364d79;
    overflow: hidden;
  }

  .ant-carousel >>> .slick-slide h3 {
    color: #fff;
  }
</style>