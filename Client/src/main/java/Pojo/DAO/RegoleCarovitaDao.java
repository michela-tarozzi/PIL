package Pojo.DAO;

import Pojo.RegoleCarovita;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;


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

    public RegoleCarovita getCarovitaAttuale()
    {
        ObservableList<RegoleCarovita> tutte = getAll();
        RegoleCarovita carovitaAttuale=new RegoleCarovita();
        Date date=new Date(); // your date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        Iterator<RegoleCarovita> it=tutte.iterator();
        boolean trovato=false;
        while (it.hasNext() && trovato)
        {
            carovitaAttuale=it.next();
            if(carovitaAttuale.getAnno()== year )
            {
            trovato=true;
            }
        }
        return carovitaAttuale;
    }

    public RegoleCarovita CreaRegola(int anno,  float percentuale) {
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

    public float getCarovita(int anno) {
        ObservableList<RegoleCarovita> tutte = getAll();
        RegoleCarovita carovitaAttuale;
        Iterator<RegoleCarovita> it=tutte.iterator();
        float result=0;
        boolean trovato=false;
        while (it.hasNext() && !trovato)
        {
            carovitaAttuale=it.next();
            if(carovitaAttuale.getAnno()== anno)
            {
                trovato=true;
                result=carovitaAttuale.getPercentuale();
            }
        }
        return result;
    }
}

