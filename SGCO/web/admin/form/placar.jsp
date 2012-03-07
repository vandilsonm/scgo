<%@page contentType="text/html" pageEncoding="UTF-8"%>
<label><span>Jogo: </span>
    <select id="ddlJogo">
    </select></label>
<label><span>Time: </span>
    <select id="ddlTime">
    </select></label>
<!-- lista dinamica, jogadores cadastrados pelo meu usuário -->
<label><span>Jogador: </span>
    <select id="ddlJogador">
    </select></label>
<label><span>Quantidade de Gols:</span>
    <input type="text" id="txtQtdeGols" value="" maxlength="2" /></label>
<input id="btnCadastro" type="button" value="Salvar dados" class="botao inputBotao icone salvar" />