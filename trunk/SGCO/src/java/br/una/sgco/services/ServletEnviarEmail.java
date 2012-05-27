/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.services;

import br.una.sgco.bo.BOEstadio;
import br.una.sgco.bo.BOJogador;
import br.una.sgco.bo.BOJogo;
import br.una.sgco.bo.BOTime;
import br.una.sgco.framework.Email;
import br.una.sgco.to.TOEstadio;
import br.una.sgco.to.TOJogo;
import br.una.sgco.to.TOTime;
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
            String message = "Prezado(a),<br/><br/>";
                   message += "Um novo jogo foi marcado, confira os dados: <br/><br/>Jogo: ";
                   
            String link = "http://localhost:8084/ServletInserirJogadorJogo?";
            
            TOJogo toJogo = new TOJogo();
            toJogo.setCodigo(Integer.parseInt(request.getParameter("idJogo")));
            link += "idJogo=" + toJogo.getCodigo() + "&";
            
            JSONObject jsonData = BOJogo.obterUm(toJogo);
            String data = jsonData.getString("dataHora").toString();

            JSONObject json = BOJogo.obterUm(toJogo);
            int timMandante = json.getInt("timeMandante");
            int timVisitante = json.getInt("timeVisitante");
            int idEstadio = json.getInt("estadio");
            
            TOEstadio toEstadio = new TOEstadio();
            toEstadio.setCodigo(idEstadio);
            JSONObject jsonLocal = BOEstadio.obterUm(toEstadio);
            String estadio = jsonLocal.getString("nome");
            
            TOTime toTime = new TOTime();
            toTime.setCodigo(timMandante);
            JSONObject jsonTime = BOTime.obterUm(toTime);
            message += jsonTime.getString("nome") + " x ";
            
            toTime.setCodigo(timVisitante);
            jsonTime = BOTime.obterUm(toTime);
            message += jsonTime.getString("nome");
            message += " <br/>Dia e hora: " + data + "<br/>";
            message += " Local: " + estadio + "<br/><br/>";

            TOTime toTimeMandante = new TOTime();
            toTimeMandante.setCodigo(timMandante);
            JSONArray jsonJogMan = BOJogador.obterTodosTime(toTimeMandante);
            
            message += "Para confirmar sua presença clique no link abaixo: <br/><br/>";
            
            for (int i = 0; i < jsonJogMan.length(); i++) {
                String messageMan = message;
                String linkMan = link;
                JSONObject jso = jsonJogMan.getJSONObject(i);
                int idTime = jso.getInt("Time");
                int idJogador = jso.getInt("Codigo");
                String email = jso.getString("Email");
                 
                linkMan += "idTime=" + idTime + "&" + "idJogador=" + idJogador;
                messageMan += "<a href="+ linkMan + ">Clique aqui para confirmar sua presença </a>";
                messageMan += "<br/><br/>Bom jogo!<br/>Equipe Golaço";
                
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
                messageVis += "<a href="+ linkVis + ">Confirmar presença </a>";
                messageVis += "<br/><br/>Bom jogo!<br/>Equipe Golaço";
                
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
