function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	$.ajax({
		type: "GET",
		url: "/api/utente/login/"+username+"/"+password,
		cache: false,
		success: function (token) { 
			$("#token").text(token);
//			$("#token").hide();
		}
	});
}

