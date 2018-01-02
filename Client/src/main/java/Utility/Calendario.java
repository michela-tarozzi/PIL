package Utility;

import java.time.LocalDate;

/**
 * Created by Michela on 02/01/2018.
 */
public class Calendario {
    public static String tornaMeseIta(LocalDate data){
        String mese="";
        switch(data.getMonthValue()){
            case 1: mese="GENNAIO";break;
            case 2: mese="FEBBRAIO";break;
            case 3: mese="MARZO";break;
            case 4: mese="APRILE";break;
            case 5: mese="MAGGIO";break;
            case 6: mese="GIUGNO";break;
            case 7: mese="LUGLIO";break;
            case 8: mese="AGOSTO";break;
            case 9: mese="SETTEMBRE";break;
            case 10: mese="OTTOBRE";break;
            case 11: mese="NOVEMBRE";break;
            case 12: mese="DICEMBRE";break;
        }
        return mese;
    }
}
