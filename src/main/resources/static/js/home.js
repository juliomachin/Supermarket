$(document).ready(function () {
		
	$(document).on('click','button#add', function(){
		console.log($("#cantidad-" + $(this).attr("data-id"))[0].value);
		$.get("/pedido/" + $(this).attr("data-id") + "/" + $("#cantidad-" + $(this).attr("data-id"))[0].value, function( data ) {
			$("#contenido").html(data);
			$(".modal").modal("show");
		});
	});
	
	$(document).on('click','button#quitar-prod', function(){
		$.get("/pedido/quitar/" + $(this).attr("data-id"), function(data){
			$("#contenido").html(data);
		});
	});

	$(document).on('click','button#procesar', function(){
		$.get("/pedido/procesar", function(data){
			$("#contenido").html(data);
			window.location.reload();
		});
	});
	
});