/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.peladinha.services;

import br.una.sgco.peladinha.bo.BOPelada;
import br.una.sgco.peladinha.to.TOJogador;
import br.una.sgco.peladinha.to.TOLocal;
import br.una.sgco.peladinha.to.TOPelada;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jana Louback
 */
public class ServletEditarPelada extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String str = request.getParameter("horario");  
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");  
            Date data = formatador.parse(str);  
            Time time = new Time(data.getTime()); 
            String[] jogadores = request.getParameter("jogadores").split("---");
            
            HttpSession session = request.getSession();
            
            TOPelada toPelada = new TOPelada();
            toPelada.setCriador(Integer.parseInt(session.getAttribute("usuario").toString()));
            toPelada.setId(Integer.parseInt(request.getParameter("id")));
            toPelada.setNome(request.getParameter("nome"));
            toPelada.setDescricao(request.getParameter("descricao"));
            toPelada.setHorario(time);
            
            for(String s : jogadores){
                TOJogador jogador = new TOJogador();
                
                jogador.setId(Integer.parseInt(s));
                toPelada.listaJogadores.add(jogador);
            }

            TOLocal toLocal = new TOLocal();
            toLocal.setId(Integer.parseInt(request.getParameter("local").toString()));
            toPelada.setIdLocal(toLocal); 
            
            BOPelada.alterar(toPelada);

            out.print("Registro alterado com sucesso.");
        } catch (Exception e) {
            out.print(e.getMessage());
        } finally {
            out.close();
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
