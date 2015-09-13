package com.moovin.agenda;

/**
 * Created by pierre on 13/09/2015.
 */
public class CardJour {

    private String cours;
    private String horaire;
    private String salle;
    private String first;

    public CardJour(String cours, String horaire,String salle) {
        this.cours = cours;
        this.horaire = horaire;
        this.salle = salle;


    }

    public String getCours() {
        return cours;
    }
    public String getHoraire() {
        return horaire;
    }


    public String getSalle() {
        return salle;
    }


    public String getFirstLetter() {


        String test = cours;
        String s = test.substring(0,1);
        return s;
    }

}
