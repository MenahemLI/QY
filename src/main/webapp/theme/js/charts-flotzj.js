(function() {
var promonthlist = $("#dotChart").attr("name").split(",|");
var promonthlists = promonthlist[1].split(",");
var promonthlists1 = promonthlist[3].split(",");
var promonthlists2 = promonthlist[5].split(",");

var plot = $.plot($("#dotChart"), [
		{
			data : [ [ 1, promonthlists[0] ], [ 2, promonthlists[1] ],
					[ 3, promonthlists[2] ], [ 4, promonthlists[3] ],
					[ 5, promonthlists[4] ], [ 6, promonthlists[5] ],
					[ 7, promonthlists[6] ], [ 8, promonthlists[7] ],
					[ 9, promonthlists[8] ], [ 10, promonthlists[9] ],
					[ 11, promonthlists[10] ], [ 12, promonthlists[11] ] ],
			label : promonthlists[12]
		},
		{
			data : [ [ 1, promonthlists2[0] ], [ 2, promonthlists2[1] ],
					[ 3, promonthlists2[2] ], [ 4, promonthlists2[3] ],
					[ 5, promonthlists2[4] ], [ 6, promonthlists2[5] ],
					[ 7, promonthlists2[6] ], [ 8, promonthlists2[7] ],
					[ 9, promonthlists2[8] ], [ 10, promonthlists1[9] ],
					[ 11, promonthlists2[10] ], [ 12, promonthlists2[11] ] ],
			label : promonthlists2[12]
		},
		{
			data : [ [ 1, promonthlists1[0] ], [ 2, promonthlists1[1] ],
					[ 3, promonthlists1[2] ], [ 4, promonthlists1[3] ],
					[ 5, promonthlists1[4] ], [ 6, promonthlists1[5] ],
					[ 7, promonthlists1[6] ], [ 8, promonthlists1[7] ],
					[ 9, promonthlists1[8] ], [ 10, promonthlists1[9] ],
					[ 11, promonthlists1[10] ], [ 12, promonthlists1[11] ] ],
			label : promonthlists1[12]
		}, ], {
	series : {
		lines : {
			show : true,
			lineWidth : 2,
			fill : false,
		},
		points : {
			show : true,
			lineWidth : 0
		},
		shadowSize : 0
	},
	grid : {
		hoverable : true,
		clickable : true,
		tickColor : "#150e0",
		borderWidth : 0
	},
	colors : [ "#ee1414", "#082683", "#CA8622" ],
	xaxis : {
		ticks : [ [ 1, "一月" ], [ 2, "二月" ], [ 3, "三月" ], [ 4, "四月" ],
				[ 5, "五月" ], [ 6, "六月" ], [ 7, "七月" ], [ 8, "八月" ],
				[ 9, "九月" ], [ 10, "十月" ], [ 11, "十一月" ], [ 12, "十二月" ], ],
		min : 1,
		max : 12,
		tickDecimals : 0,
	},
	yaxis : {
		ticks : 7,
		tickDecimals : 0
	},
});

function showTooltip(x, y, contents) {
	$('<div id="tooltip">' + contents + '</div>').css({
		position : 'absolute',
		display : 'none',
		top : y + 8,
		left : x + -40,
		border : '1px solid #fdd',
		padding : '2px',
		'background-color' : '#dfeffc',
		opacity : 0.90
	}).appendTo("body").fadeIn(200);
}

var previousPoint = null;
$("#dotChart")
		.bind(
				"plothover",
				function(event, pos, item) {
					$("#x").text(pos.x);
					$("#y").text(pos.y.toFixed(2));

					if (item) {
						if (previousPoint != item.dataIndex) {
							previousPoint = item.dataIndex;

							$("#tooltip").remove();
							var x = item.datapoint[0], y = item.datapoint[1]
									.toFixed(2);

							showTooltip(item.pageX, item.pageY,
									item.series.label + "   " + x + "月份<br/>￥"
											+ y);
						}
					} else {
						$("#tooltip").remove();
						previousPoint = null;
					}
				});
})();