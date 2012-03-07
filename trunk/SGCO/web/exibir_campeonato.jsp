<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Campeonato Brasileiro - GOLAÇO</title>
        <link href="css/reset-min.css" rel="stylesheet" type="text/css" />
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/style-css3.css" rel="stylesheet" type="text/css" />
    </head>

    <body>

        <!-- centralizando o layout -->
        <div id="centralized">

            <!-- topo do site / logo / menu principal -->
            <div id="header">

                <!-- logo -->
                <div id="header_logo">
                    <a href="index.jsp" title="Página Inicial"><img src="img/sgco.jpg" width="415" height="135" alt="GOLAÇO" border="0" /></a>
                </div>

                <!-- menu -->
                <div id="header_menu">
                    <ul>
                        <li><a href="index.jsp" class="selecionado" title="Página Inicial">Home</a></li>
                        <li><a href="campeonatos.jsp" title="Veja nossa lista de campeonatos ativos">Campeonatos</a></li>
                        <li><a href="cadastro.jsp" title="Cadastre-se e crie seu próprio campeonato">Cadastre-se</a></li>
                        <li><a href="ajuda.jsp" title="Ajuda">Ajuda</a></li>
                        <li><a href="sobre.jsp" title="Sobre o GOLAÇO">Sobre</a></li>
                    </ul>
                </div>

                <!-- submenu ou breadcrumb -->
                <div id="header_submenu">
                    <ul class="breadcrumb">
                        <li><a href="index.jsp" title="Página Inicial">Home</a></li>
                    </ul>
                </div>

            </div>

            <!-- conteudo -->
            <div id="container">
                <div id="container_text">
                    <div id="container_text_i">
                        <h1><span id="spanTituloCampeonato" class="corDestaque"></span></h1>
                    </div>
                    <div id="container_text_resultados">
                        <!-- dinamico -->
                        <div class="table">
                            <table border="0" id="tblClassificacao" cellpadding="6" cellspacing="0" class="gridListagem gridClassificacao" style="margin-left: 0px"></table>
                            <h3 class="corDestaque">Artilheiro</h3>
                            <span id="spanArtilheiro"></span>
                        </div>
                    <!-- dinamico -->
                    </div>
                </div>
                <div id="container_right">
                    <div id="container_right_ultimos_jogos">
                    </div>
                </div>
            </div>

            <!-- rodapé -->
            <div id="footer">
                <p>GOLAÇO 2011 - Desenvolvido por Andre Andrade, Heberth Moreira, Janaína Louback, Nathália Góes, Thiago Sinésio e <a href="http://www.tiagocarmo.com.br/" target="_blank">Tiago Carmo</a>.</p>
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
        <script type="text/javascript" src="js/exibir_campeonato.js"></script>
    </body>
</html>