"use strict";(self["webpackChunktemplate"]=self["webpackChunktemplate"]||[]).push([[6829],{6829:(t,e,s)=>{s.r(e),s.d(e,{default:()=>r});var i=function(){var t=this,e=t._self._c;return e("div",{staticClass:"topDiv",style:{height:t.clientHeight}},[e("Row",{attrs:{gutter:20}},[e("div",{staticClass:"body"},[e("img",{staticClass:"title",attrs:{src:s(6493),alt:""}}),e("div",{staticClass:"awayMenu"},[e("div",{staticClass:"awayLeft"},[e("span",{staticClass:"manage2"},[t._v(" 欢迎 "),e("span",[t._v(t._s(t.name))])]),e("div",{staticClass:"manage"},[t._v("登入地址："+t._s(t.location))])]),e("div",{staticClass:"bottom"},[e("span",{staticClass:"showtime"},[t._v(t._s(t.showtime))]),e("span",{staticClass:"showtime2"},[t._v(t._s(t.showtime2))])])]),e("div",{staticClass:"bigTips"},[e("span",{staticStyle:{color:"rgba(255,255,255,0.8)"}},[t._v("常用模块")]),e("span",{staticStyle:{}})]),e("div",{staticClass:"buttonMenu"},[e("div",{staticClass:"addMenuBox"},t._l(t.addMenuTempList,(function(s,i){return e("div",{key:i,staticClass:"addMenu",on:{click:function(e){return t.selectItem(s)}}},[t._v(" "+t._s(s.title)+" ")])})),0),e("div",{staticClass:"shu"})]),e("div",{staticClass:"bottomText",on:{click:t.toOwnMenu}},[t._v(' 添加"常用模块"? 点我 进入个人门户设置 ')])])])],1)},a=[],n=s(1955),o=s(2439);const c={name:"home",data(){return{name:"",showtime:"",showtime2:"",location:"公司内网",addMenuTempList:[],number1:0,number2:0,number3:0,number1List:[],number2List:[],number3List:[]}},methods:{init(){this.getMyDoorListFx();let t=JSON.parse(n.Z.get("userInfo"));this.name=t.nickname,this.getNowTime(),(0,o.v_)().then((t=>{t.success&&(this.location=t.result)})),this.timer=setInterval(this.getNowTime,1e3)},selectItem(t){void 0!=t.name&&"null"!=t.name&&this.$router.push({name:t.name})},toDaiBanPage(){this.$Message.success("正在开发，敬请期待！")},toFaQiPage(){this.$Message.success("正在开发，敬请期待！")},toJingBanPage(){this.$Message.success("正在开发，敬请期待！")},toOwnMenu(){this.$router.push("/myHome")},getMyDoorListFx(){var t=this;(0,o.lR)().then((e=>{t.addMenuTempList=e.result}))},getNowTime(){this.showtime=this.format(new Date,"yyyy年MM月dd日"),this.showtime2=this.format(new Date,"HH:mm:dd")}},mounted(){this.init(),this.clientHeight=`${document.documentElement.clientHeight}`;let t=this;window.onresize=function(){this.clientHeight=`${document.documentElement.clientHeight}`,t.$refs.page&&(t.$refs.page.style.minHeight=clientHeight-100+"px")}},watch:{clientHeight(){this.changeFixed(this.clientHeight)}}},l=c;var m=s(1001),h=(0,m.Z)(l,i,a,!1,null,"8403cc2c",null);const r=h.exports},2439:(t,e,s)=>{s.d(e,{KF:()=>a,e3:()=>l,lR:()=>n,uJ:()=>o,v_:()=>c});var i=s(7184);const a=t=>(0,i.j0)("/myDoor/getMyDoorList",t),n=t=>(0,i.j0)("/myDoor/getMyDoorList6",t),o=t=>(0,i.j0)("/myDoor/setMyDoorList",t),c=t=>(0,i.A_)("/common/ip/info",t),l=t=>(0,i.j0)("/user/edit",t)},6493:(t,e,s)=>{t.exports=s.p+"img/LZUBadge.00d3596a.png"}}]);