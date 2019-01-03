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
<script type="text/javascript" src="theme/js/echarts.js"></script>
<script type="text/javascript" src="theme/js/customerChart.js"></script>
<script>
	$(function() {
		$("#searchrFrom").off();
		$("#searchrFrom").on("submit", function() {
			var YYYY = $(this).find("select[name=YYYY]").val();
			$("#body_customer").load("manager/customerData.action", {
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
  
		
		if (document.attachEvent) {
			window.attachEvent("onload", Tstart);
		} else {
			window.addEventListener('load', Tstart, false);
		}
	}
	Tstart();
	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : false,
			searching : false,
			bLengthChange : false,
		});
	});
</script>

<div id="body_customer">
<div class="row">
	<div class="col-lg-12 ">
		<h1 class="page-header" style="float: left;">${YYYY }客户数据</h1>
		<form name="reg_testdate" role="form" method="post" style="float: left; margin-top: 36px; margin-left: 45px;"id="searchrFrom">
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
	<div class="row"id="title" style="margin-top:50px">
		<div id="one"   style="width: 400px; height: 400px; float: left;"></div>
		<div id="three" name ="${line }" style="width:63%; height: 400px; float:right;"></div>
	</div>
</div>
<script type="text/javascript">
		var elementtwo =$("#three").attr("name").split(",");
       var three = echarts.init(document.getElementById('three'));
		option3 = {
			    title: {
			        text: '客户数据折线图'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:[elementtwo[0],elementtwo[13],elementtwo[26],elementtwo[39],elementtwo[52]]
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
			    },
			    yAxis: {
			        type: 'value',
			    },
			    series: [
			        {
			            name:elementtwo[0],
			            type:'line',
			            stack: '总量',
			            data:[elementtwo[1],elementtwo[2],elementtwo[3],elementtwo[4],elementtwo[5],elementtwo[6],elementtwo[7],elementtwo[8],elementtwo[9],elementtwo[10],elementtwo[11],elementtwo[12]]
			        },
			        {
			            name:elementtwo[13],
			            type:'line',
			            stack: '总量',
			            data:[elementtwo[14],elementtwo[15],elementtwo[16],elementtwo[17],elementtwo[18],elementtwo[19],elementtwo[20],elementtwo[21],elementtwo[22],elementtwo[23],elementtwo[24],elementtwo[25]]
			        },
			        {
			            name:elementtwo[26],
			            type:'line',
			            stack: '总量',
			            data:[elementtwo[27],elementtwo[28],elementtwo[29],elementtwo[30],elementtwo[31],elementtwo[32],elementtwo[33],elementtwo[34],elementtwo[35],elementtwo[36],elementtwo[37],elementtwo[38]]
			        },
			        {
			            name:elementtwo[39],
			            type:'line',
			            stack: '总量',
			            data:[elementtwo[40],elementtwo[41],elementtwo[42],elementtwo[43],elementtwo[44],elementtwo[45],elementtwo[46],elementtwo[47],elementtwo[48],elementtwo[49],elementtwo[50],elementtwo[51]]
			        },
			        {
			            name:elementtwo[52],
			            type:'line',
			            stack: '总量',
			            data:[elementtwo[53],elementtwo[54],elementtwo[55],elementtwo[56],elementtwo[57],elementtwo[58],elementtwo[59],elementtwo[60],elementtwo[61],elementtwo[62],elementtwo[63],elementtwo[64]]
			        }
			    ]
			};

			three.setOption(option3);

		</script>