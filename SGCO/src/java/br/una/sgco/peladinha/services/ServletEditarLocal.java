/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.una.sgco.peladinha.services;

import br.una.sgco.peladinha.bo.BOLocal;
import br.una.sgco.peladinha.to.TOLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jana Louback
 */
public class ServletEditarLocal extends HttpServlet {

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
            
            HttpSession session = request.getSession();
            String idUsuarioStr = session.getAttribute("usuario").toString();
            String idLocalStr = request.getParameter("id");
            String nome = request.getParameter("nome");
            String logradouro  = request.getParameter("logradouro");
            String numero  = request.getParameter("numero");
            String complemento  = request.getParameter("complemento");
            String bairro  = request.getParameter("bairro");
            String cidade  = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            
            //valida id Usuário
            Integer idUsuario = null;
            if(idUsuarioStr != null){
                try {
                    idUsuario = Integer.parseInt(idUsuarioStr);
                } catch (Exception e) {
                    throw new Exception("Id usuário não é um número.");
                }
            }
            if(idUsuario == null)
                 throw new Exception("Id inválido.");
            
            
            //valida id local
            Integer idLocal = null;
            if(idLocalStr != null){
                try {
                    idLocal = Integer.parseInt(idLocalStr);
                } catch (Exception e) {
                    throw new Exception("Id local não é um número.");
                }
            }
            if(idLocal == null)
                 throw new Exception("Id inválido.");
            
            // valida nome
            if(nome == null || nome.equals(""))
                throw new Exception("Campo nome vazio.");
            
            // valida endereco
            if(logradouro == null || logradouro.equals(""))
                throw new Exception("Campo logradouro vazio.");

            if(numero == null || numero.equals(""))
                throw new Exception("Campo numero vazio.");

            if(bairro == null || bairro.equals(""))
                throw new Exception("Campo bairro vazio.");
            
            if(cidade == null || cidade.equals(""))
                throw new Exception("Campo cidade vazio.");
           
            TOLocal toLocal = new TOLocal();
            toLocal.setId(idLocal);
            toLocal.setNome(nome);
            toLocal.setIdUsuario(idUsuario);
            toLocal.setLogradouro(logradouro);
            toLocal.setNumero(Integer.parseInt(numero));
            toLocal.setComplemento(complemento);
            toLocal.setBairro(bairro);
            toLocal.setCidade(cidade);
            toLocal.setEstado(estado);
        
            BOLocal.alterar(toLocal);

            out.print("Alterações realizadas com sucesso.");
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
