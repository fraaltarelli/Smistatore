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
				stampaFattureSC();
			}
			else{
				$("#messaggio").text("login non valido");
			}
//			$("#token").hide();
		}
	});
}

