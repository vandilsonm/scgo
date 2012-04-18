<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="adm_container_one_text">
    <a href="index.jsp" class="fAlignRight inputBotao icone voltar">Voltar</a>
    <h2>Gerenciando: <span class="corDestaque" id="spanNomeCampeonato">Campeonato Brasileiro</span></h2>
    <div id="adm_container_one_text_form">
        <a href="time.jsp?id=<%= request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/time.png" alt="Time" /></a>
        <a href="jogador.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/jogador.png" alt="Jogador" /></a>
        <a href="estadio.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/estadio.png" alt="Estádio" /></a>
        <a href="arbitragem.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/arbitragem.png" alt="Arbitragem" /></a>
        <a href="jogos.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/jogos.png" alt="Jogos" /></a>
        <a href="placar.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/placar.png" alt="Placar" /></a>
        <a href="substituicao.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/substituicao.png" alt="Substituição" /></a>
        <a href="cartoes.jsp?id=<%=request.getParameter("id")%>" class="linkGerenciar"><img src="img/cad/cartoes.png" alt="Cartões" /></a>
    </div>
</div>