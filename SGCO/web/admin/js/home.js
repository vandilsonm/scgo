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
        // Campeonatos
        this.executeBind('content/home.jsp', '', 'GET', this._dataBind_OnSuccess);
        // Peladinha
        this.executeBind('peladinha/content/home.jsp', '', 'GET', this._dataBind_Peladinha_OnSuccess);
        // seleciona modulo
        this.executeBind('content/modulo.jsp', '', 'GET', this._dataBind_Modulo_OnSuccess);
    },

    _dataBind_OnSuccess: function(value){
        $('#links_moldura').html(value);
        this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._sucessoListaCampeonato);
    },

    _dataBind_Peladinha_OnSuccess: function(value){
        $('#peladinha_links_moldura').html(value);
        //this.executeBind('../ServletListaCampeonatoUsuario', '', 'GET', this._sucessoListaCampeonato);
    },

    _dataBind_Modulo_OnSuccess: function(value){
        $('#seleciona_modulo').html(value);
    },
    
    _sucessoListaCampeonato: function(value){
        var listaCam = eval(value);
        var html = "<h2>Meus Campeonatos</h2>";

        if (listaCam.length > 0) {
            html += "<ul class=\"linksCampeonatos\">";

            for (var i = 0; i < listaCam.length; i++ ) {
                html += "<li><a href=\"meuCampeonato.jsp?id=" +
                        listaCam[i].codigo + "\">" +
                        listaCam[i].nome +
                        "</a></li>";
            }
            html += "</ul>";
        }
        else {
            html += "<br /><br />Não existe nenhum campeonato cadastrado.";
        }
        
        $('#painel_links').html(html);
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
