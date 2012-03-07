Time = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
}

Time.Load = function(){
    var _dados = new Time();
    _dados.initialize();
    return _dados;
}

Time.prototype = {

    initialize: function() {
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._listaCampeonato);
        this.executeBind('../ServletListaTime', '', 'GET', this._loadListaOnSuccess);

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
        this.executeBind('form/time.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Time');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
    },

    _loadLista: function() {
        this.executeBind('../ServletListaTime', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaTime = eval(value);

        $('#spanTitulo').html('Times(s) Cadastrado(s)');

        var html = "<tr><th>Time</th><th>Técnico</th><th class=\"alingCenter\">Editar</th>";
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaTime.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaTime[i].Nome + "</td>";
            html += "<td>" + listaTime[i].Tecnico + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" + i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                id: listaTime[i].Codigo,
                index: i
            }
            $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
        }
    },

    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        this.executeBind('form/time.jsp', '', 'GET', this._alterarOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        $('#spanTitulo').html('Alteração de Time');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));

        var str = {
            id: this._idSelecionado
        }
        this.executeBind('../ServletListaUmTime', str, 'GET', this._alterarLoadOnSuccess);
    },

    _alterarLoadOnSuccess: function(value) {
        var dados = eval("(" + value + ")");
        $('#txtNome').attr("value", dados.nome);
        $('#txtTecnico').attr("value", dados.tecnico);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            this._idSelecionado = value.data.id;

            var str = {
                id: this._idSelecionado
            };

            this.executeBind('../ServletExcluiTime', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroOnClick: function(value) {
        if ($('#txtNome').val() == '' || $('#txtTecnico').val() == '')
            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                Nome: $('#txtNome').val(),
                Tecnico: $('#txtTecnico').val()
            }
            this.executeBind('../ServletInsereTime', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroAltOnClick: function() {
        if ($('#txtNome').val() == '' || $('#txtTecnico').val() == '')
            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                nome: $('#txtNome').val(),
                tecnico: $('#txtTecnico').val(),
                id: this._idSelecionado
            }
            this.executeBind('../ServletAlteraTime', str, 'GET', this._cadastroOnSuccess);
        }
    },


    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../ServletListaTime', '', 'GET', this._loadListaOnSuccess);
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
    Time.Load();
});
