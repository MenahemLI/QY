var randomScalingFactor = function(){ return Math.round(Math.random()*200)};

var TexesOutPrice=$("#linechart").attr("value").split(",");
var TexesInPrice=$("#linechart").attr("name").split(",");
var TexesPrice=$("#stopOut").attr("name").split(",");
var monthOutPrice= $("#pie-chart").attr("name").split(",");
var monthInPrice= $("#doughnut-chart").attr("name").split(",");

	var lineChartData = {
			labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
			datasets : [
				{
					label: "My First dataset",
					 fillColor : "transparent",  
					strokeColor : "#30A5FF",
					pointColor : "#30A5FF",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#30A5FF",
					data : [TexesInPrice[0],TexesInPrice[1],TexesInPrice[2],TexesInPrice[3],TexesInPrice[4],TexesInPrice[5],TexesInPrice[6],TexesInPrice[7],TexesInPrice[8],TexesInPrice[9],TexesInPrice[10],TexesInPrice[11]]
				},
				{
					label: "My Second dataset",
					 fillColor : "transparent",  
					strokeColor : "#fb0b0b",
					pointColor : "#fb0b0b",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#fb0b0b",
					data : [TexesOutPrice[0],TexesOutPrice[1],TexesOutPrice[2],TexesOutPrice[3],TexesOutPrice[4],TexesOutPrice[5],TexesOutPrice[6],TexesOutPrice[7],TexesOutPrice[8],TexesOutPrice[9],TexesOutPrice[10],TexesOutPrice[11]]
				}
				,
				{
					label: "My Three dataset",
					 fillColor : "transparent",  
					strokeColor : "#FFB53E",
					pointColor : "#FFB53E",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#FFB53E",
					data : [TexesPrice[0],TexesPrice[1],TexesPrice[2],TexesPrice[3],TexesPrice[4],TexesPrice[5],TexesPrice[6],TexesPrice[7],TexesPrice[8],TexesPrice[9],TexesPrice[10],TexesPrice[11]]
				}
			]

		}
	
		
	
//	var pieData = [
//				{
//					value: 300,
//					color:"#A5DEE4",
//					highlight: "#62b9fb",
//					label: ""
//				},
//				{
//					value: 50,
//					color: "#81C7D4",
//					highlight: "#fac878",
//					label: "Orange"
//				},
//				{
//					value: 100,
//					color: "#24936E",
//					highlight: "#3cdfce",
//					label: "Teal"
//				},
//				{
//					value: 120,
//					color: "#5DAC81",
//					highlight: "#f6495f",
//					label: "Red"
//				},
//				{
//					value: 20,
//					color: "#A8D8B9",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#F596AA",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#E87A90",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#F4A7B9",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#DB8E71",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#FB9966",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#E9A368",
//					highlight: "#5E225F",
//					label: "Purple"
//				},
//				{
//					value: 20,
//					color: "#58B2DC",
//					highlight: "#5E225F",
//					label: "Purple"
//				}
//
//			];
			
//	var doughnutData = [
//					{
//						value: 300,
//						color:"#30a5ff",
//						highlight: "#62b9fb",
//						label: "Blue"
//					},
//					{
//						value: 50,
//						color: "#ffb53e",
//						highlight: "#fac878",
//						label: "Orange"
//					},
//					{
//						value: 100,
//						color: "#1ebfae",
//						highlight: "#3cdfce",
//						label: "Teal"
//					},
//					{
//						value: 120,
//						color: "#f9243f",
//						highlight: "#f6495f",
//						label: "Red"
//					},
//					{
//						value: 20,
//						color: "#541A4A",
//						highlight: "#5E225F",
//						label: "Purple"
//					}
//	
//				];
//

	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
		responsive: true,
		 bezierCurve : false
	});
//	var chart2 =document.getElementById("bar-chart").getContext("2d");
//	window.myBar = new Chart(chart2).Bar(barChartData, {
//		responsive : true
//	});
//	var chart3 =document.getElementById("doughnut-chart").getContext("2d");
//	window.myDoughnut = new Chart(chart3).Doughnut(doughnutData, {responsive : true
//	});
	 var myChart1 = echarts.init(document.getElementById('pie-chart'));
	 var myChart2 = echarts.init(document.getElementById('doughnut-chart'));
	 option1 = {
		
		tooltip : {
		trigger: 'item',
		formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		 legend: {
		        orient: 'vertical',
		        x: 'left',
		        data:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
		    },
		series : [
		{
		    name: '月份',
		    type: 'pie',
            color: ['#A5DEE4', '#81C7D4', '#24936E', '#5DAC81','#A8D8B9',"#F596AA","#E87A90","#F4A7B9","#DB8E71","#FB9966","#E9A368","#58B2DC"],
		    radius : '80%',
		    center: ['50%', '50%'],
		    data:[
		        {value:monthOutPrice[0], name:'一月'},
		        {value:monthOutPrice[1], name:'二月'},
		        {value:monthOutPrice[2], name:'三月'},
		        {value:monthOutPrice[3], name:'四月'},
		        {value:monthOutPrice[4], name:'五月'},
		        {value:monthOutPrice[5], name:'六月'},
		        {value:monthOutPrice[6], name:'七月'},
		        {value:monthOutPrice[7], name:'八月'},
		        {value:monthOutPrice[8], name:'九月'},
		        {value:monthOutPrice[9], name:'十月'},
		        {value:monthOutPrice[10], name:'十一月'},
		        {value:monthOutPrice[11], name:'十二月'}
		        
		    ],
		    itemStyle: {
		        emphasis: {
		            shadowBlur: 10,
		            shadowOffsetX: 0,
		            shadowColor: 'rgba(0, 0, 0, 0.5)'
		        }
		    }
		}
		]
		};
	 
	 
	 option2 = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
			    },
			    series: [
			        
			        {
			            name:'访问来源',
			            type:'pie',
			            color: ['#A5DEE4', '#81C7D4', '#24936E', '#5DAC81','#A8D8B9',"#F596AA","#E87A90","#F4A7B9","#DB8E71","#FB9966","#E9A368","#58B2DC"],

			            radius: ['60%', '80%'],

			            data:[
			            	 {value:monthInPrice[0], name:'一月'},
			 		        {value:monthInPrice[1], name:'二月'},
			 		        {value:monthInPrice[2], name:'三月'},
			 		        {value:monthInPrice[3], name:'四月'},
			 		        {value:monthInPrice[4], name:'五月'},
			 		        {value:monthInPrice[5], name:'六月'},
			 		        {value:monthInPrice[6], name:'七月'},
			 		        {value:monthInPrice[7], name:'八月'},
			 		        {value:monthInPrice[8], name:'九月'},
			 		        {value:monthInPrice[9], name:'十月'},
			 		        {value:monthInPrice[10], name:'十一月'},
			 		        {value:monthInPrice[11], name:'十二月'}
			 		        
			            ]
			        }
			    ]
			};
			 
			  myChart1.setOption(option1); 
			  myChart2.setOption(option2); 
//	var chart4 =document.getElementById("pie-chart").getContext("2d");
//	window.myPie = new Chart(chart4).Pie(pieData, {responsive : true
//	});
