package DAO;

import Bean.Anket;
import Bean.Sorular;
import Util.Utilization;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AnketDAO {
    Connection conn;
    Anket anket;
    Statement stmt;
    public List<Sorular> soruListesi;
    public AnketDAO() {
        soruListesi = new LinkedList<Sorular>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");

            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param formId
     * @param sorular
     * @param anketIsmi
     */
    public void soruIdleriFormTablosunaEkle(String formId, List<Sorular> sorular,String anketIsmi) throws SQLException {

        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");
        String sorgu1 = "select SORU_ID FROM SORULAR WHERE KAYITLI_OLDUGU_FORM = ?;";
        try {
            PreparedStatement pstmt2 = conn2.prepareStatement(sorgu1);
            pstmt2.setString(1, formId);
            System.out.println("PreparedStatement2'nin içeriği : " + pstmt2);
            ResultSet soruIdRs = pstmt2.executeQuery();
            int i = 0;
            while(soruIdRs.next()){
                    String sorgu2 = "INSERT INTO `anket`(`FORM_ID`, `SORU_ID`, `FORM_ADI`) VALUES (?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sorgu2);
                    int s = soruIdRs.getInt("SORU_ID");
                    pstmt.setString(1, formId);
                    pstmt.setInt(2, s);
                    pstmt.setString(3, anketIsmi);
                    pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String formIdOlustur(){
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
        Date dateobj = new Date();
        return df.format(dateobj);
    }

    /**
     *
     * @param formId
     * @return
     * @throws SQLException
     */
    public List<Sorular> sorularıBirlestir(String formId) throws SQLException {
        ResultSet rs = hazirlananFormaAitSorularıGetir(formId);
        soruTablosundanSorularıGetir(rs);
        return this.soruListesi;
    }

    /**
     *
     * @param formId
     * @return
     * @throws SQLException
     */
    public ResultSet hazirlananFormaAitSorularıGetir(String formId) throws SQLException {
        String sql = "SELECT SORU_ID FROM ANKET WHERE FORM_ID = ?";
        ResultSet resultSet = null;
        PreparedStatement pstmt;
        //Statement stmt2;
        Connection conn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");
        try {
            pstmt = conn2.prepareStatement(sql);
            pstmt.setString(1, formId);
            //stmt2 = conn2.createStatement();
            resultSet = pstmt.executeQuery();
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     *
     * @param rs
     * @throws SQLException
     */
    public void soruTablosundanSorularıGetir(ResultSet rs) throws SQLException {
        PreparedStatement pstmt;
        //Statement stmt2;
        ResultSet rs2;
        Connection conn2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");
        try{
            // stmt2 = conn2.createStatement();
            rs.beforeFirst();
            while(rs.next()){
                int soruno = rs.getInt("SORU_ID");
                pstmt = conn2.prepareStatement("SELECT * FROM SORULAR WHERE SORU_ID = ?");
                pstmt.setInt(1, soruno);
                rs2 = pstmt.executeQuery();
                //rs2 = stmt2.executeQuery("select * from sorular where SORU_ID=" + soruno);
                soruyuListeyeEkle(rs2);
            }

        }
        catch(SQLException ex){
            System.out.println("soruTablosundanSorularıGetir() Metodunda Hata!");
        }
    }

    /**
     *
     * @param rs2
     */
    public void soruyuListeyeEkle(ResultSet rs2){
        Sorular soru = new Sorular();
        try {
            rs2.beforeFirst();
            while(rs2.next()){
                soru = new Sorular(rs2.getInt("SORU_ID"), rs2.getString("SORU_TIPI"),rs2.getInt("SORUNUN_SIRASI"),
                        rs2.getString("KAYITLI_OLDUGU_FORM"),rs2.getString("SECENEKLER"), rs2.getString("SORU_GOVDESI"));
                this.soruListesi.add(soru);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
