package controller;

import Pojo.DAO.PensioniDao;
import Pojo.Pensioni;
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

import java.util.Date;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerAnagraficaPensioni {

    @FXML
    public TableView<Pensioni> tableANAGRAFICA;
    @FXML
    public TableColumn<Pensioni,String> colonnaDATA;
    @FXML
    public TableColumn<Pensioni,Float> ColonnaCSUSSIDIO;
    @FXML
    public TableColumn<Pensioni,Float> ColonnaCAROVITA;
    @FXML
    public TableColumn<Pensioni,Float> ColonnaLORDO;
    @FXML
    public TableColumn<Pensioni,Float> colonnaRITENUTA;
    @FXML
    public TableColumn<Pensioni,Float> colonnaCOMUNALE;
    @FXML
    public TableColumn<Pensioni,Float> colonnaREGIONALE;
    @FXML
    public TableColumn<Pensioni,Float> colonnaNETTO;

    private String nomeClasse;
    private ObservableList<Pensioni> pensioni= FXCollections.observableArrayList();
    private PensioniDao pensioniDao=new PensioniDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colonnaDATA.setCellValueFactory(new PropertyValueFactory<Pensioni, String>("data"));
        ColonnaCSUSSIDIO.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("sussidio"));
        ColonnaCAROVITA.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("carovita"));
        ColonnaLORDO.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("lordo"));
        colonnaRITENUTA.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("ritenuta"));
        colonnaCOMUNALE.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("addizionaleComunale"));
        colonnaREGIONALE.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("addizionaleRegionale"));
        colonnaNETTO.setCellValueFactory(new PropertyValueFactory<Pensioni, Float>("netto"));
        this.pensioni=pensioniDao.getAll();
        tableANAGRAFICA.setItems(pensioni);
    }

    public void InserisciNuovaPensione(ActionEvent event) {
        App.getInstance().gotoInserisciPensione();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
