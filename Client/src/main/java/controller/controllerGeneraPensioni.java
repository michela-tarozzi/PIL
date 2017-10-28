package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import procedure.GeneraPensioni;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerGeneraPensioni {

    @FXML
    public CheckBox CKaddizionali;
    @FXML
    public DatePicker Data;
    @FXML
    public TextField txtAnno;
    @FXML
    public CheckBox CKTredicesima;

    public void Genera(ActionEvent event) {
        GeneraPensioni generaPensioni= new GeneraPensioni();
        int anno=0;
        if (CKaddizionali.isSelected()){
            anno=Integer.parseInt(txtAnno.getText());
         }
        generaPensioni.Generapensioni(CKaddizionali.isSelected(),Data.getValue(),anno,CKTredicesima.isSelected());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Pensioni Generate");
        alert.showAndWait();
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaPensioni();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
