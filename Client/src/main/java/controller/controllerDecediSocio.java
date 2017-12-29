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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

import java.time.LocalDate;

/**
 * Created by m.tarozzi on 16/11/2017.
 */
public class controllerDecediSocio {

    @FXML
    public TextField txtCF;
    @FXML
    public TextField txtCognome;
    @FXML
    public TextField txtNome;
    @FXML
    public DatePicker DateDecesso;
    @FXML
    public ComboBox ComboConto;

    public Socio socio;

    public void Inserisci(ActionEvent event) {
        SocioDao socioDao = new SocioDao();
        socioDao.uccidiSocio(socio,DateDecesso.getValue());
        // TODO: 18/11/2017 stampa della registrazione e del libro soci
        //todo: stampa della lettera per gli eredi
        App.getInstance().gotoAnagraficaSOCI();
    }
    @FXML
    public void initialize() {
        ContiDao contiDao=new ContiDao();
        ComboConto.setItems(contiDao.getAll());

        if (App.getInstance().getSocioGlobale()!=null)
        {
            socio=App.getInstance().getSocioGlobale();
            txtCF.setText(socio.getCF());
            txtCognome.setText(socio.getCognome());
            txtNome.setText(socio.getNome());
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
