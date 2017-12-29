package Pojo;

import javax.persistence.*;

/**
 * Created by m.tarozzi on 13/12/2017.
 */

public class NomiSoci {

    private String nome;
    private String cognome;

    public NomiSoci(){}

    public NomiSoci(String nome, String cognome){
        this.nome=nome;
        this.cognome=cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    @Override
    public String toString() {
        return cognome+ " "+ nome;
    }

}
