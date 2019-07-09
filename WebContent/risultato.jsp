<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

</head>
<body>
	<br>
	<div>
		<span style="width: 20%"> <img id="stemma" src=""
			style="float: right; padding-right: 4%;" alt="Stemma della regione"
			height="100" width="93">
		</span>

		<div style="padding-left: 15%;">
			<h1 id="titolo" style="text-align: center; color: blue; width: 80%;"></h1>

			<h3 id="capoluogo"
				style="text-align: center; color: green; width: 80%;"></h3>
		</div>
	</div>
	<br>
	<div id="province"
		style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
	</div>

	<script type="text/javascript">
		var arrayProvince = [];
		prelevaDati();
		function prelevaDati() {
			var myVar =
	<%=request.getAttribute("jsonArray")%>
		;

			var nomeRegione = myVar[0];
			nomeRegione = nomeRegione["regione"]; /*mi salvo solo il valore dell'oggetto*/

			var urlStemma = myVar[1];
			urlStemma = urlStemma["urlStemma"];
			//console.info(urlStemma);

			var sigla = myVar[2];
			sigla = sigla["sigla"];

			var capoluogo = myVar[3];
			capoluogo = capoluogo["nomeCapoluogo"];

			var i;

			for (i = 4; i < myVar.length - 3; i++) {
				arrayProvince.push(myVar[i]);
				//console.info(myVar[i]);
			}

			var totComuni = myVar[myVar.length - 2];
			totComuni = totComuni["totaleComuni"];
			//console.info(totComuni);

			var totPopolazione = myVar[myVar.length - 1];
			totPopolazione = totPopolazione["totalePopolazione"];

			/*modifico il titolo della pagina*/
			var c = document.getElementById("capoluogo");
			c.innerHTML = "Capoluogo: " + capoluogo;
			/*modifico il capoluogo della pagina*/
			var t = document.getElementById("titolo");
			t.innerHTML = nomeRegione;
			/*aggiungo indirizzo per lo stemma della regione all'immagine*/
			var img = document.getElementById("stemma");
			img.src = urlStemma;

			creaGraficoTortaProvince();
		}

		function creaGraficoTortaProvince() {
			// Build the chart
			Highcharts
					.chart(
							'province',
							{
								chart : {
									plotBackgroundColor : null,
									plotBorderWidth : null,
									plotShadow : false,
									type : 'pie'
								},
								title : {
									text : 'Grafico a torta basato sulla popolazione delle province'
								},
								tooltip : {
									pointFormat : 'Nome provincia: <b>{point.name} ({point.provincia}) </b><br>'
											+ '{series.name}: <b>{point.y}</b><br>'
											+ 'Numero comuni: <b>{point.numComuni}</b>'
								},
								plotOptions : {
									pie : {
										allowPointSelect : true,
										cursor : 'pointer',
										dataLabels : {
											enabled : false
										},
										showInLegend : true
									}
								},
								series : [ {
									name : 'Popolazione',
									colorByPoint : true,
									data : (function() {
										var data = [];
										var i;

										for (i = 0; i <= arrayProvince.length - 1; i++) {
											data
													.push({
														name : arrayProvince[i].nomeProvincia,
														provincia : arrayProvince[i].provincia,
														numComuni : parseInt(arrayProvince[i].numComuni),
														y : parseInt(arrayProvince[i].popolazione),
													});
										}
										return data;
									}())

								} ]
							//fine serie
							});

		};
	</script>
</body>
</html>