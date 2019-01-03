
/*各种搜索begin*/
function filterGlobal () {
    $('#example').DataTable().search(
        $('#global_filter').val()
    ).draw();
}

function filterColumn (i) {
    $('#example').DataTable().column( i ).search(
        $('.col'+i+'_filter').val()
    ).draw();
}
$(document).ready(function() {
	var yijing=0;
    $('#example').DataTable();
    $('input.global_filter').on( 'keyup click', function () {
        filterGlobal();
    });
 
    $('input.column_filter').on( 'keyup click', function () {
    	
        filterColumn( $(this).parents('.form-group').attr('data-column') );
    });
    //模拟日期末班begin
    $('.estatemoni').datepicker({							//日期格式
			format: 'yyyy-mm-dd',
		});
    //模拟日期模板end
    //原始数据begin 
    $("#original").click(function(){
    	$("#gridModalLabel").text("原始数据" +
    			"折线图");
    	var YYYY= $("#the2b").attr("name");
    	
    	$("#xianshi").trigger("click");
        $.ajax({
		  type: 'GET',//提交方式 post 或者get
		  async: false,
		  url: "manager/toOriginal.action",//提交到那里 后他的服务
		  data: {YYYY:YYYY},//提交的参数
		  
		  success:function(res){
		          
		            $("#tuli").attr("class",res);
		          
		  },
		  error:function(){
		  }
        });
        var tuli = echarts.init(document.getElementById('tuli'));
        
        var shuju=$("#tuli").attr("class").split(";");
        var TexesOutPrices=shuju[0].split(",");
        var TexesInPrices=shuju[1].split(",");
        var TexesPrices=shuju[2].split(",");
        option3 = {
        	    title: {
        	        text: '原始税额数据折线图'
        	    },
        	    tooltip: {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:["销项税额图","进项税额图","应纳税额图"]
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
        	    color: ['#fb0b0b', '#30A5FF', '#FFB53E'],
        	    series: [
        	        {
        	            name:"销项税额图",
        	            type:'line',
        	           
        	            data:[TexesOutPrices[0],TexesOutPrices[1],TexesOutPrices[2],TexesOutPrices[3],TexesOutPrices[4],TexesOutPrices[5],TexesOutPrices[6],TexesOutPrices[7],TexesOutPrices[8],TexesOutPrices[9],TexesOutPrices[10],TexesOutPrices[11]]
        	        },
        	        {
        	            name:"进项税额图",
        	            type:'line',
        	            
        	            data:[TexesInPrices[0],TexesInPrices[1],TexesInPrices[2],TexesInPrices[3],TexesInPrices[4],TexesInPrices[5],TexesInPrices[6],TexesInPrices[7],TexesInPrices[8],TexesInPrices[9],TexesInPrices[10],TexesInPrices[11]]
        	        },
        	        {
        	            name:"应纳税额图",
        	            type:'line',
        	            
        	            data:[TexesPrices[0],TexesPrices[1],TexesPrices[2],TexesPrices[3],TexesPrices[4],TexesPrices[5],TexesPrices[6],TexesPrices[7],TexesPrices[8],TexesPrices[9],TexesPrices[10],TexesPrices[11]]
        	        }
        	    ]
        	};

        tuli.setOption(option3);
    
    });
    //原始数据end
    //模拟数据begin
    $("#moni").click(function(){
    	
    	var all = $(":checkbox[name=checkboxBtn]").length;
    	var alls = $("input[type=checkbox]:checked");
    	var select = $("input[type=checkbox]:checked").length;
    	
    	
    	var ii=0;
    	$("input[type=checkbox]").each(function(){
			$("#estated"+this.value).html("未认证");
			$("#estated"+this.value).css("color","#5F6468");
		})
    	if(select==0){
    		$(".warn").html("您有尚未勾选任何需要模拟的数据，请审查");
	  		$(".warn").css("color", "red");
    		$("#warning").trigger("click");
    	}else{
    		alls.each(function(){
        		if($("#estatemoni"+this.value).val()==""){
        			ii++;
        		}
        	})
        	if(ii!=0){
        		$(".warn").html("您对勾选的项还未确认认证时间，请审查");
    	  		$(".warn").css("color", "red");
        		$("#warning").trigger("click");
        	}else{
        		
        		
        		alls.each(function(){
        			yijing=1;
            		$("#estated"+this.value).html("模拟认证状态");
            		$("#estated"+this.value).css("color","#f5b50b");
            	})
        		
        	}
    	}
    });
    //模拟数据end
//    $("#pushed").on("click",function(){
//    	alert("zhege"+$("#pushed").is(':checked'))
//    	for(var i=0;i<myfruit.length;i++){
//    	    if(myfruit[i].checked==true)
//    	       alert("第")
//    	}
//    });
    //批量修改时间begin
    $("#dtBox").DateTimePicker(
			{
				dateFormat: "yyyy-MM-dd",
				addEventHandlers: function()
				{
					var dtPickerObj = this;
				
				
				
					$("#pickerButton").click(function(e)
					{var select = $("input[type=checkbox]:checked").length;
			    	var ii=0;
			    	if(select==0){
			    		$(".warn").html("您有尚未勾选任何需要模拟的数据，请审查");
				  		$(".warn").css("color", "red");
			    		$("#warning").trigger("click");
			    	}else{
			    		$(".estatemoni").removeClass("datepicker");
						var allins = $("input[type=checkbox]:checked");
						 allins.each(function(){
							 $("#estatemoni"+this.value).addClass("datepicker")
				     		});
						e.stopPropagation();
						dtPickerObj.showDateTimePicker($(".datepicker"));
			    		
			    	}
						
					});
				}
			});
    //批量修改时间end
    //复选框事件begin
    $(":checkbox[name=checkboxBtn]").click(function() {
    	
		var all = $(":checkbox[name=checkboxBtn]").length;//所有条目的个数
		
		var select = $("input[type=checkbox]:checked").length;//获取所有被选择条目的个数
		
		
//		if(all == select) {//全都选中了
//			$("#selectAll").attr("checked", true);//勾选全选复选框
//			setJieSuan(true);//让结算按钮有效
//		} else if(select == 0) {//谁都没有选中
//			$("#selectAll").attr("checked", false);//取消全选
//			setJieSuan(false);//让结算失效
//		} else {
//			$("#selectAll").attr("checked", false);//取消全选
//			setJieSuan(true);//让结算有效				
//		}
//		showTotal();//重新计算总计
	});
    //复选框事件end
  //生成模拟数据begin
    $("#monishuju").click(function(){
    	$("#gridModalLabel").text("模拟数据折线图");
    	var ceshi=0;
    	var alls = $("input[type=checkbox]:checked");
    	alls.each(function(){
    		if($("#estated"+this.value).html()!="模拟认证状态"){
    			ceshi++;
    			
    		}
    		
    	})
    	if(yijing==0){
    		$(".warn").html("请先进行模拟认证操作");
	  		$(".warn").css("color", "red");
    		$("#warning").trigger("click");
    	}else if(ceshi!=0){
    		$(".warn").html("您勾选了尚未进行模拟认证的项，请审查");
	  		$(".warn").css("color", "red");
    		$("#warning").trigger("click");
    	}else{
    		
    		var YYYY= $("#the2b").attr("name");
    		$.ajax({
   	  		  type: 'GET',//提交方式 post 或者get
   	  		  async: false,
   	  		  url: "manager/toOriginal.action",//提交到那里 后他的服务
   	  		  data: {YYYY:YYYY},//提交的参数
   	  		  
   	  		  success:function(res){
   	  		          
   	  		            $("#tuli").attr("class",res);
   	  		  },
   	  		  error:function(){
   	  		  }
   	          });
    		
    		$("#xianshi").trigger("click");
    		 var monituli = echarts.init(document.getElementById('tuli'));
    		var shuju=$("#tuli").attr("class").split(";");
 	        var TexesOutPrices=shuju[0].split(",");
 	        var TexesInPrices=shuju[1].split(",");
 	        var TexesPrices=shuju[2].split(",");
    		 var allin = $("input[type=checkbox]:checked");
    		
     	    allin.each(function(){
     	    	
     	    	var ok= $("#taxes"+this.value).html()*1;
     	    	TexesInPrices[$("#opendate"+this.value).html().substring(5,7)-1]=(TexesInPrices[$("#opendate"+this.value).html().substring(5,7)-1]-ok.toFixed(2));
     	    	TexesInPrices[$("#estatemoni"+this.value).val().substring(5,7)-1]=( parseFloat( TexesInPrices[$("#estatemoni"+this.value).val().substring(5,7)-1])+ parseFloat(ok.toFixed(2))).toFixed(2);
     	    	TexesPrices[$("#opendate"+this.value).html().substring(5,7)-1]=(parseFloat(TexesPrices[$("#opendate"+this.value).html().substring(5,7)-1])+parseFloat(ok.toFixed(2))).toFixed(2);
     	    	TexesPrices[$("#estatemoni"+this.value).val().substring(5,7)-1]=( parseFloat( TexesPrices[$("#estatemoni"+this.value).val().substring(5,7)-1])- parseFloat(ok.toFixed(2))).toFixed(2);
     		});
     	   option = {
           	    title: {
           	        text: '模拟税额数据折线图'
           	    },
           	    tooltip: {
           	        trigger: 'axis'
           	    },
           	    legend: {
           	        data:["销项税额图","进项税额图","应纳税额图"]
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
           	    color: ['#fb0b0b', '#30A5FF', '#FFB53E'],
           	    series: [
           	        {
           	            name:"销项税额图",
           	            type:'line',
           	           
           	            data:[TexesOutPrices[0],TexesOutPrices[1],TexesOutPrices[2],TexesOutPrices[3],TexesOutPrices[4],TexesOutPrices[5],TexesOutPrices[6],TexesOutPrices[7],TexesOutPrices[8],TexesOutPrices[9],TexesOutPrices[10],TexesOutPrices[11]]
           	        },
           	        {
           	            name:"进项税额图",
           	            type:'line',
           	         
           	            data:[TexesInPrices[0],TexesInPrices[1],TexesInPrices[2],TexesInPrices[3],TexesInPrices[4],TexesInPrices[5],TexesInPrices[6],TexesInPrices[7],TexesInPrices[8],TexesInPrices[9],TexesInPrices[10],TexesInPrices[11]]
           	        },
           	        {
           	            name:"应纳税额图",
           	            type:'line',
           	          
           	            data:[TexesPrices[0],TexesPrices[1],TexesPrices[2],TexesPrices[3],TexesPrices[4],TexesPrices[5],TexesPrices[6],TexesPrices[7],TexesPrices[8],TexesPrices[9],TexesPrices[10],TexesPrices[11]]
           	        }
           	    ]
           	};

           monituli.setOption(option);
     	    
     		
    	}
    	
    	
    })
   
  //生成模拟数据end
    
});
/*各种搜索end*/
