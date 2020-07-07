package DAO;

import Bean.Anket;
import Bean.Sorular;
import Util.Utilization;

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
    public AnketDAO() {
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
     * @param soru_id
     */
    public void anketeSoruEkle(int soru_id){
        anket.sorular.add(soru_id);
    }

    /*
        Sorular, Anket sınıfındaki sorular listesine eklenir ve formu oluştur dendiğinde
        bu sorular, veritabanının sorular tablosuna kayıt edilir.
     */
    public void formuOlustur(){
        try {
            for(int i=0;i<anket.sorular.size();i++){
                stmt.executeQuery("INSERT INTO `anket`(`SORU_ID`) VALUES (" + anket.sorular.get(i) + ")");
            }
        } catch (SQLException e) {
            System.out.println("DAO.AnketDAO.formuOlustur() Metodunda Hata!");
        }
    }
    public void formdanSoruSil(List<Integer> silinecekSorular){
        for(int i=0;i<silinecekSorular.size();i++){
            try {
                stmt.executeQuery("DELETE FROM `anket` WHERE SORU_ID= " + silinecekSorular.get(i) + ")");
            } catch (SQLException e) {
                System.out.println("DAO.formdanSoruSil Metodunda Hata!");
            }
        }
    }

    /**
     *
     * @param formId
     * @param sorular
     * @param anketIsmi
     */
    public void soruIdleriFormTablosunaEkle(String formId, List<Sorular> sorular,String anketIsmi){
        String sorgu1 = "SELECT SORU_ID FROM SORULAR WHERE KAYITLI_OLDUGU_FORM=" + "'" + formId + "'";
        try {
            ResultSet soruIdRs = stmt.executeQuery(sorgu1);
            String sorgu2 = "INSERT INTO `anket`(`FORM_ID`, `SORU_ID`, `FORM_ADI`) VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sorgu2);
            int i = 0;
            while(soruIdRs.next()){
                    int s = soruIdRs.getInt("SORU_ID");
                    pstmt.setString(1,formId);
                    pstmt.setInt(2,s);
                    pstmt.setString(3,anketIsmi);
                    pstmt.executeUpdate();

            }
            /*for(int i=0;i<sorular.size();i++){
                String Id = soruIdRs.next().;
            }*/


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param formId
     * @return
     */
    public List<Sorular> sorularıBirlestir(int formId){
        ResultSet rs = hazirlananFormaAitSorularıGetir(formId);
        List<Sorular> sorular = soruTablosundanSorularıGetir(rs);
        return sorular;
    }
    public ResultSet hazirlananFormaAitSorularıGetir(int formId){
        String sql = "SELECT SORU_ID FROM ANKET";
        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(sql);
            } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public List<Sorular> soruTablosundanSorularıGetir(ResultSet rs){

        List<Sorular> sorular = new LinkedList<Sorular>();
        Sorular soru = null;
        Statement stmt2;
        ResultSet rs2 = null;
        try{
            stmt2 = conn.createStatement();
            while(rs.next()){
                rs2 = stmt2.executeQuery("select * from sorular where id=" + rs.getInt("SORU_ID"));
            }
            while(rs2.next()){
                /*soru = new Sorular(rs2.getInt("SORU_ID"), rs2.getString("SORU_TIPI"),rs2.getInt("SORUNUN_SIRASI"),
                        rs2.getInt("KAYITLI_OLDUGU_FORM"),rs2.getString("SECENEKLER"), rs2.getString("SORU_GOVDESI"));
                sorular.add(soru);*/
            }
        }
        catch(SQLException ex){
            System.out.println("soruTablosundanSorularıGetir() Metodunda Hata!");
        }
        return sorular;
    }

    public String formIdOlustur(){
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
        Date dateobj = new Date();
        return df.format(dateobj);
    }


}
