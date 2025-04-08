package servlet;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import login.Utilisateur;
import login.UtilisateurDAO;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            String nom = req.getParameter("nom");
            String mdp = req.getParameter("mdp");

            Utilisateur user = new Utilisateur(nom, mdp);
            boolean match = UtilisateurDAO.ifMatched(user);

            if(match) {
                HttpSession session = req.getSession();
                Utilisateur utilSession = UtilisateurDAO.findByName(nom, mdp);
                session.setAttribute("id", utilSession.getId());
                res.sendRedirect("credit.html");
            } else {
                res.sendRedirect("index.html");
            }

        } catch (Exception e) {
            throw new ServletException("Erreur lors du traitement du formulaire login " + e.getMessage());
        }
    }
}