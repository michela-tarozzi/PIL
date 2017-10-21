package controller;

import Pojo.DAO.QuoteDao;
import Pojo.Quote;
import Pojo.Socio;
import Utility.ControllersDispatcher;
import com.sun.org.apache.xpath.internal.operations.Quo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import org.controlsfx.control.table.TableFilter;

/**
 * Created by m.tarozzi on 16/10/2017.
 */
public class controllerAnagraficaQuote {

    @FXML
    public TableView<Quote> tableANAGRAFICA;
    @FXML
    public TableColumn<Quote,String> colonnaDATA;
    @FXML
    public TableColumn<Quote,Float> ColonnaIMPORTO;
    @FXML
    public TableColumn<Quote, Socio> ColonnaSOCIO;

    private String nomeClasse;
    private ObservableList<Quote> quote= FXCollections.observableArrayList();
    private QuoteDao quoteDao=new QuoteDao();
//
    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colonnaDATA.setCellValueFactory(new PropertyValueFactory<Quote, String>("data"));
        ColonnaIMPORTO.setCellValueFactory(new PropertyValueFactory<Quote, Float>("importo"));
        ColonnaSOCIO.setCellValueFactory(new PropertyValueFactory<Quote, Socio>("socio"));
        this.quote=quoteDao.getAll();
        tableANAGRAFICA.setItems(quote);
        try {
            TableFilter<Quote> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
         //TableFilter<Quote> t = new TableFilter<>(tableANAGRAFICA);
        }catch(Exception e ){e.printStackTrace();}
    }

    public void InserisciNuovaQuota (ActionEvent event) { App.getInstance().gotoGeneraQuote(); }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }



}
