Placar = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelJogo = null;
    this._idSelJogador = null;
}

Placar.Load = function(){
    var _dados = new Placar();
    _dados.initialize();
    return _dados;
}

Placar.prototype = {

    initialize: function() {
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._listaCampeonato);
        this.executeBind('../ServletListaPlacar', '', 'GET', this._loadListaOnSuccess);

        $('#btnNovo').bind('click', '', $.createDelegate(this, this._loadNovo));
        $('#btnLista').bind('click', '', $.createDelegate(this, this._loadLista));
    },

    _loadLista: function() {
        this.executeBind('../ServletListaPlacar', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaPlacar = eval(value);

        $('#spanTitulo').html('Gol(s) Realizado(s)');

        var html = "<tr><th>Jogo</th><th>Jogador</th><th>Qtde de Gols</th>";
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaPlacar.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaPlacar[i].timeMandante + " x   " +
                    listaPlacar[i].timeVisitante + "</td>";
            html += "<td>" + listaPlacar[i].jogadorNome + "</td>";
            html += "<td>" + listaPlacar[i].qtdeGols + "</td>";

            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" +
                    i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                idJogoCodigo: listaPlacar[i].jogoCodigo,
                idTimeCodigo: listaPlacar[i].timeCodigo,
                idJogadorCodigo: listaPlacar[i].jogadorCodigo,
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
        this.executeBind('form/placar.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Gol(s)');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
        $('#ddlJogo').bind('change', '', $.createDelegate(this, this._listaTime));
        this.executeBind('../ServletListaJogo', '', 'GET', this._listaJogo);

        $("#txtQtdeGols").numeric();
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
        $('#ddlJogador').html('');

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
        $('#ddlJogador').html('');

        if ($('#ddlTime').val() != "0") {
            var str = {
                codigoTime: $('#ddlTime').val()
            }
            this.executeBind('../ServletListaJogadorTime', str, 'GET', this._listaJogadorOnSuccess);
        }
    },

    _listaJogadorOnSuccess: function (value) {
        var listaJogador = eval(value);
        $('#ddlJogador').html('');

        for (var i = 0; i < listaJogador.length; i++ ) {
            $('#ddlJogador').append('<option value="' +
                listaJogador[i].Codigo + '">' +
                listaJogador[i].Nome + '</option>');
        }
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            var str = {
                idJogo: value.data.idJogoCodigo,
                idTime: value.data.idTimeCodigo,
                idJogador: value.data.idJogadorCodigo
            };

            this.executeBind('../ServletExcluiPlacar', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroOnClick: function() {
        if ($('#ddlJogo').val() == '0'
            || ($('#ddlTime').val() == null || $('#ddlTime').val() == '0')
            || ($('#ddlJogador').val() == null || $('#ddlJogador').val() == '0')
            || $('#txtQtdeGols').val() == '')

            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                jogo: $('#ddlJogo').val(),
                time: $('#ddlTime').val(),
                jogador: $('#ddlJogador').val(),
                qtdeGols: $('#txtQtdeGols').val()
            }
            this.executeBind('../ServletInserePlacar', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../ServletListaPlacar', '', 'GET', this._loadListaOnSuccess);
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
    Placar.Load();
});
