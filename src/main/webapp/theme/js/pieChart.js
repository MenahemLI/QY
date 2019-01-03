(function() {

	var pie = echarts.init(document.getElementById("pie"));

	pie.setOption({
		title : {
			text : '产品销售额',
			x : '80%'
		},
		tooltip : {
			trigger : 'item',
			fontsize : 80,
			formatter : "{b}的{a} : ￥{c} ({d}%)"
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			show : true,
			data : [],
		},
		series : [ {
			name : '销售额',
			type : 'pie',
			radius : '80%',
			center : [ '60%', '55%' ],
			data : [],
			itemStyle : {
				emphasis : {
					shadowBlur : 20,
					shadowOffsetX : 20,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	});

	pie.showLoading();

	var names = [];

	var nums = [];
	$.ajax({
		type : "GET",
		async : false,
		cache : false,
		url : "manager/toProtuctdata.action",
		dataType : 'json',
		data : {YYYY:$("#content").attr("name")},
		success : function(json) {
			if (json) {
				
				for (var i = 0; i < json.length; i++) {
					names.push(json[i].productname);
				}
				for (var i = 0; i < json.length; i++) {
					nums.push(json[i].salenum);
				}
				
				var length = json.length;
				
				var arrays = new Array();
				 
				for(var i = 0;i < length; i++){
				    arrays[i] = {
				        value:nums[i],
				        name:names[i]
				    }
				}
				 
				data:arrays
				
				pie.hideLoading();
				pie.setOption({
					legend : {
						orient : 'vertical',
						left : 'left',
						show : true,
						data :arrays,
					},
					series : [ {
						name : '销售额',
						type : 'pie',
						radius : '70%',
						center : [ '60%', '55%' ],
						data : arrays
					} ]
				});
			}

		},
		error : function(obj) {
			alert("图表请求数据失败!");

			pie.hideLoading();
		}
	});
})();
