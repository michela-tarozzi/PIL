package controller;

import Pojo.DAO.AddizionaleRegionaleDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciAddizionaleRegionale {

    @FXML
    public TextField txtREGIONE;
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
            AddizionaleRegionaleDao addizionaleRegionaleDao=new AddizionaleRegionaleDao();
            addizionaleRegionaleDao.CreaAddizionaleRegionale(txtREGIONE.getText(),Integer.parseInt(txtANNO.getText()),
                    Float.parseFloat(txtSOGLIAMINIMA.getText()),
                    Float.parseFloat(txtSOGLIAMASSIMA.getText()),
                    Float.parseFloat(txtALIQUOTA.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Addizionale Regionale inserito");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaREGIONALI();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaREGIONALI();
    }

    public void tornaHome(Event event) { App.getInstance().gotoHOME(); }
}
