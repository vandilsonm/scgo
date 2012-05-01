<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Painel Administrativo - GOLAÇO</title>
        <link href="../css/reset-min.css" rel="stylesheet" type="text/css" />
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
        <link href="../css/style-css3.css" rel="stylesheet" type="text/css" />
    </head>

    <body>

        <!-- centralizando o layout -->
        <div id="centralized">

            <!-- topo do site / logo / menu principal -->
            <div id="header">

                <!-- logo -->
                <div id="header_logo">
                    <a href="../index.jsp" title="Página Inícial"><img src="../img/sgco.jpg" width="415" height="135" alt="GOLAÇO" border="0" /></a>
                </div>

                <!-- menu -->
                <div id="header_menu">
                    <ul>
                        <li><a href="sair.jsp" title="">Sair (logout)</a></li>
                    </ul>
                </div>

                <!-- submenu ou breadcrumb -->
                <div id="header_submenu">
                    <ul class="breadcrumb">
                        <li><a href="index.jsp" title="Painel Administrativo">Painel Administrativo</a></li>
                    </ul>
                </div>

            </div>

            <!-- conteudo -->
            <div id="container">
                <!-- coluna unica -->
                <div id="container_one_text">
                    <div id="modulo_campeonato" style="display: none">
                        <h1>Painel Administrativo: Campeonato</h1>
                        <div id="painel_links" title="Gerenciar Campeonato">
                            <h2>Meus Campeonatos</h2>
                            tsete
                        </div>
                        <div id="links_moldura"></div>
                    </div>
                    <div id="modulo_peladinha" style="display: none">
                        <h1>Painel Administrativo: Peladinha</h1>
                        <div id="peladinha_painel_links">
                            <!--<h2>Minhas peladinhas</h2>-->
                        </div>
                        <div id="peladinha_links_moldura"></div>
                    </div>
                    <div id="seleciona_modulo">
                        
                    </div>
                </div>
            </div>

            <!-- rodapé -->
            <div id="footer">
                <%@include file = "../rodape.jsp"%>
            </div>

        </div>

        <!-- div loading, lateral superior direita -->
        <div id="loading">
            <span><img src="img/ajax-loader.gif" alt="loading" /> carregando...</span>
        </div>

        <!-- javascript global -->
        <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript" src="../js/loading.js"></script>
        <!-- javascript administrativo -->
        <script type="text/javascript" src="js/home.js"></script>
    </body>
</html>