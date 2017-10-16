package controller;

import Pojo.DAO.AsiliNidoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciAsiloNido {
    @FXML
    public TextField txtAnno;
    @FXML
    public TextField txtFiglio;
    @FXML
    public TextField txtSpesa;
    @FXML
    public TextField txtRimborso;
    @FXML
    public TextField txtIntegrazione;
    @FXML
    public TextField txtPercentuale;

    public void Inserisci(ActionEvent event) {
        try{
        AsiliNidoDao asiliNidoDao=new AsiliNidoDao();
        asiliNidoDao.CreaAsiloNido(txtFiglio.getText(),
                Integer.parseInt(txtAnno.getText()),
                Float.parseFloat(txtSpesa.getText()),
                Float.parseFloat(txtRimborso.getText()),
                Float.parseFloat(txtIntegrazione.getText()),
                Float.parseFloat(txtPercentuale.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Socio inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaAsiliNido();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Scorreggi i dati");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaSOCI();
    }


}
