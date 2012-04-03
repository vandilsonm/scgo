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
<input type="button" id="btnCadastro" value="Salvar dados" class="botao inputBotao icone salvar" />
