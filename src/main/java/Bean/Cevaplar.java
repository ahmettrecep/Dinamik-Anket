package Bean;

public class Cevaplar {
    int cevap_id;
    int cevaplayan_id;
    String form_id;
    int soru_id;
    String cevap;

    public Cevaplar(String cevap) {
        this.cevap = cevap;
    }


    public Cevaplar(int cevap_id, int cevaplayan_id, String form_id, int soru_id, String cevap) {
        this.cevap_id = cevap_id;
        this.cevaplayan_id = cevaplayan_id;
        this.form_id = form_id;
        this.soru_id = soru_id;
        this.cevap = cevap;
    }

    public Cevaplar() {

    }

    public int getCevap_id() {
        return cevap_id;
    }

    public void setCevap_id(int cevap_id) {
        this.cevap_id = cevap_id;
    }

    public int getCevaplayan_id() {
        return cevaplayan_id;
    }

    public void setCevaplayan_id(int cevaplayan_id) {
        this.cevaplayan_id = cevaplayan_id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public void setSoru_id(int soru_id) {
        this.soru_id = soru_id;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }
}
