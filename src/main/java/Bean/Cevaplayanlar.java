package Bean;

public class Cevaplayanlar {
    int cevaplayan_id;
    String cevaplayan_ip;
    String giris_tarihi;

    public Cevaplayanlar(int cevaplayan_id, String cevaplayan_ip, String giris_tarihi) {
        this.cevaplayan_id = cevaplayan_id;
        this.cevaplayan_ip = cevaplayan_ip;
        this.giris_tarihi = giris_tarihi;
    }

    public int getCevaplayan_id() {
        return cevaplayan_id;
    }

    public void setCevaplayan_id(int cevaplayan_id) {
        this.cevaplayan_id = cevaplayan_id;
    }

    public String getCevaplayan_ip() {
        return cevaplayan_ip;
    }

    public void setCevaplayan_ip(String cevaplayan_ip) {
        this.cevaplayan_ip = cevaplayan_ip;
    }

    public String getGiris_tarihi() {
        return giris_tarihi;
    }

    public void setGiris_tarihi(String giris_tarihi) {
        this.giris_tarihi = giris_tarihi;
    }
}
