function listaClientiPerNomeCercato(){
	var token = $("#token").text();
	var clienteNome = $("#ricercaNomeCliente").val();
	$.ajax({
		type: "GET",
		url: "/api/cliente/by-searchedName/"+clienteNome,
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function (listaClienti) { 
			var html='<table class="table"><thead> <tr> <th scope="col"> Nome Cliente </th> </tr> </thead> <tbody>';
			for(var i=0; i<listaClienti.length; i++){
				html+='<tr> <td>'+listaClienti[i].name+' <button onclick="spostamentoFatture('+"'"+listaClienti[i].id+"'"+')"> assegna a '+listaClienti[i].name+'</button> </td></tr>'

			}
			html+='</tbody></table>';
			$("#listaClientiPerNomeCercato").html(html);
			$("#listaClientiPerNomeCercato").show();
		}
	});

}