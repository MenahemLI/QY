<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统-注册</title>

</head>
<body>
	<div class="row">
		<div class="col-sm-6 col-sm-offset-3">
			<h1 class="text-center">注册</h1>
			<form action="addInvoice.action"  method="post" >
			
			<!-- 开票日期 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">供应商</label>
					<div class="col-sm-8">
						<input type="type" class="form-control" name="g_vendor">
					</div>
				</div>
				<!-- 开票日期 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">胡武名称</label>
					<div class="col-sm-8">
						<input type="type" class="form-control" name="g_name">
					</div>
				</div>
				<!-- 开票日期 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">货物编号</label>
					<div class="col-sm-8">
						<input type="type" class="form-control" name="g_number">
					</div>
				</div>
				<!-- 开票日期 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">货物价格</label>
					<div class="col-sm-8">
						<input type="type" class="form-control" name="g_price">
					</div>
				</div>
				<!-- 开票日期 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">规格型号</label>
					<div class="col-sm-8">
						<input type="type" class="form-control" name="g_format">
					</div>
				</div>
				
			<!-- 开票日期 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">开票日期</label>
					<div class="col-sm-8">
						<input type="type" class="form-control" name="opendate">
					</div>
				</div>
				<!-- 客户联系方式 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">客户联系方式</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="client_tel">
					</div>
				</div>
				
				<!-- 货物单位 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">货物单位</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="unit">
					</div>
				</div>
				<!-- 数量 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">数量</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="amount">
					</div>
				</div>
				<!-- 总计 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">总计</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="price">
					</div>
				</div>
				<!-- 税率 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">税率</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="taxes">
					</div>
				</div>
				<!-- 录入人员 -->
				<div class="form-group">
					<label class="col-sm-4 control-label">录入人员</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" name="username">
					</div>
				</div>
				
				<a class="" href="updateInvoice.action?id=12">修改</a>
			
				
				
				
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<input type="submit" value="注册" class="btn btn-default">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>