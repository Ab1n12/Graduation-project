<template>
  <div>
    <div class="main-body-content">
      <div style="display: flex; align-items: flex-start">
        <div style="width: 200px; border: 1px solid #ddd; border-radius: 5px; height: calc(50vh + 125px);">
          <div style="padding: 10px; font-size: 15px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee; text-align: center">教师用户</div>
          <div class="user-list-box">
            <div class="user-list-item" v-for = "item in users.admin" @click="selectToUser(item)">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role+ '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.nickname }}</span>
              <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.nickname]">{{ unRead?.[item.role + '_' + item.nickname] }}</div>
            </div>
          </div>
        </div>
        <div style="width: 200px; border: 1px solid #ddd; border-radius: 5px; height: calc(50vh + 125px);">
          <div style="padding: 10px; font-size: 15px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee; text-align: center">学生用户</div>
          <div class="user-list-box">
            <div class="user-list-item" v-for = "item in users.stu" @click="selectToUser(item)">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="flex: 1; margin-left: 10px;" :style="{ color: item.role+ '_' + item.name === toUser ? '#3a8ee6' : '' }">{{ item.nickname }}</span>
              <div class="user-list-item-badge" v-if="unRead?.[item.role + '_' + item.nickname]">{{ unRead?.[item.role + '_' + item.nickname] }}</div>
            </div>
          </div>
        </div>
        <!--  中间部分  -->
        <div style="width: 50%; border: 1px solid #ddd; border-radius: 5px; background-color: #f1f1f1; margin: 0 10px;">
          <div style="padding: 20px 0; text-align: center; border-bottom: 1px solid #ddd; color: #000; background-color: #eee; height: 60px">
            {{ toUser?.substring(toUser.indexOf('_') + 1) }}
          </div>
          <div class="im-message-box">
            <div v-for="item in messages" :key="item.id">
              <!--  右边的气泡 -->
              <div style="display: flex; flex-direction: row-reverse; align-items: flex-start"
                   v-if="item.fromuser === fromUser">
                <img :src="item.fromavatar" alt=""
                     style="width: 40px; height: 40px; border-radius: 50%; margin-left: 10px">
                <div class="im-message im-message-right" v-html="item.content"
                     v-if="item.type === 'text'"></div>
                <div class="im-message" style="padding: 0" v-if="item.type === 'img'">
                  <!-- 注意  el-image 的load函数必须加上，否则无法触发滚动条到最底端 -->
                  <el-image style="width: 100px" :src="item.content" alt=""
                            :preview-src-list=[item.content] @load="scrollToBottom"></el-image>
                </div>
                <div class="im-message im-message-download" v-if="item.type === 'file'"
                     @click="download(item.content)">
                  <div><i class="el-icon-folder-opened"></i>
                    <span>{{ item.content.substring(item.content.indexOf('-') + 1) }}</span>
                  </div>
                </div>
              </div>

              <!--  左边的气泡 -->
              <div style="display: flex; align-items: flex-start" v-else>
                <img :src="item.fromavatar" alt=""
                     style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px">
                <div style="width: 100%">
                  <div style="color: #888; font-size: 12px; width: 50%">
                    {{ item.fromuser?.substring(item.fromuser.indexOf('_') + 1) }}
                  </div>
                  <div class="im-message im-message-left" v-html="item.content"
                       v-if="item.type === 'text'"></div>
                  <div class="im-message" style="padding: 0" v-if="item.type === 'img'">
                    <!-- 注意  el-image 的load函数必须加上，否则无法触发滚动条到最底端 -->
                    <el-image style="width: 100px" :src="item.content" alt=""
                              :preview-src-list=[item.content]
                              @load="scrollToBottom"></el-image>
                  </div>
                  <div class="im-message im-message-download" v-if="item.type === 'file'"
                       @click="download(item.content)">
                    <div><i class="el-icon-folder-opened"></i>
                      <span>{{ item.content.substring(item.content.indexOf('-') + 1) }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 输入区域 -->
          <div style="padding: 10px 5px; border-top: 1px solid #ddd; display: flex; align-items: center; width: 100%;">
            <el-popover placement="top" width="300" trigger="click">
              <div class="emoji-box">
                <span v-for="(item, index) in emojis" :key="index"
                      style="margin-right: 5px; font-size: 20px; cursor: pointer" v-html="item"
                      @click="clickEmoji(item)">
                </span>
              </div>
              <i slot="reference" class="fa fa-smile-o" style="font-size: 22px; color: #666;"></i>
            </el-popover>
            <div style="margin-left: 5px">
              <el-upload action="http://localhost:8081/files/upload" :show-file-list="false" :on-success="handleFile">
                <i class="fa fa-folder-open-o" style="font-size: 20px; color: #666;"></i>
              </el-upload>
            </div>
            <div id="im-content" contenteditable
                 style="flex: 1; background-color: #fff; margin: 0 5px; padding: 10px; border: 1px solid #ddd; border-radius: 2px; outline: none; font-size: 14px;"></div>
            <el-button type="primary" @click="send" style="border: none">发送</el-button>
          </div>

        </div>
        <!--  中间部分结束  -->
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import emojis from "@/assets/emoji";
import {getUserAll, getTea, getStu, getAll} from "@/views/roster/user/api";

let client
export default {
  data() {
    return {
      user: {},
      permission: [],
      emojis: [],
      messages: [],
      users: {},
      fromUser: '',
      toUser: '',
      toAvatar: '',
      unRead: {}
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  mounted() {
    this.emojis = emojis.split(',')
    this.user = JSON.parse(localStorage.getItem('userInfo') || "{}")
    this.fromUser = this.user.nickname

    client = new WebSocket(`ws://localhost:8081/imserverSingle`)
    client.onopen = () => {
      console.log('websocket open')
    }
    client.onclose = () => {  // 页面刷新的时候和后台websocket服务关闭的时候
      console.log('websocket close')
    }
    client.onmessage = (msg) => {
      if (msg.data) {
        let json = JSON.parse(msg.data)
        if (json.content && (json.fromuser === this.fromUser && json.touser === this.toUser)
            || json.touser === this.fromUser && json.fromuser === this.toUser) {  // 聊天消息
          this.messages.push(json)
          this.scrollToBottom()  // 滚动页面到最底部
        }
        // 加载消息数字
        if (this.toUser === json.fromuser) {
          this.setUnReadNums()   // 清空正在聊天人的消息数字
        } else {
          this.loadUnReadNums()
        }
      }
    }

    // 加载聊天数据
    this.load()

   // 查询用户
    getUserAll().then(res => {
      res.result = res.result.filter(v => v.type === 1)
      this.$set(this.users, 'admin', res.result)
    })

    getUserAll().then(res => {
      res.result = res.result.filter(v => v.type === 0)
      this.$set(this.users, 'stu', res.result)
    })


  },
  beforeDestroy() {
    if (client) {
      client.close()
    }
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    load() {
      request.get('/imsingle?fromUser=' + this.fromUser + '&toUser=' + this.toUser).then(res => {
        if (res.code === '0') {
          this.messages = res.data
          this.scrollToBottom()  // 滚动条滚动到最底部
        } else {
          this.$notify.error(res.msg)
        }
        this.loadUnReadNums()
      })
    },
    setUnReadNums() {
      request.get('/imsingle?fromUser=' + this.fromUser + '&toUser=' + this.toUser).then(res => {
        this.loadUnReadNums()
      })
    },
    loadUnReadNums() {
      // 查询未读数量
      request.get('/imsingle/unReadNums?toUsername=' + this.fromUser).then(res => {
        this.unRead = res.data
      })
    },
    send() {
      if (!this.toUser) {
        this.$notify.error('请选择聊天用户')
        return
      }

      if (client) {
        let message = this.getMessage('text')
        message.fromavatar = JSON.parse(localStorage.getItem('userInfo') || "{}").avatar
        client.send(JSON.stringify(message))
      }
      document.getElementById('im-content').innerHTML = ''  // 清空输入框
    },
    selectToUser(item) {
      if (item.nickname === this.fromUser){
        this.$notify.error('聊天对象不能选择自己')
        return
      }
      this.toUser = item.nickname
      this.toAvatar = item.avatar
      //查询聊天记录
      this.load()
    },
    download(file) {
      window.open(file)
    },
    getMessage(type) {
      let inputBox = document.getElementById('im-content')
      const content = inputBox.innerHTML
      if (!content && type === 'text') {
        this.$notify.error('请输入聊天内容')
        return
      }

      return {
        fromuser: this.fromUser,
        fromavatar: this.user.avatar,
        touser: this.toUser,
        toavatar: this.toAvatar,
        content: content,
        type: type
      }
    },
    handleFile(file) {
      if (client) {

        let message = this.getMessage('img')
        message.content = file.data
        let extName = file.data.substring(file.data.lastIndexOf('.') + 1)
        if (['png', 'jpg', 'jpeg', 'gif', 'bmp', 'tiff', 'svg', 'webp'].includes(extName)) {
          message.type = 'img'
        } else {
          message.type = 'file'
        }
        client.send(JSON.stringify(message))
      }
    },
    clickEmoji(emoji) {
      document.getElementById('im-content').innerHTML += emoji
    },
    scrollToBottom() {
      this.$nextTick(() => {
        // 设置聊天滚动条到底部
        let imMessageBox = document.getElementsByClassName("im-message-box")[0]
        //设置滚动条到最底部
        imMessageBox.scrollTop = imMessageBox.scrollHeight
        console.log('触发滚动')
      })
    },
  }
}
</script>

<style scoped>
.im-message-box {
  height: 50vh;
  padding: 10px;
  overflow-y: auto;
  background-color: white;
}

.emoji-box {
  height: 200px;
  overflow-y: scroll;
  line-height: 30px;
}

.user-list-box {
  overflow-y: auto;
  /* overflow-y:scroll; */
  overflow-x:hidden;
  height: 91.5%;
}

.im-message-box::-webkit-scrollbar, .emoji-box::-webkit-scrollbar, .user-list-box::-webkit-scrollbar {
  width: 4px;
}

.im-message-box::-webkit-scrollbar-thumb, .emoji-box::-webkit-scrollbar-thumb, .user-list-box::-webkit-scrollbar-thumb {
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.1);
}

.im-message-box::-webkit-scrollbar-track, .emoji-box::-webkit-scrollbar-track, .user-list-box::-webkit-scrollbar-track {
  border-radius: 0;
  background: rgba(0, 0, 0, 0.1);
}

.im-message {
  font-size: 14px;
  padding: 10px;
  margin: 5px 0;
  border-radius: 5px;
  color: #000;
  max-width: 50%;
  line-height: 20px;
  width: fit-content;
}

.im-message-left {
  background-color: #fff;
}

.im-message-right {
  background-color: #26d470;
}

.im-message-download {
  background-color: #DBEDFF;
  cursor: pointer;
}

.user-list-item {
  display: flex;
  padding: 10px;
  align-items: center;
  border-bottom: 1px solid #eee;
  cursor: pointer;
  font-size: 14px;
  overflow-y:auto
}

.user-list-item-badge {
  padding: 2px 6px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  color: white;
  background-color: red;
}

.user-list-item:nth-last-child(1) {
  border: none;
}
</style>
