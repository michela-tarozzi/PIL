package Pojo.DAO;

import Pojo.Socio;
import Pojo.Spese;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class SpeseDao extends GenericDao {
    public SpeseDao() {
        super();
    }

    public ObservableList<Spese> getAll() {
        return findAllObservableList(Spese.class);
    }
    public Spese CreaSpesa(Socio socio, String numero, LocalDate data, float importo) {
        Spese spesa = new Spese();
        spesa.setSocio(socio);
        spesa.setNumero(numero);
        spesa.setData(data);
        spesa.setImporto(importo);
        this.save(spesa);
        return spesa;
    }

    public void save(Spese l) {
        if (validaSpesa(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Spese l) {
        super.delete(l);
    }

    public boolean isPersistente(Spese l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaSpesa(Spese l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getNumero() == null || l.getNumero().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
        }
        if (l.getData() == null || l.getData().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
        }
        if (l.getImporto() <= 0) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CODICEFISCALE_NULLO;
        }
        if (numero_campi_invalidi == 1) {
            throw new SystemExceptionRefactor(ultimoErrore, ExceptionCode.getValidazioneCode());
        } else if (numero_campi_invalidi > 1) {
            throw new SystemExceptionRefactor(ErrorLabel.CAMPI_OBBLIGATORI_LAVORATORE, ExceptionCode.getValidazioneCode());
        }
        return valido;
    }

    public void chiudiSessione() {
        closeSession();
    }

    public ObservableList<Spese> getSpeseNonRimborsate() {
        ObservableList<Spese> tutte=this.getAll();
        ObservableList<Spese> nonRimborsate= FXCollections.observableArrayList();
        Iterator<Spese> it=tutte.iterator();
        while(it.hasNext())
        {
            Spese spesa=it.next();
            if (!(spesa.getRimborsi()==null)){
                nonRimborsate.add(spesa);
            }
        }
        return nonRimborsate;
    }
}

