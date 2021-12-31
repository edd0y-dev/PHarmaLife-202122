package controller.admin;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletUpdateProdottoAdmin", value = "/ServletUpdateProdottoAdmin")
public class ServletUpdateProdottoAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idProdotto=Integer.parseInt(request.getParameter("id"));
        ProdottoDAO prodottoDAO= new ProdottoDAO();
        Prodotto prodotto= prodottoDAO.cercaProdotto(idProdotto);

        request.setAttribute("prodotto",prodotto);
        RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/pagine/admin/updateProdottoAdmin.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}