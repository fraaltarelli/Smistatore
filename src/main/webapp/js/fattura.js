numeroLinea=0;
function inserimentoFattura(){
	$("#bottoneInserimentoFattura").hide();
	$("#formInserimentoFattura").show();
}


function aggiungiLineaDocumento(){
	numeroLinea++;
	var html= $("#dettaglioLinee").html()+'<div id="divLinea'+numeroLinea+'">NumeroLinea: <input type="text" id= "numeroLinea'+numeroLinea+'" readonly value="'+numeroLinea+'" maxlength="3" size="3"> &nbsp; &nbsp'
	+'Descrizione: <input type="text" id="descrizioneLinea'+numeroLinea+'" maxlength="50" size="50"> &nbsp; &nbsp'
	+'Quantita: <input type="text" id="quantitaLinea'+numeroLinea+'" placeholder="inserisci un numero decimale (es. 22.00)" maxlength="20" size="40"> &nbsp; &nbsp;'
	+'<br>Prezzo Unitario: <input type="text" id="prezzoUnitarioLinea'+numeroLinea+'" placeholder="inserisci un numero decimale (es. 22.00)" maxlength="20" size="40"> &nbsp; &nbsp;'
	+'Prezzo Totale: <input type="text" id="prezzoTotaleLinea'+numeroLinea+'" placeholder="inserisci un numero decimale (es. 22.00)" maxlength="20" size="40"> &nbsp; &nbsp;'
	+'<br>AliquotaIVA: <input type="text" id="aliquotaIvaLinea'+numeroLinea+'" placeholder="inserisci un numero decimale (es. 22.00)" maxlength="20" size="40"> &nbsp; &nbsp;'
	+'</div>';
	$("#dettaglioLinee").html(html);

	$("#aggiungiOrdineAcquistoBottone").prop("disabled", false);
	$("#aggiungiContrattoBottone").prop("disabled", false);
	$("#aggiungiConvenzioneBottone").prop("disabled", false);
	$("#aggiungiRicezioneBottone").prop("disabled", false);

	$("#rimuoviLineaDocumentoBottone").prop("disabled", false);
	selectNumeroLineaDiv();
}


function rimuoviLineaDocumento(){
	$("#divLinea"+numeroLinea).remove();
	numeroLinea--;
	selectNumeroLineaDiv();

	if(numeroLinea==0){
		$("#aggiungiOrdineAcquistoBottone").prop("disabled", true);
		$("#aggiungiContrattoBottone").prop("disabled", true);
		$("#aggiungiConvenzioneBottone").prop("disabled", true);
		$("#aggiungiRicezioneBottone").prop("disabled", true);

		$("#rimuoviLineaDocumentoBottone").prop("disabled", true);	
	}

}


function selectNumeroLineaDiv(){
	$(".selectNumeroLinea").each(function() {
		var valore= $(this).val();		
		var html= '';
		for(var i=1; i<=numeroLinea; i++){
			html+='<option value="'+i+'">'+i+'</option>';
		} 
		$(this).html(html);
		$(this).val(valore);
	});
}


function aggiungiOrdineAcquisto(){
	var html = '<div class="ordineAcquistoDiv"><button class="toRemoveParent" type="button"> Rimuovi Ordine Acquisto </button>'
		+'<br> Riferimento Numero Linea: <select class="selectNumeroLinea"></select> &nbsp; &nbsp;'
		+'IdDocumento: <input type="number" min="1" max="100000" required> &nbsp; &nbsp;'
		+'NumItem: <input type="number" min=1" max="100000" required> <br>'
		+'CodiceCUP: <input type="text" maxlength="30" size="30" required> &nbsp; &nbsp;'
		+'CodiceCIG: <input type="text" maxlength="30" size="30" required> '
		+'</div>';
	$("#datiOrdineAcquisto").append(html);
	selectNumeroLineaDiv();
}

