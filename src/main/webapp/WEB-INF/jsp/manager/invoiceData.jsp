<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<link href="theme/css/styles.css" rel="stylesheet">
<link href="theme/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="theme/css/dataTables.responsive.css" rel="stylesheet">
<link href="theme/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script>
	$(function() {
		$("#searchrFrom").off();
		$("#searchrFrom").on("submit", function() {
			var YYYY = $(this).find("select[name=YYYY]").val();
			$("#invoiceData").load("manager/toInvoiceData.action", {
				YYYY : YYYY
			});
			return false;
		});
		
	});
	function Tstart() {
		var y = new Date().getFullYear();
		for (var i = (y - 30); i < (y + 1); i++)
			//以今年为准，前30年，后30年   
			document.reg_testdate.YYYY.options.add(new Option(" " + i + " 年",
					i));
		//赋月份的下拉框   
		
		
		if (document.attachEvent) {
			window.attachEvent("onload", Tstart);
		} else {
			window.addEventListener('load', Tstart, false);
		}
	}
	Tstart();
	$(document).ready(function() {
		$('#dataTables-example').DataTable({
			responsive : false,
			searching : false,
			bLengthChange : false,
		});
	});
	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : false,
			searching : false,
			bLengthChange : false,
		});
	});
</script>


<div id="invoiceData">
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12 ">
				<h1 class="page-header" style="float: left;">发票数据</h1>
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
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
				<div class="panel-heading">${YYYY }年进销项税收数据</div>
					<div class="panel-body">
						<div class="fa-border">
							<table width="100%" class="table  table-bordered"
								>
								<thead>
									<tr>
										<th>#</th>
										<th>一月</th>
										<th>二月</th>
										<th>三月</th>
										<th>四月</th>
										<th>五月</th>
										<th>六月</th>
										<th>七月</th>
										<th>八月</th>
										<th>九月</th>
										<th>十月</th>
										<th>十一月</th>
										<th>十二月</th>
										
									</tr>
								</thead>
									<tbody id="to">
									<tr class="even gradeC">
									<td>销项税额</td>
										<c:forEach items="${TexesOutPrice }" var="too" begin="0" varStatus="s">			
												<td>${too }</td>
										</c:forEach>
									</tr>
									<tr class="even gradeC">
									<td>进项税额</td>
										<c:forEach items="${TexesInPrice }" var="ti" begin="0" varStatus="s">			
												<td>${ti }</td>
										</c:forEach>
									</tr>
									
									<tr class="even gradeC">
									<td>应纳税额</td>
										<c:forEach items="${TexesPrice }" var="tp" begin="0" varStatus="s">			
										<c:if test="${tp<0 }">
												<td style=" color: #f50b0b" >${tp }</td>
										</c:if>
										<c:if test="${tp==0 }">
												<td style=" color: #f5b50b" >${tp }</td>
										</c:if>
										<c:if test="${tp>0 }">
												<td style=" color: #39ef0a" >${tp }</td>
										</c:if>
										</c:forEach>
									</tr>
				
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
