<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="theme/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="theme/css/dataTables.responsive.css" rel="stylesheet">
<script>
	$(function() {
		$("#searchrFrom").off();
		$("#searchrFrom").on("submit", function() {
			var YYYY = $(this).find("select[name=YYYY]").val();
			$("#customer").load("manager/customerInformation.action", {
				YYYY : YYYY
			});
			
			return false;
		});

	});
	function Tstart() {
		var y = new Date().getFullYear();
		for (var i = (y - 30); i < (y + 1); i++)
			//以今年为准，前30年，后30年   
			document.reg_testdate.YYYY.options
					.add(new Option(" " + i + " 年", i));
		

		if (document.attachEvent) {
			window.attachEvent("onload", Tstart);
		} else {
			window.addEventListener('load', Tstart, false);
		}
	}
	Tstart();
	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : true,
			searching : false,
			bLengthChange : false,
		});
	});
</script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.dataTables-example').DataTable({
			responsive : false,
			searching : true,
			bLengthChange : false,
		});
	});

</script>
<div id="customer">
	<div class="row">
		<div class="col-lg-12 ">
			<h1 class="page-header" style="float: left;">${YYYY }客户信息</h1>
			<form name="reg_testdate" role="form" method="post"
				style="float: left; margin-top: 36px; margin-left: 45px;"
				id="searchrFrom">
				<select name="YYYY" class="btn">
					<option value="#">请选择 年</option>
				</select>
				<div class="btn-group btn-group-sm" style="margin-left: 10px;">
					<input class="btn btn-info" type="submit" id="da" value="搜索"
						name="搜索" />
				</div>
			</form>
		</div>
	</div>

	<table class="altrowstable table  table-hover  table-striped dataTables-example" id="alternatecolor" style="width: 100%">
		<thead>
		<tr>
			<th>客户姓名</th>
			<th >购买日期</th>
			<th >联系方式</th>
			<th >地址</th>
		</tr>
		</thead>
		<tbody >
		<c:forEach items="${cutomer }" var="customer">
			<tr>
				<td >${customer.name }</td>
				<td >${customer.date }</td>
				<td >${customer.phone }</td>
				<td>${customer.address }</td>
			</tr>
		</c:forEach>
		</tbody >
	</table>

</div>