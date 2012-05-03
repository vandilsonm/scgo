/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.services;

import br.una.sgco.bo.BOJogador;
import br.una.sgco.bo.BOJogo;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOTime;
import br.una.sgco.framework.Email;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jana Louback
 */
public class ServletEnviarEmail extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String message = "Prezado(a),"
                    + ""; 
                   message += "Para confirmar sua presença no jogo, clique no link abaixo:<br/><br/>";
                   
            String link = "http://localhost:8084/ServletInserirJogadorJogo?";
            
            TOJogo toJogo = new TOJogo();
            toJogo.setCodigo(Integer.parseInt(request.getParameter("idJogo")));
            link += "idJogo=" + toJogo.getCodigo() + "&";

            JSONObject json = BOJogo.obterUm(toJogo);
            int timMandante = json.getInt("timeMandante");
            int timVisitante = json.getInt("timeVisitante");

            TOTime toTimeMandante = new TOTime();
            toTimeMandante.setCodigo(timMandante);
            JSONArray jsonJogMan = BOJogador.obterTodosTime(toTimeMandante);
            
            for (int i = 0; i < jsonJogMan.length(); i++) {
                String messageMan = message;
                String linkMan = link;
                JSONObject jso = jsonJogMan.getJSONObject(i);
                int idTime = jso.getInt("Time");
                int idJogador = jso.getInt("Codigo");
                String email = jso.getString("Email");
                 
                linkMan += "idTime=" + idTime + "&" + "idJogador=" + idJogador;
                messageMan += linkMan + "<br/><br/>Bom jogo!<br/>Equipe Golaço";
                
                Email semail = new Email();
                semail.sendMail("sgc.golaco", email, "Confirmação de presença", messageMan);
            }

            TOTime toTimeVisitante = new TOTime();
            toTimeVisitante.setCodigo(timVisitante);
            JSONArray jsonJogVis = BOJogador.obterTodosTime(toTimeVisitante);
            
            for (int i = 0; i < jsonJogVis.length(); i++) {
                String messageVis = message;
                String linkVis = link;
                JSONObject jso = jsonJogVis.getJSONObject(i);
                int idTime = jso.getInt("Time");
                int idJogador = jso.getInt("Codigo");
                String email = jso.getString("Email");
                 
                linkVis += "idTime=" + idTime + "&" + "idJogador=" + idJogador;
                messageVis += linkVis + "<br/><br/>Bom jogo!<br/>Equipe Golaço";
                
                Email semail = new Email();
                semail.sendMail("sgc.golaco", email, "Confirmação de presença", messageVis);
            }
            
            out.println("E-mail enviado com sucesso!");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletEnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletEnviarEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
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