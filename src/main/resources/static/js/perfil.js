$(document).ready(function(){
	
	$("#dlt-conf").click(function(){
		
		$.ajax({
		    url: '/delete/user/' + $(this).attr("data-id"),
		    type: 'GET',
		    success: function(result) {
		    	window.location.href = "/logout";
		    }
		});
		
	});
	
	
});
