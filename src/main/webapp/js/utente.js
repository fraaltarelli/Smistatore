function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	var json = {
			'id' : 0,
			'username' : username,
			'password' : password
	}
	var jsonString = JSON.stringify(json);
	$.ajax({
		type: "POST",
		url: "/api/utente/login",
		cache: false,
		contentType: "application/json; charset=utf-8",
		data: jsonString,
		success: function (token) {
			if(token!=""){
			$("#loginDiv").hide();
			$("#loggatoDiv").show();
			$("#token").text(token);
			$("#tokenDiv").hide();
			stampaFattureCliente();
			}
			else{
				$("#messaggio").text("login non valido");
			}
//			$("#token").hide();
		}
	});
}




//$.ajax({
//	type: "POST",
//	url: "/api/citta/inserisci-modifica",
//	cache: false,
//	contentType: "application/json; charset=utf-8",
//	data: jsonString,
//	dataType: "json",
//	success: function (result) { 
//		citiesByCountryCode(countryCode);
//		$("#messaggioForm").text("Salvataggio riuscito della citta "+name);
//	}
//
//});

