package DAO;

import Bean.Cevaplar;
import Util.Utilization;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CevaplarDAO {
    Utilization util;
    Connection conn;
    Statement stmt;
    public CevaplarDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        util = new Utilization();
        conn = util.baglantiOlustur();
        try{
            stmt = conn.createStatement();
        }catch(SQLException ex){
            System.out.println("SoruDAO Constructor'ında Hata!");
        }
    }
    public void yanitEkle(Cevaplar cevaplar){
        String sorgu = "INSERT INTO `cevaplar`(`CEVAPLAYAN_ID`, `FORM_ID`, `SORU_ID`, `CEVAP`) VALUES (?,?,?,?)";
        int soruid = cevaplar.getSoru_id();
        try {
            int id = cevaplar.getCevaplayan_id();
            String formid = cevaplar.getForm_id();
            int soruuid = cevaplar.getSoru_id();
            String cevap = cevaplar.getCevap();
            PreparedStatement pstmt = conn.prepareStatement(sorgu);
            pstmt.setInt(1,id);
            pstmt.setString(2,formid);
            pstmt.setInt(3,soruuid);
            String sorgucevap = cevaplar.getCevap();
            pstmt.setString(4,sorgucevap);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
