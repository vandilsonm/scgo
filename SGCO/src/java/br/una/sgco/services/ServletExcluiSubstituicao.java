/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.services;

import br.una.sgco.bo.BOSubstituicao;
import br.una.sgco.to.TOJogador;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOSubstituicao;
import br.una.sgco.to.TOTime;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nathalia
 */
public class ServletExcluiSubstituicao extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            TOSubstituicao subs = new TOSubstituicao();

            TOJogador jogadorEntrou = new TOJogador();
            jogadorEntrou.setCodigo(Integer.parseInt(request.getParameter("idJogadorEntrou")));

            TOJogador jogadorSaiu = new TOJogador();
            jogadorSaiu.setCodigo(Integer.parseInt(request.getParameter("idJogadorSaiu")));

            TOJogo jogo = new TOJogo();
            jogo.setCodigo(Integer.parseInt(request.getParameter("idJogo")));

            TOTime time = new TOTime();
            time.setCodigo(Integer.parseInt(request.getParameter("idTime")));

            subs.setJogo(jogo);
            subs.setTime(time);
            subs.setJogadorEntrou(jogadorEntrou);
            subs.setJogadorSaiu(jogadorSaiu);

            BOSubstituicao.excluir(subs);

            out.print("Registro excluído com sucesso.");
            
        } catch (Exception e) {
            out.print(e.getMessage());
        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
