<%@page contentType="text/html" pageEncoding="UTF-8"%>
<label><span>Nome:</span>
    <input type="text" id="txtNome" value="" maxlength="50" /></label>
<label><span>Logradouro:</span>
    <input type="text" id="txtLogradouro" value="" maxlength="70" /></label>
<label><span>Número:</span>
    <input type="text" id="txtNumero" value="" maxlength="10" /></label>
<label><span>Complemento:</span>
    <input type="text" id="txtComplemento" value="" maxlength="40" /></label>
<label><span>Bairro:</span>
    <input type="text" id="txtBairro" value="" maxlength="70" /></label>
<label><span>Cidade: </span>
    <input type="text" id="txtCidade" value="" maxlength="70" /></label>
<label><span>Estado: </span>
    <select id="ddlEstado">
         <option value="AC">Acre</option>
         <option value="AL">Alagoas</option>
         <option value="AP">Amapá</option>
         <option value="AM">Amazonas</option>
         <option value="BA">Bahia</option>
         <option value="CE">Ceará</option>
         <option value="DF">Distrito Federal</option>
         <option value="ES">Espírito Santo</option>
         <option value="GO">Goiás</option>
         <option value="MA">Maranhão</option>
         <option value="MT">Mato Grosso</option>
         <option value="MS">Mato Grosso do Sul</option>
         <option value="MG">Minas Gerais</option>
         <option value="PA">Pará</option>
         <option value="PB">Paraíba</option>
         <option value="PR">Paraná</option>
         <option value="PE">Pernambuco</option>
         <option value="PI">Piauí</option>
         <option value="RJ">Rio de Janeiro</option>
         <option value="RN">Rio Grande do Norte</option>
         <option value="RS">Rio Grande do Sul</option>
         <option value="RO">Rondônia</option>
         <option value="RR">Roraima</option>
         <option value="SC">Santa Catarina</option>
         <option value="SP">São Paulo</option>
         <option value="SE">Sergipe</option>
         <option value="TO">Tocantins</option>
    </select></label>
<input type="button" id="btnCadastro" value="Salvar dados" class="botao inputBotao icone salvar" />
