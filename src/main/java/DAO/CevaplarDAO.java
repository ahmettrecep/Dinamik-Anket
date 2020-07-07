package DAO;

import Util.Utilization;
import org.json.JSONObject;

import java.sql.Connection;
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
    public void yanitEkle(int cevaplayanId, int formId, int soruId, JSONObject cevap){
        String sql = "INSERT INTO `cevaplar`(`CEAPLAYAN_ID`, `FORM_ID`, `SORU_ID`, `CEVAP`) VALUES (" + cevaplayanId + "," + formId + "," + soruId + "," + cevap +")";
        try {
            stmt.executeQuery("sql");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
