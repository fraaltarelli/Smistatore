<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home Page</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	
</script>
<script type="text/javascript" src="/js/utente.js"></script>
<script type="text/javascript" src="/js/fattura.js"></script>
<script type="text/javascript" src="/js/cliente.js"></script>

<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body>

	<div id="divMain">
		<div id="loginDiv">
			<input id="username" type="text" placeholder="username"> <input
				id="password" type="text" placeholder="password">

			<button onclick="login()">login</button>

        <br> <br> Messaggio:
		<p id="messaggio">Effettua il login</p>
		</div>

		<br>
		<div id="loggatoDiv" style="display: none;">
	        <br> Token:
			<p id="token"></p>

		</div>

		
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
</body>
</html>