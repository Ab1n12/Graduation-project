"use strict";(self["webpackChunktemplate"]=self["webpackChunktemplate"]||[]).push([[3387],{3387:(t,e,a)=>{a.r(e),a.d(e,{default:()=>n});var s=function(){var t=this,e=t._self._c;return e("div",[e("Card",[e("div",{attrs:{slot:"title"},slot:"title"},[e("div",{staticClass:"edit-head"},[e("a",{staticClass:"back-title",on:{click:t.close}},[e("Icon",{attrs:{type:"ios-arrow-back"}}),t._v("返回 ")],1),e("div",{staticClass:"head-name"},[t._v("编辑")]),e("span"),e("a",{staticClass:"window-close",on:{click:t.close}},[e("Icon",{staticClass:"ivu-icon-ios-close",attrs:{type:"ios-close",size:"31"}})],1)])]),e("Form",{ref:"form",attrs:{model:t.form,"label-width":100,rules:t.formValidate,"label-position":"left"}},[e("FormItem",{attrs:{label:"课题名称",prop:"title"}},[e("Input",{staticStyle:{width:"570px"},attrs:{clearable:""},model:{value:t.form.title,callback:function(e){t.$set(t.form,"title",e)},expression:"form.title"}})],1),e("FormItem",{attrs:{label:"课题类型",prop:"type"}},[e("Select",{staticStyle:{width:"570px"},attrs:{clearable:""},model:{value:t.form.type,callback:function(e){t.$set(t.form,"type",e)},expression:"form.type"}},[e("Option",{attrs:{value:"毕业设计"}},[t._v("毕业设计")]),e("Option",{attrs:{value:"毕业论文"}},[t._v("毕业论文")])],1)],1),e("FormItem",{attrs:{label:"课题难度",prop:"level"}},[e("Select",{staticStyle:{width:"570px"},attrs:{clearable:""},model:{value:t.form.level,callback:function(e){t.$set(t.form,"level",e)},expression:"form.level"}},[e("Option",{attrs:{value:"简单"}},[t._v("简单")]),e("Option",{attrs:{value:"一般"}},[t._v("一般")]),e("Option",{attrs:{value:"复杂"}},[t._v("复杂")])],1)],1),e("FormItem",{attrs:{label:"剩余名额",prop:"quota"}},[e("Input",{staticStyle:{width:"570px"},attrs:{type:"textarea",rows:1,clearable:""},model:{value:t.form.quota,callback:function(e){t.$set(t.form,"quota",e)},expression:"form.quota"}})],1),e("FormItem",{attrs:{label:"选题要求",prop:"remark"}},[e("Input",{staticStyle:{width:"570px"},attrs:{type:"textarea",rows:4,"show-word-limit":"",maxlength:"250",clearable:""},model:{value:t.form.remark,callback:function(e){t.$set(t.form,"remark",e)},expression:"form.remark"}})],1),e("Form-item",{staticClass:"br"},[e("Button",{attrs:{loading:t.submitLoading,type:"primary"},on:{click:t.handleSubmit}},[t._v("提交并保存")]),e("Button",{on:{click:t.handleReset}},[t._v("重置")]),e("Button",{attrs:{type:"dashed"},on:{click:t.close}},[t._v("关闭")])],1)],1)],1)],1)},l=[],o=a(415);const i={name:"edit",components:{},props:{data:Object},data(){return{submitLoading:!1,form:{title:"",type:"",level:"",quota:"",remark:"",taskFile:""},formValidate:{}}},methods:{init(){this.handleReset(),this.form=this.data},changeTaskFile(t){this.form.taskFile=t.url},handleReset(){this.$refs.form.resetFields()},handleSubmit(){this.$refs.form.validate((t=>{t&&(0,o.wA)(this.form).then((t=>{this.submitLoading=!1,t.success&&(this.$Message.success("操作成功"),this.submited())}))}))},close(){this.$emit("close",!0)},submited(){this.$emit("submited",!0)}},mounted(){this.init()}},r=i;var c=a(1001),m=(0,c.Z)(r,s,l,!1,null,null,null);const n=m.exports},415:(t,e,a)=>{a.d(e,{BK:()=>r,C:()=>l,Gr:()=>o,J9:()=>m,cT:()=>c,dH:()=>n,wA:()=>i});var s=a(7184);const l=t=>(0,s.A_)("/topic/getByPage",t),o=t=>(0,s.j0)("/topic/insert",t),i=t=>(0,s.j0)("/topic/update",t),r=t=>(0,s.j0)("/topic/delByIds",t),c=t=>(0,s.j0)("/topic/uploadFile",t),m=t=>(0,s.j0)("/topic/auditTopic",t),n=t=>(0,s.j0)("/topic/checkNotTopic",t)}}]);