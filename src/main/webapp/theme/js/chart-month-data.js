var randomScalingFactor = function(){ return Math.round(Math.random()*200)};
var dayOutPrice=$("#linechart").attr("value").split(",");
var dayInPrice=$("#linechart").attr("name").split(",");

var salesList=$("#piechart").attr("value").split(",");

year=$("#thisYYYY").val();
month =$("#thisMM").val();


if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
	var labels=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'];
	for(var i=0;i<31;i++){
		
		
	}
}else if(month==2){
	
	if((year%4==0)&&(year%100!=0)||(year%400==0)){
		
		var labels=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29'];
		
	}else{
		
		var labels=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28'];
		
	}

}else{
	
	var labels=['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30'];
}



	
var lineChartData = {
			labels,
			datasets : [
				{
					label: "My First dataset",
					 fillColor : "transparent",  
					strokeColor : "#4cff00",
					pointColor : "#4cff00",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#4cff00",
					data : [dayInPrice[0],dayInPrice[1],dayInPrice[2],dayInPrice[3],dayInPrice[4],dayInPrice[5],dayInPrice[6],dayInPrice[7],dayInPrice[8],dayInPrice[9],dayInPrice[10],dayInPrice[11],dayInPrice[12],dayInPrice[13],dayInPrice[14],dayInPrice[15],dayInPrice[16],dayInPrice[17],dayInPrice[18],dayInPrice[19],dayInPrice[20],dayInPrice[21],dayInPrice[22],dayInPrice[23],dayInPrice[24],dayInPrice[25],dayInPrice[26],dayInPrice[27],dayInPrice[28],dayInPrice[29],dayInPrice[30]]
				},
				{
					label: "My Second dataset",
					 fillColor : "transparent",  
					strokeColor : "#fb0b0b",
					pointColor : "#fb0b0b",
					pointStrokeColor : "#fff",
					pointHighlightFill : "#fff",
					pointHighlightStroke : "#fb0b0b",
					data : [dayOutPrice[0],dayOutPrice[1],dayOutPrice[2],dayOutPrice[3],dayOutPrice[4],dayOutPrice[5],dayOutPrice[6],dayOutPrice[7],dayOutPrice[8],dayOutPrice[9],dayOutPrice[10],dayOutPrice[11],dayOutPrice[12],dayOutPrice[13],dayOutPrice[14],dayOutPrice[15],dayOutPrice[16],dayOutPrice[17],dayOutPrice[18],dayOutPrice[19],dayOutPrice[20],dayOutPrice[21],dayOutPrice[22],dayOutPrice[23],dayOutPrice[24],dayOutPrice[25],dayOutPrice[26],dayOutPrice[27],dayOutPrice[28],dayOutPrice[29],dayOutPrice[30]]
				}
			],
			

		}
		
	var barChartData = {
			labels,
			datasets : [
				{
					fillColor : "rgba(220,220,220,0.5)",
					strokeColor : "rgba(220,220,220,0.8)",
					highlightFill: "rgba(220,220,220,0.75)",
					highlightStroke: "rgba(220,220,220,1)",
					data : [dayInPrice[0],dayInPrice[1],dayInPrice[2],dayInPrice[3],dayInPrice[4],dayInPrice[5],dayInPrice[6],dayInPrice[7],dayInPrice[8],dayInPrice[9],dayInPrice[10],dayInPrice[11],dayInPrice[12],dayInPrice[13],dayInPrice[14],dayInPrice[15],dayInPrice[16],dayInPrice[17],dayInPrice[18],dayInPrice[19],dayInPrice[20],dayInPrice[21],dayInPrice[22],dayInPrice[23],dayInPrice[24],dayInPrice[25],dayInPrice[26],dayInPrice[27],dayInPrice[28],dayInPrice[29],dayInPrice[30]]
				},
				{
					fillColor : "rgba(48, 164, 255, 0.2)",
					strokeColor : "rgba(48, 164, 255, 0.8)",
					highlightFill : "rgba(48, 164, 255, 0.75)",
					highlightStroke : "rgba(48, 164, 255, 1)",
					data : [dayOutPrice[0],dayOutPrice[1],dayOutPrice[2],dayOutPrice[3],dayOutPrice[4],dayOutPrice[5],dayOutPrice[6],dayOutPrice[7],dayOutPrice[8],dayOutPrice[9],dayOutPrice[10],dayOutPrice[11],dayOutPrice[12],dayOutPrice[13],dayOutPrice[14],dayOutPrice[15],dayOutPrice[16],dayOutPrice[17],dayOutPrice[18],dayOutPrice[19],dayOutPrice[20],dayOutPrice[21],dayOutPrice[22],dayOutPrice[23],dayOutPrice[24],dayOutPrice[25],dayOutPrice[26],dayOutPrice[27],dayOutPrice[28],dayOutPrice[29],dayOutPrice[30]]
				}
			]
	
		}

	var pieData = [
				{
					value: parseFloat(salesList[0]),
					color:"#30a5ff",
					highlight: "#62b9fb",
					label: salesList[1]+salesList[2]
				},
				{
					value: parseFloat(salesList[3]),
					color: "#ffb53e",
					highlight: "#fac878",
					label: salesList[4]+salesList[5]
				},
				{
					value: parseFloat(salesList[6]),
					color: "#1ebfae",
					highlight: "#3cdfce",
					label: salesList[7]+salesList[8]
				},
				{
					value: parseFloat(salesList[9]),
					color: "#f9243f",
					highlight: "#f6495f",
					label: salesList[10]+salesList[11]
				},
				{
					value: 254,
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
