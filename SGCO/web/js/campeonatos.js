Campeonatos = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Campeonatos.Load = function(){
    var _dados = new Campeonatos();
    _dados.initialize();
    return _dados;
}

Campeonatos.prototype = {

    initialize: function() {
        this.executeBind('content/campeonatos.jsp', '', 'GET', this._dataBind_OnSuccess);
    },

    _dataBind_OnSuccess: function(value){
        $('#container').html(value);
        this.executeBind('form/login.jsp', '', 'GET', this._dataBind_OnSuccess_Login);
        //this.executeBind('form/pesquisa.jsp', '', 'GET', this._dataBind_OnSuccess_Pesquisa);
        this.executeBind('_servlets/share.jsp', '', 'GET', this._dataBind_OnSuccess_Share);

        this.executeBind('ServletListaCampeonato', '', 'GET', this._listaCampeonato);
    },

    _dataBind_OnSuccess_Login: function(value) {
        $('#container_right_form').html(value);
        $('#btnLogin').bind('click', '', $.createDelegate(this, this._btnLoginOnClick));
    },

    _btnLoginOnClick: function(value) {
        if($('#txtLogin').val() != '' && $('#txtSenha').val() != '') {
            var str = {
                login: $('#txtLogin').val(),
                senha: $('#txtSenha').val()
            }
            this.executeBind('ServletLogin', str, 'GET', this._sucessoLoginOnClick);
        } else {
            $('#valMensagem').html('Os campos Login e Senha são obrigatórios');
            $('#valMensagem').show();
            setTimeout(function(){
                $('#valMensagem').hide(1000);
                $('#valMensagem').html('');
            },2000);
        }
    },

    _sucessoLoginOnClick: function(value){
        if (value == "ok")
            $(window.document.location).attr('href', 'admin/index.jsp');
        else
            alert(value);
    },

//    _dataBind_OnSuccess_Pesquisa: function(value) {
//        $('#container_right_search').html(value);
//    },

    _dataBind_OnSuccess_Share: function(value) {
        $('#container_right_share').html(value);
    },

    _listaCampeonato: function (value) {
        var listaCampeonato = eval(value);

        var htmlLinks = "";

        if (listaCampeonato.length > 0) {

            for (var i = 0; i < listaCampeonato.length; i++ ) {
                htmlLinks += "<tr><td colspan=\"7\"><span class=\"linkUsuario\">"
                + listaCampeonato[i].usuarioNome + "</span>"
                + "<h3><a href=\"exibir_campeonato.jsp?id="
                + listaCampeonato[i].codigo + "\">" + listaCampeonato[i].nome + "</a></h3></td></tr>";
            }
        }
        else {
            htmlLinks += "<br /><br />Não existe nenhum campeonato cadastrado.";
        }

        $('#tblCampeonatos').html(htmlLinks);
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
    }

}

$(document).ready(function() {
    Campeonatos.Load();
});
