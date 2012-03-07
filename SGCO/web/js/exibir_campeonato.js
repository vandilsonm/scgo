ExibirCampeonato = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
}

ExibirCampeonato.Load = function(){
    var _dados = new ExibirCampeonato();
    _dados.initialize();
    return _dados;
}

ExibirCampeonato.prototype = {

    initialize: function() {
        var str = {
            id: getParameter("id")
        }
        this.executeBind('ServletListaClassificacao', str, 'GET', this._dataBind_OnSuccess);
        this.executeBind('ServletListaUmCampeonato', str, 'GET', this._exibeNomeCampeonato);
        this.executeBind('ServletListaUltimosJogosCamp', str, 'GET', this._exibeUltimosJogos);
        this.executeBind('ServletObtemArtilheiro', str, 'GET', this._obtemArtilheiro);
    },

    _obtemArtilheiro: function(value) {
        var dados = eval("(" + value + ")");
        $('#spanArtilheiro').html((dados.nomeJogador + "<br/>Time: " + dados.nomeTime
            + "<br/>" + dados.qtde + " gol(s)"));
    },

    _exibeNomeCampeonato: function (value) {
        var dados = eval("(" + value + ")");
        $('#spanTituloCampeonato').html(dados.nome);
    },

    _exibeUltimosJogos: function (value) {
        var listaJogos = eval(value);

        var html = "<div class=\"table\">"
                + "<table border=\"0\" cellpadding=\"6\" cellspacing=\"0\"><thead>"
                + "<tr><th colspan=\"7\"><h2>Últimos/Próximos Jogos</h2></th></tr>"
                + "</thead><tbody>";

        var data = "";
        for (var i = 0; i < listaJogos.length; i++ ) {

            if (data != listaJogos[i].dataHora.substr(0,5)) {
                data = listaJogos[i].dataHora.substr(0,5);

                html += "<tr><th colspan=\"7\"><h3>" + listaJogos[i].dataHora.substr(0,5) + "</h3></th></tr>";
            }

            html += "<tr><td></td>";
            html += "<td class=\"nomeTime\">" + listaJogos[i].timeNomeMandante + "</td>";
            if (listaJogos[i].jogoAconteceu == 0)
                html += "<td><span class=\"placarJogo\">&nbsp;</span></td>";
            else
                html += "<td><span class=\"placarJogo\">" + listaJogos[i].golsMandante + "</span></td>";

            html += "<td>x</td>";

            if (listaJogos[i].jogoAconteceu == 0)
                html += "<td><span class=\"placarJogo\">&nbsp;</span></td>";
            else
                html += "<td><span class=\"placarJogo\">" + listaJogos[i].golsVisitante + "</span></td>";
            
            html += "<td class=\"nomeTime\">" + listaJogos[i].timeNomeVisitante + "</td>";
            html += "<td>" + listaJogos[i].dataHora.substr(6, 5) + "</td></tr>";
        }
        html += "</tbody></table></div>";
        $('#container_right_ultimos_jogos').html(html);
    },

    _dataBind_OnSuccess: function(value){
        var listaClassificacao = eval(value);

        var html = "<thead>"
                    + "<th> &nbsp; </th>"
                    + "<th>Classificação</th>"
                    + "<th><abbr title=\"Pontos\">P</abbr></th>"
                    + "<th><abbr title=\"Jogos\">J</abbr></th>"
                    + "<th><abbr title=\"Vitórias\">V</abbr></th>"
                    + "<th><abbr title=\"Empates\">E</abbr></th>"
                    + "<th><abbr title=\"Derrotas\">D</abbr></th>"
                    + "<th><abbr title=\"Gols Pró\">GP</abbr></th>"
                    + "<th><abbr title=\"Gols Contra\">GC</abbr></th>"
                    + "<th><abbr title=\"Saldo de Gols\">SG</abbr></th>"
                    + "</thead>";

        for (var i = 0; i < listaClassificacao.length; i++ ) {

            html += "<tr><td>" + (i + 1) + "</td><td>" + listaClassificacao[i].nomeTime + "</td>";
            html += "<td>" + listaClassificacao[i].total_pontos + "</td>";
            html += "<td>" + listaClassificacao[i].qtde_jogo + "</td>";
            html += "<td>" + listaClassificacao[i].qtde_vitoria + "</td>";
            html += "<td>" + listaClassificacao[i].qtde_empate + "</td>";
            html += "<td>" + listaClassificacao[i].qtde_derrota + "</td>";
            html += "<td>" + listaClassificacao[i].qtde_gols + "</td>";
            html += "<td>" + listaClassificacao[i].qtde_gols_contra + "</td>";
            html += "<td>" + listaClassificacao[i].saldo_gols + "</td></tr>";
        }
        $('#tblClassificacao').html(html);
    },

    _dataBind_OnSuccess_ExibirCampeonato: function(value) {
        $('#container_text_resultados').html(value);
    },

    _dataBind_OnSuccess_ultimos_jogos: function(value) {
        $('#container_right_ultimos_jogos').html(value);
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
    ExibirCampeonato.Load();
});
