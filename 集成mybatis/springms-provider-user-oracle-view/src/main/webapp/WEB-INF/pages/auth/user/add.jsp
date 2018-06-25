<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label" for="username"><font color="red">*</font>用户名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="username" name="username" placeholder="请填写用户名称"/>
            <div id="validation-username" class="validate-error help-block"></div>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-3 control-label" for="username"><font color="red">*</font>名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="name" name="name"  placeholder="请填写名称" />
            <div id="validation-password" class="validate-error help-block"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="username"><font color="red">*</font>年龄：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="age" name="age"  placeholder="请填写年龄" />
            <div id="validation-password" class="validate-error help-block"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="username">工资：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="balance" name="balance" placeholder="请填写补偿" />
        </div>
    </div>
</form>
<script type="text/javascript">
	submit = function(){
		frmValidate();
		var data = $("#submitForm").serialize();
		ajaxRequest("admin/user/add", data);
	}
</script>