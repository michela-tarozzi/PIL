package Pojo.DAO;

import Pojo.*;
import Utility.HibernateUtil;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import procedure.GeneraRegistrazioniOracle;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class SocioDao extends GenericDao {



    public ObservableList<Socio> getAll() {
        return findAllObservableList(Socio.class);
    }

    public ObservableList<NomiDao> getSociMutuaHH(){return getSociMutua();}

    public Socio CreaSocio(String cf, String nome, String cognome, String indirizzo, String citta, Comune comune, String iban, LocalDate data, String categoria,Regioni regione, Conti conto) {
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
        socio.setRegione(regione);
        socio.setConto(conto);
        this.save(socio);
        GeneraRegistrazioniOracle generaRegistrazioniOracle=new GeneraRegistrazioniOracle();
        generaRegistrazioniOracle.generaIscrizioneSocio(socio);
        return socio;
    }


    public boolean uccidiSocio(Socio socio, LocalDate dataDecesso)
    {
                socio.setdataDecesso(dataDecesso);
                saveOrUpdate(socio);
        // TODO: 16/11/2017
                //genera registrazione senza importi per registrazione in caso di decesso di un socio
                //cambiare l'anagrafica dei conti per mantenere memoria dei saldi e delle registrazioni (solo per conti pensioni)
                //a quel punto si potranno generare gli importi
        return true;

    }

    public Socio CreaSocioPensionato(String cf, String nome, String cognome, String indirizzo, String citta, Comune comune, String iban, LocalDate data, String categoria, LocalDate pensione, float reddito, float ritenuta, float sussidio, Regioni regione, Conti conto){
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
        socio.setRegione(regione);
        socio.setConto(conto);
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

