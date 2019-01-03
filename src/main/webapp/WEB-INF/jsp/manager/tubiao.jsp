<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
$(document).ready(function() {
	$("#searchfind").off();
	$("#searchfind").on("submit", function() {
		var FIRST = $(this).find("select[name=FIRST]").val();
		var SECOND = $(this).find("select[name=SECOND]").val();
		var THREE = $(this).find("select[name=THREE]").val();
		/*
		$.ajax({
			type : "GET",
			async : false,
			cache : false,
			url : "manager/toProtucdatass.action",
			dataType : 'json',
			data : {YYYY:$("#content").attr("name"),FIRST : FIRST,SECOND : SECOND},
			success : function(json) {
				$("#findmode1").html();
			},
			error : function(obj) {
				alert("图表请求数据失败!");

			}
		
			});*/
		
		$("#findmode1").load("manager/toProtucdatass.action", {
			YYYY :  $("#content").attr("name"),
			FIRST : FIRST,
			SECOND : SECOND,
			THREE : THREE,
		});
		
		
		
		return false;
	});
});
	


	function Tstard() {
		var names = [];

		$.ajax({
			type : "GET",
			async : false,
			cache : false,
			url : "manager/toProtuctname.action",
			dataType : 'json',
			data : {},
			success : function(json) {
				for (var i = 0; i < json.length; i++) {
					names.push(json[i].proname);
				}

				var length = json.length;

				var arrays = new Array();


				for (var i = 0; i < length; i++) {
					for (var i = 1; i <= length; i++) {
						document.proudate.FIRST.options.add(new Option(
								names[length-i], names[length-i]));
						document.proudate.SECOND.options.add(new Option(
								names[length-i], names[length-i]));
						document.proudate.THREE.options.add(new Option(
								names[length-i], names[length-i]));
					}
					if (document.attachEvent) {
						window.attachEvent("onload", Tstard);
					} else {
						window.addEventListener('load', Tstard, false);
					}

				}
			},
			error : function(obj) {
				alert("请求数据失败!");

			}
		});
	}

	Tstard();
</script>
<script type="text/javascript" src="theme/js/charts-flotzj.js"></script>
	<div class="panel panel-default">
			<div class="panel-heading">
				<h1 class="page-header" style="float: left;">${YYYY }年</h1>
				<form name="proudate" role="form" method="post"
					style="float: left; margin-top: 0px; margin-left: px;"
					id="searchfind">
					<select name="FIRST" class="btn">
						<option value="#">请选择 产品</option>
					</select> <select name="SECOND" class="btn">
						<option value="#">请选择 产品</option>
					</select>
					<select name="THREE" class="btn">
						<option value="#">请选择 产品</option>
					</select>
					<h1 class="page-header" style="float: left;">月销售额对比</h1>

					<div class="btn-group btn-group-sm" style="margin-left: 10px;">
						<input class="btn btn-info" type="submit" id="" value="搜索" />
					</div>

				</form>
			</div>
		</div>
		<div class="container-fluid content">
			<div class="main">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-body">
								<table style="width: 100%;"
									class="table table-striped table-bordered" id="dataproduct">
									<thead>
										<tr>
											<th>产品</th>
											<th>销售额</th>
											<th>数量</th>
											<th>单位</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${productdatalist }" var="s" end="2">
											<tr class="even gradeC">
												<td>${s.productn }</td>
												<td>${s.sale }</td>
												<td>${s.salenum }</td>
												<td>${s.unit }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div id="dotChart" name="${promonthlist } "
									style="height: 300px; width: 100%"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		