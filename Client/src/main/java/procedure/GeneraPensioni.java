package procedure;

import Pojo.DAO.*;
import Pojo.Pensioni;
import Pojo.Socio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class GeneraPensioni {

    public boolean Generapensioni(Boolean addizionali, LocalDate data, int anno) {
        try {
            SocioDao socioDao = new SocioDao();
            ObservableList<Socio> soci = socioDao.getAll();

            Iterator<Socio> socioIt = soci.iterator();
            PensioniDao pensioniDao = new PensioniDao();
            float carovitaAttuale = new RegoleCarovitaDao().getCarovitaAttuale().getPercentuale();
            while (socioIt.hasNext()) {
                Socio socio = socioIt.next();
                if (socio.getCategoria().equals("PENSIONATO")) {
                    float sussidio = socio.getsussidioMensile() * 3;
                    float carovita = (sussidio * carovitaAttuale) / 100;
                    float regionale = 0;
                    float comunale = 0;
                    if (addizionali) {

                        float lordoAnnuo=pensioniDao.getLordoAnnuoSocio(socio,anno);
                        float aliquotaComunale= new AddizionaleComunaleDao().getAddizionaleSpecifico(socio.getComune().getNome(),socio.getreddito(),anno).getAliquota();
                        comunale=lordoAnnuo*aliquotaComunale/100;
                        float aliquotaRegionale=new AddizionaleRegionaleDao().getAddizionaleSpecifico(socio.getRegione().getNome(),socio.getreddito(),anno).getAliquota();
                        regionale=lordoAnnuo*aliquotaRegionale/100;
                    }
                    float lordo = sussidio + carovita;
                    float ritenuta = lordo * socio.getritenuta() / 100;
                    float netto = lordo - ritenuta - regionale - comunale;
                    Pensioni pensione = pensioniDao.CreaPensione(socio,data, sussidio, carovita, regionale, comunale, lordo, netto, ritenuta);
                    socio.addPensioni(pensione);
                }

            }
        }catch(Exception e) {e.printStackTrace(); return false;}
        return true;
    }
}
