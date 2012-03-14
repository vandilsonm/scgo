Cartoes = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Cartoes.Load = function(){
    var _dados = new Cartoes();
    _dados.initialize();
    return _dados;
}

Cartoes.prototype = {

    initialize: function() {
        alert('init'); this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._listaCampeonato);
        this.executeBind('../ServletListaCartao', '', 'GET', this._loadListaOnSuccess);

        $('#btnNovo').bind('click', '', $.createDelegate(this, this._loadNovo));
        $('#btnLista').bind('click', '', $.createDelegate(this, this._loadLista));
    },

    _loadLista: function() {
        this.executeBind('../ServletListaCartao', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaCartao = eval(value);
        
        $('#spanTitulo').html('Cartão(ões) Cadastrado(s)');

        var html = "<tr><th>Jogo</th><th>Jogador</th><th>Tipo</th>"
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        var tipo = "";
        for (var i = 0; i < listaCartao.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaCartao[i].timeMandante + " x   " +
                    listaCartao[i].timeVisitante + "</td>";
            html += "<td>" + listaCartao[i].jogadorNome + "</td>";

            if (listaCartao[i].tipo == "A")
                tipo = "Amarelo";
            else
                tipo = "Vermelho";
            
            html += "<td>" + tipo + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" +
                    i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                idCartao: listaCartao[i].idCartao,
                idJogoCodigo: listaCartao[i].jogoCodigo,
                idTimeCodigo: listaCartao[i].timeCodigo,
                idJogadorCodigo: listaCartao[i].jogadorCodigo,
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
        this.executeBind('form/cartoes.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Cartões');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
        $('#ddlJogo').bind('change', '', $.createDelegate(this, this._listaTime));
        this.executeBind('../ServletListaJogo', '', 'GET', this._listaJogo);
    },

    _btnCadastroOnClick: function() {
        if ($('#ddlJogo').val() == '0'
            || ($('#ddlTime').val() == null || $('#ddlTime').val() == '0')
            || ($('#ddlJogador').val() == null || $('#ddlJogador').val() == '0')
            || $('#ddlTipo').val() == '')

            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                jogo: $('#ddlJogo').val(),
                time: $('#ddlTime').val(),
                jogador: $('#ddlJogador').val(),
                tipo: $('#ddlTipo').val()
            }
            this.executeBind('../ServletInsereCartao', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroAltOnClick: function() {
        var str = {
            jogo: $('#ddlJogo').val(),
            time: $('#ddlTime').val(),
            jogador: $('#ddlJogador').val(),
            tipo: $('#ddlTipo').val()
        }
        this.executeBind('../ServletAlteraCartao', str, 'GET', this._cadastroOnSuccess);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            var str = {
                idCartao: value.data.idCartao
            };           
            this.executeBind('../ServletExcluiCartao', str, 'GET', this._cadastroOnSuccess);
        }
    },

//    _alterarItemOnClick: function (value) {
//        this._idSelJogo = value.data.idJogoCodigo;
//        this._idSelJogador = value.data.idJogadorCodigo;
//        this.executeBind('form/cartoes.jsp', '', 'GET', this._alterarOnSuccess);
//    },
//
//    _alterarOnSuccess: function(value) {
//        $('#spanTitulo').html('Alteração de Cartão');
//        $('#adm_container_one_text_form').html(value);
//        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));
//        this.executeBind('../ServletListaJogo', '', 'GET', this._listaJogo);
//        this.executeBind('../ServletListaJogador', '', 'GET', this._listaJogador);
//
//        var str = {
//            idJogo: this._idSelJogo,
//            idJogador: this._idSelJogador
//        }
//        this.executeBind('../ServletListaUmCartao', str, 'GET', this._alterarLoadOnSuccess);
//    },
//
//    _alterarLoadOnSuccess: function(value) {
//        var dados = eval("(" + value + ")");
//        $('#ddlJogo').attr("value", dados.jogo);
//        $('#ddlJogador').attr("value", dados.jogador);
//        $('#ddlTipo').attr("value", dados.tipo);
//
//        $('#ddlJogo').attr("disabled", "disabled");
//        $('#ddlJogador').attr("disabled", "disabled");
//    },

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

    _cadastroOnSuccess: function(value) {
        this.executeBind('../ServletListaCartao', '', 'GET', this._loadListaOnSuccess);
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
    Cartoes.Load();
});
