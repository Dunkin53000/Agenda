package com.moovin.agenda;

/**
 * Created by pierre on 13/09/2015.
 */
public class Card {

    private String line1;
    private String first;

    public Card(String line1,String first) {
        this.line1 = line1;
        this.first = first;

    }

    public String getLine1() {
        return line1;
    }


    public String getFirstLetter() {
        return first;
    }

}
