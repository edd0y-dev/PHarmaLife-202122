package controller.utente;

import model.prodotto.Prodotto;
import model.prodotto.ProdottoDAO;
import model.prodotto.ProdottoDAOMethod;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletPreferiti", value = "/ServletPreferiti")
public class ServletPreferiti extends HttpServlet {
    private ProdottoDAOMethod prodottoDAO;
    private UtenteDAOMethod utenteDAO;

    public ServletPreferiti() throws SQLException {
        prodottoDAO= new ProdottoDAO();
        utenteDAO= new UtenteDAO();
    }

    public ServletPreferiti(ProdottoDAO prodottoDAO,UtenteDAO utenteDAO){
        this.prodottoDAO=prodottoDAO;
        this.utenteDAO=utenteDAO;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        inserisciProdottoAiPreferiti(request,response);
    }

    @Generated
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Questo metodo serve per aggiungere un prodotto ai preferiti.
     * @pre //
     * @param request
     * @param response
     * @throws IOException
     * @post service.doRetrieveByAllPreferitiOfUtente.size=@pre.service.doRetrieveByAllPreferitiOfUtente.size+1
     */

    public void inserisciProdottoAiPreferiti(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int codiceProdotto=Integer.parseInt(request.getParameter("value"));
        Prodotto prodotto=prodottoDAO.cercaProdotto(codiceProdotto);
        String risposta="";
        if(session != null) {
            Utente utente = (Utente) session.getAttribute("utente");

            if (utente != null) {
                utenteDAO.insertPreferito(utente, prodotto);
                risposta="Prodotto aggiunto ai tuoi preferiti!";
            }

            response.getWriter().write(risposta);
        }
    }
}
