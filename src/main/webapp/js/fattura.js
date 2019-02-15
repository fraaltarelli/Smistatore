function stampaFattureCliente(statoFattura){
	var token = $("#token").text();
	$.ajax({
		type: "GET",
		url: "/api/utente/isAdmin",
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function (isAdmin) {
			if(isAdmin==true){
				$("#isAdmin").text("Admin");
				admin = true;
			}
			else{
				$("#assegnaFattureButton").hide();
				admin = false;
			}

			if(statoFattura==undefined){
				$.ajax({
					type: "GET",
					url: "/api/fattura/ritornaFatture/isAdmin-statoFattura/"+admin+"/null",
					cache: false,
					headers: {
						"Authorization": token
					},
					success: function (fatture) {
						mostraFiltroFatture(admin);
						mostraFatture(fatture, admin);
					}
				});
			}
			else{
				$.ajax({
					type: "GET",
					url: "/api/fattura/ritornaFatture/isAdmin-statoFattura/"+admin+"/"+statoFattura,
					cache: false,
					headers: {
						"Authorization": token
					},
					success: function (fatture) {
						mostraFiltroFatture(admin);
						mostraFatture(fatture);
					}
				});



			}
		}
	});
}




function mostraFiltroFatture(admin){

	var html = '<div class="dropdown">'
		+'<button class="btn btn-secondary dropdown-toggle" type="button" id="filtro stato fattura" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
		+'Filtra fatture per stato'
		+'</button>'
		+'<div class="dropdown-menu" aria-labelledby="dropdownMenu2">'
		+'<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'PROCESSED\')">PROCESSED</button>'
		+'<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'CHECK_REQ\')">CHECK_REQ</button>';
	if(admin==true){
		html+='<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'DISCARDED\')">DISCARDED</button>'
			+'<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'REFUSED\')">REFUSED</button>';
	}
	html+='<button class="dropdown-item" type="button" onclick="stampaFattureCliente()">ALL</button>  </div> </div>'
		+'</div></div>';
	$("#filtroFatturePerStatoDiv").html(html);

}




function mostraFatture(fatture){
	var html = ''
		+'<table class="table"> <thead> <tr> <th scope="col"> Nome Fattura </th>';
	if(admin==false){
		html+= '<th scope="col"> Risposta';
	}
	html+='<th scope="col"> Numero Documento </th>'
		+'<th scope="col"> Data Documento </th>'
		+'<th scope="col"> Stato </th>'
		+'<th scope="col"> Cliente </th>'
		+'</tr> </thead> <tbody>';

	for(var i = 0; i < fatture.length; i++){
		var cliente = fatture[i].cliente;
		var nomeCliente = "nessun cliente";
		var idCliente = 0;
		if(cliente!=null){
			idCliente=cliente.id;
			nomeCliente=cliente.name;
		}

		html += '<tr class="fatture-checkbox"> <td> <input type="checkbox" value="'+fatture[i].id+'" ';
		if(fatture[i].stato != "DISCARDED"){
			html+="disabled";
		}
		html+= '><button onclick="scaricaFattura('+"'"+fatture[i].nomeFile+"'"+','+"'"+fatture[i].id+"'"+','+"'"+idCliente+"'"+')" >'+fatture[i].nomeFile+'</button></td>';
		if(admin==false){
			
			html+='<td> <button style="display: block;" onclick="processaFattura('+"'"+fatture[i].id+"'"+')"';
			
			if(fatture[i].stato != "CHECK_REQ"){
				html+= 'disabled';
			}
			
			html+='> Processa </button>'
				+'<button style="display: block;" onclick="rifiutaFattura('+"'"+fatture[i].id+"'"+')"';

			if(fatture[i].stato != "CHECK_REQ"){
				html+= 'disabled';
			}
			
			html+='> Rifiuta </button>  </td>';
		}
		html+='<td>'+fatture[i].numeroDocumento+'</td>'
		+'<td>'+fatture[i].dataDocumento+'</td> '
		+'<td>'+fatture[i].stato+'</td>'
		+'<td>'+nomeCliente+'</td>'
		+'<tr>';

	}
	html+= '</tbody> </table>';
	$("#stampaFattureDiv").html(html);

}






function scaricaFattura(nomeFattura, idFattura, idCliente){
	var token = $("#token").text();
	$.ajax({
		type: "GET",
		url: "/api/fattura/scarica/"+idFattura+"/"+idCliente,
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function (data) {
			var blob = new Blob([data]);
			var link = document.createElement('a');
			link.href = window.URL.createObjectURL(blob);
			link.download = nomeFattura;
			link.click();

		}
	});

}




function processaFattura(fatturaId){
	var token = $("#token").text();
	$.ajax({
		type: "GET",
		url: "/api/fattura/processa/"+fatturaId,
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function () {
			stampaFattureCliente();
		}
	});
	
}




function rifiutaFattura(fatturaId){
	var token = $("#token").text();
	$.ajax({
		type: "GET",
		url: "/api/fattura/rifiuta/"+fatturaId,
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function () {
			stampaFattureCliente();
		}
	});
	
}





function assegnaAUnCliente(){
	var token = $("#token").text();
	var fattureId = [];
	$(".fatture-checkbox input[type=checkbox]:checked").each(function(){
		var fatturaId = $(this).val();
		fattureId.push(fatturaId);
	});

	$.ajax({
		type: "POST",
		url: "/api/fattura/fattureId-to-fattureNomeFile",
		cache: false,
		contentType: "application/json",
		data: JSON.stringify(fattureId),
		dataType: "json",	
		traditional: true,
		headers: {
			"Authorization": token
		},
		success: function (fattureNomeFile) {
			var text = "Fatture selezionate: ";
			for (arrayItem of fattureNomeFile)  {
				text += arrayItem+", ";
			}
			$("#riepilogoFattureSelezionate").text(text);
			$("#ricercaClientePerNomeForm").show();
		}
	});

}




function spostamentoFatture(clienteId){
	var token = $("#token").text();
	var messaggi = "";

	var fattureId = [];
	$(".fatture-checkbox input[type=checkbox]:checked").each(function(){
		var fatturaId = $(this).val();
		fattureId.push(fatturaId);
	});
	for (var i=0; i<fattureId.length; i++){

		var fatturaId= fattureId[i];
		$.ajax({
			type: "GET",
			url: "/api/cliente/spostamentoFattura/"+clienteId+"/"+fatturaId,
			cache: false,
			dataType: "text",
			headers: {
				"Authorization": token
			},
			success: function (messaggioSpostamentoFattura) { 
				messaggi+= messaggioSpostamentoFattura+",";
				$("#messaggioSpostamentoFatture").text(messaggi);
				stampaFattureCliente();
				$("#listaClientiPerNomeCercato").hide();
			}
		});

	}
}



