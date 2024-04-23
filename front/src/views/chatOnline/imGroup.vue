<template>
  <div>
    <div class="main-body-content">
      <div style="display: flex; align-items: flex-start">
        <div style="width: 200px;  margin-right: 10px; border: 1px solid #ddd; border-radius: 5px;">
          <div style="padding: 20px 10px; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">在线用户({{ users.length }})</div>
          <div class="user-list-box">
            <div class="user-list-item" v-for="(item, index) in users" :key="index">
              <img :src="item.avatar" style="width: 30px; height: 30px; border-radius: 50%">
              <span style="margin-left: 10px">{{ item.name }}</span>
            </div>
          </div>
        </div>

        <!--  中间部分  -->
        <div style="width: 50%; border: 1px solid #ddd; border-radius: 5px; background-color: #f1f1f1;">
          <div style="padding: 20px 0; text-align: center; border-bottom: 1px solid #ddd; color: #000; background-color: #eee">聊天室</div>
          <div class="im-message-box">
            <div v-for="item in messages" :key="item.id">
              <!--  右边的气泡 -->
              <div style="display: flex; flex-direction: row-reverse; align-items: flex-start" v-if="item.name === user.name">
                <img :src="item.avatar" alt="" style="width: 40px; height: 40px; border-radius: 50%; margin-left: 10px">
                <div class="im-message im-message-right" v-html="item.content" v-if="item.type === 'text'"></div>
                <div class="im-message" style="padding: 0" v-if="item.type === 'img'">
                  <!-- 注意  el-image 的load函数必须加上，否则无法触发滚动条到最底端 -->
                  <el-image style="width: 100px" :src="item.content" alt="" :preview-src-list=[item.content] @load="scrollToBottom"></el-image>
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
                <img :src="item.avatar" alt="" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px">
                <div style="width: 100%">
                  <div style="color: #888; font-size: 12px; width: 50%">{{ item.name }}</div>
                  <div class="im-message im-message-left" v-html="item.content"  v-if="item.type === 'text'"></div>
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
                      @click="clickEmoji(item)"></span>
              </div>
              <i slot="reference" class="fa fa-smile-o" style="font-size: 22px; color: #666;"></i>
            </el-popover>
            <div style="margin-left: 5px">
              <el-upload action="http://localhost:8081/files/upload" :show-file-list="false" :on-success="handleFile">
                <i class="fa fa-folder-open-o" style="font-size: 20px; color: #666;"></i>
              </el-upload>
            </div>
            <div id="im-content" contenteditable style="flex: 1; background-color: #fff; margin: 0 5px; padding: 10px; border: 1px solid #ddd; border-radius: 2px; outline: none; font-size: 14px;"></div>
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
import {getStore} from "@/libs/storage";

let client
export default {
  data() {
    return {
      user: {},
      permission: [],
      emojis: [],
      messages: [],
      users: [],
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  mounted() {
    this.emojis = emojis.split(',')
    this.user = JSON.parse(localStorage.getItem('user') || "{}")
    let name = window.btoa(encodeURI(JSON.parse(getStore("userInfo")).nickname))
    let avatar = window.btoa(encodeURI(JSON.parse(getStore("userInfo")).avatar))

    client = new WebSocket(`ws://localhost:8081/imserver/${name}/${avatar}`)
    client.onopen = () => {
      console.log('websocket open')
    }
    client.onclose = () => {  // 页面刷新的时候和后台websocket服务关闭的时候
      console.log('websocket close')
    }
    client.onmessage = (msg) => {
      if (msg.data) {
        let json = JSON.parse(msg.data)
        if (json.name && json.content) {  // 聊天消息
          this.messages.push(json)
          this.scrollToBottom()  // 滚动页面到最底部
        }
        if (json.users && json.users.length) {

          this.users = json.users
          console.log('onmessage', this.users)
        }
      }
    }
    // 加载聊天数据
    this.load()

  },
  beforeDestroy() {
    if (client) {
      client.close()
    }
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    download(file) {
      window.open(file)
    },
    handleFile(file) {
      if (client) {
        let message = {name: this.user.name, content: file.data, avatar: this.user.avatar}
        let extName = file.data.substring(file.data.lastIndexOf('.') + 1)
        if (['png', 'jpg', 'jpeg', 'gif', 'bmp', 'tiff', 'svg', 'webp'].includes(extName)) {
          message.type = 'img'
        } else {
          message.type = 'file'
        }
        let avatar = JSON.parse(getStore("userInfo")).avatar
        message.name = JSON.parse(getStore("userInfo")).nickname
        message.avatar = avatar
        client.send(JSON.stringify(message))
      }
    },
    load() {
      request.get('/imGroup').then(res => {
        if (res.code === '0') {
          this.messages = res.data
          this.scrollToBottom()  // 滚动条滚动到最底部
          this.$message.success('欢迎进入群组聊天室')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    send() {
      debugger
      let inputBox = document.getElementById('im-content')
      const content = inputBox.innerHTML
      if (!content) {
        this.$notify.error('请输入聊天内容')
        return
      }

      let avatar = JSON.parse(getStore("userInfo")).avatar
      let nickname = JSON.parse(getStore("userInfo")).nickname
      if (client) {
        let message = {name: this.user.name, content: content, avatar: this.user.avatar, type: 'text'}
        message.name = nickname
        message.avatar = avatar
        client.send(JSON.stringify(message))
      }
      inputBox.innerHTML = ''  // 清空输入框
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
  height: calc(50vh + 60px);
  overflow-y: scroll;
  background-color: #f8f8ff;
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
  padding: 10px;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #eee;
  cursor: default;
  font-size: 14px;
}
.user-list-item:nth-last-child(1) {
  border: none;
}
</style>
