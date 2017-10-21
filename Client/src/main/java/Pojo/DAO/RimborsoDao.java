package Pojo.DAO;

import Pojo.RegoleRimborsi;
import Pojo.Rimborsi;
import Pojo.Socio;
import Pojo.Spese;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class RimborsoDao extends GenericDao {
    public RimborsoDao() {
        super();
    }

    public ObservableList<Rimborsi> getAll() {
        return findAllObservableList(Rimborsi.class);
    }

    public ObservableList<Rimborsi> getRimborsiDaPagare() {

        ObservableList<Rimborsi> tutti=getAll();
        ObservableList<Rimborsi> daPagare= FXCollections.observableArrayList();
        Iterator<Rimborsi> it=tutti.iterator();
        int i=0;
        while(it.hasNext())
        {
            Rimborsi rimborso=it.next();
            if (rimborso.getPagamento()==null){
                daPagare.add(rimborso);
            }
            i++;
        }
        return daPagare;
    }

    public Rimborsi CreaRimborso(LocalDate data, Float importo, Float importoSpesa) {
        Rimborsi rimborso = new Rimborsi();
        rimborso.setData(data);
        rimborso.setImporto(importo);
        rimborso.setImportoSpesa(importoSpesa);
        this.save(rimborso);
        return rimborso;
    }


    public void save(Rimborsi l) {
        if (validaRimborsi(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Rimborsi l) {
        super.delete(l);
    }

    public boolean isPersistente(Rimborsi l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaRimborsi(Rimborsi l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        //controlli
        if (numero_campi_invalidi == 1) {
            throw new SystemExceptionRefactor(ultimoErrore, ExceptionCode.getValidazioneCode());
        } else if (numero_campi_invalidi > 1) {
            throw new SystemExceptionRefactor(ErrorLabel.CAMPI_OBBLIGATORI_LAVORATORE, ExceptionCode.getValidazioneCode());
        }
        return valido;
    }

    public int GetRicorrenzaRimborso(RegoleRimborsi regola, Socio socio)
    {
        ObservableList<Rimborsi> rimborsi= this.getAll();
        int conta=0;
        Iterator<Rimborsi> it=rimborsi.iterator();
        while (it.hasNext()){
            Rimborsi rimborso=it.next();
            Rimborsi rimborso2= (Rimborsi) get(Rimborsi.class, rimborso.getId());
            SpeseDao speseDao=new SpeseDao();
            //Spese spesa=speseDao.
            if (rimborso.getRegola().getId().equals(regola.getId()) && rimborso.getData().getYear()== LocalDate.now().getYear()// && rimborso.getSpesa().getSocio()==socio
                    )
            {conta++;}
        }
        return conta;
    }

    public void chiudiSessione() {
        closeSession();
    }

    public float getTotSocioAnno(RegoleRimborsi regola, Socio socio) {
        ObservableList<Rimborsi> rimborsi= this.getAll();
        float somma=0;
        Iterator<Rimborsi> it=rimborsi.iterator();
        while (it.hasNext()){
            Rimborsi rimborso=it.next();
            if (rimborso.getRegola()==regola && rimborso.getData().getYear()== LocalDate.now().getYear() && rimborso.getSpesa().getSocio()==socio)
            {somma=somma+rimborso.getImporto();}
        }
        return somma;
    }
}

