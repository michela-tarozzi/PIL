package controller;

import Pojo.DAO.RegoleCarovitaDao;
import Pojo.RegoleCarovita;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import org.controlsfx.control.table.TableFilter;

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
        try {
            TableFilter<RegoleCarovita> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
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

    public void Elimina(ActionEvent event) {
        int numeroRiga=tableANAGRAFICA.getSelectionModel().getFocusedIndex();
        RegoleCarovitaDao regoleCarovitaDao=new RegoleCarovitaDao();
        regoleCarovitaDao.delete(tableANAGRAFICA.getItems().get(numeroRiga));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Eliminazione");
        alert.setHeaderText("Regola ELIMINATA");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaREGOLECAROVITA();
    }
}
