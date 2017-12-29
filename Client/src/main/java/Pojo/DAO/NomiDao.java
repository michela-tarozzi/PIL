package Pojo.DAO;

import Pojo.NomiSoci;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by m.tarozzi on 21/12/2017.
 */
public class NomiDao extends GenericDao  {



    public ObservableList<NomiDao> getAll() {
        return findAllObservableList(Socio.class);
    }

    public ObservableList<NomiSoci> getSociMutuatest(){return getSociMutua();}

    public void save(Socio l) {
        if (validaSocio(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Socio l) {
        super.delete(l);
    }

    public boolean isPersistente(Socio l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaSocio(Socio l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getNome() == null || l.getNome().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
        }
        if (l.getCognome() == null || l.getCognome().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
        }
        if (l.getCF() == null || l.getCF().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CODICEFISCALE_NULLO;
        }
        if (l.getComune() == null || l.getComune().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COMUNE_NULLO;
        }
        if (l.getCitta() == null || l.getCitta().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CITTA_NULLO;
        }
        if (l.getIndirizzo() == null || l.getIndirizzo().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_INDIRIZZO_NULLO;
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
}
