$(document).ready(function () {
	
	$(document).on('click','button#ver', function(){
		$.get("/pedido/ver/" + $(this).attr("data-id"), function(data){
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