package ba.unsa.etf.rpr;

import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Kviz kz = new Kviz("KVIZ ZNANJA!", SistemBodovanja.PARCIJALNO);

        Pitanje p1 = new Pitanje("Koji je glavni grad Irske",5);
        p1.dodajOdgovor("1.", "New Delhi",false);
        p1.dodajOdgovor("2.","Talin", false);
        p1.dodajOdgovor("3.","Lisabon",false);
        p1.dodajOdgovor("4.","Ništa od ponuđenog", true);
        kz.dodajPitanje(p1);

        Pitanje p2 = new Pitanje("Kada je zavrsio II svjetski rat =",5);
        p2.dodajOdgovor("1.","SEPT - 1945", true);
        p2.dodajOdgovor("2.","SEPT - 1946",false);
        p2.dodajOdgovor("3.","OKT - 1945",false);
        p2.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p2);

        Pitanje p3 = new Pitanje("Gdje se nalazi najmanja kost u ljudskom tijeli?",5);
        p3.dodajOdgovor("1.","U malom prstu",false);
        p3.dodajOdgovor("2.","U uhu", true);
        p3.dodajOdgovor("3.","U nosu", false);
        p3.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p3);

        Pitanje p4 = new Pitanje("U kojem okeanu je potonuo Titanic?",5);
        p4.dodajOdgovor("1.","Indijski okean",false);
        p4.dodajOdgovor("2.","Tihi okean", false);
        p4.dodajOdgovor("3.","Atlantski okean", true);
        p4.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p4);

        igrajKviz(kz);
    }
    public static void igrajKviz(Kviz kviz){
        try {
            Map<Pitanje, ArrayList<String>> k= new HashMap<>();
            for (Pitanje pitanje : kviz.getPitanja()) {
                ArrayList<String> odgovor = new ArrayList<>();
                System.out.println(pitanje);
                System.out.println("\nUnesite Vaše odgovore odvojene sa ENTER(0 za kraj): ");
                for (; ; ) {
                    Scanner ulaz = new Scanner(System.in);
                    String odg = ulaz.nextLine();
                    if (odg.equals("0")) break;
                    else odgovor.add(odg);
                }
                k.put(pitanje, odgovor);
            }
            RezultatKviza rezultat = kviz.predajKviz(k);
            System.out.println("\n Kviz je završen, a broj Vaših bodova iznosi "+ rezultat + "/20.");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

