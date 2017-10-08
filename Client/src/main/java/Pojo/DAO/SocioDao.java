package Pojo.DAO;

import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.util.Date;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class SocioDao extends GenericDao {
    public SocioDao() {
        super();
    }

    public ObservableList<Socio> getAll() {
        return findAllObservableList(Socio.class);
    }

    public Socio CreaSocio(String cf, String nome, String cognome, String indirizzo, String citta, String comune, String iban, Date data, String categoria) {
        Socio socio = new Socio();
        socio.setCF(cf);
        socio.setNome(nome);
        socio.setCognome(cognome);
        socio.setIndirizzo(indirizzo);
        socio.setCitta(citta);
        socio.setComune(comune);
        socio.setdataIscrizione(data);
        socio.setIBAN(iban);
        socio.setCategoria(categoria);
        this.save(socio);
        return socio;
    }

    public Socio CreaSocioPensionato( String cf, String nome, String cognome, String indirizzo, String citta, String comune, String iban, Date data, String categoria,Date pensione, float reddito, float ritenuta,float sussidio){
        Socio socio = new Socio();
        socio.setCF(cf);
        socio.setNome(nome);
        socio.setCognome(cognome);
        socio.setIndirizzo(indirizzo);
        socio.setCitta(citta);
        socio.setComune(comune);
        socio.setdataIscrizione(data);
        socio.setIBAN(iban);
        socio.setCategoria(categoria);
        socio.setreddito(reddito);
        socio.setdataPensionamento(pensione);
        socio.setsussidioMensile(sussidio);
        socio.setritenuta(ritenuta);
        this.save(socio);
        return socio;
    }
    public void save(Socio l) {
        if (validaSocio(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Socio l) {
        super.delete(l);
    }

    public boolean isPersistente(Socio l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaSocio(Socio l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
        if (l.getNome() == null || l.getNome().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_NOME_NULLO;
        }
        if (l.getCognome() == null || l.getCognome().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COGNOME_NULLO;
        }
        if (l.getCF() == null || l.getCF().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CODICEFISCALE_NULLO;
        }
        if (l.getComune() == null || l.getComune().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_COMUNE_NULLO;
        }
        if (l.getCitta() == null || l.getCitta().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_CITTA_NULLO;
        }
        if (l.getIndirizzo() == null || l.getIndirizzo().equals("")) {
            numero_campi_invalidi++;
            ultimoErrore = ErrorLabel.DETTAGLIO_INDIRIZZO_NULLO;
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