function aggiungiContratto(){
	var html = '<div class="contrattoDiv"><button class="toRemoveParent" type="button"> Rimuovi Contratto </button>'
		+'<br> Riferimento Numero Linea: <select class="selectNumeroLinea"> </select>&nbsp; &nbsp;'
		+'IdDocumento: <input type="number" min="1" max="100000" required> &nbsp; &nbsp;'
		+'NumItem: <input type="number" min=1" max="100000" required> <br>'
		+'CodiceCUP: <input type="text" maxlength="30" size="30" required> &nbsp; &nbsp;'
		+'CodiceCIG: <input type="text" maxlength="30" size="30" required> '
		+'</div>';
	$("#datiContratto").append(html);
	selectNumeroLineaDiv();
}

function aggiungiConvenzione(){
	var html = '<div class="convenzioneDiv"><button class="toRemoveParent" type="button"> Rimuovi Convenzione </button>'
		+'<br> Riferimento Numero Linea: <select class="selectNumeroLinea"></select> &nbsp; &nbsp;'
		+'IdDocumento: <input type="number" min="1" max="100000" required> &nbsp; &nbsp;'
		+'NumItem: <input type="number" min=1" max="100000" required> <br>'
		+'CodiceCUP: <input type="text" maxlength="30" size="30" required> &nbsp; &nbsp;'
		+'CodiceCIG: <input type="text" maxlength="30" size="30" required> '
		+'</div>';
	$("#datiConvenzione").append(html);
	selectNumeroLineaDiv();
}

function aggiungiRicezione(){
	var html = '<div class="ricezioneDiv"><button class="toRemoveParent" type="button"> Rimuovi Ricezione </button>'
		+'<br> Riferimento Numero Linea: <select class="selectNumeroLinea"></select> &nbsp; &nbsp;'
		+'IdDocumento: <input type="number" min="1" max="100000" required> &nbsp; &nbsp;'
		+'NumItem: <input type="number" min=1" max="100000" required> <br>'
		+'CodiceCUP: <input type="text" maxlength="30" size="30" required> &nbsp; &nbsp;'
		+'CodiceCIG: <input type="text" maxlength="30" size="30" required> '
		+'</div>';
	$("#datiRicezione").append(html);
	selectNumeroLineaDiv();
}




function stampaFattureSC(statoFattura){
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
				$("#uploadFattura").show();
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
		+'<button class="dropdown-item" type="button" onclick="stampaFattureSC(\'PROCESSED\')">PROCESSED</button>'
		+'<button class="dropdown-item" type="button" onclick="stampaFattureSC(\'CHECK_REQ\')">CHECK_REQ</button>';
	if(admin==true){
		html+='<button class="dropdown-item" type="button" onclick="stampaFattureSC(\'DISCARDED\')">DISCARDED</button>'
			+'<button class="dropdown-item" type="button" onclick="stampaFattureSC(\'REFUSED\')">REFUSED</button>';
	}
	html+='<button class="dropdown-item" type="button" onclick="stampaFattureSC(\'SENT\')">SENT</button>'
		+'<button class="dropdown-item" type="button" onclick="stampaFattureSC()">ALL</button>  </div> </div>'
		+'</div></div>';
	$("#filtroFatturePerStatoDiv").html(html);

}




