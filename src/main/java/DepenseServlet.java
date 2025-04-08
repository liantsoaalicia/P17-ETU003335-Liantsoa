package servlet;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import prevision.Credit;
import prevision.Depense;

public class DepenseServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            int idcredit = Integer.parseInt(req.getParameter("idcredit"));
            float montantdepense = Float.parseFloat(req.getParameter("montantdepense"));
            Date datedepense = Date.valueOf(req.getParameter("datedepense"));

            HttpSession session = req.getSession();
            int idutilisateur = (int) session.getAttribute("id");

            // Check si le montant est suffisant
            Credit credit = Credit.findById(idcredit);
            boolean checkSuffisant = credit.checkMontantSuffisant(montantdepense, idutilisateur);
            if(checkSuffisant) {
                Depense depense = new Depense(idutilisateur, idcredit, montantdepense, datedepense);
                depense.save();
            }

            res.sendRedirect("credit.html");
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération dans DepenseServlet " + e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            List<Depense> depenses = Depense.findAll();
            req.setAttribute("depenses", depenses);

            List<Credit> credits = Credit.findAll();
            req.setAttribute("credits", credits);

            RequestDispatcher dispatcher = req.getRequestDispatcher("Listes.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération dans DepenseServlet " + e.getMessage());
        }
    }

}
