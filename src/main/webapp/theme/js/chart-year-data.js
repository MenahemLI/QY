var randomScalingFactor = function(){ return Math.round(Math.random()*200)};

var monthOutPrice=$("#linechart").attr("value").split(",");
var monthInPrice=$("#linechart").attr("name").split(",");
	var lineChartData = {
			labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
			datasets : [
				{
					label: "My First dataset",
					 fillColor : "transparent",  
					strokeColor : "#4cff00",
					pointColor : "#4cff00",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#4cff00",
					data : [monthInPrice[0],monthInPrice[1],monthInPrice[2],monthInPrice[3],monthInPrice[4],monthInPrice[5],monthInPrice[6],monthInPrice[7],monthInPrice[8],monthInPrice[9],monthInPrice[10],monthInPrice[11]]
				},
				{
					label: "My Second dataset",
					 fillColor : "transparent",  
					strokeColor : "#fb0b0b",
					pointColor : "#fb0b0b",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#fb0b0b",
					data : [monthOutPrice[0],monthOutPrice[1],monthOutPrice[2],monthOutPrice[3],monthOutPrice[4],monthOutPrice[5],monthOutPrice[6],monthOutPrice[7],monthOutPrice[8],monthOutPrice[9],monthOutPrice[10],monthOutPrice[11]]
				}
			]

		}
	
		
	var barChartData = {
			labels : ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
			datasets : [
				{
					fillColor : "rgba(220,220,220,0.5)",
					strokeColor : "rgba(220,220,220,0.8)",
					highlightFill: "rgba(220,220,220,0.75)",
					highlightStroke: "rgba(220,220,220,1)",
					data : [monthInPrice[0],monthInPrice[1],monthInPrice[2],monthInPrice[3],monthInPrice[4],monthInPrice[5],monthInPrice[6],monthInPrice[7],monthInPrice[8],monthInPrice[9],monthInPrice[10],monthInPrice[11]]
				},
				{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 0.8)",
					highlightFill : "rgba(48, 164, 255, 0.75)",
					highlightStroke : "rgba(48, 164, 255, 1)",
					data : [monthOutPrice[0],monthOutPrice[1],monthOutPrice[2],monthOutPrice[3],monthOutPrice[4],monthOutPrice[5],monthOutPrice[6],monthOutPrice[7],monthOutPrice[8],monthOutPrice[9],monthOutPrice[10],monthOutPrice[11]]
				}
			]
	
		}

	var pieData = [
				{
					value: 300,
					color:"#30a5ff",
					highlight: "#62b9fb",
					label: "Blue"
				},
				{
					value: 50,
					color: "#ffb53e",
					highlight: "#fac878",
					label: "Orange"
				},
				{
					value: 100,
					color: "#1ebfae",
					highlight: "#3cdfce",
					label: "Teal"
				},
				{
					value: 120,
					color: "#f9243f",
					highlight: "#f6495f",
					label: "Red"
				},
				{
					value: 20,
					color: "#541A4A",
					highlight: "#5E225F",
					label: "Purple"
				}

			];
			
	var doughnutData = [
					{
						value: 300,
						color:"#30a5ff",
						highlight: "#62b9fb",
						label: "Blue"
					},
					{
						value: 50,
						color: "#ffb53e",
						highlight: "#fac878",
						label: "Orange"
					},
					{
						value: 100,
						color: "#1ebfae",
						highlight: "#3cdfce",
						label: "Teal"
					},
					{
						value: 120,
						color: "#f9243f",
						highlight: "#f6495f",
						label: "Red"
					},
					{
						value: 20,
						color: "#541A4A",
						highlight: "#5E225F",
						label: "Purple"
					}
	
				];


	var chart1 = document.getElementById("line-chart").getContext("2d");
	window.myLine = new Chart(chart1).Line(lineChartData, {
		responsive: true,
		 bezierCurve : false
	});
	var chart2 =document.getElementById("bar-chart").getContext("2d");
	window.myBar = new Chart(chart2).Bar(barChartData, {
		responsive : true
	});
	var chart3 =document.getElementById("doughnut-chart").getContext("2d");
	window.myDoughnut = new Chart(chart3).Doughnut(doughnutData, {responsive : true
	});
	var chart4 =document.getElementById("pie-chart").getContext("2d");
	window.myPie = new Chart(chart4).Pie(pieData, {responsive : true
	});