function mostraFatture(fatture){
	var html = '<table class="table"> <thead> <tr> <th scope="col-12">#</th>  <th scope="col"> Nome Fattura </th>'
		+'<th scope="col"> Foglio di stile </th>';
	if(admin==false){
		html+= '<th scope="col"> Risposta </th>';
	}
	html+='<th scope="col"> Numero Documento </th>'
		+'<th scope="col"> Data Documento </th>'
		+'<th scope="col"> Stato </th>'
		+'<th scope="col"> Soggetto Commerciale </th>'
		+'</tr> </thead> <tbody>';

	for(var i = 0; i < fatture.length; i++){
		var sc = fatture[i].cc;
		var nomeSC = "nessun soggetto commerciale";
		var idSC = 0;
		if(sc!=null){
			idSC=sc.id;
			nomeSC=sc.denominazione;
		}

		html += '<tr class="fatture-checkbox"> <th scope="row">'+(i+1)+'</th>  <td> <input type="checkbox" value="'+fatture[i].id+'" ';
		if(fatture[i].stato != "DISCARDED"){
			html+="disabled";
		}
		html+= '><button onclick="scaricaFattura('+"'"+fatture[i].nomeFile+"'"+','+"'"+fatture[i].id+"'"+','+"'"+idSC+"'"+')" >'+fatture[i].nomeFile+'</button></td>'
		+'<td><span onclick="apriFatturaFoglioDiStile('+"'"+fatture[i].nomeFile+"'"+','+"'"+fatture[i].id+"'"+','+"'"+idSC+"'"+',\'FoglioStileAssoSoftware.xsl\')">'
		+'<i class="fas fa-align-justify"></i> </span>'
		+'<span onclick="apriFatturaFoglioDiStile('+"'"+fatture[i].nomeFile+"'"+','+"'"+fatture[i].id+"'"+','+"'"+idSC+"'"+',\'fatturab2b.xsl\')">'
		+'<i class="fas fa-align-left"></i> </span> </td>';
//		+'<td> <button style="display: block;" onclick="apriFatturaFoglioDiStile('+"'"+fatture[i].nomeFile+"'"+','+"'"+fatture[i].id+"'"+','+"'"+idCliente+"'"+',\'FoglioStileAssoSoftware.xsl\')" >stile 1</button>'
//		+'<button style="display: block;" onclick="apriFatturaFoglioDiStile('+"'"+fatture[i].nomeFile+"'"+','+"'"+fatture[i].id+"'"+','+"'"+idCliente+"'"+',\'fatturab2b.xsl\')" >stile 2</button></td>';

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
		+'<td>'+nomeSC+'</td>'
		+'<tr>';

	}
	html+= '</tbody> </table>';
	$("#stampaFattureDiv").html(html);

}






function scaricaFattura(nomeFattura, idFattura, idSC){
	var token = $("#token").text();
	$.ajax({
		type: "GET",
		url: "/api/fattura/scarica/"+idFattura+"/"+idSC,
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




function apriFatturaFoglioDiStile(nomeFattura, idFattura, idSC, foglioDiStile){
	var token = $("#token").text();
	$.ajax({
		type: "GET",
		url: "/api/fattura/by-xsl/"+idFattura+"/"+idSC+"/"+foglioDiStile,
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function (data) {
			var w = window.open('about:blank');
			w.document.open();
			w.document.write(data);
			w.document.close();

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
			stampaFattureSC();
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
			stampaFattureSC();
		}
	});

}





function assegnaAUnSC(){
	var token = $("#token").text();
	var fattureId = [];
	$(".fatture-checkbox input[type=checkbox]:checked").each(function(){
		var fatturaId = $(this).val();
		fattureId.push(fatturaId);
	});

	if(fattureId.length == 0){
		$("#messaggioSelezioneFatture").text("seleziona almeno una fattura di scarto");
	}
	else{
		$("#messaggioSelezioneFatture").text("");

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
				$("#ricercaSCPerNomeForm").show();
			}
		});

	}

}




function spostamentoFatture(scId){
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
			url: "/api/sc/spostamentoFattura/"+scId+"/"+fatturaId,
			cache: false,
			dataType: "text",
			headers: {
				"Authorization": token
			},
			success: function (messaggioSpostamentoFattura) { 
				messaggi+= messaggioSpostamentoFattura+",";
				$("#messaggioSpostamentoFatture").text(messaggi);
				stampaFattureSC();
				$("#listaSCPerNomeCercato").hide();
			}
		});

	}
}

function verificaFileCaricato(){

	var token = $("#token").text();
	var file = $("#fileUpload")[0].files; 


	var formData = new FormData(); 
	formData.append("file", file[0]);   

	if(file!=undefined){
		$.ajax({
			url : "/api/fattura/verificaFileCaricato",
			type : "POST",
			data : formData,
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			headers: {
				"Authorization": token
			},
			success : function(messaggio) {
				$("#messaggioUploadFattura").text(messaggio);
				stampaFattureSC();
				$("#fileUpload").val("");
			}


		});

	}
}


