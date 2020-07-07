package Servlet;

import Bean.Sorular;
import Bean.soruBilgisi;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Servlet/anketBilgisi")
public class anketBilgisi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        soruBilgisi sorulariDoldurma = new soruBilgisi();
        sorulariDoldurma.setKacinciSoru(1);
        sorulariDoldurma.setAnketinIsmi(req.getParameter("anketIsim"));
        sorulariDoldurma.setSoruSayisi(req.getParameter("soruSayisi"));

        HttpSession session =req.getSession();
        session.setAttribute("soruNesnesi", sorulariDoldurma);

        List<Sorular> anketSorulari = new LinkedList<Sorular>();
        session.setAttribute("anketinSorulari", anketSorulari);

        req.getRequestDispatcher("../SorularHakkinda.jsp").forward(req,resp);
    }
}
