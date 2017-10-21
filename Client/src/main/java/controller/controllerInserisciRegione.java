package controller;

import Pojo.DAO.RegioneDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciRegione {

    @FXML
    public TextField txtRegione;

    public void Inserisci(ActionEvent event) {
        try {
            RegioneDao regioneDao = new RegioneDao();
            regioneDao.CreaRegione(txtRegione.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Regione inserita");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaCOMUNI();}
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori:"+e);
            alert.showAndWait();}
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaREGIONI();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
