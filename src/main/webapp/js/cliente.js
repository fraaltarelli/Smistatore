function spostamentoFattura(){
//	var statoFattura = $("#statoFattura").val();
	var token = $("#token").text();
	var clienteNome = $("#clienteNome").val();
	var fatturaNome = $("#fatturaNome").val();
	$.ajax({
		type: "GET",
		url: "/api/cliente/spostamentoFattura/"+clienteNome+"/"+fatturaNome,
		cache: false,
		dataType: "text",
		headers: {
		    "Authorization": token
		  },
		success: function (messaggio) { 
			$("#messaggio").text(messaggio);
		}
	});
}