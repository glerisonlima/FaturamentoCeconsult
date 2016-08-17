<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="300;" charset=ISO-8859-1">
<title>Faturamento Ceconsult</title>
<!--Import Google Icon Font-->
<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/materialize.css"  media="screen,projection">
<link type="text/css" rel="stylesheet" href="css/estilo.css"  media="screen,projection">

<style>
	.amarelo{
		color:#FBC02D;	
	}
	.vermelho{
		color:red;
	}
	.verde{
		color:green;
	}
	.azul{
		color: #303F9F;
	}
</style>
</head>
<body>
	<!-- Topo da tela -->
	<nav>
    <div class="nav-wrapper">
      <a href="#"><img class="imagen" src="img/logoCeconsult.png"/></a>
      <a href="#" class="brand-logo" id="mes"></a>       
    </div>
  </nav>
	<!-- Tabela -->
	<table>
        <thead>
          <tr>
              <th data-field="id">Tipo</th>
              <th data-field="acumulado">Acumulado</th>
              <th data-field="meta">Meta</th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td>Serviços</td>
            <td id="vServicos">R$ ${servicos}</td>
            <td id="mServicos">R$ 105.000,00</td>
          </tr>
          <tr>
            <td>Peças</td>
            <td id="vPecas">R$ ${pecas}</td>
            <td id="mPecas">R$ 60.000,00</td>
          </tr>
          <tr>
            <td>Total</td>
            <td >R$ <a id="vTotal" class="">${total}</a></td>
            <td id="mTotal">R$ 165.000,00</td>
          </tr>
        </tbody>
      </table>
	<!-- Rodape -->
	<nav class="rodape">
	<div class="nav-wrapper">  
		<a id="data"></a>         
    </div>
    </nav>
    <script type="text/javascript" src="js/jquery-2.2.1.min.js"></script>
    <script>  
    
    $(document).ready(function(){
    	var total = parseInt('${total}');        
    	if (total < 155 ){
    		$('#vTotal').removeClass().addClass("vermelho");
    	}else if(total >= 155 & total < 165){    		
    		$('#vTotal').removeClass().addClass("amarelo");    		
    	}else if(total == 165){
    		$('#vTotal').removeClass().addClass("verde");
    	}else if(total > 165){
    		$('#vTotal').removeClass().addClass("azul");
    	}    	
    });
    
    
    data = new Date();
    dia = data.getDate();
    mes = data.getMonth();
    ano = data.getFullYear();

    meses = new Array(12);

    meses[0] = "Janeiro";
    meses[1] = "Fevereiro";
    meses[2] = "Março";
    meses[3] = "Abril";
    meses[4] = "Maio";
    meses[5] = "Junho";
    meses[6] = "Julho";
    meses[7] = "Agosto";
    meses[8] = "Setembro";
    meses[9] = "Outubro";
    meses[10] = "Novembro";
    meses[11] = "Dezembro";

    $('#data').html(" dia " + dia + " de " + meses[mes] + " de " + ano);
    
    $('#mes').html("Faturamento de "+meses[mes]);
    
    </script>
</body>
</html>