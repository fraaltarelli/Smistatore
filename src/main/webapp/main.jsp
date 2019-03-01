<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Home Page</title>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/utente.js"></script>
<script type="text/javascript" src="/js/fattura.js"></script>
<script type="text/javascript" src="/js/soggettoCommerciale.js"></script>

<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body>

	<div id="divMain">
		<div id="loginDiv">
			<br>
			<form action="javascript: login()">
				<input id="username" type="text" placeholder="username"> <input
					id="password" type="text" placeholder="password">

				<button type="submit">login</button>

			</form>
			Messaggio: <br> <strong id="messaggio">Effettua il
				login</strong>

		</div>

		<div id="loggatoDiv" style="display: none;">
			<div id="tokenDiv">
				<br> Token:
				<p id="token"></p>
			</div>

			<div id="uploadFattura">

				<form action="javascript: verificaFileCaricato()">
					<input id="fileUpload" type="file" name="file" size="40">
					<button type="submit" class="btn btn-info">Upload file</button>
				</form>
				<strong id="messaggioUploadFattura"></strong>

			</div>

			<button id="bottoneInserimentoFattura" onclick="inserimentoFattura()"
				type="button" class="btn btn-info">Inserimento fattura</button>

			<div id="formInserimentoFattura">
				<form action="javascript: inserisciFattura()">
					<table class="table">
						<thead>
							<tr>
								<th scope="col">
									<h3>Inserimento Fattura Elettronica</h3>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><strong>
										<h5>Fattura Elettronica Header:</h5>
								</strong></td>
							</tr>
							<tr>
								<td><strong>Dati Trasmissione: </strong></td>
							</tr>
							<tr>
								<td><i>IdTrasmittente: </i></td>
							</tr>
							<tr>
								<td>IdPaese: <input type="text" id="idPaeseTrasmittente"
									maxlength=5 size=5> &nbsp; &nbsp; IdCodice: <input
									type="text" id="idCodiceTrasmittente" maxlength=30 size=30>
									&nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td>ProgressivoInvio: <input type="text"
									id="progressivoInvio" maxlength=20 size=20 required>
								</td>
							</tr>
							<tr>
								<td>FormatoTrasmissione: <input type="text"
									id="formatoTrasmissione" maxlength=20 size=20>
								</td>
							</tr>
							<tr>
								<td>CodiceDestinatario: <input type="text"
									id="codiceDestinatario" maxlength=20 size=20>
								</td>
							</tr>
							<tr>
								<td><strong>Cedente Prestatore: </strong></td>
							</tr>
							<tr>
								<td><i>Dati Anagrafici: </i></td>
							</tr>
							<tr>
								<td><i>IdFiscaleIva: </i></td>
							</tr>
							<tr>
								<td>IdPaese: <input type="text" id="idPaeseCP" maxlength=5
									size=5 required> &nbsp; &nbsp; IdCodice: <input
									type="text" id="idCodiceCP" maxlength=30 size=30 required></td>
							</tr>
							<tr>
								<td>Codice Fiscale: <input type="text" id="codiceFiscaleCP"
									maxlength=30 size=30 required>
								</td>
							</tr>
							<tr>
								<td><i>Anagrafica: </i> <br> Denominazione: <input
									type="text" id="denominazioneCP" maxlength=50 size=50 required></td>
							</tr>
							<tr>
								<td>Regime Fiscale: <input type="text" id="regimeFiscaleCP"
									maxlength=30 size=30>
								</td>
							</tr>
							<tr>
								<td><i>Sede: </i></td>
							</tr>
							<tr>
								<td>Indirizzo: <input type="text" id="indirizzoSedeCP"
									maxlength=50 size=50 required> &nbsp; &nbsp; Cap: <input
									type="text" id="capSedeCP" maxlength=20 size=20 required>
									&nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td>Comune: <input type="text" id="comuneSedeCP"
									maxlength=40 size=40 required> &nbsp; &nbsp; Provincia:
									<input type="text" id="provinciaSedeCP" maxlength=40 size=40
									required> &nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td>Nazione: <input type="text" id="nazioneSedeCP"
									maxlength=40 size=40 required>
								</td>
							</tr>
							<tr>
								<td><strong>
										<h5>Fattura Elettronica Body:</h5>
								</strong></td>
							</tr>
							<tr>
								<td><strong> Dati Generali:</strong></td>
							</tr>
							<tr>
								<td><i> Dati Generali Documento: </i></td>
							</tr>
							<tr>
								<td>Tipo Documento: <input type="text" id="tipoDocumento"
									maxlength=30 size=30 required> &nbsp; &nbsp; Divisa: <input
									type="text" id="divisaDocumento" maxlength=20 size=20>
									&nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td>Data Documento: <input type="text" id="dataDocumento"
									maxlength=20 size=20 required> &nbsp; &nbsp; Numero
									Documento: <input type="number" id="numeroDocumento" min="0"
									max="100000" required> &nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td><i>Dati Ordine Acquisto: </i>
									<button id="aggiungiOrdineAcquistoBottone" type="button"
										onclick="aggiungiOrdineAcquisto()" disabled>Aggiungi
										Ordine Acquisto</button>
									<div id="datiOrdineAcquisto"></div></td>
							</tr>
							<tr>
								<td><i>Dati Contratto: </i>
									<button id="aggiungiContrattoBottone" type="button"
										onclick="aggiungiContratto()" disabled>Aggiungi
										Contratto</button>
									<div id="datiContratto"></div></td>
							</tr>
							<tr>
								<td><i>Dati Convenzione: </i>
									<button id="aggiungiConvenzioneBottone" type="button"
										onclick="aggiungiConvenzione()" disabled>Aggiungi
										Convenzione</button>
									<div id="datiConvenzione"></div></td>
							</tr>
							<tr>
								<td><i>Dati Ricezione: </i>
									<button id="aggiungiRicezioneBottone" type="button"
										onclick="aggiungiRicezione()" disabled>Aggiungi
										Ricezione</button>
									<div id="datiRicezione"></div></td>
							</tr>
							<tr>
								<td><i>Dati Trasporto: </i></td>
							</tr>
							<tr>
								<td><i>Dati Anagrafici Vettore: </i></td>
							</tr>
							<tr>
								<td><i>IdFiscaleIva: </i></td>
							</tr>
							<tr>
								<td>IdPaese: <input type="text" id="idPaeseVettore"
									maxlength=5 size=5> &nbsp; &nbsp; IdCodice: <input
									type="text" id="idCodiceVettore" maxlength=30 size=30>
									&nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td><i>Anagrafica: </i> <br> Denominazione: <input
									type="text" id="denominazioneVettore" maxlength=50 size=50>
								</td>
							</tr>
							<tr>
								<td>DataOraConsegna: <input type="text"
									id="dataOraConsegna" maxlength="30" size="30">
								</td>
							</tr>
							<tr>
								<td><strong> DatiBeniServizi:</strong></td>
							</tr>
							<tr>
								<td><i>Dettaglio Linee: </i>
									<button type="button" onclick="aggiungiLineaDocumento()">Aggiungi
										Linea Documento</button>
									<button type="button" id="rimuoviLineaDocumentoBottone"
										onclick="rimuoviLineaDocumento()" disabled>Rimuovi
										Linea Documento</button>
									<div id="dettaglioLinee"></div></td>
							</tr>
							<tr>
								<td><i>DatiRiepilogo: </i></td>
							</tr>
							<tr>
								<td>AliquotaIva: <input type="text"
									id="aliquotaIvaRiepilogo"
									placeholder="inserisci un numero decimale (es. 22.00)"
									maxlength="20" size="40"> &nbsp; &nbsp;
									ImponibileImporto: <input type="text"
									id="imponibileImportoRiepilogo"
									placeholder="inserisci un numero decimale (es. 22.00)"
									maxlength="20" size="40"> &nbsp; &nbsp;
								</td>
							</tr>
							<tr>
								<td>Imposta: <input type="text"
									id="imponibileImportoRiepilogo"
									placeholder="inserisci un numero decimale (es. 22.00)"
									maxlength="20" size="40"> &nbsp;&nbsp; EsigibilitaIva:
									<input type="text" id="esigibilitaIva" maxlength="1" size="3">
								</td>
							</tr>
							<tr>
								<td><i>DatiPagamento:</i></td>
							</tr>
							<tr>
								<td>CondizioniPagamento: <input type="text"
									id="condizioniPagamento" maxlength="30" size="30">
								</td>
							</tr>
							<tr>
								<td><i>DettaglioPagamento: </i></td>
							</tr>
							<tr>
								<td>Modalita Pagamento: <input type="text"
									id="modalitaPagamento" maxlength="30" size=30"> &nbsp;
									&nbsp; Data Scadenza Pagamento: <input type="text"
									id="dataScadenzaPagamento" maxlength=20 size=20> &nbsp;
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>Importo Pagamento: <input type="text"
									id="importoPagamento"
									placeholder="inserisci un numero decimale (es. 22.00)"
									maxlength="20" size="40">
								</td>
							</tr>

						</tbody>
					</table>

					<button type="submit" class="btn btn-info">Inserisci
						Fattura</button>


				</form>
			</div>

			<strong id="isAdmin"></strong> <br>
			<div id="filtroFatturePerStatoDiv"></div>
			<strong id="riepilogoFattureSelezionate"></strong>

			<div id="ricercaSCPerNomeForm">
				<input type="text" id="ricercaNomeSC" size="40"
					placeholder="ricerca Soggetto Commerciale per nome">

				<button onclick="listaSCPerNomeCercato()">Cerca</button>

				<strong id="messaggioSpostamentoFatture"></strong>

				<div id="listaSCPerNomeCercato"></div>

			</div>
			<!-- <strong id="messaggio2"></strong> -->
			<br>
			<button type="button" class="btn btn-success"
				id="assegnaFattureButton" onclick="assegnaAUnSC()">assegna
				fatture selezionate a un soggetto commerciale</button>
			<strong id="messaggioSelezioneFatture"></strong>
			<div id="stampaFattureDiv"></div>
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