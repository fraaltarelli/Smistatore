function findByStatus(){
	var statoFattura = $("#statoFattura").val();
	var token = $("#token").text();
	$("#messaggio").text("errore");
	$.ajax({
		type: "GET",
		url: "/api/fattura/find-by-searchedStatus/"+statoFattura,
		cache: false,
		dataType: "text",
		headers: {
			"Authorization": token
		},
		success: function (fatture) { 
			$("#messaggio").text(fatture);
		}
	});
}

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
			}

			if(statoFattura==undefined){
				$.ajax({
					type: "GET",
					url: "/api/fattura/ritornaFatture/isAdmin-statoFattura/"+isAdmin+"/null",
					cache: false,
					headers: {
						"Authorization": token
					},
					success: function (fatture) {
						mostraFiltroFatture(fatture, isAdmin);
						mostraFatture(fatture, isAdmin);
					}
				});
			}
			else{
				$.ajax({
					type: "GET",
					url: "/api/fattura/ritornaFatture/isAdmin-statoFattura/"+isAdmin+"/"+statoFattura,
					cache: false,
					headers: {
						"Authorization": token
					},
					success: function (fatture) {
						mostraFiltroFatture(isAdmin);
						mostraFatture(fatture);
					}
				});



			}
		}
	});
}

function mostraFiltroFatture(isAdmin){

	var html = '<div class="dropdown">'
		+'<button class="btn btn-secondary dropdown-toggle" type="button" id="filtro stato fattura" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'
		+'Filtra fatture per stato'
		+'</button>'
		+'<div class="dropdown-menu" aria-labelledby="dropdownMenu2">'
		+'<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'PROCESSED\')">PROCESSED</button>'
		+'<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'CHECK_REQ\')">CHECK_REQ</button>';
	if(isAdmin==true){
		html+='<button class="dropdown-item" type="button" onclick="stampaFattureCliente(\'DISCARDED\')">DISCARDED</button>'
	}
	html+='<button class="dropdown-item" type="button" onclick="stampaFattureCliente()">ALL</button>  </div> </div>'
		+'</div></div>';
	$("#filtroFatturePerStatoDiv").html(html);

}

function mostraFatture(fatture){
	var html = '<button type="button" class="btn btn-secondary" onclick="assegnaAUnCliente()"> assegna fatture selezionate a un cliente </button>'
		+'<table class="table"> <thead> <tr> <th scope="col"> Nome Fattura </th>'
		+'<th scope="col"> Numero Documento </th>'
		+'<th scope="col"> Data Documento </th>'
		+'<th scope="col"> Stato </th>'
		+'<th scope="col"> Cliente </th>'
		+'</tr> </thead> <tbody>';

	for(var i = 0; i < fatture.length; i++){
		var cliente = fatture[i].cliente;
		var nomeCliente="nessun cliente";
		if(cliente!=null){
			nomeCliente=cliente.name;
		}

		html += '<tr class="fatture-checkbox"> <td> <input type="checkbox" value="'+fatture[i].nomeFile+'" ';
		if(fatture[i].stato != "DISCARDED"){
			html+="disabled";
		}
		html+= '>'+fatture[i].nomeFile+'</td>'
		+'<td>'+fatture[i].numeroDocumento+'</td>'
		+'<td>'+fatture[i].dataDocumento+'</td> '
		+'<td>'+fatture[i].stato+'</td>'
		+'<td>'+nomeCliente+'</td>'
		+'<tr>';

	}
	html+= '</tbody> </table>';
	$("#stampaFattureDiv").html(html);

}


function assegnaAUnCliente(){
	fattureNomi = [];
	$(".fatture-checkbox input[type=checkbox]:checked").each(function(){
		var fatturaNome = $(this).val();	
		fattureNomi.push(fatturaNome);
	});

	var text = "Fatture selezionate: ";
	fattureNomi.forEach(function (arrayItem){
		text += arrayItem+", ";
	});
	$("#riepilogoFattureSelezionate").text(text);
	$("#ricercaClientePerNomeForm").show();
}


function spostamentoFatture(clienteNome){
	var token = $("#token").text();
	var messaggi = "";
	for (var i=0; i<fattureNomi.length; i++){

		var fatturaNome= fattureNomi[i];
		$.ajax({
			type: "GET",
			url: "/api/cliente/spostamentoFattura/"+clienteNome+"/"+fatturaNome,
			cache: false,
			dataType: "text",
			headers: {
				"Authorization": token
			},
			success: function (messaggioSpostamentoFattura) { 
				messaggi+= messaggioSpostamentoFattura+",";
				$("#messaggioSpostamentoFatture").text(messaggi);
			}
		});

	}
}



