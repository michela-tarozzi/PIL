package Pojo.DAO;

import Pojo.AddizionaleComunale;
import Pojo.AddizionaleRegionale;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class AddizionaleRegionaleDao extends GenericDao {
    public AddizionaleRegionaleDao() {
        super();
    }

    public ObservableList<AddizionaleRegionale> getAll() {
        return findAllObservableList(AddizionaleRegionale.class);
    }

    public AddizionaleRegionale CreaAddizionaleRegionale(String regione, int anno, float sogliaMinima, float sogliaMassima, float aliquota) {
        AddizionaleRegionale ac = new AddizionaleRegionale();
        ac.setRegione(regione);
        ac.setAliquota(aliquota);
        ac.setAnno(anno);
        ac.setSogliaMassima(sogliaMassima);
        ac.setSogliaMinima(sogliaMinima);
        this.save(ac);
        return ac;
    }


    public void save(AddizionaleRegionale l) {
        if (validaAddizionaleRegionale(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(AddizionaleRegionale l) {
        super.delete(l);
    }

    public boolean isPersistente(AddizionaleRegionale l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaAddizionaleRegionale(AddizionaleRegionale l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getAliquota() <0) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
        }
        if (l.getAnno() <2000) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CODICEFISCALE_NULLO;
        }
        if (l.getRegione() == null || l.getRegione().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COMUNE_NULLO;
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

