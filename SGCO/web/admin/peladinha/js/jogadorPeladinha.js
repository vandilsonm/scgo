JogadorPeladinha = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
},

JogadorPeladinha.Load = function(){
    var _dados = new JogadorPeladinha();
    _dados.initialize();
    return _dados;
}

JogadorPeladinha.prototype = {

    initialize: function() {
        //this.executeBind('../../../ServletListarPelada', '', 'GET', this._sucessoListaPeladinha);
        this.executeBind('../../../ServletListarJogador', '', 'GET', this._loadListaOnSuccess);

        $('#btnNovo').bind('click', '', $.createDelegate(this, this._loadNovo));
        $('#btnLista').bind('click', '', $.createDelegate(this, this._loadLista));
    },

    _sucessoListaPeladinha: function(value){
        this._loadListaOnSuccess(value);
        var listaCam = eval(value);
        var html = "<h2>Minhas Peladinhas</h2>";
        if (listaCam.length > 0) {
            html += "<ul class=\"linksCampeonatos\">";
            for (var i = 0; i < listaCam.length; i++ ) {
                var id = "altera"+i;
                html += "<li><a href=\"#\" id = \"altera" + i + "\">"  +
                listaCam[i].nome+"</a></li>";
            }
            html += "</ul>";
        }
        else {
            html += "<br /><br />Não existe nenhum campeonato cadastrado.";
        }
        $('#painel_links').html(html);
        for (var i = 0; i < listaCam.length; i++ ) {
            var str2 = {
                id: listaCam[i].id,
                index: i
            }
            //$('#altera'+i).bind('click', str2, $.createDelegate(this, this._alterarItemOnClick));
        }
    },

    _listaCampeonato: function (value) {

        var listaCampeonato = eval(value);

        var htmlLinks = "<h2>Minhas Peladinhas</h2>";

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
        this.executeBind('form/jogadorPeladinha.jsp', '', 'GET', this._sucessoLoadNovo);
    },

   _sucessoLoadNovo: function(value) {
        $('#spanTitulo').html('Cadastro de Jogador');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
    },

   _sucessoLoadTime: function (value) {
        var listaTime = eval(value);
        $('#ddlTime').html('');

        for (var i = 0; i < listaTime.length; i++ ) {
            $('#ddlTime').append('<option value="' +
                listaTime[i].Codigo + '">' +
                listaTime[i].Nome + '</option>');
        }
    },

    _loadLista: function() {
        this.executeBind('../../../ServletListarJogador', '', 'GET', this._loadListaOnSuccess);
    },

    _loadListaOnSuccess: function(value) {
        var listaJogador = eval(value);
        
        $('#spanTitulo').html('Jogadores(s) Cadastrado(s)');

        var html = "<tr><th>Nome</th><th>Celular</th><th>E-mail</th><th class=\"alingCenter\">Editar</th>";
        html += "<th class=\"alingCenter\">Inativar</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaJogador.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaJogador[i].nome + "</td>";
            html += "<td>" + listaJogador[i].celular + "</td>";
            html += "<td>" + listaJogador[i].email + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" + i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
               $(this).append(html);
            });

             var str = {
                id: listaJogador[i].id,
                index: i
            }
            $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
        }
    },

    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        this.executeBind('form/jogadorPeladinha.jsp', '', 'GET', this._alterarOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        $('#spanTitulo').html('Alteração de Jogador');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));

        var str = {
            id: this._idSelecionado
        }
        this.executeBind('../../../ServletListarUmJogador', str, 'GET', this._alterarLoadOnSuccess);
    },

    _alterarLoadOnSuccess: function(value) {
        var dados = eval("(" + value + ")");
        $('#txtNome').attr("value", dados.nome);
        $('#txtCelular').attr("value", dados.celular);
        $('#txtEmail').attr("value", dados.email);
    },

    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o jogador?")) {
            this._idSelecionado = value.data.id;

            var str = {
                id: this._idSelecionado
            };

            this.executeBind('../../../ServletExcluirJogador', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroOnClick: function(value) {
        if ($('#txtNome').val() == '' || $('#txtCelular').val() == '' || $('#txtEmail').val() == '')
            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                nome: $('#txtNome').val(),
                celular: $('#txtCelular').val(),
                email: $('#txtEmail').val()
            }
            this.executeBind('../../../ServletInserirJogador', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _btnCadastroAltOnClick: function() {
        if ($('#txtNome').val() == '' || $('#txtCelular').val() == '' 
                    || $('#txtEmail').val() == '')
            alert("É obrigatório informar todos os campos.");
        else {
            var str = {
                nome: $('#txtNome').val(),
                celular: $('#txtCelular').val(),
                email: $('#txtEmail').val(),
                id: this._idSelecionado
            }
            this.executeBind('../../../ServletEditarJogador', str, 'GET', this._cadastroOnSuccess);
        }
    },

    _cadastroOnSuccess: function(value) {
        this.executeBind('../../../ServletListarJogador', '', 'GET', this._loadListaOnSuccess);
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
    JogadorPeladinha.Load();
});
