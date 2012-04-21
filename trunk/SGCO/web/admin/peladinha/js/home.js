Home = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Home.Load = function(){
    var _dados = new Home();
    _dados.initialize();
    return _dados;
}

Home.prototype = {

    initialize: function() {
        // Peladinha
        this.executeBind('content/home_interno.jsp', '', 'GET', this._dataBind_Peladinha_OnSuccess);
    },

    _dataBind_Peladinha_OnSuccess: function(value){
        $('#peladinha_links_moldura').html(value);
        this.executeBind('../../../ServletListarPelada', '', 'GET', this._sucessoListaPeladinhas);
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
    Home.Load();
});
