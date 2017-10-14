package Pojo.DAO;

import Pojo.Comune;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class ComuneDao extends GenericDao {
    public ComuneDao() {
        super();
    }

    public ObservableList<Comune> getAll() {
        return findAllObservableList(Comune.class);
    }

    public Comune CreaBorsaDiStudio(String codiceCatastale,  String nome) {
        Comune bds = new Comune();
        bds.setCodiceCatastale(codiceCatastale);
        bds.setNome(nome);
        this.save(bds);
        return bds;
    }


    public void save(Comune l) {
        if (validaBorsa(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Comune l) {
        super.delete(l);
    }

    public boolean isPersistente(Comune l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaBorsa(Comune l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getCodiceCatastale() == null || l.getCodiceCatastale().equals("")) {
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

