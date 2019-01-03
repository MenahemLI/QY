
$("tbody  tr").on("click",function(){
	
	//为了移除之前添加的信息，首先得清理一下
	$("#every tr").remove();
	//自动触发显示框
	$("#xianshi").trigger("click");
	//获取到点击的该销项，即outList
	var outvoice = $(this).attr("value");
	//用]把票据和其对应的货物分割开来，用来模拟对象
	var spgoods = $(this).attr("value").split("]");
	//获取发票编号
	$("#o_number_item").text(spgoods[0].split(";")[6].split("=")[1]);//获取截取到的第0个对象，然后用;号分割，获得第6个对象，然后=号分割，获取第一个对象
	//获取购买方
	$("#o_buyer_item").text(spgoods[0].split(";")[2].split("=")[1]);//同上
	//按照;号分割，按位置查找
	var sp = outvoice.split("; ");
	var  name = sp[1].split(", ");
	var  unit =sp[5].split(", ");
	var  amount =sp[7].split(", ");
	//获得单个商品总计
	var  price =sp[8].split(", ");
	var buyer = sp[2].split(", ");
	//获得总计
	var addprice=sp[12].split("=")[1];
	var taxes=sp[9].split(",");
	$("#price_item").text((addprice*1.0).toFixed(2));
	
	//获取开票时间
	$("#opendate_item").text(sp[3].split("=")[1]);
	//整理格式
	name[0]=(name[0].split("="))[1];
	unit[0]=(unit[0].split("="))[1];
	amount[0]=(amount[0].split("="))[1];
	price[0]=(price[0].split("="))[1];
	buyer[0]=(buyer[0].split("="))[1];
	taxes[0]=taxes[0].split("=")[1];
	var addtaxes=0;

	for (var int = 0; int < name.length; int++) {
		 var gsp= spgoods[int+1].split(", ");
		
		var every = "<tr>"+
						"<td>"+name[int]+"</td>"+
						"<td>"+gsp[5].split("=")[1]+"</td>"+
						"<td>"+unit[int]+"</td>"+
						"<td>"+amount[int]+"</td>"+
						"<td>"+gsp[4].split("=")[1]+"</td>"+
						"<td>"+price[int]+"</td>"+
						"<td>"+taxes[int]+"</td>"+
					"</tr>"		
						addtaxes=price[int]*(taxes[int]*0.01)+addtaxes;
			$("#every").append(every);
	}
	//计算税额

	$("#addtaxes").text(addtaxes.toFixed(2));
	//计算价税总计
	$("#account").text(addprice*1.0+addtaxes*1.0);
	
})
function put()
  		{     	var i = 0;  //用来判断必填内容是否为空，为空，+1
  				var reaty = 0;//用来判断必填内容是否格式错误，错误，+1
  			$("#addOutvoiceForm input[type='text']").each(function(){	//循环遍历input  
  				  if($(this).val()=="")
  							i++;
  					})
  			$("#addOutvoiceForm .control-label").each(function(){	//循环遍历laber
  				
  				  if($(this).css("color")=="rgb(255, 0, 0)")
  					  
  							reaty++;
  			})
  				
  		  		if(i==0&&reaty==0){
  		  		
  		  			$("#addOutvoiceForm").submit();
  		  				
  		  			
  		  			
  		  			
  		  			} 
  		  		else if(i!=0)
  		  			{
  		  			$(".warn").html("您有尚未填全的信息，请审查");
  		  			$(".warn").css("color", "red");
  		  			$("#warning").trigger("click");
  		  			
  					} else if (reaty!=0){
  						$(".warn").html("你有填写不正确的内容，请审查");
  	  		  			$(".warn").css("color", "red");
  	  		  		$("#warning").trigger("click");
  					}else  if(i!=0&&reaty!=0){
  						$(".warn").html("你有未填写或填写错误的内容，请审查");
  	  		  			$(".warn").css("color", "red");
  	  		  		$("#warning").trigger("click");
  					}
  		}

