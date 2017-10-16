package Pojo.DAO;

import Pojo.Pagamenti;
import Pojo.Pensioni;
import Pojo.Socio;
import Utility.exception.ErrorLabel;
import Utility.exception.ExceptionCode;
import Utility.exception.SystemExceptionRefactor;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Date;


/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class PagamentoDao extends GenericDao {
    public PagamentoDao() {
        super();
    }

    public ObservableList<Pagamenti> getAll() {
        return findAllObservableList(Pagamenti.class);
    }

    public Pagamenti CreaPagamento( LocalDate data, float lordo, float netto, float trattenuta) {
        Pagamenti pagamento = new Pagamenti();
        pagamento.setData(data);
        pagamento.setLordo(lordo);
        pagamento.setNetto(netto);
        pagamento.setTrattenuta(trattenuta);
        this.save(pagamento);
        return pagamento;
    }

    public void save(Pagamenti l) {
        if (validaPagamento(l)) {
            super.saveOrUpdate(l);
        }
    }

    public void delete(Pagamenti l) {
        super.delete(l);
    }

    public boolean isPersistente(Pagamenti l) {
        boolean sonoSuDB = false;
        if (l.getId() != null) {
            sonoSuDB = true;
        }
        return sonoSuDB;
    }

    public boolean validaPagamento(Pagamenti l) {
        boolean valido = true;
        String ultimoErrore = "";
        int numero_campi_invalidi = 0;
       //fare controlli
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

