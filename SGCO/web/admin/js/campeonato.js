Campeonato = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
}

Campeonato.Load = function(){
    var _dados = new Campeonato();
    _dados.initialize();
    return _dados;
}

Campeonato.prototype = {

    initialize: function() {
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._dataBind_OnSuccess);

        $('#btnNovo').bind('click', '', $.createDelegate(this, this._loadNovo));
        $('#btnLista').bind('click', '', $.createDelegate(this, this._loadLista));
    },

    _dataBind_OnSuccess: function(value){
        var listaCampeonato = eval(value);

        $('#spanTitulo').html('Campeonato(s) Cadastrado(s)');

        //html relativo aos menu lateral dos campeonatos
        var htmlLinks = "<h2>Meus Campeonatos</h2>";

        var html = "<tr><th>Nome</th><th>Descricao</th>"
        html += "<th class=\"alingCenter\">Editar</th>";

        $('#adm_container_one_text_form').html(html);

        if (listaCampeonato.length > 0) {
            htmlLinks += "<ul class=\"linksCampeonatos\">";
            
            for (var i = 0; i < listaCampeonato.length; i++ ) {
                html = "";
                html += "<tr>" +
                        "<td>" + listaCampeonato[i].nome + "</td>" +
                        "<td>" + listaCampeonato[i].descricao + "</td>";
                html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
                html += "</tr>";

                htmlLinks += "<li><a href=\"meuCampeonato.jsp?id=" +
                    listaCampeonato[i].codigo + "\">" +
                    listaCampeonato[i].nome +
                    "</a></li>";

                $('#adm_container_one_text_form').each(function(){
                   $(this).append(html);
                });

                 var str = {
                    id: listaCampeonato[i].codigo,
                    index: i
                }
                $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            }

            htmlLinks += "</ul>";
        }
        else {
            htmlLinks += "<br /><br />Não existe nenhum campeonato cadastrado.";
        }

        $('#painel_links').html(htmlLinks);
    },

    _loadNovo: function() {
        this.executeBind('form/campeonato.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Campeonato');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
    },

    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        this.executeBind('form/campeonato.jsp', '', 'GET', this._alterarOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        $('#spanTitulo').html('Alteração de Campeonato');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));

        var str = {
            id: this._idSelecionado
        }
        this.executeBind('../ServletListaUmCampeonato', str, 'GET', this._alterarLoadOnSuccess);
    },

    _alterarLoadOnSuccess: function(value) {
        var dados = eval("(" + value + ")");
        $('#txtNome').attr("value", dados.nome);
        $('#txtDescricao').attr("value", dados.descricao);
    },

    _loadLista: function() {
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._dataBind_OnSuccess);
    },

    _btnCadastroOnClick: function() {
        var str = {
            nome: $('#txtNome').val(),
            descricao: $('#txtDescricao').val()
        }
        this.executeBind('../ServletInsereCampeonato', str, 'GET', this._cadastroOnSuccess);
    },

    _btnCadastroAltOnClick: function() {
        var str = {
            nome: $('#txtNome').val(),
            descricao: $('#txtDescricao').val(),
            id: this._idSelecionado
        }
        this.executeBind('../ServletAlteraCampeonato', str, 'GET', this._cadastroOnSuccess);
    },

    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._dataBind_OnSuccess);
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
    Campeonato.Load();
});
