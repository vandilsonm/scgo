<%@page contentType="text/html" pageEncoding="UTF-8"%>
<label><span>Nome:</span>
    <input type="text" id="txtNome" value="" maxlength="50" /></label>
<label><span>Descrição:</span>
    <input type="text" id="txtDescricao" value="" maxlength="70" /></label>
<label><span>Horário</span>
    <input type="text" id="txtHorario" value="" maxlength="10" /></label>
<!-- lista dinâmica, locais cadastrados pelo meu usuário -->
<label><span>Local: </span>
    <select id="ddlLocal">
    </select></label>


<fieldset class="estiloJogadorPeladinha">
        <legend>Jogadores</legend>
        <label> <input type="checkbox" name="jogador" value="ON" />Jogador 2 </label>
        <label> <input type="checkbox" name="jogador" value="ON" />Jogador 3 </label>
        <label> <input type="checkbox" name="jogador" value="ON" />Jogador 4 </label>
        <label> <input type="checkbox" name="jogador" value="ON" />Jogador 5 </label>
        <label> <input type="checkbox" name="jogador" value="ON" />Jogador 6 </label>
    </fieldset>


<input type="button" id="btnCadastro" value="Salvar dados" class="botao inputBotao icone salvar" />
