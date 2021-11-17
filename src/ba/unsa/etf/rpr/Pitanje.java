package ba.unsa.etf.rpr;
import java.util.*;


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


    public double izracunajPoene(List<String> idevi, SistemBodovanja sb) { //lista id-eva
        boolean provjera = false; //za pretragu
        for (String i : idevi) {
            for (String o : odgovori.keySet()) { //id je tipa string
                if (i == o) provjera = true;
            }
            if (provjera == false) //provjera == false
                throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
            provjera = false;
        }

        Set<String> set = new HashSet<>(idevi);
        if (set.size() != idevi.size())
            throw new IllegalArgumentException("Postoje duplikati među odabranim odgovorima");

        //Racunanje bodova po sistemu bodovanja
        int tacnih = 0, netacnih = 0, ukupno = 0;
        int tacnihZ = 0, netacnihZ = 0, ukupnoZ = 0;
        for (Map.Entry<String, Odgovor> odg : odgovori.entrySet()) {
            if (odg.getValue().isTacno()) {
                tacnih = tacnih + 1;
                if (idevi.contains(odg.getKey()))
                    tacnihZ = tacnihZ + 1;
            } else
                netacnih = netacnih + 1;
        }

            ukupno = tacnih + netacnih;
            ukupnoZ = idevi.size(); //zaokruzeni odgovori i tacni i netacni
            netacnihZ = ukupnoZ - tacnihZ;

            if (sb.equals(SistemBodovanja.BINARNO)) {
                if (tacnihZ == tacnih && netacnihZ == 0) //Ako je korisnik zaokruzio samo tacne odgovore
                    return brojPoena;
                else return 0.;
            } else if (sb.equals(SistemBodovanja.PARCIJALNO)) {
                if (tacnihZ == 0 && netacnihZ > 0)
                    return 0;
                else if (tacnihZ != tacnih && netacnihZ == 0)
                    return ((brojPoena/ukupno)*tacnihZ);
                else if (tacnihZ == tacnih && netacnihZ == 0)
                    return  brojPoena;
            } else if (sb.equals(SistemBodovanja.PARCIJALNO_SA_NEGATIVNIM)) {
                if (tacnihZ == tacnih && netacnihZ == 0)
                    return  brojPoena;
                else if (tacnihZ != tacnih && netacnihZ == 0)
                    return ((brojPoena/ukupno)*tacnihZ);
                else if (netacnihZ > 0)
                    return (-(brojPoena/2.));
            }
        return 0;
    }
}