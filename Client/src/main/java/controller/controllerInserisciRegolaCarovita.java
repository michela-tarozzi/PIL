package controller;

import Pojo.DAO.RegoleCarovitaDao;
import Pojo.RegoleCarovita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciRegolaCarovita {
    @FXML
    public TextField txtANNO;
    @FXML
    public TextField txtPERCENTUALE;

    public void Inserisci(ActionEvent event) {
        try {
            RegoleCarovitaDao regoleCarovitaDao = new RegoleCarovitaDao();
            regoleCarovitaDao.CreaRegola(Integer.parseInt(txtANNO.getText()), Float.parseFloat(txtPERCENTUALE.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Regola inserita");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaREGOLECAROVITA();}
        catch (Exception e){Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori");
            alert.showAndWait();}
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaREGOLECAROVITA();
    }

}
