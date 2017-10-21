package controller;

import Pojo.DAO.RimborsoDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciRimborso {

    @FXML
    public DatePicker Date;
    @FXML
    public TextField txtImporto;
    @FXML
    public TextField txtImportoSpesa;

    public void Inserisci(ActionEvent event) {
        try{
        RimborsoDao rimborsoDao=new RimborsoDao();
        LocalDate ld=Date.getValue();
        rimborsoDao.CreaRimborso (ld,
                Float.parseFloat(txtImporto.getText()),
                Float.parseFloat(txtImportoSpesa.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Rimborso inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaRimborsi();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggi  i  dati"+e);
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaRimborsi();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}


