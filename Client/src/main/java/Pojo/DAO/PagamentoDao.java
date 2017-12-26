package Pojo.DAO;

import Pojo.*;
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
    public Pagamenti CreaPagamento(Rimborsi rimborsi, LocalDate data, float lordo, float netto, float trattenuta) {
        Pagamenti pagamento = new Pagamenti();
        pagamento.setData(data);
        pagamento.setLordo(lordo);
        pagamento.setNetto(netto);
        pagamento.setTrattenuta(trattenuta);
        pagamento.addRimborso(rimborsi);
        pagamento.setDescrizione(rimborsi.getSpesa().getSocio().getCognome()+" "+rimborsi.getSpesa().getSocio().getNome()+" - Rimborso "+rimborsi.getRegola().getDescrizione());
        this.save(pagamento);
        return pagamento;
    }
    public Pagamenti CreaPagamento(AsiliNido rimborsi, LocalDate data, float lordo, float netto, float trattenuta) {
        Pagamenti pagamento = new Pagamenti();
        pagamento.setData(data);
        pagamento.setLordo(lordo);
        pagamento.setNetto(netto);
        pagamento.setTrattenuta(trattenuta);
        pagamento.addAsiliNido(rimborsi);
        pagamento.setDescrizione(rimborsi.getSocio().getCognome()+" "+rimborsi.getSocio().getNome()+" - Rimborso Asilo Nido "+rimborsi.getFiglio());
        this.save(pagamento);
        return pagamento;
    }
    public Pagamenti CreaPagamento(BorseDiStudio rimborsi, LocalDate data, float lordo, float netto, float trattenuta) {
        Pagamenti pagamento = new Pagamenti();
        pagamento.setData(data);
        pagamento.setLordo(lordo);
        pagamento.setNetto(netto);
        pagamento.setTrattenuta(trattenuta);
        pagamento.addBorseDiStudio(rimborsi);
        pagamento.setDescrizione(rimborsi.getSocio().getCognome()+" "+rimborsi.getSocio().getNome()+" - Rimborso da Isaia Levi Borsa di Studio "+rimborsi.getFiglio());
        this.save(pagamento);
        return pagamento;
    }
    public Pagamenti CreaPagamento(Pensioni pensione, LocalDate data, float lordo, float netto, float trattenuta) {
        Pagamenti pagamento = new Pagamenti();
        pagamento.addPensione(pensione);
        pagamento.setDescrizione(pensione.getSocio().getCognome()+" "+pensione.getSocio().getNome()+" - Pensione");
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

    public Socio getSocio(Pagamenti pagamento){
        Socio socio;
        if(pagamento.getPensioni().size()!=0)
        {
            Pensioni pensione= pagamento.getPensioni().get(0);
            socio=pensione.getSocio();}
        else
            if(pagamento.getAsiliNido().size()!=0)
            {
                AsiliNido asiloNido=pagamento.getAsiliNido().get(0);
                socio=asiloNido.getSocio();
            }
        else if (pagamento.getRimborsi().size()!=0){
            Rimborsi rimborso=pagamento.getRimborsi().get(0);
            Spese spesa=rimborso.getSpesa();
            socio=spesa.getSocio();
        }
        else if (pagamento.getEredi().size()!=0)
        {
            Eredi erede=pagamento.getEredi().get(0);
            socio=erede.getSocio();
        }
        else
            {
                BorseDiStudio borsaDiStudio=pagamento.getBorseDiStudio().get(0);
            socio=borsaDiStudio.getSocio();
            }

        return socio;
    }

    public void chiudiSessione() {
        closeSession();
    }
}

