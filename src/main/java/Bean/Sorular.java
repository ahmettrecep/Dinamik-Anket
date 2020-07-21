package Bean;

public class Sorular {
    int soru_id;
    String soru_tipi;
    int sorunun_sirasi;
    String kayitli_oldugu_form;
    String secenekler;
    String soruGovdesi;

    public Sorular(int soru_id, String soru_tipi, int sorunun_sirasi, String kayitli_oldugu_form, String secenekler, String soruGovdesi) {
        this.soru_id = soru_id;
        this.soru_tipi = soru_tipi;
        this.sorunun_sirasi = sorunun_sirasi;
        this.kayitli_oldugu_form = kayitli_oldugu_form;
        this.secenekler = secenekler;
        this.soruGovdesi = soruGovdesi;
    }

    public String getSoruGovdesi() {
        return soruGovdesi;
    }


    public Sorular() {
    }

    public Sorular(String soru_tipi, int sorunun_sirasi, String secenekler, String soruGovdesi) {
        this.soruGovdesi = soruGovdesi;
        this.soru_tipi = soru_tipi;
        this.sorunun_sirasi = sorunun_sirasi;
        this.secenekler = secenekler;
    }

    public int getSoru_id() {
        return soru_id;
    }

    public void setSoru_id(int soru_id) {
        this.soru_id = soru_id;
    }

    public String getSoru_tipi() {
        return soru_tipi;
    }

    public void setSoru_tipi(String soru_tipi) {
        this.soru_tipi = soru_tipi;
    }

    public int getSorunun_sirasi() {
        return sorunun_sirasi;
    }

    public void setSorunun_sirasi(int sorunun_sirasi) {
        this.sorunun_sirasi = sorunun_sirasi;
    }

    public String getKayitli_oldugu_form() {
        return kayitli_oldugu_form;
    }

    public void setKayitli_oldugu_form(String kayitli_oldugu_form) {
        this.kayitli_oldugu_form = kayitli_oldugu_form;
    }

    public String getSecenekler() {
        return secenekler;
    }

    public void setSecenekler(String secenekler) {
        this.secenekler = secenekler;
    }
}