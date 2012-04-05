Local = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
}

Local.Load = function(){
    var _dados = new Local();
    _dados.initialize();
    return _dados;
}

Local.prototype = {

    initialize: function() {
        this._loadLista();

        $('#btnNovo').bind('click', '', $.createDelegate(this, this._loadNovo));
        $('#btnLista').bind('click', '', $.createDelegate(this, this._loadLista));
    },

    _listaCampeonato: function (value) {
        var listaCampeonato = eval(value);

        var htmlLinks = "<h2>Meus Campeonatos</h2>";

        if (listaCampeonato.length > 0) {
            htmlLinks += "<ul class=\"linksCampeonatos\">";

            for (var i = 0; i < listaCampeonato.length; i++ ) {
                htmlLinks += "<li><a href=\"meuCampeonato.jsp?id=" +
                    listaCampeonato[i].codigo + "\">" +
                    listaCampeonato[i].nome +
                    "</a></li>";
            }

            htmlLinks += "</ul>";
        }
        else {
            htmlLinks += "<br /><br />Não existe nenhum campeonato cadastrado.";
        }

        $('#painel_links').html(htmlLinks);
    },

    _loadNovo: function() {
        this.executeBind('form/local.jsp', '', 'GET', this._sucessoLoadNovo);
    },

    _sucessoLoadNovo: function(value) {
        $('#adm_container_one_text_form').html(value);
        $('#spanTitulo').html('Cadastro de Local');
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
    },

    _loadLista: function() {
        this.executeBind('../../ServletListarLocal', '', 'GET', this._sucessoLoadLista);
    },

    _sucessoLoadLista: function(value) {
        var listaLocal = eval(value);
        
        $('#spanTitulo').html('Local(is) cadastrado(s)');

        var html = "<tr><th>Nome</th><th class=\"alingCenter\">Editar</th>";
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaLocal.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaLocal[i].nome + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" + i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                id: listaLocal[i].id,
                index: i
            }
            $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
        }
    },

    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        this.executeBind('form/local.jsp', '', 'GET', this._alterarOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        $('#spanTitulo').html('Alteração do local');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));
        var str = {
            idLocal: this._idSelecionado
        }
        this.executeBind('../../ServletListaUmLocal', str, 'GET', this._alterarLoadOnSuccess);
    },

    _alterarLoadOnSuccess: function(value) {
        
        var dados = eval("(" + value + ")");
        
        $('#txtNome').attr("value", dados.nome);
        $('#txtLogradouro').attr("value", dados.logradouro);
        $('#txtNumero').attr("value", dados.numero);
        $('#txtComplemento').attr("value", dados.complemento);
        $('#txtBairro').attr("value", dados.bairro);
        $('#txtCidade').attr("value", dados.cidade);
        $('#ddlEstado').attr("value", dados.estado);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro? ")) {
            this._idSelecionado = value.data.id;

            var str = {
                idLocal: this._idSelecionado
            };

            this.executeBind('../../ServletExcluirLocal', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroOnClick: function(value) {
        if ($('#txtNome').val() == '') {
            alert("É obrigatório informar o nome.");
        }
       
        else {
             var str = {nome: $('#txtNome').val(),
            logradouro: $('#txtLogradouro').val(),
            numero: $('#txtNumero').val(),
            complemento: $('#txtComplemento').val(),
            bairro: $('#txtBairro').val(),
            cidade: $('#txtCidade').val(),
            estado: $('#ddlEstado').val()};
           
           this.executeBind('../../ServletInserirLocal', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroAltOnClick: function () {
        if ($('#txtNome').val() == '') {
            alert("É obrigatório informar o nome.");
        }
        else {
            var str = {
                nome: $('#txtNome').val(),
                logradouro: $('#txtLogradouro').val(),
                numero: $('#txtNumero').val(),
                complemento: $('#txtComplemento').val(),
                bairro: $('#txtBairro').val(),
                cidade: $('#txtCidade').val(),
                estado: $('#ddlEstado').val(),
                id: this._idSelecionado
            }
            this.executeBind('../../ServletEditarLocal', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _cadastroOnSuccess: function(value) {
        this._loadLista();
    },

    executeBind: function(dataUrl, data, type, handlerSuccess) {
        this._type = type;
        this._dataUrl = dataUrl;
        this._data = data;
        this._handlerSuccess = handlerSuccess;
        this.dataBind();
    },

    dataBind: function() {
        $.ajax({
            type: this._type,
            cache: false,
            data: this._data,
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: this._dataUrl,
            success: $.createDelegate(this, this._handlerSuccess),
            error: $.createDelegate(this, this._dataBind_OnFailure)
        });
    },

    _dataBind_OnFailure: function(e) {
        alert(e);
    }

}

$(document).ready(function() {
    Local.Load();
});
