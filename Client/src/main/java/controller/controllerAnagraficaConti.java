package controller;

import Pojo.Conti;
import Pojo.DAO.ContiDao;
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
 */
public class controllerAnagraficaConti {
    @FXML
    public TableView tableANAGRAFICA;
    @FXML
    public TableColumn<Conti, String> ColonnaCONTO;
    @FXML
    public TableColumn<Conti, String> ColonnaDESCRIZIONE;


    private String nomeClasse;
    private ObservableList<Conti> conti= FXCollections.observableArrayList();
    private ContiDao conto=new ContiDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaCONTO.setCellValueFactory(new PropertyValueFactory<Conti, String>("numero"));
        ColonnaDESCRIZIONE.setCellValueFactory(new PropertyValueFactory<Conti, String>("descrizione"));
        this.conti=conto.getAll();
        tableANAGRAFICA.setItems(conti);
        try {
            TableFilter<Conti> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }

    public void InserisciNuovoConto(ActionEvent event) {
        App.getInstance().gotoInserisciConto();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
