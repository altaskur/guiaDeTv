/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package inc.tanuki.javacrud;

import dto.ProgramaTv;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author isaac
 *
 *
 */
public class crudServelet extends HttpServlet {

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

        // Codificamos tanto los parametros de request como de response
        // en utf-8
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            // almacenamos la sessión
            HttpSession session = request.getSession();

            // Comprobamos si existe una sesión y si no existe creamos ArrayList vacio
            List<ProgramaTv> listaTv = session.getAttribute("ProgramaTv") == null ? new ArrayList<>() : (ArrayList<ProgramaTv>) session.getAttribute("ProgramaTv");

            // Capturamos si quermeos eliminar toda la lista de programación a traves de get
            // Eliminamos sesion, creamos una nueva y el arraylist lo vaciamos.
            if (request.getParameter("deleteList") != null) {
                session.invalidate();
                session = request.getSession(true);

                listaTv.clear();
            }

            // Cuando recivimos erase
            // Quitamos una fila de la lista de programatv
            if (request.getParameter("erase") != null) {

//               List<ProgramaTv> filteredList = listaTv.stream()
//                       .filter(programTv -> programTv.getId() != Integer.parseInt(request.getParameter("erase")))
//                       .collect(Collectors.toList());
//                listaTv = filteredList;
//                System.out.println(filteredList);
                int id = Integer.parseInt(request.getParameter("erase"));
                ProgramaTv miPrograma = new ProgramaTv(id, "", "", "");

                if (listaTv.contains(miPrograma)) {
                    listaTv.remove(listaTv.indexOf(miPrograma));
                }

            }

            if (request.getParameter("edit") != null) {
                int id = Integer.parseInt(request.getParameter("edit"));

                ProgramaTv miPrograma = new ProgramaTv(id, "", "", "");
                if (listaTv.contains(miPrograma)) {
                    session.setAttribute("Editar", listaTv.get(listaTv.indexOf(miPrograma)));
                }

            }

            if (request.getParameter("cancelEdit") != null) {
                out.println("funciona?");
                session.setAttribute("Editar", null);
            }

            if (request.getParameter("editPrograma") != null) {
                int id = Integer.parseInt(request.getParameter("editPrograma"));
                ProgramaTv miPrograma = new ProgramaTv(id, "", "", "");
                if (listaTv.contains(miPrograma)) {
                    ProgramaTv editado = listaTv.get(listaTv.indexOf(miPrograma));
                    editado.setCanal(request.getParameter("canal"));
                    editado.setHorario(request.getParameter("hora"));
                    editado.setTitulo(request.getParameter("titulo"));

                    session.setAttribute("Editar", null);
                }
            }

//            Datos de test
//            listaTv.add(new ProgramaTv("Antena dos", "Altaskur y el misterio de tanuki", "22:30"));
//            listaTv.add(new ProgramaTv("Tanuki National Television", "Java vs JavaScript", "23:30"));
//            listaTv.add(new ProgramaTv("Java 1", "COBOL el bárbaro", "8:30"));
            // Recogemos por parametro canal y con ello damos de alta
            // un registro nuevo
            if (request.getParameter("nuevoPrograma") != null && request.getParameter("nuevoPrograma") != "") {

                out.println("Creando nuevo objeto");
                // Hacemos un Id AutoIncrementable
                Integer newId = listaTv.size() + 1;
                listaTv.add(new ProgramaTv(newId, request.getParameter("canal"), request.getParameter("titulo"), request.getParameter("hora")));
                session.setAttribute("ProgramaTv", listaTv);
            }

            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect("index.jsp");
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
