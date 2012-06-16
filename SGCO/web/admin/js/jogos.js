Jogos = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
}

Jogos.Load = function(){
    var _dados = new Jogos();
    _dados.initialize();
    return _dados;
}

Jogos.prototype = {

    initialize: function() {
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._listaCampeonato);
        this.executeBind('../ServletListaJogo', '', 'GET', this._loadListaOnSuccess);

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

    _listaEstadio: function (value) {
        var listaEstadio = eval(value);
        $('#ddlEstadio').html('');

        for (var i = 0; i < listaEstadio.length; i++ ) {
            $('#ddlEstadio').append('<option value="' +
                listaEstadio[i].codigo + '">' +
                listaEstadio[i].nome + '</option>');
        }
    },

    _listaTime: function (value) {
        var listaTime = eval(value);
        $('#ddlTimeMandante').html('');
        $('#ddlTimeVisitante').html('');

        for (var i = 0; i < listaTime.length; i++ ) {
            $('#ddlTimeMandante').append('<option value="' +
                listaTime[i].Codigo + '">' +
                listaTime[i].Nome + '</option>');
            $('#ddlTimeVisitante').append('<option value="' +
                listaTime[i].Codigo + '">' +
                listaTime[i].Nome + '</option>');
        }
    },

    _listaArbitro: function (value) {
        var listaArbitro = eval(value);
        $('#ddlJuiz').html('');
        $('#ddlJuizReserva').html('');
        $('#ddlBandeirinha1').html('');
        $('#ddlBandeirinha2').html('');

        for (var i = 0; i < listaArbitro.length; i++ ) {
            switch (listaArbitro[i].tipo) {
                case 'JZ':
                    $('#ddlJuiz').append('<option value="' +
                        listaArbitro[i].codigo + '">' +
                        listaArbitro[i].nome + '</option>');
                    break;
                case 'BD':
                    $('#ddlBandeirinha1').append('<option value="' +
                        listaArbitro[i].codigo + '">' +
                        listaArbitro[i].nome + '</option>');
                    $('#ddlBandeirinha2').append('<option value="' +
                        listaArbitro[i].codigo + '">' +
                        listaArbitro[i].nome + '</option>');
                    break;
                case 'JR':
                    $('#ddlJuizReserva').append('<option value="' +
                        listaArbitro[i].codigo + '">' +
                        listaArbitro[i].nome + '</option>');
                    break;
                default:
                    tipo = "Não especificado";
            }
        }
    },

    _loadNovo: function() {
        this.executeBind('form/jogos.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Jogo');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
        this.executeBind('../ServletListaEstadio', '', 'GET', this._listaEstadio);
        this.executeBind('../ServletListaTime', '', 'GET', this._listaTime);
        this.executeBind('../ServletListaArbitragem', '', 'GET', this._listaArbitro);

        $("#txtDataHora").mask("99/99/9999 99:99");
    },

    _loadLista: function() {
        this.executeBind('../ServletListaJogo', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaJogos = eval(value);

        $('#spanTitulo').html('Jogo(s) Cadastrado(s)');

        var html = "<tr><th>Time Mandante</th><th>Time Visitante</th><th>Data/hora</th>";
        html += "<th class=\"alingCenter\">Editar</th>";
        html += "<th class=\"alingCenter\">Excluir</th>";
        html += "<th class=\"alingCenter\"><abbr title=\"Enviar e-mail confirmação\" style=\"border-bottom:1px dotted #000\">E-mail conf.</abbr></th>";
        html += "<th class=\"alingCenter\"><abbr title=\"Jogadores confirmados\"  style=\"border-bottom:1px dotted #000\">Jog. conf.</abbr></th>";
        html += "<th class=\"alingCenter\"><abbr title=\"Enviar twitter\"  style=\"border-bottom:1px dotted #000\">Twitter.</abbr></th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaJogos.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaJogos[i].timeMandante + "</td>";
            html += "<td>" + listaJogos[i].timeVisitante + "</td>";
            html += "<td>" + listaJogos[i].dataHora + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" + i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"mail" + i + "\" class=\"inputBotao icone mail\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"check" + i + "\" class=\"inputBotao icone check\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"twit" + i + "\" class=\"inputBotao icone twitter\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
                $(this).append(html);
            });

            var str = {
                id: listaJogos[i].codigo,
                index: i
            }
            $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
            $('#mail' + i).bind('click', str, $.createDelegate(this, this._enviarEmailOnClick));
            $('#check' + i).bind('click', str, $.createDelegate(this, this._listarJogadoresItemOnClick));
            $('#twit' + i).bind('click', str, $.createDelegate(this, this._enviarTwitter));
        }
    },
    
    _enviarEmailOnClick: function (value) {
        this._idSelecionado = value.data.id;
        
        var str = {
            idJogo: this._idSelecionado
        }
        this.executeBind('../../../ServletEnviarEmail', str, 'GET', this._enviarEmailOnClickOnSuccess);
    },
    
    _enviarTwitter: function (value) {
        this._idSelecionado = value.data.id;
        
        var str = {
            idJogo: this._idSelecionado
        }
        this.executeBind('../../../ServletEnviarTwitter', str, 'GET', this._enviarTwitterOnClickOnSuccess);
    },
    
    _enviarEmailOnClickOnSuccess : function (value){
        alert(value);
    },
    
    _enviarTwitterOnClickOnSuccess : function (value){
        alert(value);
    },
    
    _listarJogadoresItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        
        var str = {
            id: this._idSelecionado
        }
        this.executeBind('../../../ServletListarJogadoresConfirmados', str, 'GET', this._listarJogadoresOnSuccess);
    },
    
    _listarJogadoresOnSuccess : function (value) {
        var listarJogadoresPeladinha = eval(value);
        
        $('#spanTitulo').html('Jogadores confirmados');

        var html = "<tr><th>Nome</th><th>Celular</th><th>E-mail</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listarJogadoresPeladinha.length; i++ ) {
            html = "";
            html += "<tr><td>" + listarJogadoresPeladinha[i].nome + "</td>";
            html += "<td>" + listarJogadoresPeladinha[i].celular + "</td>";
            html += "<td>" + listarJogadoresPeladinha[i].email + "</td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
                $(this).append(html);
            });
        }  
    },

    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        
        this.executeBind('form/jogos.jsp', '', 'GET', this._alterarOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        $('#spanTitulo').html('Alteração de Jogo');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));
        
        var str = {
            id: this._idSelecionado
        }
        this.executeBind('../ServletListaEstadio', '', 'GET', this._listaEstadio);
        this.executeBind('../ServletListaTime', '', 'GET', this._listaTime);
        this.executeBind('../ServletListaArbitragem', '', 'GET', this._listaArbitro);
        this.executeBind('../ServletListaUmJogo', str, 'GET', this._alterarLoadOnSuccess);
    },

    _alterarLoadOnSuccess: function(value) {
        var dados = eval("(" + value + ")");
        $('#ddlTimeMandante [value='+dados.timeMandante+']').attr("selected", "selected");
        $('#ddlTimeVisitante [value='+dados.timeVisitante+']').attr("selected", "selected");
        $('#txtDataHora').attr("value", dados.dataHora);
        $('#ddlJuiz').attr("value", dados.juiz);
        $('#ddlJuizReserva').attr("value", dados.juizReserva);
        $('#ddlBandeirinha1 [value='+dados.bandeirinha1+']').attr("selected", "selected");
        $('#ddlBandeirinha2 [value='+dados.bandeirinha2+']').attr("selected", "selected");
    },

    _btnCadastroOnClick: function() {
        if ($('#ddlEstadio').val() == ''
            || $('#ddlTimeMandante').val() == ''
            || $('#ddlTimeVisitante').val() == ''
            || $('#txtDataHora').val() == ''
            || $('#ddlJuiz').val() == ''
            || $('#ddlJuizReserva').val() == ''
            || $('#ddlBandeirinha1').val() == ''
            || $('#ddlBandeirinha2').val() == '')

            alert("É obrigatório informar todos os campos.");
        else {
            if ($('#ddlTimeMandante').val() == $('#ddlTimeVisitante').val()) {
                alert("O time visitante deve ser diferente do mandante.");
            }
            if ($('#ddlBandeirinha1').val() == $('#ddlBandeirinha2').val()) {
                alert("Os bandeirinhas devem ser diferentes.");
            }
            else {
                var str = {
                    estadio: $('#ddlEstadio').val(),
                    timeMandante: $('#ddlTimeMandante').val(),
                    timeVisitante: $('#ddlTimeVisitante').val(),
                    dataHora: $('#txtDataHora').val(),
                    juiz: $('#ddlJuiz').val(),
                    juizReserva: $('#ddlJuizReserva').val(),
                    bandeirinha1: $('#ddlBandeirinha1').val(),
                    bandeirinha2: $('#ddlBandeirinha2').val()
                }
                this.executeBind('../ServletInsereJogo', str, 'GET', this._cadastroOnSuccess);
            }
        }
    },

    _btnCadastroAltOnClick: function() {
        if ($('#ddlEstadio').val() == ''
            || $('#ddlTimeMandante').val() == ''
            || $('#ddlTimeVisitante').val() == ''
            || $('#txtDataHora').val() == ''
            || $('#ddlJuiz').val() == ''
            || $('#ddlJuizReserva').val() == ''
            || $('#ddlBandeirinha1').val() == ''
            || $('#ddlBandeirinha2').val() == '')

            alert("É obrigatório informar todos os campos.");
        else {
            if ($('#ddlTimeMandante').val() == $('#ddlTimeVisitante').val()) {
                alert("O time visitante deve ser diferente do mandante.");
            }
            
            if ($('#ddlBandeirinha1').val() == $('#ddlBandeirinha2').val()) {
                alert("Os bandeirinhas devem ser diferentes.");
            }
            else {
                var str = {
                    estadio: $('#ddlEstadio').val(),
                    timeMandante: $('#ddlTimeMandante').val(),
                    timeVisitante: $('#ddlTimeVisitante').val(),
                    dataHora: $('#txtDataHora').val(),
                    juiz: $('#ddlJuiz').val(),
                    juizReserva: $('#ddlJuizReserva').val(),
                    bandeirinha1: $('#ddlBandeirinha1').val(),
                    bandeirinha2: $('#ddlBandeirinha2').val(),
                    id: this._idSelecionado
                }
                this.executeBind('../ServletAlteraJogo', str, 'GET', this._cadastroOnSuccess);
            }
        }
    },

    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../ServletListaJogo', '', 'GET', this._loadListaOnSuccess);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            var str = {
                idJogo: value.data.id
            };

            this.executeBind('../ServletExcluiJogo', str, 'GET', this._cadastroOnSuccess);
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
    Jogos.Load();
});
