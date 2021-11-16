package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kviz {
    private String naziv;
    private ArrayList<Pitanje> pitanja = new ArrayList<>();
    private SistemBodovanja sistemBodovanja;
    int brojPitanja = 0;

    public Kviz(String naziv, SistemBodovanja sistemBodovanja) {
        this.naziv = naziv;
        this.sistemBodovanja = sistemBodovanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(ArrayList<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }
    public void dodajPitanje(Pitanje p) {
        for (Pitanje vr : pitanja) {
            if (vr.equals(p)) {//provjera da li pitanje postoji
                throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji"); //bacit ce izuzetak ukoliko ima isto pitanje
            }
        }
        pitanja.add(p); //u kolekciju pitanja dodajemo pitanje
        brojPitanja++;
    }

    @Override
    public String toString() {
        String s = ""; //prazan string
        s = s + "Kviz " + "\"" + naziv + "\"" + " boduje se " + sistemBodovanja + "." + " Pitanja:";
        int i = 0;
        for (Pitanje p : pitanja) {
            s = s + "\n"  + (i+1) + ". " + p.getTekst() + "(" + p.getBrojPoena() + "b)";
            for (String odg : p.getOdgovori().keySet()) {
                s = s + "\n" + "\t" + odg + ": " + p.getTekst().toString();
            }
            i++;
            if (i != brojPitanja) s = s + "\n";

        }
        return s;
    }
    public RezultatKviza predajKviz(Map<Pitanje, ArrayList<String>> odg) {
        RezultatKviza rezultat = new RezultatKviza(this);
        Map<Pitanje, Double> b = new HashMap<>();
        double ukupno = 0;
        for (Pitanje p : pitanja) {
            Double x = 0.0;
            for (HashMap.Entry<Pitanje,ArrayList<String>> y : odg.entrySet()) {
                if (y.getKey().equals(p)) {//ukoliko to pitanje postoji
                    x = p.izracunajPoene(y.getValue(), sistemBodovanja);
                    break;
                }
            }
            b.put(p, x);//za pitanje p broj bodova x
            ukupno = ukupno + x; //sabiranje za ukupan broj bodova
        }
        rezultat.setBodovi(b);
        rezultat.setTotal(ukupno);
        return rezultat;
    }


}
