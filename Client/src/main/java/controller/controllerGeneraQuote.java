package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import procedure.GeneraQuote;

/**
 * Created by m.tarozzi on 16/10/2017.
 */
public class controllerGeneraQuote {

    @FXML
    public DatePicker Data;

    public void Genera(ActionEvent event) {
        GeneraQuote generaQuote= new GeneraQuote();
        generaQuote.generaQuote(Data.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Quote Generate");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaQuote();
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaQuote();
    }

    public void Stima(ActionEvent event) {
        GeneraQuote generaQuote= new GeneraQuote();
        float importo=generaQuote.StimaQuote();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Stima: "+importo);
        alert.showAndWait();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
