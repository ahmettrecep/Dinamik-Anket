package DAO;

import Bean.Sorular;
import Util.Utilization;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SoruDAO {
    Utilization util;
    Connection conn;
    Statement stmt2;
    public SoruDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");
            stmt2 = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param anketSorulari
     */
    public void soruKaydıYap(List<Sorular> anketSorulari, String anketIsmi) throws SQLException, ClassNotFoundException {
        AnketDAO anketDao = new AnketDAO();
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");
        stmt2 = conn.createStatement();
        String formId = anketDao.formIdOlustur();

        //Sçeneklerin sonundaki virgülleri temizler.
        for(int i=0;i<anketSorulari.size();i++){
            String temizle = "";
            StringBuilder sb;
            if(anketSorulari.get(i).getSecenekler() != ""){
                temizle = anketSorulari.get(i).getSecenekler();
                sb = new StringBuilder();
                sb.append(temizle);
                sb.deleteCharAt(temizle.length() - 1);
                temizle = sb.toString();
                anketSorulari.get(i).setSecenekler(temizle);
            }else{
                break;
            }
        }
        for(int i=0;i<anketSorulari.size();i++){
            anketSorulari.get(i).setKayitli_oldugu_form(formId);
            anketSorulari.get(i).setSecenekler(anketSorulari.get(i).getSecenekler() + "}");
            System.out.println(i + ". soru : " + anketSorulari.get(i).getSecenekler());
            String sorgu = "INSERT INTO `sorular`(`SORU_TIPI`, `SORUNUN_SIRASI`, `KAYITLI_OLDUGU_FORM`, `SECENEKLER`,`SORU_GOVDESI`) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sorgu);
            System.out.println("anketSorulari.get(" + i + ") sorusunun seçenekleri" + anketSorulari.get(i).getSecenekler());
            pstmt.setString(1, anketSorulari.get(i).getSoru_tipi());
            pstmt.setInt(2, anketSorulari.get(i).getSorunun_sirasi());
            pstmt.setString(3, anketSorulari.get(i).getKayitli_oldugu_form());
            pstmt.setString(4, anketSorulari.get(i).getSecenekler());
            pstmt.setString(5,anketSorulari.get(i).getSoruGovdesi());
            pstmt.executeUpdate();
        }
        anketDao.soruIdleriFormTablosunaEkle(formId,anketSorulari,anketIsmi);
    }
    public String[] yanitlariAyristir(String yanit){
        yanit = yanit.substring(0, 0) + yanit.substring(1);
        yanit = yanit.substring(0, yanit.length()-1) + yanit.substring(yanit.length());
        String secenek[] = yanit.split(",");


        for(int j=0;j<secenek.length;j++) {
            while (secenek[j].charAt(0) != ':') {
                secenek[j] = secenek[j].substring(0, 0) + secenek[j].substring(1);
            }
            secenek[j] = secenek[j].substring(0, 0) + secenek[j].substring(1);
            secenek[j].trim();
            secenek[j] = secenek[j].substring(2, secenek[j].length() - 1);
            System.out.println(secenek[j]);
        }
        return secenek;
    }
}