/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.bo;

import br.una.sgco.dao.DAOArbitragem;
import br.una.sgco.framework.Data;
import br.una.sgco.to.TOArbitragem;
import br.una.sgco.to.TOCampeonato;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nathalia
 */
public class BOArbitragem {

    public static void inserir (TOArbitragem arbitro) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOArbitragem.inserir(arbitro, c);
        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void alterar (TOArbitragem arbitro) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOArbitragem.alterar(arbitro, c);
        } finally {
            if (c != null)
                c.close();
        }
    }

    public static void excluir (TOArbitragem arbitro) throws Exception {
        Connection c = null;

        try {
            c = Data.openConnection();

            DAOArbitragem.excluir(arbitro, c);
        } finally {
            if (c != null)
                c.close();
        }
    }

    public static JSONArray lista (TOCampeonato campeonato) throws Exception {
        Connection c = null;
        JSONArray ja = null;

        try {
            c = Data.openConnection();

            ja = DAOArbitragem.obterTodos(campeonato, c);

        } finally {
            if (c != null)
                c.close();
        }

        return ja;
    }

    public static  JSONObject obterUm(TOArbitragem arbitro) throws Exception {
        Connection c = null;
        JSONObject jo = null;

        try {
            c = Data.openConnection();

            jo = DAOArbitragem.obterUm(arbitro, c);

        } finally {
            if (c != null)
                c.close();
        }

        return jo;
    }

}
