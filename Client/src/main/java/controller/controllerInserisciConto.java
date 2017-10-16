package controller;

import Pojo.DAO.ContiDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciConto {

    @FXML
    public TextField txtNumero;
    @FXML
    public TextField txtDescrizione;

    public void Inserisci(ActionEvent event) {
        try {
            ContiDao contoDao = new ContiDao();
            contoDao.CreaConto(txtNumero.getText(), txtDescrizione.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Conto inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaCONTI();}
        catch (Exception e){Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori");
            alert.showAndWait();}
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaCONTI();
    }
}
