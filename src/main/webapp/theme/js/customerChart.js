(function() {
	var  pie= echarts.init(document.getElementById("one"));
	pie.setOption({
		title : {
			text : '客户数据扇形图',
			x : 'center'
		},
		legend : {
			orient : 'vertical',
			left : 'left',
			show : true,
			data : [],
		},
		series : [ {
			name : '访问来源',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : [],
			itemStyle : {
				emphasis : {
					shadowBlur : 10,
					shadowOffsetX : 0,
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
		url : "manager/toCustomerdata.action",
		dataType : 'json',
		data : {},
		success : function(json) {
			if (json) {
				for (var i = 0; i < json.length; i++) {
					names.push(json[i].customer_name);
				}
				for (var i = 0; i < json.length; i++) {
					nums.push(json[i].sale);
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
						name : '访问来源',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
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
