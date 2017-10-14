package Pojo.DAO;

import Pojo.RegoleCarovita;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class RegoleCarovitaDao extends GenericDao {
    public RegoleCarovitaDao() {
        super();
    }

    public ObservableList<RegoleCarovita> getAll() {
        return findAllObservableList(RegoleCarovita.class);
    }

    public RegoleCarovita CreaBorsaDiStudio(int anno,  float percentuale) {
        RegoleCarovita bds = new RegoleCarovita();
        bds.setAnno(anno);
        bds.setPercentuale(percentuale);
        this.save(bds);
        return bds;
    }


    public void save(RegoleCarovita l) {
        super.saveOrUpdate(l);

    }

    public void delete(RegoleCarovita l) {
        super.delete(l);
    }

    public boolean isPersistente(RegoleCarovita l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }



    public void chiudiSessione() {
        closeSession();
    }
}

