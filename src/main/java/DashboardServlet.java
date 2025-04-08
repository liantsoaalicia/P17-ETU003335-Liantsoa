package servlet;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import login.Utilisateur;
import login.UtilisateurDAO;
import prevision.Credit;

public class DashboardServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            List<Credit> credits = Credit.findAll();
            req.setAttribute("credits", credits);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement du formulaire DashboardServlet" + e.getMessage());
        }
    }

}
