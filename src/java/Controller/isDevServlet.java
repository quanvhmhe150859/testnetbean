/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import model.User;

/**
 *
 * @author Cun
 */
@WebServlet(name = "isDevServlet", urlPatterns = {"/isDev"})
public class isDevServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet isDevServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet isDevServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("UserID"));

        User user = new userDAO().findUserByID(Id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("isdev.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDAO d = new userDAO();
        try {
            String name = request.getParameter("name");
            User u = d.findUserByName(name);
            d.updateisDevUser(u);
            HttpSession session = request.getSession();
            session.removeAttribute("acc");
            response.sendRedirect("mainscreen");
        } catch (NumberFormatException e) {
        }
//        userDAO d = new userDAO();
//        try {
//            int id = Integer.parseInt(request.getParameter("id"));
//            //boolean isDev = Boolean.parseBoolean(request.getParameter("isdev"));
//            d.updateisDevUser(id);
//            processRequest(request, response);
//
//        } catch (ServletException | IOException | NumberFormatException e) {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet isDevServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet isDevServlet at " + e.getMessage() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

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
