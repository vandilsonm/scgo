Cadastro = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Cadastro.Load = function(){
    var _dados = new Cadastro();
    _dados.initialize();
    return _dados;
}

Cadastro.prototype = {

    initialize: function() {
        this.executeBind('content/cadastro.jsp', '', 'GET', this._dataBind_OnSuccess);
    },

    _dataBind_OnSuccess: function(value){
        $('#container').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
    },

    _btnCadastroOnClick: function(value){
        if ($('#txtNome').val() == ''
            || $('#txtEmail').val() == ''
            || $('#txtLogin').val() == ''
            || $('#txtSenha').val() == ''
            || $('#txtConfSenha').val() == '') {
            alert("Todos os campos são obrigatórios!");
        }
        else {
            if ($('#txtSenha').val() != $('#txtConfSenha').val()) {
                alert("A confirmação não corresponde a senha informada.");
            }
            else {
                var str = {
                    nome: $('#txtNome').val(),
                    email: $('#txtEmail').val(),
                    login: $('#txtLogin').val(),
                    senha: $('#txtSenha').val()
                }
                this.executeBind('ServletCadastroUsuario', str, 'GET', this._sucessoCadastroOnClick);
            }
        }
    },

    _sucessoCadastroOnClick: function(value){
        alert(value);
        $(window.document.location).attr('href', 'login.jsp');
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
    Cadastro.Load();
});
