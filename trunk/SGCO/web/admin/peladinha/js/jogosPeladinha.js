JogosPeladinha = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
}

JogosPeladinha.Load = function(){
    var _dados = new JogosPeladinha();
    _dados.initialize();
    return _dados;
}

JogosPeladinha.prototype = {

    initialize: function() {
        this.executeBind('../ServletListarPelada', '', 'GET', this._loadListaOnSuccess);

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

    _listaLocal: function (value) {
        var listarLocal = eval(value);
        $('#ddlLocal').html('');

        for (var i = 0; i < listarLocal.length; i++ ) {
            $('#ddlLocal').append('<option value="' +
                listarLocal[i].id + '">' +
                listarLocal[i].nome + '</option>');
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
                default: tipo = "Não especificado";
            }
        }
    },

    _loadNovo: function() {
        this.executeBind('form/peladinha.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Peladinha');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
        this.executeBind('../ServletListarLocal', '', 'GET', this._listaLocal);
    },

    _loadLista: function() {
        this.executeBind('../ServletListarPelada', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaPeladinha = eval(value);

        $('#spanTitulo').html('Peladinha(s) Cadastrada(s)');

        var html = "<tr><th>Nome</th><th>Descricao</th><th>Horário</th><th>Local</th>";
        html += "<th class=\"alingCenter\">Editar</th>";
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaPeladinha.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaPeladinha[i].nome + "</td>";
            html += "<td>" + listaPeladinha[i].descricao + "</td>";
            html += "<td>" + listaPeladinha[i].horario + "</td>";
            html += "<td>" + listaPeladinha[i].local + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" + i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                id: listaPeladinha[i].id,
                index: i
            }
            $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
        }
    },

    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        this.executeBind('form/peladinha.jsp', '', 'GET', this._alterarOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        $('#spanTitulo').html('Alteração da peladinha');
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
        $('#ddlTimeMandante').attr("value", dados.timeMandante);
        $('#ddlTimeVisitante').attr("value", dados.timeVisitante);
        $('#txtDataHora').attr("value", dados.dataHora);
        $('#ddlJuiz').attr("value", dados.juiz);
        $('#ddlJuizReserva').attr("value", dados.juizReserva);
        $('#ddlBandeirinha1').attr("value", dados.bandeirinha1);
        $('#ddlBandeirinha2').attr("value", dados.bandeirinha2);
    },

    _btnCadastroOnClick: function() {
        if ($('#txtNome').val() == ''
            || $('#txtDescricao').val() == ''
            || $('#txtHorario').val() == ''
            || $('#ddlLocal').val() == '')

            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                nome: $('#txtNome').val(),
                descricao: $('#txtDescricao').val(),
                horario: $('#txtHorario').val(),
                local: $('#ddlLocal').val()
            }
            this.executeBind('../ServletInserirPelada', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroAltOnClick: function() {
        if ($('#txtNome').val() == ''
            || $('#txtDescricao').val() == ''
            || $('#txtHorario').val() == ''
            || $('#ddlLocal').val() == '')

            alert("É obrigatório informar todos os campos.");
            
        else {
            var str = {
                nome: $('#txtNome').val(),
                descricao: $('#txtDescricao').val(),
                horario: $('#txtHorario').val(),
                local: $('#ddlLocal').val(),
                id: this._idSelecionado
            }
                this.executeBind('../ServletEditarPelada', str, 'GET', this._cadastroOnSuccess);
            }
        
    },

    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../ServletListarPelada', '', 'GET', this._loadListaOnSuccess);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            var str = {
                idJogo: value.data.id
            };

            this.executeBind('../ServletExcluirPelada', str, 'GET', this._cadastroOnSuccess);
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
    JogosPeladinha.Load();
});
