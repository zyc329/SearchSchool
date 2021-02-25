<template>
  <div class="div-background mh w100">
    <div class="mh w100 div-content">
      <div style="width: 100%;text-align: center;font-size: 60px;">讨论区</div>
      <a-button type="dashed" @click="$router.go(-1)">返回</a-button>
      <a-row>
        <a-col :span="2" style="text-align: right;font-size: 24px;">评论：</a-col>
        <a-col :span="22">
          <textarea v-model="comment" rows="5" style="width: 100%"></textarea>
        </a-col>
      </a-row>
      <a-row style="text-align: right;margin-bottom: 20px;">
        <a-button type="primary" @click="clickComments">评论</a-button>
      </a-row>

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
    </div>
    <div style="height: 60px"></div>
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
  </div>
</template>

<script>
  import * as utils from '@/utils/utilZengh'
  import {commentsFindList, commentsAdd, commentsDelete} from '@/api/admin/comment'
  import {sensitiveWords} from '@/utils/sensitiveWords'
  export default {
    data() {
      return {
        utils,
        sensitiveWords,
        visible: false,
        title: '',
        loading: false,
        replyContent: '',
        selectRow: {},
        selectRow1: {},
        replyType: '',
        comment: '',
        commentList: [],
      }
    },
    mounted() {
      this.getComments()
    },
    methods: {
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
          schoolId: '1111111',
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
            schoolId: '1111111',
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
            schoolId: '1111111',
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
        commentsFindList({schoolId: '1111111'}).then(res => {
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
    padding: 20px;
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
