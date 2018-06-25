<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="main-menu" role="navigation">
	<div id="main-menu-inner">
		<div class="menu-content top" id="menu-content-demo">
			<div>
				<div class="text-bg">
					<span class="text-semibold">${loginUser.username}</span>
				</div>
				<img src="${ctx}/assets/images/user-head-default.png" alt=""
					class="">
				<div class="btn-group"></div>
				<a href="#" class="close">&times;</a>
			</div>
		</div>
		<ul class="navigation">
			<li class="mm-dropdown" opCode="01"><a href="#"><i
					class="menu-icon fa fa-cogs"></i><span class="mm-text">权限管理</span></a>
				<ul>
					<li opCode="0101"><a tabindex="-1"
						href="javascript:goPage('admin/user/mainPage')"><span
							class="mm-text">用户管理</span></a></li>
				</ul></li>
		</ul>
	</div>
</div>
