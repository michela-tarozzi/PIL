package controller;

import Pojo.DAO.RegioneDao;
import Pojo.Regioni;
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
public class controllerAnagraficaRegioni {

    @FXML
    public TableView tableANAGRAFICA;
    @FXML
    public TableColumn<Regioni, String> ColonnaREGIONE;


    private String nomeClasse;
    private ObservableList<Regioni> regioni= FXCollections.observableArrayList();
    private RegioneDao regioneDao=new RegioneDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaREGIONE.setCellValueFactory(new PropertyValueFactory<Regioni, String>("nome"));
        this.regioni=regioneDao.getAll();
        tableANAGRAFICA.setItems(regioni);
    }

    public void InserisciNuovaRegione(ActionEvent event) {
        App.getInstance().gotoInserisciRegione();
    }
}
