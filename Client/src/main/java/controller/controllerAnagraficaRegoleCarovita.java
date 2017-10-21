package controller;

import Pojo.DAO.RegoleCarovitaDao;
import Pojo.RegoleCarovita;
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

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerAnagraficaRegoleCarovita {

    @FXML
    public TableView tableANAGRAFICA;
    @FXML
    public TableColumn<RegoleCarovita, Integer> ColonnaANNO;
    @FXML
    public TableColumn<RegoleCarovita, Float> ColonnaPERCENTUALE;


    private String nomeClasse;
    private ObservableList<RegoleCarovita> regole= FXCollections.observableArrayList();
    private RegoleCarovitaDao regoleCarovitaDao=new RegoleCarovitaDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaANNO.setCellValueFactory(new PropertyValueFactory<RegoleCarovita, Integer>("anno"));
        ColonnaPERCENTUALE.setCellValueFactory(new PropertyValueFactory<RegoleCarovita, Float>("percentuale"));
        this.regole=regoleCarovitaDao.getAll();
        tableANAGRAFICA.setItems(regole);
    }

    public void InserisciNuovoConto(ActionEvent event) {
        App.getInstance().gotoInserisciConto();
    }

    public void InserisciNuovaRegolaCarovita(ActionEvent event) {
        App.getInstance().gotoInserisciRegolaCarovita();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
