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
<link href="theme/css/sb-admin-2.css" rel="stylesheet">
<link href="theme/css/DateTimePicker.css" rel="stylesheet">
<link href="theme/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="theme/js/echarts.js"></script>
<script type="text/javascript" src="theme/js/DateTimePicker.js"></script>

<script>
	$(function() {
		$("#searchrFrom").off();
		$("#searchrFrom").on("submit", function() {
			var YYYY = $(this).find("select[name=YYYY]").val();
			$("#dataManager").load("manager/toDataManager.action", {
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
	
	
</script>

<div id="dataManager" style="width: 1920px; height: 327.6px">
	<div class="row">
		<div class="col-lg-12 ">
			<input type="hidden" id="the2b" name="${YYYY }">
			<h1 class="page-header" style="float: left;">${YYYY }年发票数据管理</h1>
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

	<div class="thefind">
		<button type="button" id="original" class="btn btn-primary">原始数据走势</button>
		
		<div class="row">

			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="0">
					<label class="control-label " id="address_control_label">全局搜索</label>
					<input class="form-control col0_filter global_filter" type="text"
						id="global_filter" name="address"> <span
						class="material-input"></span>
				</div>
				
			</div>
			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="1">
					<label class="control-label " id="address_control_label">发票代码搜索</label>
					<input class="form-control col1_filter column_filter" type="text"
						id="address" name="address"> <span class="material-input"></span>
				</div>
			</div>
			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="2">
					<label class="control-label " id="buyer_control_label">发票号码搜索</label>
					<input class="form-control col2_filter column_filter" id="buyer"
						type="text" name="buyer"> <span class="material-input"></span>
				</div>
			</div>
			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="9">
					<label class="control-label " id="buyer_control_label">供销商搜索</label>
					<input class="form-control col9_filter column_filter" id="buyer"
						type="text" name="buyer"> <span class="material-input"></span>
				</div>
			</div>
			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="10">
					<label class="control-label " id="buyer_control_label">联系方式搜索</label>
					<input class="form-control col10_filter column_filter" id="buyer"
						type="text" name="buyer"> <span class="material-input"></span>
				</div>
			</div>
			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="11">
					<label class="control-label " id="buyer_control_label">地址搜索</label>
					<input class="form-control col11_filter column_filter" id="buyer"
						type="text" name="buyer"> <span class="material-input"></span>
				</div>
			</div>
			<div class="col-md-1">
				<div class="form-group label-floating is-empty" data-column="8">
					<label class="control-label " id="buyer_control_label">开票日期搜索</label>
					<input class="form-control col8_filter column_filter" id="buyer"
						type="text" name="buyer"> <span class="material-input"></span>
				</div>
			</div>
		</div>

	</div>
	<table
		class="altrowstable table  table-hover  table-striped dataTables-example"
		id="example" class="display" cellspacing="0" style="width: 100%">
		<thead>
			<tr>
				<th class="col-md-1">选择</th>
				<th class="col-md-1">发票代码</th>
				<th class="col-md-1">发票号码</th>
				<th class="col-md-1">认证状态</th>
				
				<th class="col-md-1" style="text-align: right;">合计金额</th>
				<th class="col-md-1" style="text-align: right;">价税合计</th>
				<th class="col-md-1" style="text-align: right;">税额</th>
				<th class="col-md-1" >认证时间</th>
				<th class="col-md-1" >开票时间</th>
				<th class="col-md-1">供销商</th>
				<th class="col-md-1">联系方式</th>
				<th class="col-md-1">地址</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${invoiceList }" var="i">
				<tr>
					<td class="col-md-1">
					<c:if test="${!i.estate }">
							<input name="checkboxBtn" type="checkbox"
						id="pushed" value="${i.i_code }${i.i_number }">
						</c:if>
						<c:if test="${i.estate }">
							
						</c:if>
					</td>
					<td class="col-md-1">${i.i_code }</td>
					<td class="col-md-1">${i.i_number }</td>
					<td class="col-md-1 estated"  id="estated${i.i_code }${i.i_number }">
					<c:if test="${!i.estate }">
							未认证
						</c:if>
						<c:if test="${i.estate }">
							已认证
						</c:if>
					</td>
					
					<td class="col-md-1" style="text-align: right;">${i.addprice }</td>
					<td class="col-md-1" style="text-align: right;">${i.addprice }</td>
					<td class="col-md-1" style="text-align: right;" id="taxes${i.i_code }${i.i_number }">${i.taxesprice }</td>
					<td class="col-md-1" >
						<c:if test="${!i.estate }">
							<input value="" placeholder="YYYY-MM-DD" class="estatemoni" id="estatemoni${i.i_code }${i.i_number }" type="text" > 
						</c:if>
						<c:if test="${i.estate }">
							${i.opendate }
						</c:if>
					</td>
					<td class="col-md-1" id="opendate${i.i_code }${i.i_number }">${i.opendate }</td>
					<td class="col-md-1">${i.vendor }</td>
					<td class="col-md-1">${i.client_tel }</td>
					<td class="col-md-1">${i.address }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="btn-group dropup">
		<button type="button" class="btn btn-success" id="moni">模拟认证</button>
		<button type="button" class="btn btn-success dropdown-toggle"
			data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<span class="caret"></span>

		</button>
		<ul class="dropdown-menu">
			<li  id="pickerButton">批量修改认证时间</li>
		</ul>
		<div id="dtBox"></div>
	</div>
	<button type="button" id="monishuju" class="btn btn-success">生成模拟数据图</button>

</div>
<input type="hidden" id="xianshi" data-toggle="modal"
	data-target=".bs-example-modal-lg" />
<div id="gridSystemModal" class="modal fade bs-example-modal-lg in"
	tabindex="-1" role="dialog" aria-labelledby="gridModalLabel"
	style="display: none;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="gridModalLabel">数据折线图</h4>
			</div>
			<div class="modal-body">
				<div id="tuli" class="" style="width: 850px; height: 400px;"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 警告框 -->
<input type="hidden" class="btn btn-primary" data-toggle="modal"
	data-target=".bs-example-modal-sm" id="warning">
<!--   <buttons type="hidden" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm" id="warning">小模态框</buttons>-->
<div class="modal fade bs-example-modal-sm in" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog modal-sm" role="document" aria-hidden="true">
		<div class="modal-content" aria-hidden="true">
			<div class="modal-header ">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="mySmallModalLabel">警告...</h4>
			</div>
			<div class="modal-body warn"></div>
		</div>
	</div>
</div>
<script src="theme/js/dataManager.js" type="text/javascript"
	charset="utf-8"></script>