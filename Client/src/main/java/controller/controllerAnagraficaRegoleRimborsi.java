package controller;

import Pojo.DAO.RegoleRimborsiDao;
import Pojo.DAO.SocioDao;
import Pojo.RegoleRimborsi;
import Pojo.Socio;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import org.controlsfx.control.table.TableFilter;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerAnagraficaRegoleRimborsi {
    @FXML
    public TableView<RegoleRimborsi> tableANAGRAFICA;
    @FXML
    public TableColumn<RegoleRimborsi, Integer> ColonnaANNO;
    @FXML
    public TableColumn<RegoleRimborsi, String> ColonnaCATEGORIA;
    @FXML
    public TableColumn<RegoleRimborsi, Float> ColonnaMAXANNUO;
    @FXML
    public TableColumn<RegoleRimborsi, Float> colonnaPERCENTUALE;
    @FXML
    public TableColumn<RegoleRimborsi, Float> colonnaMAXSINGOLAPRESTAZIONE;
    @FXML
    public TableColumn<RegoleRimborsi, Integer> colonnaRICORRENZAANNUA;

    private String nomeClasse;
    private ObservableList<RegoleRimborsi> regole = FXCollections.observableArrayList();
    private RegoleRimborsiDao regoleRimborsiDao = new RegoleRimborsiDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaANNO.setCellValueFactory(new PropertyValueFactory<RegoleRimborsi, Integer>("anno"));
        ColonnaCATEGORIA.setCellValueFactory(new PropertyValueFactory<RegoleRimborsi, String>("descrizione"));
        ColonnaMAXANNUO.setCellValueFactory(new PropertyValueFactory<RegoleRimborsi, Float>("maxAnnuo"));
        colonnaPERCENTUALE.setCellValueFactory(new PropertyValueFactory<RegoleRimborsi, Float>("percentuale"));
        colonnaMAXSINGOLAPRESTAZIONE.setCellValueFactory(new PropertyValueFactory<RegoleRimborsi, Float>("maxSingolaPrestazione"));
        colonnaRICORRENZAANNUA.setCellValueFactory(new PropertyValueFactory<RegoleRimborsi, Integer>("ricorrenza"));

        this.regole = regoleRimborsiDao.getAll();
        tableANAGRAFICA.setItems(regole);
        try {
            TableFilter<RegoleRimborsi> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }


    public void InserisciNuovaRegola(ActionEvent event) {
        App.getInstance().gotoInserisciRegolaRimborso();
    }
}