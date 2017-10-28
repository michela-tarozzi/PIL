package Pojo.DAO;

import Pojo.AsiliNido;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.util.Date;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class AsiliNidoDao extends GenericDao {
    public AsiliNidoDao() {
        super();
    }

    public ObservableList<AsiliNido> getAll() {
        return findAllObservableList(AsiliNido.class);
    }

    public AsiliNido CreaAsiloNido(Socio socio, String figlio, int anno, float integrazione, float percentuale, float spesa, float rimborso){       AsiliNido asili = new AsiliNido();
        asili.setFiglio(figlio);
        asili.setSocio(socio);
        asili.setAnno(anno);
        asili.setIntegrazione(integrazione);
        asili.setPercentuale(percentuale);
        asili.setRimborso(rimborso);
        asili.setSpesa(spesa);
        this.save(asili);
        SocioDao socioDao=new SocioDao();
        socio.addAsiliNido(asili);
        socioDao.update(asili);
        return asili;
    }

    public void save(AsiliNido l) {
        if (validaAsiloNido(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(AsiliNido l) {
        super.delete(l);
    }

    public boolean isPersistente(AsiliNido l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaAsiloNido(AsiliNido l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getFiglio() == null || l.getFiglio().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
        }
        if (l.getAnno() <2000) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
        }
        if (l.getSpesa() <=0) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CODICEFISCALE_NULLO;
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

