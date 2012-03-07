Login = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Login.Load = function(){
    var _dados = new Login();
    _dados.initialize();
    return _dados;
}

Login.prototype = {

    initialize: function() {
        this.executeBind('content/login.jsp', '', 'GET', this._dataBind_OnSuccess);
    },

    _dataBind_OnSuccess: function(value){
        $('#container').html(value);
        $('#btnLogin').bind('click', '', $.createDelegate(this, this._btnLoginOnClick));
    },

    _btnLoginOnClick: function(value) {
        var str = {
            login: $('#txtLogin').val(),
            senha: $('#txtSenha').val()

        }
        this.executeBind('ServletLogin', str, 'GET', this._sucessoLoginOnClick);
    },

    _sucessoLoginOnClick: function(value){
        if (value == "ok")
            $(window.document.location).attr('href', 'admin/index.jsp');
        else
            alert(value);
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
    Login.Load();
});
