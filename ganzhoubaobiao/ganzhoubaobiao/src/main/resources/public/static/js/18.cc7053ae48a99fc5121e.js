webpackJsonp([18],{LvT2:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("3cXf"),i=a.n(n),l=a("1/Ac"),o={data:function(){return{title:"",pageSize:10,currentPage:1,tableData:[],pageList:[]}},mounted:function(){this.$nextTick(function(){this.queryDataCollectList()})},methods:{dateFormat:function(t,e,a,n){var i=t[e.property];return i?Object(l.b)(new Date(i),"YYYY-MM-DD HH:mm:ss"):""},queryDataCollectList:function(){var t=this;this.$post("/admin/queryDataCollectList",{title:this.title,page:this.currentPage-1,size:this.pageSize}).then(function(e){t.tableData=e.content.page.content,t.pageList=e.content.page})},createData:function(){sessionStorage.removeItem("DataCollectionID"),this.$router.push({name:"DataCollectionEdit"})},editData:function(t){sessionStorage.setItem("DataCollectionID",t),this.$router.push({name:"DataCollectionEdit"})},deleteData:function(t){var e=this;this.$confirm("删除后将无法恢复，确定删除该数据吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$post("/admin/deleteDataCollect",{id:t}).then(function(t){0==t.resultCode&&e.$message({showClose:!0,type:"success",message:"操作成功！"}),e.queryDataCollectList()})}).catch(function(){e.$message({showClose:!0,type:"info",message:"已取消删除！"})})},detailPage:function(t){var e=i()(t);sessionStorage.setItem("DataCollectionItem",e),this.$router.push({name:"DataCollectionDetail"})},exportPage:function(t){window.open("/ganzhoubaobiao/admin/exportSummaryData?id="+t)},handleSizeChange:function(t){this.currentPage=1,this.pageSize=t,this.queryDataCollectList()},handleCurrentChange:function(t){this.currentPage=t,this.queryDataCollectList()}}},r={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("section",{staticClass:"data-collection__wrapper plate"},[a("div",{staticClass:"plate-alone"},[a("p",{staticClass:"plate-alone__title"},[t._v("数据收集")]),t._v(" "),a("el-row",{staticClass:"margin-bottom__medium",attrs:{gutter:10}},[a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{placeholder:"标题",clearable:""},on:{clear:t.queryDataCollectList},model:{value:t.title,callback:function(e){t.title=e},expression:"title"}})],1),t._v(" "),a("el-col",{attrs:{span:4}},[a("el-button",{attrs:{type:"primary"},on:{click:t.queryDataCollectList}},[t._v("查询")])],1),t._v(" "),a("el-col",{staticClass:"text-right",attrs:{span:6,offset:6}},[a("el-button",{attrs:{type:"primary",plain:"",icon:"el-icon-plus"},on:{click:t.createData}},[t._v("创建内容")])],1)],1),t._v(" "),a("el-table",{staticClass:"margin-bottom__medium first-cell__left",staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{type:"index",label:"序号",width:"80"}}),t._v(" "),a("el-table-column",{attrs:{prop:"title","show-overflow-tooltip":"",width:"120",label:"标题"}}),t._v(" "),a("el-table-column",{attrs:{prop:"createTime",formatter:t.dateFormat,label:"发布日期"}}),t._v(" "),a("el-table-column",{attrs:{width:"180",label:"已提交/总数"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return[a("span",{staticClass:"margin-right__mini"},[t._v(t._s(n.referNumber)+"/"+t._s(n.sumNumber))]),t._v(" "),a("el-button",{attrs:{type:"primary",plain:""},on:{click:function(e){return t.detailPage(n)}}},[t._v("详情")])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作",width:"268"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return[n.referNumber?a("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"导出汇总数据",placement:"top"}},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.exportPage(n.id)}}},[t._v("导出")])],1):t._e(),t._v(" "),a("el-button",{attrs:{icon:"el-icon-edit"},on:{click:function(e){return t.editData(n.id)}}}),t._v(" "),a("el-button",{attrs:{icon:"el-icon-delete"},on:{click:function(e){return t.deleteData(n.id)}}})]}}])})],1),t._v(" "),t.pageList.totalPages>0?a("el-pagination",{staticClass:"is-background-white text-center",attrs:{"current-page":t.currentPage,"page-sizes":[10,20,50,100],"page-size":t.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.pageList.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}):t._e()],1)])},staticRenderFns:[]};var s=a("C7Lr")(o,r,!1,function(t){a("ZT+x")},null,null);e.default=s.exports},"ZT+x":function(t,e){}});
//# sourceMappingURL=18.cc7053ae48a99fc5121e.js.map