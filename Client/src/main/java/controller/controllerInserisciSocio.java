package controller;

import Pojo.Comune;
import Pojo.Conti;
import Pojo.DAO.ComuneDao;
import Pojo.DAO.ContiDao;
import Pojo.DAO.RegioneDao;
import Pojo.DAO.SocioDao;
import Pojo.Regioni;
import Pojo.Socio;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import javafx.scene.control.Alert;
import procedure.GeneraRegistrazioniOracle;

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
    @FXML
    public ComboBox ComboComune;
    @FXML
    public ComboBox ComboRegione;
    @FXML
    public ComboBox ComboConto;

    public void Inserisci(ActionEvent event) {
        SocioDao socioDao=new SocioDao();
        if (txtSussidio.getText().length()==0)
        {
            LocalDate ld=DateIscrizione.getValue();
            Socio socio=socioDao.CreaSocio(txtCF.getText(),txtCognome.getText(),txtNome.getText(),txtIndirizzo.getText(),
                    txtCitta.getText(),(Comune) ComboComune.getValue(),txtIBAN.getText(),ld,txtCategoria.getText(),(Regioni) ComboRegione.getValue(),(Conti)ComboConto.getValue() );
        }
        else
        {
            LocalDate ld=DateIscrizione.getValue();
            LocalDate ld2=DateIscrizione.getValue();
            socioDao.CreaSocioPensionato(txtCF.getText(),txtCognome.getText(),txtNome.getText(),txtIndirizzo.getText(),
                    txtCitta.getText(),(Comune) ComboComune.getValue(),txtIBAN.getText(),ld,txtCategoria.getText(),ld2,
                    Float.parseFloat(txtReddito.getText()),Float.parseFloat(txtRitenuta.getText()),Float.parseFloat(txtSussidio.getText()), (Regioni)ComboRegione.getValue(),(Conti)ComboConto.getValue());

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Socio inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaSOCI();
    }
    @FXML
    public void initialize() {

        ComuneDao comuneDao=new ComuneDao();
        ComboComune.setItems(comuneDao.getAll());
        RegioneDao regioneDao=new RegioneDao();
        ComboRegione.setItems(regioneDao.getAll());
        ContiDao contiDao=new ContiDao();
        ComboConto.setItems(contiDao.getAll());

        if (App.getInstance().getSocioGlobale()!=null)
        {
            Socio socio=App.getInstance().getSocioGlobale();
            DateIscrizione.setValue(socio.getdataIscrizione());
            txtCF.setText(socio.getCF());
            txtCognome.setText(socio.getCognome());
            txtNome.setText(socio.getNome());
            txtIndirizzo.setText(socio.getIndirizzo());
            txtCitta.setText(socio.getCitta());
            DatePensionamento.setValue(socio.getdataPensionamento());
            txtSussidio.setText(String.valueOf(socio.getsussidioMensile()));
            txtRitenuta.setText(String.valueOf(socio.getritenuta()));
            txtReddito.setText(String.valueOf(socio.getreddito()));
            txtIBAN.setText(socio.getIBAN());
            txtCategoria.setText(socio.getCategoria());
            ComboComune.setValue(socio.getComune());
            ComboRegione.setValue(socio.getRegione());
            ComboConto.setValue(socio.getConto());
            App.getInstance().setSocioGlobale(null);
        }


    }
    public void Annulla(ActionEvent event) {
    App.getInstance().gotoAnagraficaSOCI();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
