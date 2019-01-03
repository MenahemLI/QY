<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<% 
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    Date date  = new Date();
    String now = sdf.format(date);
    %>
   <link rel="stylesheet" type="text/css" href="theme/css/mytext.css"/>
<link href="theme/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="theme/css/dataTables.responsive.css" rel="stylesheet">
<link href="theme/css/sb-admin-2.css" rel="stylesheet">
<link href="theme/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
   	<script src="theme/js/addOutvoice.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#dataTables-example').DataTable({
			responsive : false,
			searching : false,
			bLengthChange : false,
		});
	});

</script>	

<div class="container-fluid">
	<div class="row">
		<div class="col-md-8">
			<div class="card">
				<div class="card-header" data-background-color="green">
					<h4 class="title">销项发票记录表</h4>
					<p class="category">请如实填写票据信息</p>
				</div>
				<div class="card-content">
					<form name="addOutvoice" action="manager/addOutvoice.action"
						method="post" id="addOutvoiceForm">
						<input type="hidden" name="username" value="${user.u_number }">

	                                   <div class="row">
	                                   <div class="col-md-3">
								<div class="form-group label-floating is-empty">
									<label class="control-label" id="o_code_label">票据代码</label> 
									<input class="form-control" type="text" name="o_code" id="o_code">
									<span class="material-input"></span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group label-floating is-empty">
									<label class="control-label" id="o_number_label">票据号码</label> 
									<input class="form-control" type="text" name="o_number" id="o_number">
									<span class="material-input"></span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group label-floating" id="opendate">
									<label class="control-label" id="date_control_label">开票日期</label>
									<input class="form-control opendate" value="<%=now %>" id="dp1"
										type="text" name="opendate"> <span
										class="material-input"></span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group label-floating is-empty">
									<label class="control-label" id="phone_control_label">联系电话</label>
									<input class="form-control" type="text" id="client_tel"
										name="client_tel"> <span class="material-input"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group label-floating is-empty">
									<label class="control-label" id="address_control_label">地址</label>
									<input class="form-control" type="text" id="address"
										name="address"> <span class="material-input"></span>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group label-floating is-empty">
									<label class="control-label" id="buyer_control_label">购买方</label>
									<input class="form-control" id="buyer" type="text"
										name="buyer"> <span class="material-input"></span>
								</div>
							</div>
						</div>
							
	                                    
	                                    <div id="show">
											
										</div>
										<input type="button" name="btn" class="btn btn-primary" id="btn" value="添加内容" />
										<input type="button" name="btn" class="btn btn-primary" id="remo" value="删除内容" />
	                                    <div class="row">
	                                        <div class="col-md-12">
	                                            <div class="form-group">
	                                                <label>备注</label>
													<div class="form-group label-floating is-empty">
									    				<label class="control-label">如对发票信息有疑问，请备注</label>
								    					<textarea class="form-control" rows="5"></textarea>
		                        					<span class="material-input"></span></div>
	                                            </div>
	                                        </div>
	                                    </div>
	                                   
										 <input type="button" class="btn btn-primary pull-right ppt" value="确认提交"  onclick="put()"  >
										 <input type="hidden" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm" id="warning">
	                              <!--   <buttons type="hidden" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm" id="warning">小模态框</buttons>-->
										<div class="modal fade bs-example-modal-sm in" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
													<div class="modal-dialog modal-sm" role="document" aria-hidden="true">
														<div class="modal-content" aria-hidden="true">

																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
																		<h4 class="modal-title" id="mySmallModalLabel">警告...</h4>
																</div>
													<div class="modal-body warn">
										...
												</div>
											</div>
													<!-- /.modal-content -->
									</div>
							<!-- /.modal-dialog -->
						</div>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">您已添加过</div>
					<div class="panel-body">
						<table class="table  table-hover  table-striped" id="dataTables-example">
						<thead>
							<tr>
								<th>票据编号</th>
								<th>进/销项</th>
								<th>创建时间</th>
							</tr>
							</thead>
						<tbody >
							<c:forEach items="${outList }" var="o" varStatus="s">

								<tr id="colorList"  class ="fe" value="${o }">
									<td>${o.get(0).o_number }</td>
									<td>销项</td>

									<td>${o.get(0).date }</td>
								</tr>
							
							</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--弹出框-->
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
				<h4 class="modal-title" id="gridModalLabel">发票数据</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-7">
						<div class="col-md-2 ">编号：</div>
						<div class="col-md-10" id = "o_number_item"></div>
					</div>
					<div class="col-lg-5">
						<div class="col-md-4">购买方：</div>
						<div class="col-md-8" id="o_buyer_item"></div>
					</div>
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>商品</th>
							<th>规格型号</th>
							<th>单位</th>
							<th>数量</th>
							<th>单价</th>
							<th>金额</th>
							<th>税率</th>
						</tr>
					</thead>
					<tbody id="every" >
					</tbody>
				</table>
				<div class="row">
					<div class="col-md-3">
						<div class="col-md-6" style="padding: 0px;">合计：</div>
						<div class="col-md-6" style="padding: 0px;" id="price_item"></div>
					</div>
					<div class="col-lg-3">
						<div class="col-md-5" style="padding: 0px;">税额：</div>
						<div class="col-md-7"style="padding: 0px;" id ="addtaxes"></div>
					</div>
					<div class="col-lg-3 ">
						<div class="col-md-5" style="padding: 0px;">价税合计：</div>
						<div class="col-md-7" style="padding: 0px;" id="account"></div>
					</div>
					<div class="col-lg-3">
						<div class="col-md-6" style="padding: 0px;">开票日期：</div>
						<div class="col-md-6" style="padding: 0px;" id="opendate_item"></div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
