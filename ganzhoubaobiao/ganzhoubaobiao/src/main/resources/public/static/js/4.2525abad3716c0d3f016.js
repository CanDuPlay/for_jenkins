webpackJsonp([4],{"5Qlr":function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("3cXf"),i=s.n(a),n=s("7w7D"),o=s("Jp5S"),r=s("cTn1"),c=s("1/Ac"),l=(o.a,n.a,r.a,{directives:{TransferDom:o.a},components:{vSearch:n.a,Popup:r.a},data:function(){return{showData:this.$route.params,value:"",itemAllShow:!1,tableData:[],showPopup:!1,COLORS:["#fee9e9","#e1f5fe","#e8f6ed"],status:"",projectName:"",townId:localStorage.getItem("townId"),roleId:localStorage.getItem("roleId"),list:[],daopaiList:[{name:"",time:"",isFinish:0}]}},watch:{showPopup:function(t){t&&this.$nextTick(function(){document.querySelector("#popups").scrollTop=0})}},mounted:function(){this.init()},methods:{toDtail:function(t){localStorage.setItem("projectId",t),this.$router.push({name:"AppCountySchool"})},init:function(){var t=this;this.$post("/web/queryProjectList",{year:this.year,status:this.status,projectName:this.projectName,townId:this.townId,size:1e3}).then(function(e){0==e.resultCode&&(t.tableData=e.content.page.content)})},showDate:function(t,e){var s;s="startTime"==e?t.startTime:t.finishTime,this.$vux.datetime.show({cancelText:"取消",confirmText:"确定",format:"YYYY-MM-DD",value:Object(c.b)(s,"YYYY-MM-DD"),onShow:function(){},onHide:function(){"startTime"==e?t.startTime=this.value:t.finishTime=this.value}})},change:function(t){var e=this;this.showPopup=!0,this.$post("/web/queryProjectSchedule",{projectId:t.id,townId:this.townId}).then(function(t){if(0==t.resultCode){var s=[];JSON.parse(i()(t.content.list)).forEach(function(t,e){s.length&&t.id===s[s.length-1].id?s[s.length-1].list.push(t):(t.list=[JSON.parse(i()(t))],s.push(t))}),e.list=s}})},saveData:function(){for(var t=this,e=[],s=0;s<this.list.length;s++)for(var a=this.list[s],n=0;n<a.list.length;n++){var o=[];o[0]=a.list[n].startTime,o[1]=a.list[n].finishTime,a.list[n].dateTime=o,e.push(a.list[n])}this.$post("/web/saveTownProjectTime",{projectTime:i()(e),townId:this.townId}).then(function(e){0==e.resultCode&&(t.$vux.toast.show({text:"项目时间更新成功!",width:"3rem"}),t.showPopup=!1)})}}}),u={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("app-view",{staticClass:"app-county-index"},[s("div",{staticClass:"detail-header"},[s("section",{staticClass:"dp-box dp-items-between gutter"},[s("div",{staticClass:"dp-span-8"},[s("select",{directives:[{name:"model",rawName:"v-model",value:t.status,expression:"status"}],staticClass:"z-app-input",on:{change:[function(e){var s=Array.prototype.filter.call(e.target.options,function(t){return t.selected}).map(function(t){return"_value"in t?t._value:t.value});t.status=e.target.multiple?s:s[0]},t.init]}},[s("option",{attrs:{value:""}},[t._v("全部")]),t._v(" "),s("option",{attrs:{value:"1"}},[t._v("未开工")]),t._v(" "),s("option",{attrs:{value:"2"}},[t._v("已开工")]),t._v(" "),s("option",{attrs:{value:"3"}},[t._v("已完成")])])]),t._v(" "),s("div",{staticClass:"dp-span-16"},[s("v-search",{on:{blur:t.init},model:{value:t.projectName,callback:function(e){t.projectName=e},expression:"projectName"}})],1)])]),t._v(" "),t.tableData.length?s("ul",{staticClass:"detail-list word-bw"},t._l(t.tableData,function(e,a){return s("li",{key:a,staticClass:"item clear-app-style",on:{click:function(s){return s.stopPropagation(),s.preventDefault(),t.toDtail(e.id)}}},[s("div",{staticClass:"top"},[s("div",{staticClass:"dp-box dp-items-center gutter"},[s("section",{staticClass:"dp-span-5"},[2==e.status?s("span",{staticClass:"state-style is-state__1-text"},[t._v("进行中")]):t._e(),t._v(" "),1==e.status?s("span",{staticClass:"state-style is-state__3-text"},[t._v("未开始")]):t._e()]),t._v(" "),s("section",{staticClass:"dp-span-17"},[s("p",[t._v(t._s(e.name))]),t._v(" "),s("p",{staticStyle:{"font-size":"0.24rem"}},[t._v("项目周期："+t._s(t._f("YYYY")(e.startTime))+"年 - "+t._s(t._f("YYYY")(e.endTime))+"年")])]),t._v(" "),110001==t.roleId?s("section",{staticClass:"dp-span-2"},[s("i",{staticClass:"el-icon-setting",staticStyle:{color:"#03A9F4","font-size":"0.4rem"},on:{click:function(s){return s.stopPropagation(),s.preventDefault(),s.target!==s.currentTarget?null:t.change(e)}}})]):t._e()])])])}),0):s("v-nodata"),t._v(" "),s("div",{directives:[{name:"transfer-dom",rawName:"v-transfer-dom"}]},[s("popup",{staticStyle:{"background-color":"#fff"},attrs:{id:"popups",height:"100%"},model:{value:t.showPopup,callback:function(e){t.showPopup=e},expression:"showPopup"}},[s("ul",{staticClass:"popup-list"},t._l(t.list,function(e,a){return s("li",{key:a,staticClass:"popup-item"},[s("p",{staticClass:"popup-title",style:{backgroundColor:t.COLORS[a%3]}},[t._v(t._s(e.stage))]),t._v(" "),t._l(e.list,function(e,a){return s("div",{staticClass:"popup-content"},[s("section",{staticClass:"dp-box dp-items-between gutter"},[s("span",{staticClass:"text-right dp-span-7"},[t._v(t._s(e.scheduleName))]),t._v(" "),s("span",{staticClass:"dp-span-8"},[s("input",{directives:[{name:"model",rawName:"v-model",value:e.startTime,expression:"row.startTime"}],staticClass:"z-app-input",attrs:{type:"text",placeholder:"选择日期"},domProps:{value:e.startTime},on:{focus:function(s){return t.showDate(e,"startTime")},input:function(s){s.target.composing||t.$set(e,"startTime",s.target.value)}}})]),t._v(" "),s("span",{staticClass:"dp-span-1"},[t._v("-")]),t._v(" "),s("span",{staticClass:"dp-span-8"},[s("input",{directives:[{name:"model",rawName:"v-model",value:e.finishTime,expression:"row.finishTime"}],staticClass:"z-app-input",attrs:{type:"text",placeholder:"选择日期"},domProps:{value:e.finishTime},on:{focus:function(s){return t.showDate(e,"finishTime")},input:function(s){s.target.composing||t.$set(e,"finishTime",s.target.value)}}})])])])})],2)}),0),t._v(" "),s("div",{staticClass:"text-right",staticStyle:{padding:"0 0.25rem 0.3rem"}},[s("el-button",{attrs:{type:"primary"},on:{click:t.saveData}},[t._v("确定")]),t._v(" "),s("el-button",{on:{click:function(e){t.showPopup=!1}}},[t._v("取消")])],1)])],1)],1)},staticRenderFns:[]};var p=s("C7Lr")(l,u,!1,function(t){s("nCbK"),s("mgCc")},"data-v-f8341668",null);e.default=p.exports},"7w7D":function(t,e,s){"use strict";String,String,Boolean;var a={data:function(){return{searchContext:"",maskElt:null}},props:{scrollView:{type:String,default:"body"},placeholder:String,hasBorder:{type:Boolean,default:!0}},watch:{},mounted:function(){this.$nextTick(function(){})},methods:{searchMethod:function(t){t.preventDefault(),t.target[0].blur()},blur:function(){this.$emit("blur",this.searchContext),document.getElementsByTagName("body")[0].removeChild(this.maskElt)},focus:function(t){this.$emit("focus");var e=this.$refs["z-search"],s=e.clientHeight+e.offsetTop,a=document.createElement("div");a.className="z-mask-box__search",a.style.top=s+"px",document.body.appendChild(a),this.maskElt=document.getElementsByClassName("z-mask-box__search")[0],this.maskElt.addEventListener("touchstart",function(e){e.preventDefault(),t.target.blur()})}}},i={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("section",{ref:"z-search",staticClass:"z-search"},[s("div",{class:["z-search-inner","dp-box","dp-items-center",{"has-border":t.hasBorder}]},[s("i",{staticClass:"z-search__icon el-icon-search"}),t._v(" "),s("div",{staticClass:"z-search-input dp-flex-grow"},[s("form",{on:{submit:function(e){return t.searchMethod(e)}}},[s("input",{staticClass:"z-search-input__inner",attrs:{type:"search",autocomplete:"off",placeholder:t.placeholder},domProps:{value:t.searchContext},on:{focus:function(e){return t.focus(e)},blur:function(e){return t.blur()},input:function(e){return t.$emit("input",e.target.value)}}})])])])])},staticRenderFns:[]};var n=s("C7Lr")(a,i,!1,function(t){s("Z4RH")},"data-v-e6d9709a",null);e.a=n.exports},Z4RH:function(t,e){},mgCc:function(t,e){},nCbK:function(t,e){}});
//# sourceMappingURL=4.2525abad3716c0d3f016.js.map