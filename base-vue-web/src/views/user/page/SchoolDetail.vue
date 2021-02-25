<template>
  <div class="div-background mh w100">
    <a-carousel :autoplay="true" dots-class="slick-dots slick-thumb" v-if="schoolDetail.length>0">
      <div v-for="item in schoolDetail" :key="item.picId">
        <img style="height: 300px; width: 100%" :src="`/download?picId=${item.picId}`"/>
      </div>
    </a-carousel>
    <div class="mh w100 div-content" v-if="isShow">
      <div class="title w100">
        <h1>{{ school.schoolName }}</h1>
      </div>
      <div class="w100 clear plr50 mb30">
        <div style="width: 400px; height: 400px; float: left" v-if="schoolTitle.length>0">
          <p style="color: #1890ff; font-size: 22px; font-family: 楷体; margin-bottom: 0; font-weight: bolder">校标</p>
          <img style="width: 350px; height: 350px" :src="`/download?picId=${schoolTitle[0].picId}`"/>
        </div>
        <div style="float: left; margin-left: 20px">
          <span> 学校简介：{{ school.schoolIntroduction }} </span>
        </div>
      </div>
      <div class="plr50 w100">
        <a-tabs default-active-key="1" v-model="tabsKey" @change="tabChange">
          <a-tab-pane key="1" tab="学校信息">
            <table v-if="school" border="1" align="center" width="100%" style="font-size: 16px; font-family: 黑体：">
              <tr>
                <td align="center" width="30%">学校名称</td>
                <td>{{ school.schoolName }}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校类型</td>
                <td>{{ school.schoolType === '0' ? '专科' : '本科' }}</td>
              </tr>
              <tr>
                <td align="center" width="30%">创校时间</td>
                <td>{{ moment(school.schoolTime).format('yyyy-MM-DD') }}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校地址</td>
                <td>{{ school.schoolSrc }}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校负责人</td>
                <td>{{ school.schoolHead }}</td>
              </tr>
              <tr>
                <td align="center" width="30%">负责人电话</td>
                <td>{{ school.schoolTel }}</td>
              </tr>
              <tr>
                <td align="center" width="30%">学校官网</td>
                <td>
                  <a :href="school.schoolUrl" target="_blank">{{ school.schoolUrl }}</a>
                </td>
              </tr>
            </table>
          </a-tab-pane>
          <a-tab-pane key="2" tab="教师信息" class="clear">
            <a-card hoverable style="width: 240px; margin-right: 60px; float: left" v-for="item in teachers">
              <img slot="cover" alt="老师照片" :src="`/download?picId=${item.profilePicture}`"/>
              <a-card-meta :title="'教师名称：' + item.teacherName">
                <template slot="description">
                  <p>基本信息：</p>
                  <span>年龄：{{ item.teachingAge }}</span
                  ><span style="float: right">职称：{{ item.title }}</span
                ><br/>
                  <span>授课专业：{{ utils.parse_str(item.professionalId, professionalDirs) }}</span
                  ><br/>
                  <p>老师简介：{{ item.teacherIntroduction }}</p>
                </template>
              </a-card-meta>
            </a-card>
          </a-tab-pane>
          <a-tab-pane key="6" tab="专业信息">
            <div style="width: 100%">
              <a-input style="width: 20%" v-model="professionalName"></a-input>
              <a-button type="primary" @click="getProfessional" style="margin-right: 20px;margin-left: 20px;">搜索专业</a-button>
              <a-button type="primary" @click="allProfessional">显示专业</a-button>
            </div>
            <div class="w100 clear">
              <a-card v-for="(item,index) in professionalData"
                      style="margin-top: 20px;background: rgba(255,255,255,0.8)">
                <div style="width: 100%;text-align: center;" slot="title">{{item.professionalName}}</div>
                <div>
                  {{item.professionalIntroduction}}
                </div>
              </a-card>
            </div>
          </a-tab-pane>
          <a-tab-pane key="3" tab="分数线对比">
            <a-checkbox-group v-model="professionalSelect" name="checkboxgroup" :options="professionalOption"/>
            <a-button type="primary" @click="seeEcheats">查询</a-button>
            <div style="width: 100%; margin-top: 60px">
              <div style="width: 100%">
                <a-select style="width: 20%" v-model="queryYear" @change="seeEcheats">
                  <a-select-option v-for="item in [5, 10]" :key="item" :value="item"> 近{{ item }}年分数线</a-select-option>
                </a-select>
              </div>
              <a-spin :spinning="loading" style="margin: 50px">
                <div style="width: 100%">
                  <v-chart :options="options" style="width: 100%"/>
                </div>
              </a-spin>
            </div>
          </a-tab-pane>
          <a-tab-pane key="4" tab="照片墙">
            <div class="w100 clear">
              <div v-for="pic in schoolPhoto" style="float: left; width: 300px;height: 300px; padding: 25px;">
                <img style="width: 250px;height: 250px;" :src="`/download?picId=${pic.picId}`"/>
              </div>
            </div>
          </a-tab-pane>
          <a-tab-pane key="5" tab="评价">
            <a-card v-for="(item,index) in commentList" size="small" :title="`第${index+1}条评论`"
                    style="margin-bottom: 10px;">
              <a slot="extra" @click="delectComment(item.commentsId)"
                 v-if="item.criticsId===$store.getters.userInfo.userId">删除评论</a>
              <div>
                {{item.criticsName+' 评论：'+item.comments}}
                <a-button type="link" @click="replyModal(item)">
                  回复
                </a-button>
                <div style="margin-left: 20px;margin-bottom: 10px;" v-for="reply in item.replyList">
                  {{reply.criticsName+' 回复了 '+reply.toCriticsName+'：'+reply.comments}}
                  <a @click="replyModal1(item,reply)">
                    回复
                  </a>
                  <a
                    @click="delectComment(reply.commentsId)"
                    v-if="reply.criticsId===$store.getters.userInfo.userId"
                    type="link">
                    删除
                  </a>
                </div>
              </div>
            </a-card>
            <div style="margin-top: 20px;">
              <a-row>
                <a-col :span="1" style="text-align: right;">评论：</a-col>
                <a-col :span="23">
                  <textarea v-model="comment" rows="5" style="width: 100%"></textarea>
                </a-col>
              </a-row>
              <a-row style="text-align: right;">
                <a-button type="primary" @click="clickComments">评论</a-button>
              </a-row>
            </div>
          </a-tab-pane>
        </a-tabs>
      </div>
      <a-modal :title="title"
               width="40%"
               :visible="visible"
               :destroyOnClose="true"
               :closable="false">
        <textarea v-model="replyContent" rows="5" style="width: 100%"></textarea>
        <div slot="footer">
          <a-button type="primary" :loading="loading" @click="reply"> 回复</a-button>
          <a-button @click="visible=false">关闭</a-button>
        </div>
      </a-modal>
      <div style="height: 60px"></div>
    </div>
  </div>
