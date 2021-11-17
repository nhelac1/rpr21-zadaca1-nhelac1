package ba.unsa.etf.rpr;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        Kviz kz = new Kviz("KVIZ ZNANJA!", SistemBodovanja.PARCIJALNO);

        Pitanje p1 = new Pitanje("Koji je glavni grad Irske ? ",20);
        p1.dodajOdgovor("1.", "New Delhi",false);
        p1.dodajOdgovor("2.","Talin", false);
        p1.dodajOdgovor("3.","Lisabon",false);
        p1.dodajOdgovor("4.","Ništa od ponuđenog", true);
        kz.dodajPitanje(p1);

        Pitanje p2 = new Pitanje("Kada je zavrsio II svjetski rat ? ",20);
        p2.dodajOdgovor("1.","SEPT - 1945", true);
        p2.dodajOdgovor("2.","SEPT - 1946",false);
        p2.dodajOdgovor("3.","OKT - 1945",false);
        p2.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p2);

        Pitanje p3 = new Pitanje("Gdje se nalazi najmanja kost u ljudskom tijeli ? ",20);
        p3.dodajOdgovor("1.","U malom prstu",false);
        p3.dodajOdgovor("2.","U uhu", true);
        p3.dodajOdgovor("3.","U nosu", false);
        p3.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p3);

        Pitanje p4 = new Pitanje("U kojem okeanu je potonuo Titanic ? ",20);
        p4.dodajOdgovor("1.","Indijski okean",false);
        p4.dodajOdgovor("2.","Tihi okean", false);
        p4.dodajOdgovor("3.","Atlantski okean", true);
        p4.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p4);

        Pitanje p5 = new Pitanje("Kako se naziva strah od računara ? ",20);
        p5.dodajOdgovor("1.","Aklufobija",false);
        p5.dodajOdgovor("2.","Didaskaleinofobija", false);
        p5.dodajOdgovor("3.","Cyberfobija", true);
        p5.dodajOdgovor("4.","Ništa od ponuđenog", false);
        kz.dodajPitanje(p5);

        igrajKviz(kz);
    }
    public static void igrajKviz(Kviz kviz){
        try {
            System.out.println("SRETNO !!!");
            Map<Pitanje, ArrayList<String>> k= new HashMap<>();
            for (Pitanje pitanje : kviz.getPitanja()) {
                ArrayList<String> odgovor = new ArrayList<>();
                System.out.println(pitanje);
                System.out.println("\nUnesite broj Vašeg odgovore, te potom 0 na prelazak na iduće pitanje: ");
                for (; ;) {
                    Scanner ulaz = new Scanner(System.in);
                    String odg = ulaz.nextLine();
                    if (odg.equals("0"))
                        break;
                    else odgovor.add(odg);
                }
                k.put(pitanje, odgovor);
            }
            RezultatKviza rezultat = kviz.predajKviz(k);
            System.out.println("\nKviz je završen! Osvojili te " + rezultat + " / 100 poena, sto predstavlja " + rezultat +
                    "% od ovog kviza. Hvala na ucestvovanju!");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

