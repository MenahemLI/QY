<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="theme/css/bootstrap.min.css" rel="stylesheet">
	<link href="theme/css/datepicker3.css" rel="stylesheet">
	<link href="theme/css/styles.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="theme/css/mytext.css"/>
	
	<link rel="stylesheet" type="text/css" href="theme/css/mytext.css"/>
<title>Insert title here</title>
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
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> 用户 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-user"></span> 简介</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="搜索">
			</div>
		</form>
		<ul class="nav menu">
			<li ><a href="index.html"><span class="glyphicon glyphicon-list-alt"></span> 事务管理</a></li>
			<li><a href="widgets.html"><span class="glyphicon glyphicon-info-sign"></span> 发票数据</a></li>
			<li class="parent ">
				<a href="">
					<span class="glyphicon glyphicon-list"></span> 添加发票 <span data-toggle="collapse" href="#sub-item-3" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-3">
					<li>
						<a class="" href="charts.html">
							<span class="glyphicon glyphicon glyphicon-pencil"></span> 进项发票
						</a>
					</li>
					<li>
						<a class="" href="tables.html">
							<span class="glyphicon glyphicon-pencil"></span> 销项发票
						</a>
					</li>
				</ul>
			</li>
			<li class="parent ">
				<a href="">
					<span class="glyphicon glyphicon-list"></span> 分析图 <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="forms.html">
							<span class="glyphicon glyphicon glyphicon-stats"></span> 月度统计
						</a>
					</li>
					<li>
						<a class="" href="panels.html">
							<span class="glyphicon glyphicon-stats"></span> 年度统计
						</a>
					</li>
				</ul>
			</li>
			<li class="parent ">
				<a href="">
					<span class="glyphicon glyphicon-list"></span> 报表 <span data-toggle="collapse" href="#sub-item-2" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-2">
					<li>
						<a class="" href="">
							<span class="glyphicon glyphicon-th"></span> 月度报表
						</a>
					</li>
					<li>
						<a class="" href="">
							<span class="glyphicon glyphicon-th"></span> 年度报表
						</a>
					</li>
					<li>
						<a class="" href="">
							<span class="glyphicon glyphicon-th"></span> 数据分析
						</a>
					</li>
				</ul>
			</li>
			<li role="presentation" class="divider"></li>
			<li><a href="login.html"><span class="glyphicon glyphicon-user"></span> 登录</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">主页</li>
			</ol>
		</div><!--/.row-->
		
		<div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">进项发票记录表</h4>
									<p class="category">请如实填写票据信息</p>
	                            </div>
	                            <div class="card-content">
	                                <form action="updateInvoice.action"  method="post">
	                                <input type="hidden" name="idi" value="${invoice.id }">
	                                <input type="hidden" name="idg" value="${invoice.id }">
	                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">票据编号</label>
													<input class="form-control"  type="text" name="i_number" value="${invoice.i_number }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">开票日期</label>
													<input class="form-control" type="text" name="opendate" value="${invoice.opendate }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">联系电话</label>
													<input class="form-control" type="text" name="client_tel" value="${invoice.client_tel }">
												<span class="material-input"></span></div>
	                                        </div>
	                                       
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-6">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">地址</label>
													<input class="form-control" type="text" name="address" value="${invoice.address }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-6">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">供应商</label>
													<input class="form-control" type="text" name="g_vendor" value="${goods.g_vendor }">
												<span class="material-input"></span></div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">货物名称</label>
													<input class="form-control" type="text" name="g_name" value="${goods.g_name }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">货物编号</label>
													<input class="form-control" type="text" name ="g_number" value="${goods.g_number }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">规格型号</label>
													<input class="form-control" type="text" name="g_format" value="${goods.g_format }">
												<span class="material-input"></span></div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">货物单位(个/批)</label>
													<input class="form-control" type="text" name ="unit" value="${invoice.unit }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">单价</label>
													<input class="form-control" type="text" name ="g_price" value="${goods.g_price }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">数量</label>
													<input class="form-control" type="text" name ="amount" value="${invoice.amount }">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">总价</label>
													<input class="form-control" type="text" name="price" value="${invoice.price }">
												<span class="material-input"></span></div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-12">
	                                            <div class="form-group">
	                                                <label>备注</label>
													<div class="form-group label-floating is-empty is-focused">
									    				<label class="control-label">如对发票信息有疑问，请备注</label>
								    					<textarea class="form-control" rows="5"></textarea>
		                        					<span class="material-input"></span></div>
	                                            </div>
	                                        </div>
	                                    </div>

	                                    <button type="submit" class="btn btn-primary pull-right">确认提交</button>
	                                    <div class="clearfix"></div>
	                                </form>
	                            </div>
	                        </div>
	                    </div>
						 <div class="row">
						<div class="col-md-4">
    						<div class="panel panel-default">
								<div class="panel-heading">已添加</div>
									<div class="panel-body">
									
						<table class="table table-striped table-hover" >
						    		<tr >
						       				 <th>票据编号</th>
						       				 <th>进/销项</th>
						      		  		<th>创建时间</th>
						    			</tr>
						    			<tr >
						       				  <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    	
						    			<tr  class="success">
						       				  <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    			<tr >
						       				 <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    	
						    			<tr  class="warning">
						       				 <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    			<tr >
						       				 <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    	
						    			<tr  class="danger">
						       				  <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    			<tr >
						       				 <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						    	
						    			<tr  class="info">
						       				 <td>10001</td>
						       				 <td>进项</td>
						      		  		<td>2017/2/23</td>
						    			</tr>
						  
						    		
						    			
						    			
						    	
						</table>
					</div>
				</div>
		    			</div>
		    			</div>
	                </div>
	            </div>
        
       	</div>		
		
		<!--/.main-->

	<script src="theme/js/jquery-1.11.1.min.js"></script>
	<script src="theme/js/bootstrap.min.js"></script>
	<script src="theme/js/chart.min.js"></script>
	<script src="theme/js/chart-data.js"></script>
	<script src="theme/js/easypiechart.js"></script>
	<script src="theme/js/easypiechart-data.js"></script>
	<script src="theme/js/bootstrap-datepicker.js"></script>
	<script src="theme/js/material-dashboard.js" type="text/javascript" charset="utf-8"></script>
	<script src="theme/js/material.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$('#calendar').datepicker({
		});

		!function ($) {
		    $(document).on("click","ul.nav li.parent > a > span.icon", function(){          
		        $(this).find('em:first').toggleClass("glyphicon-minus");      
		    }); 
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})
	</script>	

</body>
</html>