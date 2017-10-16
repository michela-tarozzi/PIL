package Pojo.DAO;

import Pojo.Rimborsi;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.util.Date;


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

    public Rimborsi CreaRimborso(Date data, Float importo, Float importoSpesa) {
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

    public void chiudiSessione() {
        closeSession();
    }
}

