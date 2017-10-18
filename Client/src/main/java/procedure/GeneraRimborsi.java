package procedure;

import Pojo.DAO.RimborsoDao;
import Pojo.DAO.SpeseDao;
import Pojo.RegoleRimborsi;
import Pojo.Spese;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.Iterator;

/**
 * Created by m.tarozzi on 17/10/2017.
 */
public class GeneraRimborsi {
    public float generaRimborsi(Spese spesa, float importo, RegoleRimborsi categoria)
    {
        RimborsoDao rimborsoDao=new RimborsoDao();
        float rimborso=importo;
        //bb
        //
        if (categoria.getRicorrenza()==rimborsoDao.GetRicorrenzaRimborso(categoria,spesa.getSocio()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Raggiunto il numero massimo di  ricorrenze ");
            alert.showAndWait();
        }
        if(categoria.getPercentuale()<100){ rimborso=importo*categoria.getPercentuale()/100;}
        if (categoria.getMaxSingolaPrestazione()<=rimborso)
        {
            rimborso=categoria.getMaxSingolaPrestazione();
        }
        float annuo=rimborsoDao.getTotSocioAnno(categoria, spesa.getSocio());
        if (categoria.getMaxAnnuo()<=(annuo+rimborso))
        {
            rimborso=categoria.getMaxAnnuo()-annuo;
        }
//TEST

        return rimborso;
    }
}
