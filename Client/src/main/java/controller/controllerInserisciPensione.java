package controller;

import Pojo.DAO.PensioniDao;
import Pojo.DAO.SocioDao;
import Pojo.Pensioni;
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

    private Pensioni pensione;

    public void Inserisci(ActionEvent event) {
        if (pensione!=null)
        {
            pensione.setSocio((Socio) ComboSocio.getValue());
            pensione.setRitenuta(Float.parseFloat(txtRitenuta.getText()));
            pensione.setNetto( Float.parseFloat(txtNetto.getText()));
            pensione.setLordo( Float.parseFloat(txtLordo.getText()));
            pensione.setAddizionaleComunale(Float.parseFloat(txtComunale.getText()));
            pensione.setAddizionaleRegionale( Float.parseFloat(txtRegionale.getText()));
            pensione.setData(Date.getValue());
            pensione.setCarovita( Float.parseFloat(txtCarovita.getText()));
            pensione.setSussidio(Float.parseFloat(txtSussidio.getText()));
            App.getInstance().setPensioneGlobale(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifica");
            alert.setHeaderText("Pensione aggiornata");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaPensioni();
        }else
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
        pensione=null;
        SocioDao socioDao=new SocioDao();
        ComboSocio.setItems(socioDao.getAll());
        if (App.getInstance().getPensioneGlobale()!=null)
        {
            pensione=App.getInstance().getPensioneGlobale();
            Date.setValue(pensione.getData());
            ComboSocio.setValue(pensione.getSocio());
            txtSussidio.setText(String.valueOf(pensione.getSussidio()));
            txtRitenuta.setText(String.valueOf(pensione.getRitenuta()));
            txtCarovita.setText(String.valueOf(pensione.getCarovita()));
            txtNetto.setText(String.valueOf(pensione.getNetto()));
            txtLordo.setText(String.valueOf(pensione.getLordo()));
            txtRegionale.setText(String.valueOf(pensione.getAddizionaleRegionale()));
            txtComunale.setText(String.valueOf(pensione.getAddizionaleComunale()));
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaPensioni();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
