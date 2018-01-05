package Pojo.DAO;

import Pojo.*;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.JavaFXBuilderFactory;
import procedure.GeneraEstrazioniDati;

import java.util.Iterator;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class AddizionaleComunaleDao extends GenericDao {
    public AddizionaleComunaleDao() {
        super();
    }

    public ObservableList<AddizionaleComunale> getAll() {
        return findAllObservableList(AddizionaleComunale
                .class);
    }
    public AddizionaleComunale getAddizionaleSpecifico(String comune, float reddito, int anno)
    {
        AddizionaleComunale addizionaleComunale=new AddizionaleComunale();
        ObservableList<AddizionaleComunale> tutte = getAll();
        Iterator<AddizionaleComunale> it=tutte.iterator();
        boolean trovato=false;
        while (it.hasNext()&& !trovato)
        {
            addizionaleComunale=it.next();
            if(addizionaleComunale.getAnno()== anno && addizionaleComunale.getRedditoMinimo()<=reddito &&
                    addizionaleComunale.getRedditoMassimo()>reddito && addizionaleComunale.getComune().equals(comune))
            {
                trovato=true;
            }
        }
        return addizionaleComunale;
    }

    public AddizionaleComunale CreaAddizionaleComunale(String codice, String comune, int anno, float aliquotaZero,
                                                       float redditoMinimo, float redditoMassimo) {
        AddizionaleComunale ac = new AddizionaleComunale();
        ac.setCodice(codice);
        ac.setComune(comune);
        ac.setAnno(anno);
        ac.setAliquota(aliquotaZero);
        ac.setRedditoMinimo(redditoMinimo);
        ac.setRedditoMassimo(redditoMassimo);
        this.save(ac);
        return ac;
    }


    public void save(AddizionaleComunale l) {
        if (validaAddizionaleComunale(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(AddizionaleComunale l) {
        super.delete(l);
    }

    public boolean isPersistente(AddizionaleComunale l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaAddizionaleComunale(AddizionaleComunale l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getCodice() == null || l.getCodice().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
        }
        if (l.getAnno() <2000) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CODICEFISCALE_NULLO;
        }
        if (l.getComune() == null || l.getComune().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COMUNE_NULLO;
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

    public void CalcolaPercentualiPerTuttiISoci(int anno) {
        SocioDao socioDao=new SocioDao();
        AddizionaleRegionaleDao ard=new AddizionaleRegionaleDao();
        ObservableList<AliquoteAddizionali> aliquoteAddizionali= FXCollections.observableArrayList();
        ObservableList<Socio> soci= socioDao.getAll();
        Iterator<Socio> sociIt=soci.iterator();
        while(sociIt.hasNext()){
            Socio socio=sociIt.next();
            Comune comune=socio.getComune();
            Regioni regione= socio.getRegione();
            AddizionaleComunale ac=this.getAddizionaleSpecifico(comune.getNome(),socio.getreddito(),anno);
            AddizionaleRegionale ar=ard.getAddizionaleSpecifico(regione.getNome(),socio.getreddito(),anno);
           if(ac!=null && ar!=null) {
               AliquoteAddizionali aa = new AliquoteAddizionali();
               aa.setSocio(socio);
               aa.setAliquotaComunale(ac.getAliquota());
               aa.setAliquotaRegionale(ar.getAliquota());
               aliquoteAddizionali.add(aa);
           }
        }
        GeneraEstrazioniDati.GeneraEstrazioneDatiAliquote(aliquoteAddizionali);
    }
}

