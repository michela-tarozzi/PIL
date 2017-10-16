package procedure;

import Pojo.DAO.PagamentoDao;
import Pojo.DAO.PensioniDao;
import Pojo.DAO.SocioDao;
import Pojo.Pagamenti;
import Pojo.Pensioni;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class GeneraPagamenti {

    public boolean GeneraPagamentoPensioni(ObservableList<Pensioni> pensioni, LocalDate data) {
        PagamentoDao pagamentoDao=new PagamentoDao();
        PensioniDao pensioniDao=new PensioniDao();
        Iterator<Pensioni> it= pensioni.iterator();
        while(it.hasNext())
        {
            Pensioni pensione=it.next();
            Pagamenti pagamento=pagamentoDao.CreaPagamento(data,pensione.getLordo(), pensione.getNetto(),pensione.getRitenuta()+
                    pensione.getAddizionaleComunale()+pensione.getAddizionaleRegionale());
            pensione.setPagamento(pagamento);
            pensione.setStato("PAGATA");
            pensioniDao.update(pensione);
        }
        return true;
    }

}
