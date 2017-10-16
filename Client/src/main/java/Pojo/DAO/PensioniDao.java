package Pojo.DAO;

import Pojo.Pagamenti;
import Pojo.Pensioni;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class PensioniDao extends GenericDao {
    public PensioniDao() {
        super();
    }

    public ObservableList<Pensioni> getAll() {
        return findAllObservableList(Pensioni.class);
    }

    public float getLordoAnnuoSocio(Socio socio, int anno){
        Float totale=(Float.parseFloat("0"));
        ObservableList<Pensioni> pensioni=this.getAll();
        Iterator<Pensioni> it=pensioni.iterator();

        while(it.hasNext())
        {
            Pensioni pensione=it.next();
            String cf=pensione.getSocio().getCF();
            String cf2=socio.getCF();

            if (cf.equals(cf2) && pensione.getData().getYear()==anno)
            {
                totale=totale+pensione.getLordo();
            }
        }
     return totale;
    }

    public Pensioni CreaPensione(Socio socio,LocalDate data, float sussidio, float carovita, float regionale, float comunale, float lordo, float netto, float ritenuta) {
        Pensioni pensione = new Pensioni();
        pensione.setSocio(socio);
        pensione.setData(data);
      pensione.setSussidio(sussidio);
      pensione.setCarovita(carovita);
      pensione.setAddizionaleComunale(comunale);
      pensione.setAddizionaleRegionale(regionale);
      pensione.setLordo(lordo);
      pensione.setStato("DA PAGARE");
      pensione.setNetto(netto);
      pensione.setRitenuta(ritenuta);
        this.save(pensione);
        return pensione;
    }
    public Pensioni AggiungiPagamento(Pensioni pensione, Pagamenti pagamento)
    {
        pensione.setPagamento(pagamento);
        this.update(pensione);
        return pensione;
    }

    public void save(Pensioni l) {
        if (validaPensione(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Pensioni l) {
        super.delete(l);
    }

    public boolean isPersistente(Pensioni l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaPensione(Pensioni l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        //eventuali controlli
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

    public ObservableList<Pensioni> getNonPagate() {
        ObservableList<Pensioni> tutte=this.getAll();
        ObservableList<Pensioni> pensioni= FXCollections.observableArrayList();
        Iterator<Pensioni> it=tutte.iterator();
        int i=0;
        while(it.hasNext())
        {
            Pensioni pensione=it.next();
           if (!(pensione.getStato().equals("PAGATA"))){
               pensioni.add(pensione);
           }
           i++;
        }
        return pensioni;
    }
}

