package Servlet;

import Bean.Cevaplar;
import Bean.Cevaplayanlar;
import Bean.Sorular;
import DAO.CevaplarDAO;
import DAO.CevaplayanlarDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/Servlet/YanitlariKaydet")
public class YanitlariKaydet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int[] secenekUzunluklari = (int[]) session.getAttribute("secenekUzunluklari");
        Cevaplar tempCevap = new Cevaplar();
        CevaplarDAO cevaplarDAO = new CevaplarDAO();
        List<Sorular> soru = (List<Sorular>) session.getAttribute("formaAitSorular");
        Cevaplayanlar cevaplayanlar = new Cevaplayanlar();


        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        CevaplayanlarDAO cevaplayanDao;
        Date dateobj = new Date();

        try {
            cevaplayanDao = new CevaplayanlarDAO();
            URL cevaplayanIp = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    cevaplayanIp.openStream()));
            String ip = in.readLine();
            String giris_tarihi = df.format(dateobj);

            cevaplayanlar.setCevaplayan_ip(ip);
            cevaplayanlar.setGiris_tarihi(giris_tarihi);
            int cevaplayanId = cevaplayanDao.cevaplayanEkle(cevaplayanlar);
            cevaplayanlar.setCevaplayan_id(cevaplayanId);
            tempCevap.setCevaplayan_id(cevaplayanId);

            char secenekPoint = 'A';
            for(int i=0;i<soru.size();i++){

                JSONObject opsiyonlar = new JSONObject();
                if(soru.get(i).getSoru_tipi().equals("acikUclu")){

                    String parametre = "yanit" + soru.get(i).getSorunun_sirasi();
                    String yanit = req.getParameter(parametre);

                    opsiyonlar.put(String.valueOf('"' + secenekPoint + '"'), yanit);
                    String secenek = opsiyonlar.toString();
                    tempCevap.setCevap(secenek);
                }else{
                    String parametre = soru.get(i).getSorunun_sirasi() + ".secenek";
                    String yanit = req.getParameter(parametre);

                    opsiyonlar.put(String.valueOf('"' + secenekPoint + '"'), yanit);
                    String secenek = opsiyonlar.toString();
                    tempCevap.setCevap(secenek);
                }
                String formParametre = soru.get(i).getKayitli_oldugu_form();
                int soruIdParametre = soru.get(i).getSoru_id();
                tempCevap.setForm_id(formParametre);
                tempCevap.setSoru_id(soruIdParametre);
                cevaplarDAO.yanitEkle(tempCevap);
                secenekPoint++;
            }
            req.getRequestDispatcher("../anketBitti.jsp").forward(req,resp);
        } catch (MalformedURLException ex) {
            System.out.println("YanitlariKaydet().MalformedURLException!");
        } catch (IOException e) {
            System.out.println("YanitlariKaydet().IOException!");
        } catch (SQLException e) {
            System.out.println("YanitlariKaydet().SQLException!");
        } catch (ServletException e) {
            System.out.println("YanitlariKaydet().ServletException!");
        } catch (JSONException e) {
            System.out.println("YanitlariKaydet().JSONException!");
        }
    }
}
