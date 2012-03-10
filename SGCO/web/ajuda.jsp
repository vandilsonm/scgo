<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Ajuda - GOLAÇO</title>
        <link href="css/reset-min.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/style-css3.css" rel="stylesheet" type="text/css" />
    </head>

    <body>

        <div id="centralized">

            <div id="header">

                <div id="header_logo">
                    <a href="index.jsp" title="Página Inicial"><img src="img/sgco.jpg" width="415" height="135" alt="GOLAÇO" border="0" /></a>
                </div>

                <div id="header_menu">
                    <ul>
                        <li><a href="index.jsp" title="Página Inicial">Home</a></li>
                        <li><a href="campeonatos.jsp" title="Veja nossa lista de campeonatos ativos">Campeonatos</a></li>
                        <li><a href="cadastro.jsp" title="Cadastre-se e crie seu próprio campeonato">Cadastre-se</a></li>
                        <li><a href="ajuda.jsp" class="selecionado" title="Ajuda">Ajuda</a></li>
                        <li><a href="sobre.jsp" title="Sobre o GOLAÇO">Sobre</a></li>
                    </ul>
                </div>

                <div id="header_submenu">
                    <ul class="breadcrumb">
                        <li><a href="index.jsp" title="Página Inícial">Home</a></li>
                        <li>&raquo;</li>
                        <li><a href="ajuda.jsp" title="Ajuda!">Ajuda</a></li>
                    </ul>
                </div>

            </div>

            <div id="container">
                <div id="container_one_text">
                    <h1>Ajuda</h1>
                </div>
            </div>

            <div id="footer">
                <p>GOLAÇO<br />
                    2011 - Andre Andrade, Heberth Moreira, Janaína Louback, Nathália Góes, Thiago Sinésio e Tiago Carmo.<br />
                    2012 - Andre Andrade, Cleidison Alexsandro, Diego Marlon, Filipe Miranda, Heberth Moreira, Janaína Louback, Thiago Sinésio e Tiago Carmo.
                </p>
            </div>

        </div>

        <!-- div loading, lateral superior direita -->
        <div id="loading">
            <span><img src="img/ajax-loader.gif" alt="loading" /> carregando...</span>
        </div>

        <!-- javascript -->
        <script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
        <script type="text/javascript" src="js/util.js"></script>
        <script type="text/javascript" src="js/loading.js"></script>
        <script type="text/javascript" src="js/ajuda.js"></script>
    </body>
</html>