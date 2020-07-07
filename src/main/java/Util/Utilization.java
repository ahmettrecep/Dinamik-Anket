package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilization {
    public Connection baglantiOlustur(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/anket?useSSL=false", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Veritabanı Bağlantısı Yapılırken Hata!");
        }
        return conn;
    }
    private void baglantiyiKapat(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Veritabanı Bağlantısını Kapatırken Hata!");
        }
    }
}
