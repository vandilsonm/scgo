/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.peladinha.to;

import br.una.sgco.to.TOUsuario;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jana Louback
 */
public class TOPelada {
    private int id;
    private String nome;
    private String descricao;
    private Time horario;
    private TOLocal idLocal;
    private int criador;
    public List<TOJogador> listaJogadores = new ArrayList<TOJogador>();
    public int getCriador() {
        return criador;
    }

    public void setCriador(int criador) {
        this.criador = criador;
    }

    public TOLocal getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(TOLocal idLocal) {
        this.idLocal = idLocal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
