package controller;

import Pojo.DAO.PensioniDao;
import Pojo.DAO.SocioDao;
import Pojo.Socio;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciPensione {

    @FXML
    public TextField txtSussidio;
    @FXML
    public TextField txtCarovita;
    @FXML
    public TextField txtNetto;
    @FXML
    public TextField txtLordo;
    @FXML
    public TextField txtRitenuta;
    @FXML
    public TextField txtComunale;
    @FXML
    public DatePicker Date;
    @FXML
    public TextField txtRegionale;
    @FXML
    public ComboBox ComboSocio;

    public void Inserisci(ActionEvent event) {
        try {
            LocalDate ld=Date.getValue();
            PensioniDao pensioniDao = new PensioniDao();
            pensioniDao.CreaPensione((Socio) ComboSocio.getValue(),ld,
                    Float.parseFloat(txtSussidio.getText()),
                    Float.parseFloat(txtCarovita.getText()),
                    Float.parseFloat(txtNetto.getText()),
                    Float.parseFloat(txtLordo.getText()),
                    Float.parseFloat(txtRitenuta.getText()),
                    Float.parseFloat(txtComunale.getText()),
                    Float.parseFloat(txtRegionale.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Pensione inserito");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaPensioni();
        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori "+e );
            alert.showAndWait();
        }
    }
    @FXML
    public void initialize() {
        SocioDao socioDao=new SocioDao();
        ComboSocio.setItems(socioDao.getAll());
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaPensioni();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
