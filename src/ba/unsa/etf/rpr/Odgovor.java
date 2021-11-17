package ba.unsa.etf.rpr;

import java.util.Objects;

public class Odgovor {
    private String tekstOdgovora;
    private boolean tacno;

    public Odgovor(String tekstOdgovora, boolean tacno) { //konstruktor
        this.tekstOdgovora = tekstOdgovora;
        this.tacno = tacno;
    }

    public String getTekstOdgovora() {
        return tekstOdgovora;
    }
    public boolean isTacno() {
        return tacno;
    }
    public void setTekstOdgovora(String tekstOdgovora) {
        this.tekstOdgovora = tekstOdgovora;
    }
    public void setTacno(boolean tacno) {
        this.tacno = tacno;
    }

    @Override
    public boolean equals(Object o) { //zbog HashMape za neprimitivni tip (kada su 2 odgovora jednaka)
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odgovor odgovor = (Odgovor) o;
        return tacno == odgovor.tacno && Objects.equals(tekstOdgovora, odgovor.tekstOdgovora);
    }

}
