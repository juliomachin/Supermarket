$(document).ready(function(){
	
	$(document).on('click','button#del-prod', function(){
		$.ajax({
		    url: '/delete/producto' + $(this).attr("data-id"),
		    type: 'GET',
		    success: function(result) {
		    	
		    }
		});
	});
	
});