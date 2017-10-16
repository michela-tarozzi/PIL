package controller;

import Pojo.Comune;
import Pojo.DAO.ComuneDao;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerAnagraficaComuni {

    @FXML
    public TableView tableANAGRAFICA;
    @FXML
    public TableColumn<Comune, String> ColonnaCODICE;
    @FXML
    public TableColumn<Comune, String> ColonnaCOMUNE;


    private String nomeClasse;
    private ObservableList<Comune> comuni= FXCollections.observableArrayList();
    private ComuneDao conto=new ComuneDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaCODICE.setCellValueFactory(new PropertyValueFactory<Comune, String>("codiceCatastale"));
        ColonnaCOMUNE.setCellValueFactory(new PropertyValueFactory<Comune, String>("nome"));
        this.comuni=conto.getAll();
        tableANAGRAFICA.setItems(comuni);
    }

    public void InserisciNuovoComune(ActionEvent event) {
        App.getInstance().gotoInserisciComune();
    }
}
