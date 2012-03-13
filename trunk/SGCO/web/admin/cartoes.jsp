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
                        <li><a href="index.jsp" class="selecionado" title="Painel Administrativo">Painel Administrativo</a></li>
                        <li><a href="sair.jsp" title="">Sair (logout)</a></li>
                    </ul>
                </div>

                <!-- submenu ou breadcrumb -->
                <div id="header_submenu">
                    <ul class="breadcrumb">
                        <li><a href="index.jsp" title="Painel Administrativo">Painel Administrativo</a></li>
                        <!--<li>&raquo;</li>-->
                        <!--<li><a href="meuCampeonato.jsp?id=1" title="Campeonato">Campeonato Brasileiro</a></li>-->
                        <li>&raquo;</li>
                        <li><a href="cartoes.jsp" title="Cartões">Cartões</a></li>
                    </ul>
                </div>

            </div>

            <!-- conteudo -->
            <div id="container">
                <!-- coluna unica -->
                <div id="container_one_text">
                    <h1>Painel Administrativo</h1>
                    <div id="painel_links">
                        <h2>Meus Campeonatos</h2>
                    </div>
                    <div id="links_moldura">
                        <div id="adm_container_one_text">
                            <a href="javascript:void(0)" id="btnNovo" class="fAlignRight inputBotao icone novo">Novo</a>
                            <a href="javascript:void(0)" id="btnLista" class="fAlignRight inputBotao icone lista">Lista</a>
                            <a href="meuCampeonato.jsp" class="fAlignRight inputBotao icone voltar" style="margin-right: 50px;">Voltar</a>
                            <h2>Gerenciando: <span class="corDestaque">Meus Campeonatos</span> // <span class="corDestaque">Cartões</span></h2>
                            <fieldset>
                                <span id="spanTitulo" class="legend"></span>
                                <table width="540" border="1" cellspacing="2" cellpadding="2" id="adm_container_one_text_form" class="gridListagem">
                                </table>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>

            <!-- rodapé -->
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

        <!-- javascript global -->
        <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
        <script type="text/javascript" src="../js/util.js"></script>
        <script type="text/javascript" src="../js/loading.js"></script>
        <!-- javascript administrativo -->
        <script type="text/javascript" src="js/cartoes.js"></script>
    </body>
</html>