webpackJsonp([11],{B8Qw:function(t,e){},SfXB:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=s("1/Ac"),a={data:function(){return{tableData:[],userName:"",tTeacherList:[]}},mounted:function(){this.queryAllTeacher(),this.queryAccountAuthorityList()},methods:{dateFormat:function(t,e,s,a){var r=t[e.property];return r?Object(n.b)(new Date(r),"YYYY-MM-DD HH:mm:ss"):""},queryAllTeacher:function(){var t=this;this.$post("/admin/queryAllTeacher").then(function(e){t.tTeacherList=e.content.tTeacherList})},queryAccountAuthorityList:function(){var t=this;this.$post("/admin/queryAccountList",{}).then(function(e){-2==e.resultCode?t.queryAccountAuthorityList():0==e.resultCode&&(t.tableData=e.content.userRoleList)})},saveAccountAuthority:function(){var t=this;if(!this.userName)return this.$message({showClose:!0,type:"error",message:"请选择人员名称！"});this.$post("/admin/accountAssignment",{userId:this.userName}).then(function(e){0==e.resultCode?(t.$message({showClose:!0,type:"success",message:"操作成功！"}),t.userName="",t.queryAccountAuthorityList()):-1==e.resultCode&&t.$message({showClose:!0,type:"error",message:e.resultDesc})})},deleteAccountAuthority:function(t){var e=this;this.$confirm("删除后将无法恢复，确定删除该数据吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.$post("/admin/deleteAccountAssignment",{id:t}).then(function(t){0==t.resultCode&&e.$message({showClose:!0,type:"success",message:"操作成功！"}),e.queryAccountAuthorityList()})}).catch(function(){e.$message({showClose:!0,type:"info",message:"已取消删除！"})})},handleSizeChange:function(t){this.currentPage=1,this.pageSize=t,this.queryAccountAuthorityList()},handleCurrentChange:function(t){this.currentPage=t,this.queryAccountAuthorityList()}}},r={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("section",{staticClass:"user-auth__wrapper plate"},[s("div",{staticClass:"plate-alone"},[s("p",{staticClass:"plate-alone__title"},[t._v("市局科室账号分配")]),t._v(" "),s("el-form",{attrs:{inline:""}},[s("el-form-item",[s("el-select",{staticStyle:{width:"230px"},attrs:{clearable:"",placeholder:"姓名"},model:{value:t.userName,callback:function(e){t.userName=e},expression:"userName"}},t._l(t.tTeacherList,function(t,e){return s("el-option",{key:t.globalId,attrs:{label:t.name,value:t.globalId}})}),1)],1),t._v(" "),s("el-form-item",[s("el-button",{attrs:{type:"primary",icon:"el-icon-circle-plus-outline"},on:{click:t.saveAccountAuthority}},[t._v("创建")])],1)],1),t._v(" "),s("section",{staticClass:"margin-bottom__medium",staticStyle:{"margin-left":"-25px","margin-right":"-25px"}},[s("el-table",{staticClass:"first-cell__left",staticStyle:{width:"100%"},attrs:{data:t.tableData}},[s("el-table-column",{attrs:{type:"index",label:"序号",width:"80"}}),t._v(" "),s("el-table-column",{attrs:{prop:"userName",label:"姓名"}}),t._v(" "),s("el-table-column",{attrs:{prop:"createTime",formatter:t.dateFormat,label:"创建时间"}}),t._v(" "),s("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return e.$index,[s("el-button",{attrs:{icon:"el-icon-delete"},on:{click:function(e){return t.deleteAccountAuthority(n.id)}}})]}}])})],1)],1)],1)])},staticRenderFns:[]};var i=s("VU/8")(a,r,!1,function(t){s("B8Qw")},null,null);e.default=i.exports}});
//# sourceMappingURL=11.db795cb2826bdd50eef8.js.map