package Pojo.DAO;

import Pojo.Pagamenti;
import Pojo.Pensioni;
import Pojo.Quote;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Iterator;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class QuoteDao extends GenericDao {
    public QuoteDao() {
        super();
    }

    public ObservableList<Quote> getAll() {
        return findAllObservableList(Quote.class);
    }

    public Quote CreaQuota(Socio socio,LocalDate data, float importo) {
        Quote quota = new Quote();
        quota.setSocio(socio);
        quota.setData(data);
        quota.setImporto(importo);
        this.save(quota);
        return quota;
    }

    public void save(Quote l) {
        if (validaQuote(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Quote l) {
        super.delete(l);
    }

    public boolean isPersistente(Quote l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaQuote(Quote l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        //eventuali controlli
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
}

