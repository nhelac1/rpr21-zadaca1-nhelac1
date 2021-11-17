package ba.unsa.etf.rpr;

public enum SistemBodovanja {
    BINARNO, PARCIJALNO, PARCIJALNO_SA_NEGATIVNIM;

    public String MalaSlova() {
       return switch (this) {
           case PARCIJALNO -> "parcijalno";
           case BINARNO -> "binarno";
           case PARCIJALNO_SA_NEGATIVNIM -> "parcijalno sa negativnim bodovima";
       };
    }

}
