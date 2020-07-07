package Bean;

public class soruBilgisi {
    String anketinIsmi;
    String sorununKendisi;
    String soruSayisi;
    int kacinciSoru;

    public String getAnketinIsmi() {
        return anketinIsmi;
    }

    public void setAnketinIsmi(String anketinIsmi) {
        this.anketinIsmi = anketinIsmi;
    }

    public String getSorununKendisi() {
        return sorununKendisi;
    }

    public void setSorununKendisi(String sorununKendisi) {
        this.sorununKendisi = sorununKendisi;
    }

    public String getSoruSayisi() {
        return soruSayisi;
    }

    public void setSoruSayisi(String soruSayisi) {
        this.soruSayisi = soruSayisi;
    }

    public int getKacinciSoru() {
        return kacinciSoru;
    }

    public void setKacinciSoru(int kacinciSoru) {
        this.kacinciSoru = kacinciSoru;
    }

    public soruBilgisi(){

    }
    public soruBilgisi(String anketinIsmi, String sorununKendisi, String soruSayisi, int kacinciSoru) {
        super();
        this.anketinIsmi = anketinIsmi;
        this.sorununKendisi = sorununKendisi;
        this.soruSayisi = soruSayisi;
        this.kacinciSoru = kacinciSoru;
    }
}
