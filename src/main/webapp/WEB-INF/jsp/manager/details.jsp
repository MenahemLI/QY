<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM");
	Date date = new Date();
	String now = sdf.format(date);
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<link href="theme/css/bootstrap.min2.css" rel="stylesheet">
<link href="theme/css/styles2.css" rel="stylesheet">
<link href="theme/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="theme/css/dataTables.responsive.css" rel="stylesheet">
<link href="theme/css/sb-admin-2.css" rel="stylesheet">
<link href="theme/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<script>
$(function() {

				$("#searchrFrom").off();
				$("#searchrFrom").on("submit", function() {
					var years = $(this).find("select[name=years]").val();
					var months = $(this).find("select[name=months]").val();
					$("#wrapper").load("manager/toregister.action", {
						years : years,
						months : months
					});
					return false;
				});
			});
function Tstart() {
	var y = new Date().getFullYear();
	for (var i = (y - 30); i < (y + 1); i++)
		//以今年为准，前30年，后30年   
		document.reg_testdate.years.options.add(new Option(" " + i
				+ " 年", i));
	//赋月份的下拉框   
	for (var i = 1; i < 12; i++)
		if (i <= 9) {
			document.reg_testdate.months.options.add(new Option(" "
					+ "0" + i + " 月", i));
		} else {
			document.reg_testdate.months.options.add(new Option(" "
					+ i + " 月", i));
		}
	document.reg_testdate.months.options.add(new Option(" " + i
			+ " 月", i));

	
	document.reg_testdate.months.value = new Date().getMonth() + 1;
	if (document.attachEvent) {
		window.attachEvent("onload", Tstart);
	} else {
		window.addEventListener('load', Tstart, false);
	}
}
Tstart();
			$(document).ready(function() {
				$('#dataTables-example').DataTable({
					responsive : true,
					searching : true,
					bLengthChange : false,
				});
			});
			$(document).ready(function() {
				$('#dataTables').DataTable({
					responsive : true,
					searching : true,
					bLengthChange : false,
				});
			});
			
		</script>

</head>
<body>
	<div id="wrapper">
		<div class="row">
			<div class="col-lg-12 ">
				<h1 class="page-header" style="float: left;">发票数据</h1>
				<form name="reg_testdate" role="form"
					action="manager/toregister.action" method="post"
					style="float: left; margin-top: 36px; margin-left: 45px;"
					id="searchrFrom">
					<select name="years" class="btn">
						<option value="#">请选择 年</option>
					</select> <select name="months" class="btn">
						<option value="#">请选择 月</option>
					</select>
					<div class="btn-group btn-group-sm" style="margin-left: 10px;">
						<input class="btn btn-info" type="submit" id="da" value="搜索"
							name="搜索" />
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<!--进项发票-->
				<div class="panel panel-default">
					<div class="panel-heading">${presentMonth }月进项数据</div>
					<div class="panel-body panel-red">
						<div class="">
							<table width="100%" class="table  table-bordered"
								id="dataTables-example">
								<thead>
									<tr>
										<th>货物</th>
										<th>规格型号</th>
										<th>数量</th>
										<th>单位</th>
										<th>金额/元</th>
										<th>销售方</th>

									</tr>
								</thead>
								<tbody id="tf">
									<c:forEach items="${ientitylist }" var="inp">
										<c:forEach items="${inp }" var="list" begin="0" varStatus="s">
											<tr class="even gradeC">
											    <td>${list.wares }</td>
												<td>${list.size }</td>
												<td>${list.amount }</td>
												<td>${list.unit }</td>
												<td>${list.unitprice }</td>
												<td>${list.salesunit }</td>
											</tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="">
							<ul class="list-group ">
								<li class="list-group-item list-group-item-info"
									style="float: left; margin-right: 50px;">本月购买货物：<span
									id="InputNumber">${fn:length(inputwares)}</span>种
								</li>
								<li class="list-group-item list-group-item-info"
									style="float: left; margin-right: 50px;">总金额：￥ <span id="">${InputPrice }</span>
								</li>
								<li class="list-group-item list-group-item-info"
									style="float: left; margin-right: 50px;">税额：￥ <span id="">${taxrates }</span>
								</li>
								<li class="list-group-item list-group-item-info"
									style="float: left; margin-right: 50px;">价税合计：￥ <span
									id="">${taxrates+InputPrice }</span>
								</li>
							</ul>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>
				<!--销项发票-->
				<div class="panel panel-default">
					<div class="panel-heading">${presentMonth }月销项数据</div>
					<div class="panel-body panel-red">
						<div class="">
							<table class="table  table-bordered" id="dataTables">
								<thead>
									<tr>
										<th>货物</th>
										<th>规格型号</th>
										<th>数量</th>
										<th>单位</th>
										<th>金额/元</th>
										<th>购买方</th>

									</tr>
								</thead>
								<tbody id="to">
									<c:forEach items="${oentitylist }" var="onp" varStatus="s">
										<c:forEach items="${onp }" var="list" begin="0" varStatus="s">
											<tr class="even gradeC">
												<td>${list.wares }</td>
												<td>${list.size }</td>
												<td>${list.amount }</td>
												<td>${list.unit }</td>
												<td>${list.unitprice }</td>
												<td>${list.salesunit }</td>
											</tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="">
							<ul class="list-group ">
								<li class="list-group-item list-group-item-info"
									style="float: left; margin-right: 50px;">本月出售货物：<span
									id="OutputNumber">${fn:length(outputwares) }</span>种
								</li>
								<li class="list-group-item list-group-item-info"
									style="float: left; margin-right: 50px;">总金额：￥ <span >${OutputPrice }</span>
								</li>
							</ul>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</div>


</body>

