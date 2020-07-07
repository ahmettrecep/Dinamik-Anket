package Servlet;

import Bean.Cevaplar;
import Bean.Sorular;
import Bean.soruBilgisi;
import DAO.SoruDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Servlet/soruEkle")
public class soruEkle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        soruBilgisi soruNesnesi = (soruBilgisi) session.getAttribute("soruNesnesi");
        List<Sorular> anketSorulari =  new LinkedList<Sorular>();
        anketSorulari = (List<Sorular>) session.getAttribute("anketinSorulari");
        //Bu ismi soruIdleriFormTablosunaEkle() metoduna göndermek için alıyoruz.
        //Önce SoruDAO sınıfımzda soruKaydıYap() metoduna göndereceğiz.
        String anketIsmi = soruNesnesi.getAnketinIsmi();

        //Request Parametrelerini Getirmek
        String soruGovdesi = req.getParameter("soruGovdesi");
        String secenekSayisi = req.getParameter("seceneksayisi");
        String soruTipi = req.getParameter("soruTipiRadio");
        String secenekler = "";

        JSONObject opsiyonlar = new JSONObject();
        if(soruTipi.equals("test")){
            char secenekPoint = 'A';
            for(int i = 1; i <= Integer.parseInt(secenekSayisi); i++){
                String isarettb = req.getParameter("tb_" + i);
                try {
                    opsiyonlar.put(String.valueOf('"' + secenekPoint + '"'), isarettb);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //secenekler += secenekPoint + ":" + isarettb + ",";
                secenekPoint++;
            }
        }
        secenekler = opsiyonlar.toString();
        /*
            Soruların burada kayıt edildiği bilgileri,
                - Sorunun Tipi
                - Sorunun Sırası
                - Seçenekler
                - Sorunun Gövdesi
         */

        /*
            KayıtlıOlduguForm bilgisi SoruDAO classının soruKaydıYap()
            metodunda girilecektir. Çünkü formId oradan çağırılıyor.
            soruKaydiYap

            Seçenekler string değişkeninin son karakteri silinecek. Çünkü MySQL'e gittiğinde
            JSON olarak kaydedilecek
         */

        Sorular soruKaydet = new Sorular(soruTipi,soruNesnesi.getKacinciSoru(), secenekler, soruGovdesi);
        anketSorulari.add(soruKaydet);

        if(soruNesnesi.getKacinciSoru() < Integer.parseInt(soruNesnesi.getSoruSayisi())){

            soruNesnesi.setKacinciSoru(soruNesnesi.getKacinciSoru()+1);
            session.setAttribute("soruNesnesi", soruNesnesi);
            session.setAttribute("anketinSorulari", anketSorulari);

            req.getRequestDispatcher("../SorularHakkinda.jsp").forward(req,resp);
        }
        else if(soruNesnesi.getKacinciSoru() >= Integer.parseInt(soruNesnesi.getSoruSayisi())) {
            SoruDAO soruDao = new SoruDAO();
            try {
                soruDao.soruKaydıYap(anketSorulari, anketIsmi);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            session.setAttribute("anketinSorulari", anketSorulari);
            req.getRequestDispatcher("../sorularEklendi.jsp").forward(req, resp);
        }
        else{
            System.out.println("Hata!");
        }
    }
}
