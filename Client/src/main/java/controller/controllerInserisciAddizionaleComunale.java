package controller;

import Pojo.DAO.AddizionaleComunaleDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

public class controllerInserisciAddizionaleComunale {

    @FXML
    public TextField txtCODICE;
    @FXML
    public TextField txtCOMUNE;
    @FXML
    public TextField txtANNO;
    @FXML
    public TextField txtSOGLIAMINIMA;
    @FXML
    public TextField txtSOGLIAMASSIMA;
    @FXML
    public TextField txtALIQUOTA;


    public void Inserisci(ActionEvent event) {
        try{
        AddizionaleComunaleDao addizionaleComunaleDao=new AddizionaleComunaleDao();
        addizionaleComunaleDao.CreaAddizionaleComunale(txtCODICE.getText(),txtCOMUNE.getText(),Integer.parseInt(txtANNO.getText()),
                Float.parseFloat(txtSOGLIAMINIMA.getText()),
                Float.parseFloat(txtSOGLIAMASSIMA.getText()),
                        Float.parseFloat(txtALIQUOTA.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Addizionale Comunale inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaCOMUNALI();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaCOMUNALI();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
