package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import procedure.GeneraRegistrazioniOracle;

import javax.swing.*;

/**
 * Created by Michela on 02/01/2018.
 */
public class controllerGeneraRegistrazioneFIMIV {
    @FXML
    public TextField txtAnno;
    @FXML
    public TextField txtImportoFIMIV;
    @FXML
    public TextField txtImportoTessere;
    @FXML
    public TextField txtImportoSpesePostali;

    public void Genera(ActionEvent actionEvent) {
        GeneraRegistrazioniOracle gro = new GeneraRegistrazioniOracle();
        try {
            gro.generaIscrizioneFimiv(Float.parseFloat(txtImportoFIMIV.getText()),Float.parseFloat(txtImportoTessere.getText()), Float.parseFloat(txtImportoSpesePostali.getText()),Integer.parseInt(txtAnno.getText()));
        }
        catch (Exception e ){
            JOptionPane.showMessageDialog(null,"i dati inseriti non sono corretti");
        }
        JOptionPane.showMessageDialog(null,"File Generato");
    }
}
