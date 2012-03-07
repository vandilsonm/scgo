Ajuda = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Ajuda.Load = function(){
    var _dados = new Ajuda();
    _dados.initialize();
    return _dados;
}

Ajuda.prototype = {

    initialize: function() {
        this.executeBind('content/ajuda.jsp', '', 'GET', this._dataBind_OnSuccess);
    },

    _dataBind_OnSuccess: function(value){
        $('#container').html(value)
        this.executeBind('form/login.jsp', '', 'GET', this._dataBind_OnSuccess_Login);
        //this.executeBind('form/pesquisa.jsp', '', 'GET', this._dataBind_OnSuccess_Pesquisa);
        this.executeBind('_servlets/share.jsp', '', 'GET', this._dataBind_OnSuccess_Share);
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
    Ajuda.Load();
});
