webpackJsonp([23],{TpIm:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});s("J8vU");var a=s("1/Ac"),n={data:function(){return{options:[{value:"选项1",label:"黄金糕"},{value:"选项2",label:"双皮奶"},{value:"选项3",label:"蚵仔煎"},{value:"选项4",label:"龙须面"},{value:"选项5",label:"北京烤鸭"}],value:"",radio:0,tableData:[],CITY:1,DISTRICT:2,SCHOOL:3,showSchool:!1,showSchoolInfo:!1,ifCheck:sessionStorage.getItem("ifCheck"),lastStatus:sessionStorage.getItem("lastStatus"),nextStatus:sessionStorage.getItem("nextStatus"),status:sessionStorage.getItem("status"),townId:sessionStorage.getItem("townId"),month:"",projectId:this.$route.params.projectId,projectName:this.$route.params.projectName,dialogVisible:!1,dialogImageUrl:"",heshiList:[{value:"",label:"全部"},{value:"1",label:"已核实"},{value:"2",label:"未核实"}],heshi:""}},computed:{tMenu:function(){return this.tableData[0].scheduleList}},mounted:function(){this.$nextTick(function(){this.queryInit()})},methods:{queryInit:function(){var t=this;this.$post("/web/checkList",{projectId:t.projectId,month:t.month,status:t.lastStatus,level:"2",stage:"",town_id:t.townId,heshi:t.heshi}).then(function(e){t.tableData=e.content.list,""==t.month&&(t.month=e.content.month)})},openSchoolInfo:function(t,e){t.target;e.types===this.DISTRICT?this.getChildren(".school_"+e.id):e.types===this.SCHOOL&&this.getChildren(".school-info_"+e.pId)},getChildren:function(t){document.querySelector("#spectaculars-table").querySelectorAll(t).forEach(function(t){Object(a.c)(t,"hide")?Object(a.e)(t,"hide"):Object(a.a)(t,"hide")})},check:function(t,e,s){var a=this;a.$post("/web/checkStatus",{checkType:3,status:t,lastStatus:s,projectId:a.projectId,id:e,month:a.month}).then(function(t){a.queryInit(),a.$message({type:"success",message:"审核成功!"})}).catch(function(t){})},rowMerge:function(t){return t.invertedList?4:1},handlePictureCardPreview:function(t){this.dialogImageUrl=t.url||t,this.dialogVisible=!0},setRowClass:function(t){return t.level==this.DISTRICT?"district":t.level==this.SCHOOL?"school_"+t.id:void 0}}},o={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("section",{staticClass:"county-checkinfo__wrapper"},[s("div",{staticClass:"title-bar__big clearfix"},[s("span",[t._v(t._s(t.projectName))]),t._v(" "),s("el-button",{staticClass:"pull-right",attrs:{type:"text"},on:{click:function(e){return t.$router.go(-1)}}},[t._v("返回")])],1),t._v(" "),s("el-form",{attrs:{inline:""}},[s("el-form-item",[s("el-date-picker",{attrs:{format:"yyyy-MM",type:"month","value-format":"yyyy-MM",placeholder:"选择月份"},model:{value:t.month,callback:function(e){t.month=e},expression:"month"}})],1),t._v(" "),s("el-form-item",[s("el-select",{attrs:{placeholder:"全部核实状态"},model:{value:t.heshi,callback:function(e){t.heshi=e},expression:"heshi"}},t._l(t.heshiList,function(t){return s("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}),1),t._v(" "),s("el-button",{attrs:{type:"primary"},on:{click:t.queryInit}},[t._v("查询")])],1)],1),t._v(" "),t.tableData.length?s("div",{staticClass:"spectaculars-table-wrap"},[s("table",{staticClass:"table multifunction-table",attrs:{id:"spectaculars-table"}},[s("thead",[s("tr",[s("th",{attrs:{rowspan:"2"}},[t._v("序号")]),t._v(" "),s("th",{attrs:{rowspan:"2"}},[t._v("项目学校")]),t._v(" "),s("th",{attrs:{colspan:"4"}},[t._v("项目基本情况")]),t._v(" "),s("th",{attrs:{colspan:"2"}},[t._v("建设时限情况")]),t._v(" "),s("th",{attrs:{colspan:t.tMenu.length}},[t._v("项目形象进度情况")]),t._v(" "),t._m(0),t._v(" "),s("th",{attrs:{rowspan:"2"}},[t._v("材料图片或现场照片")]),t._v(" "),1==t.ifCheck?s("th",{attrs:{rowspan:"2"}},[t._v("状态")]):t._e(),t._v(" "),1==t.ifCheck?s("th",{attrs:{rowspan:"2"}},[t._v("操作")]):t._e()]),t._v(" "),s("tr",[s("th",[t._v("学校类型")]),t._v(" "),t._m(1),t._v(" "),t._m(2),t._v(" "),t._m(3),t._v(" "),s("th",[t._v("开工年份")]),t._v(" "),s("th",[t._v("竣工年份")]),t._v(" "),t._l(t.tMenu,function(e,a){return s("th",{key:a,style:{backgroundColor:e.color}},[t._v("\n\t\t\t\t\t\t\t"+t._s(e.name)+"\n\t\t\t\t\t\t")])})],2)]),t._v(" "),t._l(t.tableData,function(e,a){return 3==e.level?s("tbody",{key:a,class:[t.setRowClass(e),"row-school-info_bgcolor",{hasChild:!!e.invertedList&&e.invertedList.length>0,"row-city__bgcolor":e.level==t.CITY,"row-district__bgcolor":e.level==t.DISTRICT}],on:{click:function(s){return t.openSchoolInfo(s,e)}}},[s("tr",[s("td",{attrs:{rowspan:t.rowMerge(e)}},[t._v(t._s(a))]),t._v(" "),s("td",{attrs:{rowspan:t.rowMerge(e)}},[t._v(t._s(e.schoolName))]),t._v(" "),s("td",{attrs:{rowspan:t.rowMerge(e)}},[t._v(t._s(e.schoolType))]),t._v(" "),s("td",{attrs:{rowspan:t.rowMerge(e)}},[t._v(t._s(e.area))]),t._v(" "),s("td",{attrs:{rowspan:t.rowMerge(e)}},[t._v(t._s(e.newBuild))]),t._v(" "),s("td",{attrs:{rowspan:t.rowMerge(e)}},[t._v(t._s(e.invest))]),t._v(" "),s("td",[t._v(t._s(e.startYear))]),t._v(" "),s("td",[t._v(t._s(e.endYear))]),t._v(" "),t._l(t.tMenu,function(a,n){return s("td",{key:n},[e.level==t.CITY||e.level==t.DISTRICT?s("span",[t._v(t._s(null!=e.scheduleList[n]&&null!=e.scheduleList[n].count?e.scheduleList[n].count:""))]):a.id===e.schedule?s("i",{staticClass:"el-icon-star-on",staticStyle:{color:"#f54b10"}}):t._e()])}),t._v(" "),s("td",[t._v(t._s(e.allInvest))]),t._v(" "),s("td",{attrs:{rowspan:t.rowMerge(e)}},t._l(e.fileList,function(e,a){return s("img",{attrs:{src:e.fileUrl,width:"50",alt:""},on:{click:function(s){return s.stopPropagation(),s.preventDefault(),t.handlePictureCardPreview(e.fileUrl)}}})}),0),t._v(" "),1==t.ifCheck?s("td",[t.lastStatus<e.status?s("span",{staticClass:"state-style is-state__1-text"},[t._v("已确认")]):t._e(),t._v(" "),t.lastStatus==e.status?s("span",{staticClass:"state-style is-state__2-text"},[t._v("未确认")]):t._e()]):t._e(),t._v(" "),1==t.ifCheck?s("td",{attrs:{rowspan:t.rowMerge(e)}},[t.status==e.status?s("el-button",{attrs:{type:"text"},on:{click:function(s){return s.stopPropagation(),s.preventDefault(),t.check(t.lastStatus,e.schoolId,t.status)}}},[t._v("取消确认")]):t._e(),t._v(" "),t.lastStatus==e.status?s("el-button",{attrs:{type:"text"},on:{click:function(s){return s.stopPropagation(),s.preventDefault(),t.check(t.status,e.schoolId,t.lastStatus)}}},[t._v("确认信息")]):t._e(),t._v(" "),t.lastStatus==e.status?s("el-button",{attrs:{type:"text"},on:{click:function(s){return s.stopPropagation(),s.preventDefault(),t.check(0,e.schoolId,t.lastStatus)}}},[t._v("打回信息")]):t._e()],1):t._e()],2),t._v(" "),e.invertedList&&e.invertedList.length>0?s("tr",{class:[{hide:t.showSchoolInfo},"school-info_"+e.id]},[s("td",[t._v("重点说明情况：")]),t._v(" "),s("td",{attrs:{colspan:e.invertedList.length}},[t._v(t._s(e.reward))])]):t._e(),t._v(" "),e.invertedList&&e.invertedList.length>0?s("tr",{class:[{hide:t.showSchoolInfo},"school-info_"+e.id]},[s("td",{attrs:{rowspan:"2"}},[t._v("倒排时间节点：")]),t._v(" "),t._l(e.invertedList,function(e,a){return s("td",{key:a},[t._v(t._s(e.name))])})],2):t._e(),t._v(" "),e.invertedList&&e.invertedList.length>0?s("tr",{class:[{hide:t.showSchoolInfo},"school-info_"+e.id]},t._l(e.invertedList,function(e,a){return s("td",{key:a},[t._v(t._s(t._f("YMD")(e.time)))])}),0):t._e()]):t._e()})],2),t._v(" "),s("el-dialog",{attrs:{visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e}}},[s("img",{attrs:{width:"100%",src:t.dialogImageUrl,alt:""}})])],1):t._e()],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("th",{attrs:{rowspan:"2"}},[this._v("累计完成投资"),e("br"),this._v("（万元）")])},function(){var t=this.$createElement,e=this._self._c||t;return e("th",[this._v("建筑面积"),e("br"),this._v("（m²）")])},function(){var t=this.$createElement,e=this._self._c||t;return e("th",[this._v("新增学位"),e("br"),this._v("（个）")])},function(){var t=this.$createElement,e=this._self._c||t;return e("th",[this._v("规划总投资"),e("br"),this._v("（万元）")])}]};var r=s("VU/8")(n,o,!1,function(t){s("zCiB")},null,null);e.default=r.exports},zCiB:function(t,e){}});
//# sourceMappingURL=23.2fae1895c4dca5feeb62.js.map