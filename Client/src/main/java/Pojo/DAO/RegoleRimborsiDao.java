package Pojo.DAO;

import Pojo.RegoleCarovita;
import Pojo.RegoleRimborsi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Iterator;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class RegoleRimborsiDao extends GenericDao {
    public RegoleRimborsiDao() {
        super();
    }

    public ObservableList<RegoleRimborsi> getAll() {
        return findAllObservableList(RegoleRimborsi.class);
    }

    public RegoleRimborsi CreaRegola(int anno, String categoria, float maxannuo, float percentuale, float maxSingolaPrestazione,int ricorrenza) {
        RegoleRimborsi regola = new RegoleRimborsi();
        regola.setAnno(anno);
        regola.setDescrizione(categoria);
        regola.setMaxAnnuo(maxannuo);
        regola.setMaxSingolaPrestazione(maxSingolaPrestazione);
        regola.setRicorrenza(ricorrenza);
        regola.setPercentuale(percentuale);
        this.save(regola);
        return regola;
    }


    public void save(RegoleRimborsi l) {
        super.saveOrUpdate(l);

    }

    public void delete(RegoleRimborsi l) {
        super.delete(l);
    }

    public boolean isPersistente(RegoleRimborsi l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }



    public void chiudiSessione() {
        closeSession();
    }

    public ObservableList getAnnoCorrente() {
        ObservableList<RegoleRimborsi> regoleRimborsi=this.getAll();
        ObservableList<RegoleRimborsi> annoCorrente= FXCollections.observableArrayList();
        Iterator<RegoleRimborsi> it=regoleRimborsi.iterator();
        int anno = LocalDate.now().getYear();
        while(it.hasNext())
        {
            RegoleRimborsi regola=it.next();
            if (regola.getAnno()==anno)
            {
                annoCorrente.add(regola);
            }
        }
        return annoCorrente;
    }
}

