"use strict";(self["webpackChunktemplate"]=self["webpackChunktemplate"]||[]).push([[5786],{5786:(t,e,a)=>{a.r(e),a.d(e,{default:()=>d});var l=function(){var t=this;t._self._c;return t._m(0)},i=[function(){var t=this,e=t._self._c;return e("div",[e("div",{attrs:{id:"container"}})])}],n=a(1821);const s={name:"test-page",components:{},props:{},data(){return{stackedBarPlot:{},userData:[{title:"零食类",type:"进货量",value:6},{title:"零食类",type:"交易量",value:5},{title:"零食类",type:"询问数",value:2},{title:"零食类",type:"售后数",value:10},{title:"冷冻类",type:"进货量",value:3},{title:"冷冻类",type:"交易量",value:3},{title:"冷冻类",type:"询问数",value:1},{title:"冷冻类",type:"售后数",value:5},{title:"烟酒类",type:"进货量",value:10},{title:"烟酒类",type:"交易量",value:11},{title:"烟酒类",type:"询问数",value:7},{title:"烟酒类",type:"售后数",value:15}]}},methods:{init(){this.initAntvFx()},initAntvFx(){var t=this.userData;this.stackedBarPlot=new n.Bar("container",{data:t,isGroup:!0,xField:"value",yField:"title",seriesField:"type",dodgePadding:4,label:{position:"middle",layout:[{type:"interval-adjust-position"},{type:"interval-hide-overlap"},{type:"adjust-color"}]}}),this.stackedBarPlot.render()}},mounted(){this.init()}},r=s;var u=a(1001),p=(0,u.Z)(r,l,i,!1,null,"b1723520",null);const d=p.exports}}]);