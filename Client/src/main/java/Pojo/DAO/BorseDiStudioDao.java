package Pojo.DAO;

import Pojo.BorseDiStudio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.util.Date;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class BorseDiStudioDao extends GenericDao {
    public BorseDiStudioDao() {
        super();
    }

    public ObservableList<BorseDiStudio> getAll() {
        return findAllObservableList(BorseDiStudio.class);
    }

    public BorseDiStudio CreaBorsaDiStudio(String figlio, int anno, int cfu, String iban, float lordo, float netto, float ritenuta) {
        BorseDiStudio bds = new BorseDiStudio();
        bds.setFiglio(figlio);
        bds.setAnno(anno);
        bds.setCFU(cfu);
        bds.setIBAN(iban);
        bds.setLordo(lordo);
        bds.setNetto(netto);
        bds.setRitenuta(ritenuta);
        this.save(bds);
        return bds;
    }


    public void save(BorseDiStudio l) {
        if (validaBorsa(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(BorseDiStudio l) {
        super.delete(l);
    }

    public boolean isPersistente(BorseDiStudio l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaBorsa(BorseDiStudio l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getFiglio() == null || l.getFiglio().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
        }
        if (l.getAnno() <2000) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
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

