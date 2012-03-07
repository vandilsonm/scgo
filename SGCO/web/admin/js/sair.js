Sair = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

Sair.Load = function(){
    var _dados = new Sair();
    _dados.initialize();
    return _dados;
}

Sair.prototype = {

    initialize: function() {
        this.executeBind('../ServletLogout', '', 'GET', this._logout);
    },

    _logout:function () {
        $(window.document.location).attr('href', '../index.jsp')
    },

    _dataBind_OnSuccess: function(value){
        $('#links_moldura').html(value);
    },

    _listaCampeonato: function (value) {
        var listaCampeonato = eval(value);

        var htmlLinks = "<h2>Meus Campeonatos</h2>";

        $('#painel_links').html(htmlLinks);

        if (listaCampeonato.length > 0) {
            htmlLinks = "<ul class=\"linksCampeonatos\">";

            for (var i = 0; i < listaCampeonato.length; i++ ) {
                htmlLinks += "<li><a href=\"meuCampeonato.jsp?id=" +
                    listaCampeonato[i].codigo + "\">" +
                    listaCampeonato[i].nome +
                    "</a></li>";
            }

            htmlLinks += "</ul>";

            $('#painel_links').each(function(){
                $(this).append(htmlLinks);
            });
        }
        else {
            htmlLinks += "<br /><br />Não existe nenhum campeonato cadastrado.";
        }

        //$('#painel_links').html(htmlLinks);
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
    Sair.Load();
});
