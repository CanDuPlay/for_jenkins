webpackJsonp([28],{"Vs+T":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l={data:function(){return{value:"",options:[{value:"选项1",label:"黄金糕"},{value:"选项2",label:"双皮奶"},{value:"选项3",label:"蚵仔煎"},{value:"选项4",label:"龙须面"},{value:"选项5",label:"北京烤鸭"}],tableData:[],endTime:"",startTime:"",page:[],roleList:[],roleId:"",currentPage:1,pageSize:10}},mounted:function(){this.initRole(),this.init()},methods:{init:function(){var e=this;this.$post("/web/queryLog",{startTime:""!=e.value?e.value[0]:"",endTime:""!=e.value?e.value[1]:"",roleId:e.roleId,page:e.currentPage-1,size:e.pageSize}).then(function(t){0==t.resultCode&&(e.page=t.content.page,console.log(e.page))})},initRole:function(){var e=this;this.$post("/web/queryRole",{}).then(function(t){0==t.resultCode&&(e.roleList=t.content.list,e.roleList.unshift({id:"",name:"请选择"}))})},handleSizeChange:function(e){this.pageSize=e,this.init()},handleCurrentChange:function(e){this.currentPage=e,this.init()}}},n={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("section",{staticClass:"record-index__wrapper plate"},[e._m(0),e._v(" "),a("el-form",{staticClass:"clearfix",attrs:{inline:""}},[a("el-form-item",[a("el-date-picker",{attrs:{type:"daterange","value-format":"yyyy-MM-dd","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.value,callback:function(t){e.value=t},expression:"value"}})],1),e._v(" "),a("el-form-item",[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.roleId,callback:function(t){e.roleId=t},expression:"roleId"}},e._l(e.roleList,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{staticClass:"pull-right"},[a("el-button",{attrs:{type:"primary"},on:{click:e.init}},[e._v("搜索")])],1)],1),e._v(" "),a("el-table",{staticClass:"first-cell__left margin-bottom__small",staticStyle:{width:"100%"},attrs:{data:e.page.content}},[a("el-table-column",{attrs:{label:"操作类型"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.row;return[e._v("\n          "+e._s(0==a.type?"新增":1==a.type?"修改":"删除")+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"userName",label:"操作人"}}),e._v(" "),a("el-table-column",{attrs:{prop:"roleName",label:"权限"}}),e._v(" "),a("el-table-column",{attrs:{prop:"content",label:"详情"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作时间"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.row;return[e._v("\n          "+e._s(e._f("YMDHms")(a.createTime))+"\n      ")]}}])})],1),e._v(" "),e.page.totalPages>0?a("el-pagination",{staticClass:"is-background-white text-center",attrs:{"current-page":e.currentPage,"page-sizes":[10,20,50,100],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.page.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}):e._e()],1)},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"plate-title"},[this._v("\n    审计记录\n    "),t("span",{staticClass:"hint-info"},[this._v("记录操作日志")])])}]},r=a("VU/8")(l,n,!1,null,null,null);t.default=r.exports}});
//# sourceMappingURL=28.f8034cf3f8c503b8f786.js.map