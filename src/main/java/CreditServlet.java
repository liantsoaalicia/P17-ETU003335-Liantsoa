package servlet;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import prevision.Credit;

public class CreditServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            String libelle = req.getParameter("libelle");
            float montant = Float.parseFloat(req.getParameter("montant"));
            Date datedebut = Date.valueOf(req.getParameter("datedebut"));
            Date datefin = Date.valueOf(req.getParameter("datefin"));

            Credit credit = new Credit(libelle, montant, datedebut, datefin);
            credit.save();
            res.sendRedirect("credit.html");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors du traitement du formulaire de ligne de credit" + e.getMessage());
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            res.sendRedirect("credit.html");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la redirection " + e.getMessage());
        }
    }

}