</template>

<script>
  import {detailFindId} from '@/api/admin/school'
  import moment from 'moment'
  import {professionalFindList} from '@/api/admin/specialty'
  import ECharts from 'vue-echarts'
  import {scoreByColleges} from '@/api/admin/score'
  import {parse_str} from '@/utils/utilZengh'
  import * as utils from '@/utils/utilZengh'
  import {commentsFindList, commentsAdd, commentsDelete} from '@/api/admin/comment'
  import {sensitiveWords} from '@/utils/sensitiveWords'

  export default {
    components: {
      'v-chart': ECharts,
    },
    data() {
      return {
        moment,
        utils,
        sensitiveWords,
        isShow: false,
        loading: false,
        schoolId: '',
        colleges: [],
        profess: [],
        school: {},
        teachers: [],
        pic: [],
        schoolTitle: [],
        schoolDetail: [],
        schoolPhoto: [],
        professionalDirs: [],
        professionalOption: [],
        professionalSelect: [],
        options: {},
        // 查询近几年
        queryYear: 10,
        tabsKey: '1',
        comment: '',
        commentList: [],
        visible: false,
        title: '',
        replyContent: '',
        selectRow: {},
        selectRow1: {},
        replyType: '',
        professionalData: [],
        professionalName: ''
      }
    },
    mounted() {
      this.schoolId = this.$route.query.schoolId
      this.queryAll()
    },
    methods: {
      tabChange() {
        let that = this
        if (this.tabsKey === '5' && this.utils.isEmpty(this.$store.getters.userInfo)) {
          this.$info({
            title: '提示',
            content: '需要注册账号才能查看参与评价！',
            onOk() {
              that.tabsKey = '1'
            }
          })
        }
      },
      async queryAll() {
        this.isShow = false
        await this.detailFind()
        await this.getProfessional()
        await this.getComments()
        this.isShow = true
      },
      replyModal(item) {
        this.replyType = 0
        this.selectRow = item
        this.title = '回复' + item.criticsName
        this.visible = true
      },
      replyModal1(item, reply) {
        this.replyType = 1
        this.selectRow = item
        this.selectRow1 = reply
        this.title = '回复' + item.criticsName
        this.visible = true
      },
      delectComment(id) {
        commentsDelete({commentsId: id}).then(res => {
          this.$message.success('删除成功！')
          this.getComments()
        }).catch((err) => {
          this.$message.error(err.message)
        })
      },
      clickComments() {
        if (this.comment.length <= 0) {
          this.$message.error('评论不能为空！')
          return
        }
        for(let i=0;i<this.sensitiveWords.length;i++){
          this.comment = this.comment.replace(this.sensitiveWords[i],"*");
        }
        let params = {
          schoolId: this.schoolId,
          isComments: 1,
          comments: this.comment,
          criticsName: this.$store.getters.userInfo.userName,
          criticsId: this.$store.getters.userInfo.userId
        }
        commentsAdd(params).then(res => {
          this.$message.success('评论成功！')
          this.comment = ''
          this.getComments()
        }).catch((err) => {
          this.$message.error(err.message)
        })
      },
      closeModal() {
        this.replyContent = ''
        this.selectRow = {}
        this.selectRow1 = {}
        this.visible = false
      },
      reply() {
        if (this.replyContent.length <= 0) {
          this.$message.error('回复不能为空！')
          return
        }
        for(let i=0;i<this.sensitiveWords.length;i++){
          this.replyContent = this.replyContent.replace(this.sensitiveWords[i],"*");
        }
        let params = {}
        if (this.replyType === 0) {
          params = {
            schoolId: this.schoolId,
            isComments: 0,
            comments: this.replyContent,
            criticsName: this.$store.getters.userInfo.userName,
            criticsId: this.$store.getters.userInfo.userId,
            toCommentsId: this.selectRow.commentsId,
            toCriticsName: this.selectRow.criticsName,
            toCriticsId: this.selectRow.criticsId
          }
        } else {
          params = {
            schoolId: this.schoolId,
            isComments: 0,
            comments: this.replyContent,
            criticsName: this.$store.getters.userInfo.userName,
            criticsId: this.$store.getters.userInfo.userId,
            toCommentsId: this.selectRow.commentsId,
            toCriticsName: this.selectRow1.criticsName,
            toCriticsId: this.selectRow1.criticsId
          }
        }

        commentsAdd(params).then(res => {
          this.$message.success('回复成功！')
          this.closeModal()
          this.getComments()
        }).catch((err) => {
          this.$message.error(err.message)
        })
      },
      getComments() {
        commentsFindList({schoolId: this.schoolId}).then(res => {
          let arr = _.cloneDeep(res.data)
          let returnArr = []
          for (let item of arr) {
            if (item.isComments === '1') {
              item.replyList = []
              returnArr.push(item)
            }
          }
          for (let item of returnArr) {
            for (let reply of arr) {
              if (item.commentsId === reply.toCommentsId) {
                item.replyList.push(reply)
              }
            }
          }
          this.commentList = returnArr
        }).catch((err) => {
          this.$message.error(err.message)
        })
      },
      detailFind() {
        detailFindId({schoolId: this.schoolId}).then((res) => {
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
      },
      allProfessional() {
        this.professionalName = ''
        this.getProfessional()
      },
      getProfessional() {
        professionalFindList({schoolId: this.schoolId, professionalName: this.professionalName})
          .then((res) => {
            let professional = []
            let professionalOption = []
            this.professionalData = res.data
            for (let item of res.data) {
              professional.push({value: item.professionalId, text: item.professionalName})
              professionalOption.push({label: item.professionalName, value: item.professionalId})
            }
            this.professionalDirs = professional
            this.professionalOption = professionalOption
          })
          .catch((err) => {
            this.$message.error(err.message)
          })
      },
      seeEcheats() {
        if (this.professionalSelect.length <= 0) {
          this.$message.error('请先选择你要查询的专业！')
          return
        }
        this.loading = true
        let param = {
          year: this.queryYear,
          professionalIds: this.professionalSelect.join(','),
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
          series: series,
        }
        return options
      },
    },
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
    background: rgba(219, 218, 212, 0.8);
  }

  .title {
    text-align: center;
  }

  .clear:after {
    content: '';
    clear: both;
    display: block;
  }

  .plr50 {
    padding: 0 50px;
  }

  .mb30 {
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
