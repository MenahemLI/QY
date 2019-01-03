<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta HTTP-EQUIV="pragma" CONTENT="no-cache">

<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<meta HTTP-EQUIV="expires" CONTENT="0">
<link href="theme/css/bootstrap.min.css" rel="stylesheet">
<link href="theme/css/datepicker3.css" rel="stylesheet">
<link href="theme/css/styles.css" rel="stylesheet">
<link href="theme/css/mytext.css" rel="stylesheet" type="text/css" />
<script src="theme/js/jquery-1.11.1.min.js"></script>
<script src="theme/js/jquery.flot.min.js" type="text/javascript"></script>
<script src="theme/js/echarts.min.js" type="text/javascript"></script>
<script src="theme/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/jquery.dataTables.min.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/dataTables.bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/dataTables.responsive.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/bootstrap-datepicker.js" type="text/javascript" charset="utf-8"></script>		
<script src="theme/js/jquery.form.js" type="text/javascript"></script>
<script src="theme/js/material.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/material-dashboard.js" type="text/javascript" charset="utf-8"></script>	
<script type="text/javascript" >

//function load(url,data){
    //alert($(url).attr("href"));

//    $.ajaxSetup({cache: false});
//    $(".rightContent").load(url,data, function(result){
        //alert(result);
        //将被加载页的JavaScript加载到本页执行

//        $result = $(result); 
//        $result.find("script").appendTo('.rightContent');
 //   });
//};
$(function(){
	$(".menu li").off("click");
	$(".menu li").on("click",function(){
		
		var cate = $(this);
		
		if(!cate.hasClass("active")){
			cate.addClass("active");
			cate.siblings().removeClass("active");
		}
		var url = cate.attr("url");
		//将url指定地址的返回值页面显示到rightContent中
			 $(".rightContent").load(url);

	});
	//默认激发第一个li
});
	
	
</script>




</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
			
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<span class="navbar-brand">三人行-团队</span>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
						<c:if test="${!empty user }">
							${user.u_name }<span class="caret"></span></a>
							
							<ul class="dropdown-menu" role="menu">
								<li><a href="toIntroduce.action"><span class="glyphicon glyphicon-user"></span> 简介</a></li>
								<li><a href="setUserjsp.action"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
								<li><a href="toSetPassword.action"><span class="glyphicon glyphicon-log-out"></span> 修改密码</a></li>
								<li><a href="logout.action"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
							</ul>
						</c:if>
						<c:if test="${empty user }">
							<a href="toLogin.action">用户</a></span></a>			
								
						</c:if>
								
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				
			</div>
		</form>
		<ul class="nav menu">
			
			<li class="parent" >
				<a  data-toggle="collapse" href="#sub-item-0" >
					<span class="glyphicon glyphicon-list"></span>事务管理 <span class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-0">
					<li url="manager/toDataManager.action">
						<a href="javascript:void(0);">
							<span class="glyphicon glyphicon glyphicon-pencil"></span>数据管理
						</a>
					</li>
					<li  url="manager/toDataList.action">
						<a href="javascript:void(0)">
							<span class="glyphicon glyphicon-pencil"></span>发票数据
						</a>
					</li>
				</ul>
			</li>
			<li class="parent" >
				<a  data-toggle="collapse" href="#sub-item-3" >
					<span class="glyphicon glyphicon-list"></span> 添加发票 <span class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-3">
					<li url="manager/toAddInvoice.action">
						<a href="javascript:void(0);">
							<span class="glyphicon glyphicon glyphicon-pencil"></span> 进项发票
						</a>
					</li>
					<li  url="manager/toAddOutvoice.action">
						<a href="javascript:void(0)">
							<span class="glyphicon glyphicon-pencil"></span> 销项发票
						</a>
					</li>
				</ul>
			</li>
			
		
			<li class="parent" >
				<a  data-toggle="collapse" href="#sub-item-4" >
					<span class="glyphicon glyphicon-list"></span>进销项分析图表<span class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-4">
					<li url="manager/toInvoiceData.action">
						<a href="javascript:void(0);">
							<span class="glyphicon glyphicon-th"></span>进销项统计表
						</a>
					</li>
					<li  url="manager/toInvoiceChart.action">
						<a href="javascript:void(0)">
							<span class="glyphicon glyphicon-signal"></span>进销项统计图
						</a>
					</li>
				</ul>
			</li>
			<!-- 客户信息部分 -->
			<li class="parent" >
				<a  data-toggle="collapse" href="#sub-item-6" >
					<span class="glyphicon glyphicon-list"></span>客户<span class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-6">
					<li url="manager/customerData.action">
						<a href="javascript:void(0);">
							<span class="glyphicon glyphicon glyphicon-pencil"></span>客户数据
						</a>
					</li>
					<li  url="manager/customerInformation.action">
						<a href="javascript:void(0)">
							<span class="glyphicon glyphicon-info-sign"></span>客户信息
						</a>
					</li>
				</ul>
			</li>
			<li url="manager/toProtuct.action">
			<a href="javascript:void(0);">
			<span class="glyphicon glyphicon-shopping-cart"></span> 经营产品</a>
			</li>
			<li role="presentation" class="divider"></li>
			<li><a href="toLogin.action"><span class="glyphicon glyphicon-user"></span> 登录</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">主页</li>
			</ol>
		</div><!--/.row-->
		
		<div class="rightContent">

		<img src="theme/img/2d6a17ee6ed3bafc6d35e87afa908cd8.jpg" width="100%" height="640px" />
		<!-- ======================================================内容加载区================================================ -->
		<!-- ================================================================================================================= -->
		</div>
        
       			
		
	</div>	<!--/.main-->

  		
	


</body>
</html>