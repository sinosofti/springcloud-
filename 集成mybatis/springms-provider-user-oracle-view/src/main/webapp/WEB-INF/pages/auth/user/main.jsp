<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
    <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:showModal('添加用户', 'admin/user/addPage');"><span class="btn-label icon fa fa-plus"></span>添加用户</button>
    </div>
  </div>
</div>
<div class="openAppGrid">
	<div id="openAppGrid"></div>
</div>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
               /*  {field:"id",text:"用户ID", sortColumn:"id"}, */
                {field:"username", text:"用户名称"},
                {field:"name", text:"名称"},
                {field:"age", text:"年龄"},
                {field:"balance", text:"工资"},
                {field:"id", text:"操作",width:80, style:"text-align:center", formatter:function(index, content, data){
                	if("admin" == data.username){
                		return "禁止操作超级管理员";
                	}else{
	                    var editUrl = "admin/user/updatePage/" + content;
	                    var resetPwd = "admin/user/updatePwdPage?id=" + content;
	                    var delUrl = "admin/user/deleteByID/" + content;
	                    return "<a href='javascript:showModal(\"更新用户\", \""+editUrl+"\");' data-original-title='修改' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
	                        + "&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");'  data-original-title='删除' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                	}
                }}
            ],
            cls: "",
            url: _urlPath + "admin/user/queryPage",
            sort:"id",
            order:"desc",
            pagination:false,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>