$(function(){
			var ine=0;
			$("#line").on("click",function(){
				$("#barchart").css("display","none");	
				$("#linechart").css("display","block");
			});
			$("#bar").on("click",function(){
				$("#linechart").css("display","none");	
				$("#barchart").css("display","block");	
			});
			if(ine==0){
				$("#line").trigger("click");
					ine++;
			}
		});
		
		function YYYYMMstart() {
			//先给年下拉框赋内容   
			var y = new Date().getFullYear();
			for(var i = (y - 20); i <= y; i++) //以今年为准，前30年，后30年   
				document.reg_testdate.YYYY.options.add(new Option(" " + i + " 年", i));
			for(var i = 1; i < 12; i++)
				if(i<=9){
					document.reg_testdate.MM.options.add(new Option(" " +"0"+ i + " 月", i));
				}else{
					document.reg_testdate.MM.options.add(new Option(" " + i + " 月", i));
				}
				document.reg_testdate.MM.options.add(new Option(" " + i + " 月", i));
				

			document.reg_testdate.YYYY.value = y;
			document.reg_testdate.MM.value = new Date().getMonth() + 1;
		}
		YYYYMMstart();
!function ($) {
		    $(window).on("click","ul.nav li.parent > a > span.icon", function(){          
		        $(this).find('em:first').toggleClass("glyphicon-minus");      
		    }); 
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
		})