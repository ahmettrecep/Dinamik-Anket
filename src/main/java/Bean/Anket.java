package Bean;

import java.util.LinkedList;
import java.util.List;

public class Anket {
    int form_id;
    public List<Integer> sorular = new LinkedList<Integer>();

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public List<Integer> getSorular() {
        return sorular;
    }

    public void setSorular(List<Integer> sorular) {
        this.sorular = sorular;
    }

}
