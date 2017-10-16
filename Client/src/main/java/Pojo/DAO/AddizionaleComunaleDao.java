package Pojo.DAO;

import Pojo.AddizionaleComunale;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
            if(addizionaleComunale.getAnno()== anno && addizionaleComunale.getSogliaMinima()<reddito &&
                    addizionaleComunale.getSogliaMassima()>reddito && addizionaleComunale.getComune().equals(comune))
            {
                trovato=true;
            }
        }

        return addizionaleComunale;
    }

    public AddizionaleComunale CreaAddizionaleComunale(String codice, String comune, int anno, float sogliaMinima, float sogliaMassima, float aliquota) {
        AddizionaleComunale ac = new AddizionaleComunale();
        ac.setCodice(codice);
        ac.setComune(comune);
        ac.setAliquota(aliquota);
        ac.setAnno(anno);
        ac.setSogliaMassima(sogliaMassima);
        ac.setSogliaMinima(sogliaMinima);
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
        if (l.getAliquota() <0) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
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
}

