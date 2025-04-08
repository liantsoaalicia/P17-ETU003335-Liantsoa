package servlet;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import prevision.Credit;

public class FormDepenseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        try {
            List<Credit> credits = Credit.findAll();
            req.setAttribute("credits", credits);

            RequestDispatcher dispatcher = req.getRequestDispatcher("FormDepense.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la récupération des credits dans FormDepenseServlet " + e.getMessage());
        }
    }
}
