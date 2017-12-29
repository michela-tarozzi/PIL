package controller;

import Pojo.AddizionaleComunale;
import Pojo.Socio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 26/12/2017.
 */
public class controllerSceltaAddizionale {
    @FXML
    public TextField txtComune;
    @FXML
    public TextField txtSocio;
    @FXML
    public TextField txtReddito;
    @FXML
    public RadioButton RDZERO;
    @FXML
    public RadioButton RDUNO;
    @FXML
    public RadioButton RDDUE;
    @FXML
    public RadioButton RDTRE;
    @FXML
    public RadioButton RDQUATTRO;
    @FXML
    public RadioButton RDCINQUE;

    public Socio socio;
    public AddizionaleComunale addizionaleComunale;

    public void Inserisci(ActionEvent event) {
    }

    @FXML
    public void initialize() {

        if (App.getInstance().getSocioGlobale()!=null)
        {
            socio=App.getInstance().getSocioGlobale();
            txtSocio.setText(socio.toString());
            txtReddito.setText(String.valueOf(socio.getreddito()));
            txtComune.setText(socio.getComune().toString());
            App.getInstance().setSocioGlobale(null);
            addizionaleComunale=App.getInstance().getAddizionaleComunaleGlobale();
        }
    }
}
