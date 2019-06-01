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
	
	//Cambiar contraseña 
	
	$("#confirmar").click(function(){
		var pass = $("#password")[0];
		var passC = $("#confirmPassword")[0];
		if(pass.value !== passC.value) {
			$("#confirmPassword").setCustomValidity("Passwords Diferentes");
		} else {
			$.ajax({
				url: '/user/perfil/update?nombre=' + $("#nombre")[0].value + '&apellido=' + $("#apellido")[0].value + '&password=' + $("#password")[0].value,
				type: 'GET',
				success: function(result) {
					window.location.reload();
				}
			});
		}
		
		
		
	});
	
	
	
});
