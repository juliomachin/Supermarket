$(document).ready(function(){
	
	$(document).on('click','button#del-prod', function(){
		$.ajax({
		    url: '/delete/producto/' + $(this).attr("data-id"),
		    type: 'GET',
		    success: function(result) {
		    	window.location.reload();
		    }
		});
	});

	$(document).on('click','button#del-usr', function(){
		$.ajax({
		    url: '/delete/user/' + $(this).attr("data-id"),
		    type: 'GET',
		    success: function(result) {
		    	window.location.reload();
		    }
		});
	});
	
	$("#add_producto").click(function(){
		$.ajax({
			url: '/add/producto?nombre=' + $("#nombre")[0].value + '&descripcion=' + $("#descripcion")[0].value + '&precio=' + $("#precio")[0].value,
			type: 'GET',
			success: function(result) {
				window.location.reload();
			}
		});
	});
	
});