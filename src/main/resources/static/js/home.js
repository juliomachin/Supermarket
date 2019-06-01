$(document).ready(function () {
		
	$(document).on('click','button#add', function(){
		console.log($("#cantidad-" + $(this).attr("data-id"))[0].value);
		$.post("/pedido?id=" + $(this).attr("id") + "&cant=" + $("#cantidad-" + $(this).attr("data-id"))[0].value, function( data ) {
			alert(data);
		});
	});

});