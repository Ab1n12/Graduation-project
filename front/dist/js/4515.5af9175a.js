"use strict";(self["webpackChunktemplate"]=self["webpackChunktemplate"]||[]).push([[4515],{4515:(t,e,a)=>{a.r(e),a.d(e,{default:()=>h});var i=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticStyle:{display:"flex"}},[e("Input",{staticStyle:{"margin-right":"10px"},attrs:{readonly:"",placeholder:t.placeholder,clearable:t.clearable},on:{"on-clear":t.clearSelect},model:{value:t.departmentTitle,callback:function(e){t.departmentTitle=e},expression:"departmentTitle"}}),e("Poptip",{attrs:{transfer:"",trigger:"click",placement:"right",title:"选择部门",width:"250"}},[e("Button",{attrs:{icon:"md-list"}},[t._v("选择部门")]),e("div",{attrs:{slot:"content"},slot:"content"},[e("Input",{attrs:{suffix:"ios-search",placeholder:"输入部门名搜索",clearable:""},on:{"on-change":t.searchDep},model:{value:t.searchKey,callback:function(e){t.searchKey=e},expression:"searchKey"}}),e("div",{staticClass:"dep-tree-bar"},[e("Tree",{attrs:{data:t.dataDep,"load-data":t.loadData,multiple:t.multiple},on:{"on-select-change":t.selectTree}}),t.depLoading?e("Spin",{attrs:{size:"large",fix:""}}):t._e()],1)],1)],1)],1)])},l=[],s=a(7877);const n={name:"departmentTreeChoose",props:{multiple:{type:Boolean,default:!1},clearable:{type:Boolean,default:!0},placeholder:{type:String,default:"点击选择部门"}},data(){return{depLoading:!1,departmentTitle:"",searchKey:"",dataDep:[],selectDep:[],departmentId:[]}},methods:{initDepartmentData(){(0,s.E5)().then((t=>{t.success&&(t.result.forEach((function(t){t.isParent&&(t.loading=!1,t.children=[]),-1==t.status&&(t.title="[已禁用] "+t.title,t.disabled=!0)})),this.dataDep=t.result)}))},loadData(t,e){(0,s.G8)(t.id).then((t=>{t.success&&(t.result.forEach((function(t){t.isParent&&(t.loading=!1,t.children=[]),-1==t.status&&(t.title="[已禁用] "+t.title,t.disabled=!0)})),e(t.result))}))},searchDep(){this.searchKey?(this.depLoading=!0,(0,s.N8)({title:this.searchKey}).then((t=>{this.depLoading=!1,t.success&&(t.result.forEach((function(t){-1==t.status&&(t.title="[已禁用] "+t.title,t.disabled=!0)})),this.dataDep=t.result)}))):this.initDepartmentData()},selectTree(t){let e=[],a="";t.forEach((t=>{e.push(t.id),a=""==a?t.title:a+"、"+t.title})),this.departmentId=e,this.departmentTitle=a,this.multiple?this.$emit("on-change",this.departmentId):this.$emit("on-change",this.departmentId[0])},clearSelect(){this.departmentId=[],this.departmentTitle="",this.initDepartmentData(),this.multiple?this.$emit("on-change",[]):this.$emit("on-change",""),this.$emit("on-clear")},setData(t,e){this.departmentTitle=e,this.multiple?this.departmentId=t:(this.departmentId=[],this.departmentId.push(t)),this.$emit("on-change",this.departmentId)}},mounted(){this.initDepartmentData()}},r=n;var d=a(1001),c=(0,d.Z)(r,i,l,!1,null,null,null);const h=c.exports}}]);