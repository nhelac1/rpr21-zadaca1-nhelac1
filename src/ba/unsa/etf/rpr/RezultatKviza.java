package ba.unsa.etf.rpr;

import java.util.HashMap;
import java.util.Map;

public class RezultatKviza {
    private Kviz kviz;
    private double total; //ukupan broj bodova
    private Map<Pitanje, Double> bodovi = new HashMap<>();;

    public RezultatKviza(Kviz kviz) {
        this.kviz = kviz;
        this.total = 0;
    }

    public Kviz getKviz() {
        return kviz;
    }

    public void setKviz(Kviz kviz) {
        this.kviz = kviz;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<Pitanje, Double> getBodovi() {
        return bodovi;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public String toString() {
        String s = "" ;
        s = s + ("Na kvizu \"" + kviz + "\" ostvarili ste ukupno " + total + "." + " poena. Raspored po pitanjima:");
        for (Map.Entry<Pitanje, Double> b : bodovi.entrySet()) {
            s = s + "\n" + b.getKey().getTekst() + " - " + b.getValue().toString() + "b";
        }
        return s;
    }
}
