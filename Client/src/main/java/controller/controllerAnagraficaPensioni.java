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
import org.controlsfx.control.table.TableFilter;
import procedure.GeneraEstrazioniDati;

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
    @FXML
    public TableColumn<Pensioni,String> colonnaSOCIO;

    private String nomeClasse;
    private ObservableList<Pensioni> pensioni= FXCollections.observableArrayList();
    private PensioniDao pensioniDao=new PensioniDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colonnaSOCIO.setCellValueFactory(new PropertyValueFactory<Pensioni, String>("socio"));
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
        try {
            TableFilter<Pensioni> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }

    public void InserisciNuovaPensione(ActionEvent event) {
        App.getInstance().gotoInserisciPensione();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

    public void Modifica(ActionEvent event) {
        int numeroRiga=tableANAGRAFICA.getSelectionModel().getFocusedIndex();
        App.getInstance().setPensioneGlobale(tableANAGRAFICA.getItems().get(numeroRiga));
        App.getInstance().gotoInserisciPensione();
    }

    public void Estrai(ActionEvent event) {
        GeneraEstrazioniDati.GeneraEstrazioneDati(tableANAGRAFICA.getItems());
    }
}
