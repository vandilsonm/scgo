/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.una.sgco.services;

import br.una.sgco.bo.BOJogo;
import br.una.sgco.to.TOArbitragem;
import br.una.sgco.to.TOEstadio;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOTime;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nathalia
 */
public class ServletAlteraJogo extends HttpServlet {
   
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
            TOJogo jogo = new TOJogo();
            jogo.setCodigo(Integer.parseInt(request.getParameter("id")));

            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date data = formato.parse(request.getParameter("dataHora"));
            Timestamp dataHora = new Timestamp(data.getTime());
            jogo.setDataHora(dataHora);

            TOEstadio estadio = new TOEstadio();
            estadio.setCodigo(Integer.parseInt(request.getParameter("estadio")));
            jogo.setEstadio(estadio);

            TOTime timeMandante = new TOTime();
            timeMandante.setCodigo(Integer.parseInt(request.getParameter("timeMandante")));
            jogo.setTimeMandante(timeMandante);

            TOTime timeVisitante = new TOTime();
            timeVisitante.setCodigo(Integer.parseInt(request.getParameter("timeVisitante")));
            jogo.setTimeVisitante(timeVisitante);

            TOArbitragem juiz = new TOArbitragem();
            juiz.setCodigo(Integer.parseInt(request.getParameter("juiz")));
            jogo.setJuiz(juiz);

            TOArbitragem juizReserva = new TOArbitragem();
            juizReserva.setCodigo(Integer.parseInt(request.getParameter("juizReserva")));
            jogo.setJuizAuxiliar(juizReserva);

            TOArbitragem bandeirinha1 = new TOArbitragem();
            bandeirinha1.setCodigo(Integer.parseInt(request.getParameter("bandeirinha1")));
            jogo.setBandeirinha1(bandeirinha1);

            TOArbitragem bandeirinha2 = new TOArbitragem();
            bandeirinha2.setCodigo(Integer.parseInt(request.getParameter("bandeirinha2")));
            jogo.setBandeirinha2(bandeirinha2);

            BOJogo.alterar(jogo);

            out.print("Alterações realizadas com sucesso.");

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
