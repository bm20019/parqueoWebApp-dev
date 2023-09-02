package sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.boundary.servlet;

import jakarta.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335.parqueo.parqueowebapp.app.entity.TipoReserva;

/**
 *
 * @author adalberto
 */
@WebServlet(name = "TipoReservaServlet", urlPatterns = {"/TipoReservaServlet"})
public class TipoReservaServlet extends HttpServlet {

    
    @Inject
    TipoReservaBean trBean;
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
        String nombre = null, descripcion=null;
        Boolean publico = null;
        
        if(request != null){
            if(request.getParameter("nombre")!= null){
                nombre = request.getParameter("nombre");
            }
            
            if(request.getParameter("descripcion")!= null){
                descripcion = request.getParameter("descripcion");
            }
            
            if(request.getParameter("publico")!= null){
                publico = Boolean.valueOf(request.getParameter("publico"));
            }
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TipoReservaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TipoReservaServlet at " + request.getContextPath() + "</h1>");
            if(nombre !=null &&  publico != null && descripcion != null){
                TipoReserva nuevo = new TipoReserva();
                nuevo.setDescripcion(descripcion);
                nuevo.setNombre(nombre);
                nuevo.setPublico(publico);
                try{
                    trBean.create(nuevo);
                    out.println("<p>Registro creado</p>");
                }catch(Exception ex){
                    
                }
                
            }
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
        processRequest(request, response);
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
