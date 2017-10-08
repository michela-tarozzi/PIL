package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class controllerInserisciSocio {

    @FXML
    public DatePicker DateIscrizione;
    @FXML
    public TextField txtCF;
    @FXML
    public TextField txtCognome;
    @FXML
    public TextField txtNome;
    @FXML
    public TextField txtIndirizzo;
    @FXML
    public TextField txtComune;
    @FXML
    public TextField txtCitta;
    @FXML
    public DatePicker DatePensionamento;
    @FXML
    public TextField txtSussidio;
    @FXML
    public TextField txtRitenuta;
    @FXML
    public TextField txtReddito;
    @FXML
    public TextField txtIBAN;
    @FXML
    public TextField txtCategoria;

    public void Inserisci(ActionEvent event) {
    }

    public void Annulla(ActionEvent event) {
    }
}
