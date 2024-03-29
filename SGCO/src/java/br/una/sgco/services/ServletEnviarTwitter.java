/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 *
 * @author Jana Louback
 */
public class ServletEnviarTwitter extends HttpServlet {

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
           
            if(request.getSession().getAttribute("oauth_verifier")!=null)
                response.sendRedirect("/TwitterCallBack?id="+request.getParameter("id")+"&msg="+request.getParameter("msg"));
            
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer("IfNKV9RsTabg3a9CuV03Q", "DPPcpFCFao0sj64xZc5KofRtpvkg4UvQEMBed5GH0");
            request.getSession().setAttribute("twitter", twitter);
            try {
                request.getSession().setAttribute("msg", request.getParameter("msg"));
                StringBuffer callbackURL = request.getRequestURL();
                int index = callbackURL.lastIndexOf("/");
                String msg = "/TwitterCallBack?id="+request.getParameter("id");
                System.out.println("msg: "+msg);
                callbackURL.replace(index, callbackURL.length(), "").append(msg);

                RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
                request.getSession().setAttribute("requestToken", requestToken);
                response.sendRedirect(requestToken.getAuthenticationURL());

            } catch (TwitterException e) {
                throw new ServletException(e);
            }

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
