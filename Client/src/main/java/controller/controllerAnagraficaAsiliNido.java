package controller;

import Pojo.AsiliNido;
import Pojo.DAO.AsiliNidoDao;
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
public class controllerAnagraficaAsiliNido {
    @FXML
    public TableView<AsiliNido> tableANAGRAFICA;
    @FXML
    public TableColumn<AsiliNido,Integer> ColonnaANNO;
    @FXML
    public TableColumn<AsiliNido,String> ColonnaFIGLIO;
    @FXML
    public TableColumn<AsiliNido,String> ColonnaSOCIO;
    @FXML
    public TableColumn<AsiliNido,Float> ColonnaSPESA;
    @FXML
    public TableColumn<AsiliNido,Float> colonnaRIMBORSO;
    @FXML
    public TableColumn<AsiliNido,Float> colonnaINTEGRAZIONE;
    @FXML
    public TableColumn<AsiliNido,Float> colonnaPERCENTUALE;

    private String nomeClasse;
    private ObservableList<AsiliNido> asili= FXCollections.observableArrayList();
    private AsiliNidoDao asiliNidoDao=new AsiliNidoDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaANNO.setCellValueFactory(new PropertyValueFactory<AsiliNido, Integer>("anno"));
        ColonnaFIGLIO.setCellValueFactory(new PropertyValueFactory<AsiliNido, String>("figlio"));
        ColonnaSOCIO.setCellValueFactory(new PropertyValueFactory<AsiliNido, String>("socio"));
        ColonnaSPESA.setCellValueFactory(new PropertyValueFactory<AsiliNido, Float>("spesa"));
        colonnaRIMBORSO.setCellValueFactory(new PropertyValueFactory<AsiliNido, Float>("rimborso"));
        colonnaINTEGRAZIONE.setCellValueFactory(new PropertyValueFactory<AsiliNido, Float>("integrazione"));
        colonnaPERCENTUALE.setCellValueFactory(new PropertyValueFactory<AsiliNido, Float>("percentuale"));

        this.asili=asiliNidoDao.getAll();
        tableANAGRAFICA.setItems(asili);
        try {
            TableFilter<AsiliNido> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }


    public void InserisciNuovoRimborsoAsilo(ActionEvent event) {
        App.getInstance().gotoInserisciAsilo();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
