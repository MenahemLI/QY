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


<script src="theme/js/chart.min.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/chart-year-data.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/easypiechart.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/easypiechart-data.js" type="text/javascript" charset="utf-8"></script>
<script src="theme/js/yearChart.js" type="text/javascript" charset="utf-8"></script>
<script>

	$(function() {
		
		$("#searchrFrom").off();
		$("#searchrFrom").on("submit",function(){
			
			var YYYY =$(this).find("select[name=YYYY]").val();
			$("#yearC").load("manager/toYearChart.action",{
				YYYY:YYYY
			});
			return false;
		});
	});
	
</script>
    <!-- ===================================================== -->
<div id="yearC">
    <div class="row">
			<div class="col-lg-12">
			<input type="hidden" value=${YYYY } id="thisYYYY">
				<h1 class="page-header" style="float: left;">年分析图</h1>
				<form name="reg_testdate" id="searchrFrom" style="float: left ; margin-top: 65px; margin-left: 45px;">
							<select name="YYYY">
								<option value="2017">请选择 年</option>
							</select>
						<div class="btn-group btn-group-sm" style="margin-left: 10px;">
						<input class="btn btn-info" type="submit"  id="da" value="搜索" name="搜索"/>
					</div>	
				</form>
			</div>
		</div><!--/.row-->
		
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div id="line" class="panel-heading" style="float: left;border-bottom: 0px;">折点统计</div>
					<span class=" panel-heading" id="" style="float: left;border-bottom: 0px;">
						|
					</span>
					<div id="bar"  class=" panel-heading" style="float: left;border-bottom: 0px;">条形图</div>
					<div id="stop" style="padding: 14px;margin-left: 470px">
						<div id="stopOut" class="glyphicon glyphicon-stop" style="color: #64BEF3 ;float: left;margin: 10px">销项数据</div>
						<div id ="stopIn" class="glyphicon glyphicon-stop" style="color: #D2D9DE ;float: left;margin: 10px">进项数据</div>
					</div>
					<div id=""  class=" panel-heading" style="height: 1px; clear:both;padding: 0px;">
					</div>
					<div id="linechart" class="panel-body" value=${monthOutPrices } name=${monthInPrices }>
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
						</div>
					</div>
					<div id="barchart" class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="main-chart" id="bar-chart" height="200" width="600"></canvas>
						</div>
					</div>
				</div>
				
			</div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">本月进项数据</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="chart" id="pie-chart" ></canvas>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">本月销项数据</div>
					<div class="panel-body">
						<div class="canvas-wrapper">
							<canvas class="chart" id="doughnut-chart" ></canvas>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->
		<div class="row">
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Label:</h4>
						<div class="easypiechart" id="easypiechart-blue" data-percent="92" ><span class="percent">92%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Label:</h4>
						<div class="easypiechart" id="easypiechart-orange" data-percent="65" ><span class="percent">65%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Label:</h4>
						<div class="easypiechart" id="easypiechart-teal" data-percent="56" ><span class="percent">56%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-6 col-md-3">
				<div class="panel panel-default">
					<div class="panel-body easypiechart-panel">
						<h4>Label:</h4>
						<div class="easypiechart" id="easypiechart-red" data-percent="27" ><span class="percent">27%</span>
						</div>
					</div>
				</div>
			</div>
		</div><!--/.row-->

 </div>

