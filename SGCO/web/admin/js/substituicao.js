Substituicao = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Substituicao.Load = function(){
    var _dados = new Substituicao();
    _dados.initialize();
    return _dados;
}

Substituicao.prototype = {

    initialize: function() {
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._listaCampeonato);
        this.executeBind('../ServletListaSubstituicao', '', 'GET', this._loadListaOnSuccess);

        $('#btnNovo').bind('click', '', $.createDelegate(this, this._loadNovo));
        $('#btnLista').bind('click', '', $.createDelegate(this, this._loadLista));
    },

    _loadLista: function() {
        this.executeBind('../ServletListaSubstituicao', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaSubs = eval(value);

        $('#spanTitulo').html('Substituição(ões) Realizada(s)');

        var html = "<tr><th>Jogo</th><th>Jogador Entrou</th><th>Jogador Saiu</th>"
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaSubs.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaSubs[i].timeMandante + " x   " +
                    listaSubs[i].timeVisitante + "</td>";
            html += "<td>" + listaSubs[i].jogadorNomeEntrou + "</td>";
            html += "<td>" + listaSubs[i].jogadorNomeSaiu + "</td>";

            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" +
                    i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                idJogoCodigo: listaSubs[i].jogoCodigo,
                idTimeCodigo: listaSubs[i].timeCodigo,
                idJogadorCodigoEntrou: listaSubs[i].jogadorCodigoEntrou,
                idJogadorCodigoSaiu: listaSubs[i].jogadorCodigoSaiu,
                index: i
            }
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
        }
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
        this.executeBind('form/substituicao.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Substituicao no Jogo');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
        $('#ddlJogo').bind('change', '', $.createDelegate(this, this._listaTime));
        this.executeBind('../ServletListaJogo', '', 'GET', this._listaJogo);
    },

    _listaJogo: function (value) {
        var listaJogo = eval(value);
        $('#ddlJogo').html('');

        $('#ddlJogo').append('<option value="0">Selecione</option>');
        for (var i = 0; i < listaJogo.length; i++ ) {
            $('#ddlJogo').append('<option value="' +
                listaJogo[i].codigo + '">' +
                listaJogo[i].timeMandante + ' x ' + listaJogo[i].timeVisitante + '</option>');
        }
        $('#ddlJogo').find('option[value="0"]').attr('selected',true);
    },

    _listaTime: function (value) {
        $('#ddlTime').html('');
        $('#ddlJogadorEntrou').html('');
        $('#ddlJogadorSaiu').html('');

        if ($('#ddlJogo').val() != "0") {
            var str = {
                codigoJogo: $('#ddlJogo').val()
            }
            this.executeBind('../ServletListaTimeJogo', str, 'GET', this._listaTimeOnSuccess);
        }
    },

    _listaTimeOnSuccess: function (value) {
        var listaJogador = eval(value);
        $('#ddlTime').html('');

        $('#ddlTime').append('<option value="0">Selecione</option>');
        for (var i = 0; i < listaJogador.length; i++ ) {
            $('#ddlTime').append('<option value="' +
                listaJogador[i].Codigo + '">' +
                listaJogador[i].Nome + '</option>');
        }
        $('#ddlTime').find('option[value="0"]').attr('selected',true);
        $('#ddlTime').bind('change', '', $.createDelegate(this, this._listaJogador));
    },

    _listaJogador: function (value) {
        $('#ddlJogadorEntrou').html('');
        $('#ddlJogadorSaiu').html('');

        if ($('#ddlTime').val() != "0") {
            var str = {
                codigoTime: $('#ddlTime').val()
            }
            this.executeBind('../ServletListaJogadorTime', str, 'GET', this._listaJogadorOnSuccess);
        }
    },

    _listaJogadorOnSuccess: function (value) {
        var listaJogador = eval(value);
        $('#ddlJogadorEntrou').html('');
        $('#ddlJogadorSaiu').html('');

        for (var i = 0; i < listaJogador.length; i++ ) {
            $('#ddlJogadorEntrou').append('<option value="' +
                listaJogador[i].Codigo + '">' +
                listaJogador[i].Nome + '</option>');
            $('#ddlJogadorSaiu').append('<option value="' +
                listaJogador[i].Codigo + '">' +
                listaJogador[i].Nome + '</option>');
        }
    },

    _btnCadastroOnClick: function() {
        if ($('#ddlJogo').val() == '0'
            || ($('#ddlTime').val() == null || $('#ddlTime').val() == '0')
            || ($('#ddlJogadorEntrou').val() == null || $('#ddlJogadorEntrou').val() == '0')
            || ($('#ddlJogadorSaiu').val() == null || $('#ddlJogadorSaiu').val() == '0'))

            alert("É obrigatório informar todos os campos.");
        else {
            if ($('#ddlJogadorEntrou').val() == $('#ddlJogadorSaiu').val())
                alert("O jogador que entrou não pode ser o mesmo que saiu.");
            else {
                var str = {
                    jogo: $('#ddlJogo').val(),
                    time: $('#ddlTime').val(),
                    jogadorEntrou: $('#ddlJogadorEntrou').val(),
                    jogadorSaiu: $('#ddlJogadorSaiu').val()
                }
                this.executeBind('../ServletInsereSubstituicao', str, 'GET', this._cadastroOnSuccess);
            }
        }
    },

    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../ServletListaSubstituicao', '', 'GET', this._loadListaOnSuccess);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            var str = {
                idJogo: value.data.idJogoCodigo,
                idTime: value.data.idTimeCodigo,
                idJogadorEntrou: value.data.idJogadorCodigoEntrou,
                idJogadorSaiu: value.data.idJogadorCodigoSaiu
            };

            this.executeBind('../ServletExcluiSubstituicao', str, 'GET', this._cadastroOnSuccess);
        }
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
    Substituicao.Load();
});
