package controller;

import Pojo.DAO.SocioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.util.Date;

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
        SocioDao socioDao=new SocioDao();
        if (txtSussidio.getText().length()==0)
        {
            LocalDate ld=DateIscrizione.getValue();
            Date data=new Date( ld.getYear(),ld.getMonthValue(),ld.getDayOfMonth());
            socioDao.CreaSocio(txtCF.getText(),txtCognome.getText(),txtNome.getText(),txtIndirizzo.getText(),
                    txtCitta.getText(),txtComune.getText(),txtIBAN.getText(),data,txtCategoria.getText());

        }
        else
        {
            LocalDate ld=DateIscrizione.getValue();
            Date data=new Date( ld.getYear(),ld.getMonthValue(),ld.getDayOfMonth());
            LocalDate ld2=DateIscrizione.getValue();
            Date data2=new Date( ld2.getYear(),ld2.getMonthValue(),ld2.getDayOfMonth());
            socioDao.CreaSocioPensionato(txtCF.getText(),txtCognome.getText(),txtNome.getText(),txtIndirizzo.getText(),
                    txtCitta.getText(),txtComune.getText(),txtIBAN.getText(),data,txtCategoria.getText(),data2,
                    Float.parseFloat(txtReddito.getText()),Float.parseFloat(txtRitenuta.getText()),Float.parseFloat(txtSussidio.getText()));

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Socio inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagrafica();
    }

    public void Annulla(ActionEvent event) {
    App.getInstance().gotoAnagrafica();
    }
}
