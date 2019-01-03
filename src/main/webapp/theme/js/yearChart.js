
		function YYYYMMstart() {
			//先给年下拉框赋内容   
			var y = new Date().getFullYear();
			for(var i = (y - 20); i <= y; i++) //以今年为准，前30年，后30年   
				document.reg_testdate.YYYY.options.add(new Option(" " + i + " 年", i));
			document.reg_testdate.YYYY.value = y;
			
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