package Pojo.DAO;

import Pojo.AddizionaleComunale;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

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
   // public AddizionaleComunale getAddizionaleSpecifico(String comune, float reddito, int anno)
    {
        //todo
        /*
        AddizionaleComunale addizionaleComunale=new AddizionaleComunale();
        ObservableList<AddizionaleComunale> tutte = getAll();
        Iterator<AddizionaleComunale> it=tutte.iterator();
        boolean trovato=false;
        while (it.hasNext()&& !trovato)
        {
            addizionaleComunale=it.next();
            if(addizionaleComunale.getAnno()== anno && addizionaleComunale.getSogliaMinima()<reddito &&
                    addizionaleComunale.getSogliaEsente()>reddito && addizionaleComunale.getComune().equals(comune))
            {
                trovato=true;
            }
        }

        return addizionaleComunale;
        */
    }

    public AddizionaleComunale CreaAddizionaleComunale(String codice, String comune, int anno, float aliquotaZero,
                                                       float aliquotaUno, float aliquotaDue, float aliquotaTre, float aliquotaQuattro,
                                                       float aliquotaCinque, String fasciaZero, String fasciaUNo, String fasciaDue, String fasciaTre,
                                                       String fasciaQuattro,String fasciaCinque, float sogliaEsente) {
        AddizionaleComunale ac = new AddizionaleComunale();
        ac.setCodice(codice);
        ac.setComune(comune);
        ac.setAnno(anno);
        ac.setAliquotaZero(aliquotaZero);
        ac.setAliquotaUno(aliquotaUno);
        ac.setAliquotaDue(aliquotaDue);
        ac.setAliquotaTre(aliquotaTre);
        ac.setAliquotaQuattro(aliquotaQuattro);
        ac.setAliquotaCinque(aliquotaCinque);
        ac.setFasciaZero(fasciaZero);
        ac.setFasciaUno(fasciaUNo);
        ac.setFasciaDue(fasciaDue);
        ac.setFasciaTre(fasciaTre);
        ac.setFasciaQuattro(fasciaQuattro);
        ac.setFasciaCinque(fasciaCinque);
        ac.setSogliaEsente(sogliaEsente);
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

    public void CalcolaAliquoteAddizionali(int anno) {
        SocioDao socioDao=new SocioDao();
        ObservableList<Socio> soci=socioDao.getAll();
        Iterator<Socio> socioIterator=soci.iterator();
        while(socioIterator.hasNext()){
            Socio socio=socioIterator.next();
            AddizionaleComunaleDao addizionaleComunaleDao= new AddizionaleComunaleDao();
            AddizionaleComunale ac=addizionaleComunaleDao.trovaAddizionale(socio, anno);
        }
    }

    private AddizionaleComunale trovaAddizionale(Socio socio, int anno) {
        ObservableList<AddizionaleComunale> addCom= query("SELECT * FROM ADDIZIONALICOMUNALI WHERE CODICE='"+socio.getComune().getCodiceCatastale()+"'", null);

    }

}

