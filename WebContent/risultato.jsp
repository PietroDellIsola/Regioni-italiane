<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>

<script src="https://code.highcharts.com/highcharts.js"></script>

</head>
<body>
	<h1>PROVINCE</h1>

	<div id="province" style="min-width: 310px; max-width: 600px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
prelevaDati();
function prelevaDati(){
	var myVar = <%= request.getAttribute("jsonArray") %>;    

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
	var arrayProvince = [];
	for(i = 4; i < myVar.length-3; i++)
		{	
			arrayProvince.push( myVar[i]);
		}
	
	var totComuni = myVar[myVar.length-2];
	totComuni = totComuni["totaleComuni"];
	//console.info(totComuni);
	
	var totPopolazione = myVar[myVar.length-1];
	totPopolazione = totPopolazione["totalePopolazione"];
	
	creaGraficoTortaProvince(arrayProvince);
}

function creaGraficoTortaProvince(arrayProvince){ 
	// Create the chart
	Highcharts.chart('province', {
	    chart: {
	        type: 'pie'
	    },
	    title: {
	        text: 'Province'
	    },
	    subtitle: {
	        text: 'Click the slices to view versions. Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>'
	    },
	    plotOptions: {
	        series: {
	            dataLabels: {
	                enabled: true,
	                format: '{point.name}: {point.y:.1f}%'
	            }
	        }
	    },

	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	    },

	    /*ADATTARE LE SERIE AI DATI IN INPUT DELLE PROVINCE*/
	    series: [
	        {
	            name: "Browsers",
	            colorByPoint: true,
	            data: [
	                {
	                    name: "Chrome",
	                    y: 62.74,
	                    drilldown: "Chrome"
	                },
	                {
	                    name: "Firefox",
	                    y: 10.57,
	                    drilldown: "Firefox"
	                },
	                {
	                    name: "Internet Explorer",
	                    y: 7.23,
	                    drilldown: "Internet Explorer"
	                },
	                {
	                    name: "Safari",
	                    y: 5.58,
	                    drilldown: "Safari"
	                },
	                {
	                    name: "Edge",
	                    y: 4.02,
	                    drilldown: "Edge"
	                },
	                {
	                    name: "Opera",
	                    y: 1.92,
	                    drilldown: "Opera"
	                },
	                {
	                    name: "Other",
	                    y: 7.62,
	                    drilldown: null
	                }
	            ]
	        }
	    ],
	    drilldown: {
	        series: [
	            {
	                name: "Chrome",
	                id: "Chrome",
	                data: [
	                    [
	                        "v65.0",
	                        0.1
	                    ],
	                    [
	                        "v64.0",
	                        1.3
	                    ],
	                    [
	                        "v63.0",
	                        53.02
	                    ],
	                    [
	                        "v62.0",
	                        1.4
	                    ],
	                    [
	                        "v61.0",
	                        0.88
	                    ],
	                    [
	                        "v60.0",
	                        0.56
	                    ],
	                    [
	                        "v59.0",
	                        0.45
	                    ],
	                    [
	                        "v58.0",
	                        0.49
	                    ],
	                    [
	                        "v57.0",
	                        0.32
	                    ],
	                    [
	                        "v56.0",
	                        0.29
	                    ],
	                    [
	                        "v55.0",
	                        0.79
	                    ],
	                    [
	                        "v54.0",
	                        0.18
	                    ],
	                    [
	                        "v51.0",
	                        0.13
	                    ],
	                    [
	                        "v49.0",
	                        2.16
	                    ],
	                    [
	                        "v48.0",
	                        0.13
	                    ],
	                    [
	                        "v47.0",
	                        0.11
	                    ],
	                    [
	                        "v43.0",
	                        0.17
	                    ],
	                    [
	                        "v29.0",
	                        0.26
	                    ]
	                ]
	            },
	            {
	                name: "Firefox",
	                id: "Firefox",
	                data: [
	                    [
	                        "v58.0",
	                        1.02
	                    ],
	                    [
	                        "v57.0",
	                        7.36
	                    ],
	                    [
	                        "v56.0",
	                        0.35
	                    ],
	                    [
	                        "v55.0",
	                        0.11
	                    ],
	                    [
	                        "v54.0",
	                        0.1
	                    ],
	                    [
	                        "v52.0",
	                        0.95
	                    ],
	                    [
	                        "v51.0",
	                        0.15
	                    ],
	                    [
	                        "v50.0",
	                        0.1
	                    ],
	                    [
	                        "v48.0",
	                        0.31
	                    ],
	                    [
	                        "v47.0",
	                        0.12
	                    ]
	                ]
	            },
	            {
	                name: "Internet Explorer",
	                id: "Internet Explorer",
	                data: [
	                    [
	                        "v11.0",
	                        6.2
	                    ],
	                    [
	                        "v10.0",
	                        0.29
	                    ],
	                    [
	                        "v9.0",
	                        0.27
	                    ],
	                    [
	                        "v8.0",
	                        0.47
	                    ]
	                ]
	            },
	            {
	                name: "Safari",
	                id: "Safari",
	                data: [
	                    [
	                        "v11.0",
	                        3.39
	                    ],
	                    [
	                        "v10.1",
	                        0.96
	                    ],
	                    [
	                        "v10.0",
	                        0.36
	                    ],
	                    [
	                        "v9.1",
	                        0.54
	                    ],
	                    [
	                        "v9.0",
	                        0.13
	                    ],
	                    [
	                        "v5.1",
	                        0.2
	                    ]
	                ]
	            },
	            {
	                name: "Edge",
	                id: "Edge",
	                data: [
	                    [
	                        "v16",
	                        2.6
	                    ],
	                    [
	                        "v15",
	                        0.92
	                    ],
	                    [
	                        "v14",
	                        0.4
	                    ],
	                    [
	                        "v13",
	                        0.1
	                    ]
	                ]
	            },
	            {
	                name: "Opera",
	                id: "Opera",
	                data: [
	                    [
	                        "v50.0",
	                        0.96
	                    ],
	                    [
	                        "v49.0",
	                        0.82
	                    ],
	                    [
	                        "v12.1",
	                        0.14
	                    ]
	                ]
	            }
	        ]
	    }
	});

}; 


</script>
</body>
</html>