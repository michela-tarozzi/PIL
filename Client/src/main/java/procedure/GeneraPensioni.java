package procedure;

import Pojo.DAO.*;
import Pojo.Pensioni;
import Pojo.Socio;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class GeneraPensioni {

    public boolean Generapensioni(Boolean addizionali, LocalDate data, int anno, Boolean tredicesima) {
        try {
            SocioDao socioDao = new SocioDao();
            ObservableList<Socio> soci = socioDao.getAll();

            Iterator<Socio> socioIt = soci.iterator();
            PensioniDao pensioniDao = new PensioniDao();
            float carovitaAttuale = new RegoleCarovitaDao().getCarovita(data.getYear());
            float sussidio=0;
            float carovita=0;

            while (socioIt.hasNext()) {
                Socio socio = socioIt.next();
                if (socio.getCategoria().equals("PENSIONATO")) {
                    if(socio.getdataPensionamento().getYear()==data.getYear()&&
                           data.getMonthValue()-socio.getdataPensionamento().getMonthValue()<2)
                    {
                        int giorni=socio.getdataPensionamento().lengthOfMonth()-socio.getdataPensionamento().getDayOfMonth();
                        sussidio=socio.getsussidioMensile()*(data.getMonthValue()-socio.getdataPensionamento().getMonthValue())+(socio.getsussidioMensile()*giorni/socio.getdataPensionamento().lengthOfMonth());
                        if (tredicesima)
                        {
                            if (socio.getdataPensionamento().getDayOfMonth()>16)
                            sussidio=sussidio+(socio.getsussidioMensile()*((12-socio.getdataPensionamento().getMonthValue()))/12);
                            else
                                sussidio=sussidio+(socio.getsussidioMensile()*((13-socio.getdataPensionamento().getMonthValue()))/12);
                        }
                    }
                    else{
                    if (tredicesima)
                    {
                        sussidio=socio.getsussidioMensile()*4;
                    }
                    else
                    {
                        sussidio = socio.getsussidioMensile() * 3;
                    }
                    carovita = (sussidio * carovitaAttuale) / 100;}
                    float regionale = 0;
                    float comunale = 0;
                    if (addizionali) {
                        float lordoAnnuo=pensioniDao.getLordoAnnuoSocio(socio,anno);
                        float aliquotaComunale= new AddizionaleComunaleDao().getAddizionaleSpecifico(socio.getComune().getNome(),socio.getreddito(),anno).getAliquota();
                        comunale=lordoAnnuo*aliquotaComunale;
                        float aliquotaRegionale=new AddizionaleRegionaleDao().getAddizionaleSpecifico(socio.getRegione().getNome(),socio.getreddito(),anno).getAliquota();
                        regionale=lordoAnnuo*aliquotaRegionale;
                    }
                    float lordoAnnuo=pensioniDao.getLordoAnnuoSocio(socio,data.getYear());
                    float trattenuteAnnue=pensioniDao.getTrattenuteAnnueSocio(socio,data.getYear());
                    float arrotondam=trattenuteAnnue-(lordoAnnuo*socio.getritenuta());
                    float lordo = sussidio + carovita;
                    float ritenuta = (lordo * socio.getritenuta())+arrotondam;
                    float netto = lordo - ritenuta - regionale - comunale;
                    Pensioni pensione = pensioniDao.CreaPensione(socio,data, sussidio, carovita, regionale, comunale, lordo, netto, ritenuta);
                    //socio.addPensioni(pensione);
                    //socioDao.update(socio);
                }

            }
        }catch(Exception e) {e.printStackTrace(); return false;}
        return true;
    }
}