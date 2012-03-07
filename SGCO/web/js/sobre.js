Sobre = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Sobre.Load = function(){
    var _dados = new Sobre();
    _dados.initialize();
    return _dados;
}

Sobre.prototype = {

    initialize: function() {
        this.executeBind('content/sobre.jsp', '', 'GET', this._dataBind_OnSuccess);
    },

    _dataBind_OnSuccess: function(value){
        $('#container').html(value)
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
    Sobre.Load();
});
