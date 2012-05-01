<%@page contentType="text/html" pageEncoding="UTF-8"%>
<label><span>Nome:</span>
    <input type="text" id="txtNome" value="" maxlength="100" /></label>
<label><span>Posição:</span>
    <input type="text" id="txtPosicao" value="" maxlength="40" /></label>
<label><span>Tipo: </span>
    <select id="ddlTipo">
         <option value="T">Titular</option>
         <option value="R">Reserva</option>
    </select></label>
<!-- lista dinamica, times cadastrados pelo meu usuário -->
<label><span>Time: </span>
    <select id="ddlTime">
    </select></label>
<!-- campo celular -->
<label><span>Celular: </span>
    <input type="text" id="txtCelular" value="" maxlength="14" onkeypress="MascaraTelefone(this)"/></label>
<label><span>E-mail: </span>
    <input type="text" id="txtEmail" value="" maxlength="100"  /></label>
<input id="btnCadastro" type="button" value="Salvar dados" class="botao inputBotao icone salvar" />