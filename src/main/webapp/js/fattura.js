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