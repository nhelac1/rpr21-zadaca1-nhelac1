package ba.unsa.etf.rpr;
import java.util.ArrayList;
import java.util.HashMap;


public class Pitanje {
    private String tekst;
    private double brojPoena;
    private HashMap<String, Odgovor> odgovori = new HashMap<>(); //kolekcija odgovora incijalizovana na praznu mapu
    private int brojOdgovora = 0;

    public Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
    }

    //geteri i seteri
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public HashMap<String, Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(HashMap<String, Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    public int getBrojOdgovora() {
        return brojOdgovora;
    }

    public void setBrojOdgovora(int brojOdgovora) {
        this.brojOdgovora = brojOdgovora;
    }

    public void dodajOdgovor(String id, String tekst, boolean tacno) {
        if (odgovori.containsKey(id)) //provjera da li id postoji
            throw new IllegalArgumentException("Id odgovora mora biti jedinstven"); //bacit ce izuzetak ukoliko ima vec postojeci id
        Odgovor o = new Odgovor(tekst, tacno);
        odgovori.put(id, o); //(a,10),(b,2) - u kolekciju odgovora dodajemo odgovor sa tim id
        brojOdgovora = brojOdgovora + 1; //broj odgovora se povecava jer je id ispravan
    }

    public void obrisiOdgovor(String id) {
        if (!odgovori.containsKey(id)) //provjera da li id ne postoji
            throw new IllegalArgumentException("Odgovor s ovim id-em ne postoji");
        odgovori.remove(id); //brisanje tog id ako postoji
        brojOdgovora = brojOdgovora - 1; //jer je doslo do brisanja odgovora pod nekim id
    }

    public ArrayList<Odgovor> dajListuOdgovora() {
        ArrayList<Odgovor> lista = new ArrayList<>(); //kolekcija na prazno
        for (Odgovor o : odgovori.values()) {
            lista.add(o); //dodavanje odgovora(tekst) u listu
        }
        return lista; //vracamo listu samo sa odgovorima
    }

    @Override
    public String toString() {
        String s = ""; //prazan string
        s = s + tekst + "(" + brojPoena + "b)";
        for (String id : odgovori.keySet()) {
            s = s + "\n" + "\t" + id + ": " + odgovori.get(id).getTekstOdgovora(); //tacan odgovor
        }
        return s;
    }
    public double izracunajPoene(ArrayList<String> idevi, SistemBodovanja sb) { //lista id-eva

        boolean provjera = false; //za pretragu
        for (String i : idevi) {
            for (String o : odgovori.keySet()){ //id je tipa string
                if (i == o) provjera = true;
            }
            if (!provjera) //provjera == false
                throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
            provjera = false;
        }

        int brojac = 0;
        for (String i : idevi) {
            for (String o : odgovori.keySet()){ //id je tipa string
                if (i == o) brojac++;
            }
            if (brojac > 1) //provjera == false
                throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");
            brojac = 0;
        }

        int Tacnih = 0, Zaokruzenih = 0;
        double poeni = 0;
        if (sb.equals(SistemBodovanja.BINARNO)) {
            if (Tacnih != idevi.size())
                return 0;
            else {
                for (int i = 0; i< idevi.size(); i++) {
                    if (!odgovori.get(idevi.get(i)).isTacno())
                        return 0;
                }
                poeni = brojPoena;
            }
        }
        if (sb.equals(SistemBodovanja.PARCIJALNO)) {
            for (int i = 0; i < idevi.size(); i++) {
                if (!odgovori.get(idevi.get(i)).isTacno())
                    return 0;
            }
            if (Tacnih == idevi.size())
                return brojPoena;
            else
                return (brojPoena/odgovori.size())* idevi.size();
        }
        if (sb.equals(SistemBodovanja.PARCIJALNO_SA_NEGATIVNIM)) {
            int T = 0, N = 0;
            for (int i = 0; i <idevi.size(); i++) {
                if (odgovori.get(idevi.get(i)).isTacno())
                    T++;
            }
            for (int i = 0; i <idevi.size(); i++) {
                if (!odgovori.get(idevi.get(i)).isTacno())
                N++;
            }
            if (T == odgovori.size() && N == 0)
                return brojPoena;
            else if (N != 0)
                return -(brojPoena/2.);
            else
                return (brojPoena/odgovori.size())*T;
        }
        return 0;
    }

}