$(document).ready(function(){           //ready指页面初始化就渲染这个js
  		
  		 i=0;
  		 reaty = 0;
  	
  		 if($("#dp1").val()!=""){									//开票时间，是否为空
  				$("#opendate").removeClass("is-empty");
  				$("#opendate").addClass("is-focused");
  		}
  		
  		$('#dp1').datepicker({							//日期格式
  			format: 'yyyy-mm-dd',
  		});
  		
  	
  	//销项异步提交，当所有判断成功以后，再执行
  		$("#addOutvoiceForm").off();
  		$("#addOutvoiceForm").on("submit",function(){
  			//异步提交表单	AJAX 异步的JavaScript和XML（JSON）
  			$(this).ajaxSubmit({
  				async:false,
  				success:function(){
  					
  					alert("保存成功");
  						$(".rightContent").load("manager/toAddOutvoice.action");
  				},
  				resetForm:true
  			});
  			//取消该事件的默认行为（取消该表单的提交）
  			return false;
  		});
  		//票据号码判断部分
  		//获取焦点
  		var o_number = /^[\d]{8}$/;
  		$("#o_number").blur(function() {
  			
  			
  			$.ajax({
  				type: "GET",
  				url: "manager/findOutvoice.action",
  				async:false,
  				cache:false,
  				data: {
  					o_number: $("#o_number").val()
  				},
  				success: function(res) {
  					if($("#o_number").val() =="") {
  		  				$("#o_number_label").html("票据号码(请填写编号)");
  		  				$("#o_number_label").css("color", "red");
  		  		     } 
  					else if($("#o_number").val().match(o_number)){
  						if(res[0]=='S')
  	  					{
  	  						$("#o_number_label").html("票据号码");
  	  						$("#o_number_label").css("color", "#AAAAAA");
  	  						
  	  					}else {
  	  						$("#o_number_label").html("票据号码(已存在，不可重复编写)");
  	  						$("#o_number_label").css("color", "red");
  	  						
  	  						
  	  						}
  						
  					}else{
  						$("#o_number_label").html("票据号码(格式错误)");
  	  					$("#o_number_label").css("color", "red");
  						
  					}
  				},
  				error: function() {	
  				}
  				});
  			
  		});
  	//票据代码判断部分
  		var o_code = /^[\d]{10}|[\d]{12}$/;
  		
  		$("#o_code").blur(function() {
  		
  			if ($("#o_code").val() == "") {
  				$("#o_code_label").html("票据代码（请填写票据代码）");
  				$("#o_code_label").css("color", "red");
  				
  				
  			} else if($("#o_code").val().match(o_code)){
  				$("#o_code_label").html("票据代码");
  				$("#o_code_label").css("color", "#AAAAAA");
  			
  				}
  				else{
  					$("#o_code_label").html("票据代码(格式错误)");
  					$("#o_code_label").css("color", "red");
  				}
  			
  		});
  		//日期判断部分
  		var date= /^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
  		
  		
  		$(".opendate").blur(function() {
  			
  			
  			
  			if($(".opendate").val()=="")
  				{
  				$("#date_control_label").html("请填写时间");
  				$("#date_control_label").css("color", "red");
  				
  				}
  			else if ($(".opendate").val().match(date)) {
  				
  			} else {
  				alert("抱歉，您输入的日期格式有误，正确格式应为+2012-01-01.");
  				
  			}
  		});
  		//手机判断部分
  		var phone_reg = /^1[3|4|5|7|8]\d{9}$/;
  		
  		$("#client_tel").blur(function() {
  			if ($("#client_tel").val() == "") {
  				$("#phone_control_label").html("联系方式（请填写手机号）");
  				$("#phone_control_label").css("color", "red");
  				
  				
  			} else if($("#client_tel").val().match(phone_reg)){
  				$("#phone_control_label").html("联系方式");
  				$("#phone_control_label").css("color", "#AAAAAA");
  			
  				}
  				else{
  					$("#phone_control_label").html("联系方式(格式错误)");
  					$("#phone_control_label").css("color", "red");
  					
  				}
  			
  		});
  		//地址判断部分
  		$("#address").blur(function() {
  			if ($("#address").val() == "") {
  				$("#address_control_label").html("地址（请填写地址）");
  				$("#address_control_label").css("color", "red");
  				
  			} else {
  				$("#address_control_label").html("地址");
  				$("#address_control_label").css("color", "#AAAAAA");
  				
  			}
  		});
  		//购买方判断
  		$("#buyer").blur(function() {
  			if ($("#buyer").val() == "") {
  				$("#buyer_control_label").html("购买方（请填写购买方）");
  				$("#buyer_control_label").css("color", "red");
  			
  			} else {
  				$("#buyer_control_label").html("购买方");
  				$("#buyer_control_label").css("color", "#AAAAAA");
  				
  			}
  		});
  		//货物名称判断
  	//因为这些部分的append内容添加出来的，所以DOM没有渲染过，所以内容中定义的方法不能找到这些js，所以用$(document).on来解决，下面同样
  		$(document).on("blur","#g_name",function() {		
  			var _this = "."+ $(this).attr("class").substring(0, 7);			
  			var id = $(this).attr("class").substring(6, 7);
  			$(_this).blur(function() {
	  			if ($(_this).val() == "") {
	  				$(".g_name_control-label"+id).html("货物名称（请填写货物名称）");
	  				$(".g_name_control-label"+id).css("color", "red");
	  			
	  			} else {
	  				$(".g_name_control-label"+id).html("货物名称");
	  				$(".g_name_control-label"+id).css("color", "#AAAAAA");
	  				
	  			}
	  		});
  	  	});
  		
  		//税率判断
  		$(document).on("blur","#taxes",function() {
  			var _this = "."+ $(this).attr("class").substring(0, 6);		
  			
  			var id = $(this).attr("class").substring(5,6);
  			$(_this).blur(function() {
  	  			if ($(_this).val() == "") {
  	  				$(".taxes_control-label"+id).html("税率（请填写税率）");
  	  				$(".taxes_control-label"+id).css("color", "red");
  	  		
  	  			} else {
  	  				$(".taxes_control-label"+id).html("税率(%)");
  	  				$(".taxes_control-label"+id).css("color", "#AAAAAA");
  	  		
  	  			}
  	  		});
  	  	});
  		
  		
  		//货物单价判断
  		$(document).on("blur","#g_price",function() {
  			
  			var _this = "."+ $(this).attr("class").substring(0, 8);		
  			
  			var id = $(this).attr("class").substring(7,8);
  			$(_this).blur(function() {
  	  			if ($(_this).val() == "") {
  	  				$(".g_price_control-label"+id).html("货物单价（请填写货物单价）");
  	  				$(".g_price_control-label"+id).css("color", "red");
  	  		
  	  			} else {
  	  				$(".g_price_control-label"+id).html("货物单价");
  	  				$(".g_price_control-label"+id).css("color", "#AAAAAA");
	  	  			
  	  				if($(".amount"+id).val()!=""){
  	  					var g_price= $(".g_price"+id).val();
  	  					var g_amount = $(".amount"+id).val();
  	  					var add_price=g_price*g_amount;
	  	  				
		  	  			$("#price_item"+id).removeClass("is-empty");
		  	  			
		  	  			$(".price"+id).val(add_price);
	  	  				
  	  				}
  	  			}
  	  			});
  	  	});
  	//货物数量判断
  		$(document).on("blur","#amount",function() {
  			var _this = "."+ $(this).attr("class").substring(0, 7);			
  			var id = $(this).attr("class").substring(6,7);
  			$(_this).blur(function() {
  	  			if ($(_this).val() == "") {
  	  				$(".amount_control-label"+id).html("货物数量（请填写货物数量）");
  	  				$(".amount_control-label"+id).css("color", "red");
  	  			
  	  			} else {
  	  				$(".amount_control-label"+id).html("货物数量");
  	  				$(".amount_control-label"+id).css("color", "#AAAAAA");
  	  			
  	  			if($(".g_price"+id).val()!=""){
  	  				var g_price= $(".g_price"+id).val();
					var g_amount = $(".amount"+id).val();
					var add_price=g_price*g_amount;
	  	  				
		  	  				$("#price_item"+id).removeClass("is-empty");
		  	  			
		  	  			
		  	  				$(".price"+id).val(add_price);
	  	  					
	  				}
  	  			}
  	  		});
  	  	});
  			
  		//货物总价判断
  		$(document).on("blur","#price",function() {
  			
  			var _this = "."+ $(this).attr("class").substring(0, 6);			
  			var id = $(this).attr("class").substring(5,6);
  			$(_this).blur(function() {
  	  			if ($(_this).val() == "") {
  	  				$(".price_control-label"+id).html("货物总价（请填写货物总价）");
  	  				$(".price_control-label"+id).css("color", "red");
  	  			
  	  			} else {
  	  				$(".price_control-label"+id).html("货物总价");
  	  				$(".price_control-label"+id).css("color", "#AAAAAA");
  	  			
  	  			}
  	  		});
  	  	});
		
  
  			
  		
  		$("#remo").click(function(){
  			if(i>1){
  				$("#show"+i).remove();
  				i--;
  			}
  		});
  		$("#btn").click(function(){
  			
  			i++;
  			var html = "<div id = "+"'show"+i+"'"+">" 
            +"<label class='control-label' style='color: #C9302C;'>货物"
			+ i
			+ "</label>"
			+ "<div class='row'>"
			+ " <div class='col-md-5'>"
			+ "<div class='form-group label-floating is-empty'>"
			+ "<label class='control-label g_name_control-label"+i+"\' id=''>货物名称</label>"
			+ "<input class='g_name"+i+" form-control' type='text' name='g_name' id='g_name'>"
			+ "<span class='material-input'></span></div>"
			+ " </div>"
			+ "<div class='col-md-5'>"
			+ "<div class='form-group label-floating is-empty'>"
			+ "<label class='control-label g_format_control-label"+i+"\' id=''>规格型号</label>"
			+ "<input class='g_format"+i+"  form-control ' type='ssss' name='g_format' id='g_format'>"
			+ "<span class='material-input'></span></div>"
			+ " </div>"
			+ "<div class='col-md-2'>"
			+ "<div class='form-group label-floating is-empty'>"
			+ "<label class='control-label taxes_control-label"+i+"\' id=''>税率(%)</label>"
			+ "<input class='taxes"+i+"  form-control ' type='text' name='taxes' id='taxes'>"
			+ "<span class='material-input'></span></div>"
			+ " </div>"
			+ "  </div>"
			+" <div class='row'>"
			+ " <div class='col-md-3'>"
			+ "<div class='form-group label-floating is-empty'>"
			+ "<label class='control-label'>货物单位(个/批)</label>"
			+ "<input class='form-control' type='ssss' name ='unit'>"
			+ "<span class='material-input'></span></div>"
			+ "   </div>"
			+ " <div class='col-md-3'>"
			+ "<div class='form-group label-floating is-empty'>"
			+ "<label class='control-label g_price_control-label"+i+"\' id=''>货物单价</label>"
			+ "	<input class='g_price"+i+"  form-control ' type='text' name ='g_price' id='g_price'>"
			+ "	<span class='material-input'></span></div>"
			+ "   </div>"
			+ "  <div class='col-md-3'>"
			+ "<div class='form-group label-floating is-empty'>"
			+ "<label class='control-label amount_control-label"+i+"\' id=''>货物数量</label>"
			+ "<input class='amount"+i+"  form-control ' type='text' name ='amount' id='amount'>"
			+ "<span class='material-input'></span></div>"
			+ "   </div>"
			+ " <div class='col-md-3'>"
			+ "<div class='form-group label-floating is-empty' id='price_item"+i+"\'>"
			+ "	<label class='control-label price_control-label"+i+"\' id=''>货物总价</label>"
			+ "<input class='price"+i+"  form-control ' type='text' disabled='' name='price' id='price'>"
			+ "<input class='price"+i+"  form-control ' type='hidden'  name='price' id='price'>"
			+ "<span class='material-input'></span></div>"
			+ " </div>" + " </div>	" + "</div>";
  			
  			$("#show").append(html);
  			
  		});
  		
  		//trigger方法需要写在要触发的实体方法后面才可以生效，亲测
  		 if(i==0){
  			$("#btn").trigger("click");
  		 }
  	})