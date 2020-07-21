package DAO;

import Bean.Cevaplayanlar;
import Util.Utilization;

import javax.rmi.CORBA.Util;
import java.sql.*;

public class CevaplayanlarDAO {
    Utilization util;
    Connection conn;
    public CevaplayanlarDAO() throws SQLException {
        util = new Utilization();
        conn = util.baglantiOlustur();
    }

    public int cevaplayanIpyeGoreIdGetir(String cevaplayanIp){
        String sorgu = "SELECT CEVAPLAYAN_ID FROM CEVAPLAYANLAR WHERE CEVAPLAYAN_IP = ?";
        int cevaplayanId = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sorgu);
            pstmt.setString(1, cevaplayanIp);
            //Statement stmt = conn.createStatement();
            //stmt.executeQuery(sorgu);
            ResultSet rs = pstmt.executeQuery();
            rs.first();
            cevaplayanId = rs.getInt("CEVAPLAYAN_ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cevaplayanId;
    }
    public int cevaplayanEkle(Cevaplayanlar cevaplayan){
        String sorgu ="INSERT INTO `cevaplayanlar`( `CEVAPLAYAN_IP`, `GIRIS_TARIHI`) VALUES (?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sorgu);
            String ip = cevaplayan.getCevaplayan_ip();
            String girisTarihi = cevaplayan.getGiris_tarihi();
            pstmt.setString(1, ip);
            pstmt.setString(2, girisTarihi);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int cevaplayanId = cevaplayanIpyeGoreIdGetir(cevaplayan.getCevaplayan_ip());
        return cevaplayanId;
    }
}
