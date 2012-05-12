JogosPeladinha = function(){
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._idSelecionado = null;
}

JogosPeladinha.Load = function(){
    var _dados = new JogosPeladinha();
    _dados.initialize();
    return _dados;
}

JogosPeladinha.prototype = {
    /*==============================================================================
    inicialize
    ==============================================================================*/
    initialize: function() {
        
        this.executeBind('../../../ServletListarPelada', '', 'GET', this._sucessoListaPeladinha);
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
            $('#altera'+i).bind('click', str2, $.createDelegate(this, this._alterarItemOnClick));
        }
    },
    

    /*==============================================================================
    click
    ==============================================================================*/
    _btnCadastroOnClick: function() {
        /*if ($('#txtNome').val() == ''
            || $('#txtDescricao').val() == ''
            || $('#txtHorario').val() == ''
            || $('#ddlLocal').val() == '')

            alert("É obrigatório informar todos os campos.");
        else {*/
        var listaSelecionados = $('input[name=jogador]');
        var str = {
            nome: $('#txtNome').val(),
            descricao: $('#txtDescricao').val(),
            horario: $('#txtHorario').val(),
            local: $('#ddlLocal').val(),
            id: this._idSelecionado
        }
        var lista ="";
        for(var i = 0; i<listaSelecionados.length;i++){
            //lista += "{\"id\":\""+listaSelecionados[i].value+"\",\"value\":\""+listaSelecionados[i].checked+"\"},"
            //alert(listaSelecionados[i].checked+"  "+listaSelecionados[i].value);
            if(listaSelecionados[i].checked)
                lista += listaSelecionados[i].value+"---";
        }
            
        var str = {
            nome: $('#txtNome').val(),
            descricao: $('#txtDescricao').val(),
            horario: $('#txtHorario').val(),
            local: $('#ddlLocal').val(),
            id: this._idSelecionado,
            jogadores: lista
        }
        this.executeBind('../../../ServletInserirPelada', str, 'GET', this._cadastroOnSuccess);
    //}
    },

    _btnCadastroAltOnClick: function() {
        if ($('#txtNome').val() == ''
            || $('#txtDescricao').val() == ''
            || $('#txtHorario').val() == ''
            || $('#ddlLocal').val() == '')

            alert("É obrigatório informar todos os campos.");
            
        else {
            var listaSelecionados = $('input[name=jogador]');
            var lista ="";
            for(var i = 0; i<listaSelecionados.length;i++){
                //lista += "{\"id\":\""+listaSelecionados[i].value+"\",\"value\":\""+listaSelecionados[i].checked+"\"},"
                //alert(listaSelecionados[i].checked+"  "+listaSelecionados[i].value);
                if(listaSelecionados[i].checked)
                    lista += listaSelecionados[i].value+"---";
            }
            
            
            var str = {
                nome: $('#txtNome').val(),
                descricao: $('#txtDescricao').val(),
                horario: $('#txtHorario').val(),
                local: $('#ddlLocal').val(),
                id: this._idSelecionado,
                jogadores: lista
            }
            this.executeBind('../../../ServletEditarPelada', str, 'GET', this._cadastroOnSuccess);
        }
        
    },
    _excluirItemOnClick: function (value) {
        if (confirm("Deseja excluir o registro?")) {
            var str = {
                id: value.data.id
            };
            this.executeBind('../../ServletExcluirPelada', str, 'GET', this._cadastroOnSuccess);
        }
    },
    
    
    
    
    
    /*==============================================================================
    funcoes
    ==============================================================================*/
    _loadListaOnSuccess: function(value) {
        var listaPeladinha = eval(value);
        
        $('#spanTitulo').html('Peladinha(s) Cadastrada(s)');

        var html = "<tr><th>Nome</th><th>Descrição</th><th>Horário</th><th>Local</th>";
        html += "<th class=\"alingCenter\">Editar</th>";
        html += "<th class=\"alingCenter\">Excluir</th></tr>";

        $('#adm_container_one_text_form').html(html);

        for (var i = 0; i < listaPeladinha.length; i++ ) {
            html = "";
            html += "<tr><td>" + listaPeladinha[i].nome + "</td>";
            html += "<td>" + listaPeladinha[i].descricao + "</td>";
            html += "<td>" + listaPeladinha[i].horario + "</td>";
            html += "<td>" + listaPeladinha[i].descricaoLocal + "</td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"alt" + i + "\" class=\"inputBotao icone editar\"></a></td>";
            html += "<td class=\"alingCenter\"><a href=\"#\" id = \"exc" + i + "\" class=\"inputBotao icone excluir\"></a></td>";
            html += "</tr>";

            $('#adm_container_one_text_form').each(function(){
                $(this).append(html);
            });

            var str = {
                id: listaPeladinha[i].id,
                index: i
            }
            $('#alt' + i).bind('click', str, $.createDelegate(this, this._alterarItemOnClick));
            $('#exc' + i).bind('click', str, $.createDelegate(this, this._excluirItemOnClick));
        }
    },
    
    _alterarItemOnClick: function (value) {
        this._idSelecionado = value.data.id;
        this.executeBind('form/peladinha.jsp', '', 'GET', this._alterarOnSuccess);
        
    },
    _loadListaJogadorOnSuccess: function(value) {
        var listaJogador = eval(value);
        this._loadJogadores(listaJogador);
    },
    _loadJogadores: function(listaJogador) {
        var html = "";
        var checked = "checked";
        var noChecked = "";
        for (var i = 0; i < listaJogador.length; i++ ) {
            var str = "";
            if(listaJogador[i].selecionado){
                str = checked
            }else{
                str = noChecked
            }
                
            html += "<label> <input type=\"checkbox\" name=\"jogador\" value=\""+listaJogador[i].id+"\" "+str+"/>"+listaJogador[i].nome+"</label>";
        }
        
        $('#listaJogador').html(html);
    },

    _listaCampeonato: function (value) {
        var listaCampeonato = eval(value);
        var htmlLinks = "<h2>Meus Campeonatos</h2>";
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

    _listaLocal: function (value) {
        var listarLocal = eval(value);
        $('#ddlLocal').html('');

        for (var i = 0; i < listarLocal.length; i++ ) {
            $('#ddlLocal').append('<option value="' +
                listarLocal[i].id + '">' +
                listarLocal[i].nome + '</option>');
        }
    },

    _loadNovo: function() {
        this.executeBind('form/peladinha.jsp', '', 'GET', this._loadNovoOnSuccess);
    },

    _loadNovoOnSuccess: function(value) {
        $('#spanTitulo').html('Cadastro de Peladinha');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroOnClick));
        this.executeBind('../../../ServletListarLocal', '', 'GET', this._listaLocal);
        this.executeBind('../../../ServletListarJogador', '', 'GET', this._loadListaJogadorOnSuccess);
    },

    _loadLista: function() {
        this.executeBind('../../../ServletListarPelada', '', 'GET', this._loadListaOnSuccess);
    },

    _alterarOnSuccess: function(value) {
        
        $('#spanTitulo').html('Alteração da peladinha');
        $('#adm_container_one_text_form').html(value);
        $('#btnCadastro').bind('click', '', $.createDelegate(this, this._btnCadastroAltOnClick));

        var str = {
            idPeladinha: this._idSelecionado
        }

        this.executeBind('../../../ServletListarLocal', '', 'GET', this._listaLocal);
        this.executeBind('../../../ServletListarUmaPeladinha', str, 'GET', this._alterarLoadOnSuccess);
    },

    _alterarLoadOnSuccess: function(value) {
        var dados = eval("(" + value + ")");
        $('#txtNome').attr("value", dados.nome);
        $('#txtDescricao').attr("value", dados.descricao);
        $('#txtHorario').attr("value", dados.horario);
        $('#ddlLocal').attr("value", dados.local);
        
        this._loadJogadores(dados.jogadores);
        
    },

    
    _cadastroOnSuccess: function(value) {
        alert(value);
        this.executeBind('../../../ServletListarPelada', '', 'GET', this._loadListaOnSuccess);
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
    JogosPeladinha.Load();
});
