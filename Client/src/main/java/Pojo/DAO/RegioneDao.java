package Pojo.DAO;


import Pojo.Regioni;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class RegioneDao extends GenericDao {
    public RegioneDao() {
        super();
    }

    public ObservableList<Regioni> getAll() {
        return findAllObservableList(Regioni.class);
    }

    public Regioni CreaRegione(String nome) {
        Regioni bds = new Regioni();
        bds.setNome(nome);
        this.save(bds);
        return bds;
    }


    public void save(Regioni l) {
        if (validaBorsa(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Regioni l) {
        super.delete(l);
    }

    public boolean isPersistente(Regioni l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaBorsa(Regioni l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getNome() == null || l.getNome().equals("")) {
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

