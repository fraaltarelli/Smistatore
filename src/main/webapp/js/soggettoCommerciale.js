function listaSCPerNomeCercato(){
	var token = $("#token").text();
	var scNome = $("#ricercaNomeSC").val();
	$.ajax({
		type: "GET",
		url: "/api/sc/by-searchedName/"+scNome,
		cache: false,
		headers: {
			"Authorization": token
		},
		success: function (listaSC) { 
			var html='<table class="table"><thead> <tr> <th scope="col"> Nome Soggetto Commerciale </th> </tr> </thead> <tbody>';
			for(var i=0; i<listaSC.length; i++){
				html+='<tr> <td>'+listaSC[i].denominazione+' <button onclick="spostamentoFatture('+"'"+listaSC[i].id+"'"+')"> assegna a '+listaSC[i].denominazione+'</button> </td></tr>'

			}
			html+='</tbody></table>';
			$("#listaSCPerNomeCercato").html(html);
			$("#listaSCPerNomeCercato").show();
		}
	});

}