
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <link rel="stylesheet" type="text/css" href="theme/css/mytext.css"/>

<script>
$(function(){
	$("#addInvoiceForm").off();
	$("#addInvoiceForm").on("submit",function(){
		//异步提交表单	AJAX 异步的JavaScript和XML（JSON）
		$(this).ajaxSubmit({
			success:function(){
				alert("保存成功");
				$(".rightContent").load("toAddInvoice.action");
			},
			resetForm:true
		});
		//取消该事件的默认行为（取消该表单的提交）
		return false;
	});
});
</script>
<script type="text/javascript">

function addone(){

    document.addInvoice.action="addInvoice.action?way=1";

    document.addInvoice.submit();

}
function addtwo(){

    document.addInvoice.action="addInvoice.action?way=2";

    document.addInvoice.submit();

}

</script>
<div class="container-fluid">
	                <div class="row">
	                    <div class="col-md-8">
	                        <div class="card">
	                            <div class="card-header" data-background-color="purple">
	                                <h4 class="title">进项发票记录表</h4>
									<p class="category">请如实填写票据信息</p>
	                            </div>
	                            <div class="card-content">
	                                <form  action="addInvoice.action" name= "addInvoice"  method="post" id="addInvoiceForm">
	                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused " >
													<label class="control-label">票据编号</label>
													<input class="form-control" disabled="" type="text" value="10001">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">开票日期</label>
													<input class="form-control" type="text" disabled="" value ="2017.2.13">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">联系电话</label>
													<input class="form-control" type="text" disabled="" value ="13119388382">
												<span class="material-input"></span></div>
	                                        </div>
	                                       
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-6">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">地址</label>
													<input class="form-control" type="text" disabled="" value="甘肃秦安">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-6">
												<div class="form-group label-floating is-empty is-focused">
													<label class="control-label">供应商</label>
													<input class="form-control" type="text" disabled="" value="小米">
												<span class="material-input"></span></div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty">
													<label class="control-label">货物名称</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty">
													<label class="control-label">货物编号</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-4">
												<div class="form-group label-floating is-empty">
													<label class="control-label">规格型号</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                    </div>

	                                    <div class="row">
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty">
													<label class="control-label">货物单位(个/批)</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty">
													<label class="control-label">单价</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty">
													<label class="control-label">数量</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                        <div class="col-md-3">
												<div class="form-group label-floating is-empty">
													<label class="control-label">总价</label>
													<input class="form-control" type="text">
												<span class="material-input"></span></div>
	                                        </div>
	                                    </div>

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
										 <button type="submit" class="btn btn-primary pull-right" style="float: right;" onclick="addtwo()">确认提交</button>
	                                     <button type="submit" class="btn  pull-right" style="margin-right: 30px;color: #fff;float: right;background-color: #E38D13;" onclick="addone()">同一表单操作</button>
	                                    
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