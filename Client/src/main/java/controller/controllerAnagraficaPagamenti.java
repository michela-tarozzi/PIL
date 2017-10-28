package controller;

import Pojo.DAO.PagamentoDao;
import Pojo.Pagamenti;
import Pojo.Rimborsi;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import org.controlsfx.control.table.TableFilter;

/**
 * Created by m.tarozzi on 15/10/2017.
 *  <TableColumn fx:id="ColonnaDATA" prefWidth="89.0" text="DATA" />
 <TableColumn fx:id="ColonnaLORDO" prefWidth="89.0" text="LORDO" />
 <TableColumn fx:id="ColonnaNETTO" prefWidth="89.0" text="NETTO" />
 <TableColumn fx:id="colonnaTRATTENUTA" prefWidth="89.0" text="TRATTENUTA" />
 */
public class controllerAnagraficaPagamenti {
    @FXML
    public TableView<Pagamenti> tableANAGRAFICA;
    @FXML
    public TableColumn<Pagamenti,String> ColonnaDATA;
    @FXML
    public TableColumn<Pagamenti,Float> ColonnaLORDO;
    @FXML
    public TableColumn<Pagamenti,Float> ColonnaNETTO;
    @FXML
    public TableColumn<Pagamenti,Float> colonnaTRATTENUTA;
    @FXML
    public TableColumn<Pagamenti,String> ColonnaSOCIO;

    private String nomeClasse;
    private ObservableList<Pagamenti> pagamenti= FXCollections.observableArrayList();
    private PagamentoDao pagamentiDao=new PagamentoDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaDATA.setCellValueFactory(new PropertyValueFactory<Pagamenti, String>("data"));
        ColonnaLORDO.setCellValueFactory(new PropertyValueFactory<Pagamenti, Float>("lordo"));
        ColonnaNETTO.setCellValueFactory(new PropertyValueFactory<Pagamenti, Float>("netto"));
        colonnaTRATTENUTA.setCellValueFactory(new PropertyValueFactory<Pagamenti, Float>("trattenuta"));
        ColonnaSOCIO.setCellValueFactory(new PropertyValueFactory<Pagamenti, String>("descrizione"));

        this.pagamenti=pagamentiDao.getAll();
        tableANAGRAFICA.setItems(pagamenti);
        try {
            TableFilter<Pagamenti> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }


    public void InserisciNuovoPagamento(ActionEvent event) {
        App.getInstance().gotoInserisciPagamento();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
