package controller;

import Pojo.DAO.RimborsoDao;
import Pojo.DAO.SocioDao;
import Pojo.Rimborsi;
import Pojo.Socio;
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
 *
 */
public class controllerAnagraficaRimborsi {

    @FXML
    public TableView<Rimborsi> tableANAGRAFICA;
    @FXML
    public TableColumn<Rimborsi,String> ColonnaDATA;
    @FXML
    public TableColumn<Rimborsi,Float> ColonnaIMPORTO;
    @FXML
    public TableColumn<Rimborsi,Float> ColonnaIMPORTOSPESA;

    private String nomeClasse;
    private ObservableList<Rimborsi> rimborsi= FXCollections.observableArrayList();
    private RimborsoDao rimborsiDao=new RimborsoDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaDATA.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("data"));
        ColonnaIMPORTO.setCellValueFactory(new PropertyValueFactory<Rimborsi, Float>("importo"));
        ColonnaIMPORTOSPESA.setCellValueFactory(new PropertyValueFactory<Rimborsi, Float>("importoSpesa"));

        this.rimborsi=rimborsiDao.getAll();
        tableANAGRAFICA.setItems(rimborsi);
    }


    public void InserisciNuovoRimborso(ActionEvent event) {
        App.getInstance().gotoInserisciRimborso();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
