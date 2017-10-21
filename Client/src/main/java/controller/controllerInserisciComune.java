package controller;

import Pojo.DAO.ComuneDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciComune {

    @FXML
    public TextField txtCodice;
    @FXML
    public TextField txtComune;

    public void Inserisci(ActionEvent event) {
        try {
            ComuneDao comuneDao = new ComuneDao();
            comuneDao.CreaComune(txtCodice.getText(), txtComune.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Comune inserito");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaCOMUNI();}
        catch (Exception e){Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori");
            alert.showAndWait();}
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaCOMUNI();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
