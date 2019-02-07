function spostamentoFattura(){
//	var statoFattura = $("#statoFattura").val();
	var token = $("#token").text();
	var clienteId = $("#clienteId").val();
	var fatturaId = $("#fatturaId").val();
	$.ajax({
		type: "GET",
		url: "/api/cliente/spostamentoFattura/"+clienteId+"/"+fatturaId,
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