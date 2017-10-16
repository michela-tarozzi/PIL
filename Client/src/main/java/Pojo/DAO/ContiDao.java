package Pojo.DAO;

import Pojo.Conti;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class ContiDao extends GenericDao {
    public ContiDao() {
        super();
    }

    public ObservableList<Conti> getAll() {
        return findAllObservableList(Conti.class);
    }

    public Conti CreaConto(String numero, String descrizione) {
        Conti bds = new Conti();
        bds.setNumero(numero);
        bds.setDescrizione(descrizione);
        this.save(bds);
        return bds;
    }


    public void save(Conti l) {
        if (validaBorsa(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Conti l) {
        super.delete(l);
    }

    public boolean isPersistente(Conti l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaBorsa(Conti l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getNumero() == null || l.getNumero().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
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

