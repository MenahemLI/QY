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
	 $('#example').DataTable();
	    $('input.global_filter').on( 'keyup click', function () {
	        filterGlobal();
	    });
	 
	    $('input.column_filter').on( 'keyup click', function () {
	    	
	        filterColumn( $(this).parents('.form-group').attr('data-column') );
	    });
	    
	  
	    $("#invoiceData").click(function(){
	    	
	    	 var panduan=$("#invoiceData").attr("name")
	    	  	 var YYYY = $("#the2b").attr("name");
	    	 $("#dataList").load("manager/toDataList.action", {
					YYYY : YYYY,panduan:panduan
				});
	    	

	    });
	    $("#outvoiceData").click(function(){
	    	
	    	 var panduan=$("#outvoiceData").attr("name")
	    	 var YYYY = $("#the2b").attr("name");
	    	 $("#dataList").load("manager/toDataList.action", {
					YYYY : YYYY,panduan:panduan
				});
	    	 

	    });
})