package controller;

import Pojo.DAO.RegoleRimborsiDao;
import Pojo.DAO.RimborsoDao;
import Pojo.DAO.SocioDao;
import Pojo.DAO.SpeseDao;
import Pojo.RegoleRimborsi;
import Pojo.Rimborsi;
import Pojo.Socio;
import Pojo.Spese;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;
import procedure.GeneraRimborsi;

import java.time.LocalDate;

/**
 * Created by m.tarozzi on 17/10/2017.
 */
public class controllerInserisciSpesa {
    @FXML
    public DatePicker Data;
    @FXML
    public TextField txtNumero;
    @FXML
    public TextField txtImportoSpeso;
    @FXML
    public TextField txtImportoSingolo;
    @FXML
    public ComboBox ComboSocio;
    @FXML
    public ComboBox ComboCategoria;

    private Spese spesaLocal=null;
    @FXML
    public void initialize() {
        SocioDao socioDao=new SocioDao();
        ComboSocio.setItems(socioDao.getAll());
        RegoleRimborsiDao regoleRimborsiDao=new RegoleRimborsiDao();
        ComboCategoria.setItems(regoleRimborsiDao.getAnnoCorrente());
    }

    public void Inserisci(ActionEvent event) {
        try {
            LocalDate ld=Data.getValue();
            SpeseDao speseDao = new SpeseDao();
            spesaLocal=speseDao.CreaSpesa((Socio) ComboSocio.getValue(), txtNumero.getText(),ld,
                    Float.parseFloat(txtImportoSpeso.getText()));
            SocioDao socioDao =new SocioDao();
            Socio socio= (Socio)ComboSocio.getValue();
            socio.addSpesa(spesaLocal);
            socioDao.update(socio);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Spesa inserita");
            alert.showAndWait();
        }catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori "+e );
            alert.showAndWait();
        }
    }

    public void InserisciRimborso(ActionEvent event) {
        if (spesaLocal!=null && txtImportoSingolo.getText()!=null && ComboCategoria.getValue()!=null)
        {
            GeneraRimborsi generaRimborsi= new GeneraRimborsi();
            float importoRimborso=generaRimborsi.generaRimborsi(spesaLocal, Float.parseFloat(txtImportoSingolo.getText()),(RegoleRimborsi) ComboCategoria.getValue());
           if (importoRimborso!=0){
            RimborsoDao rimborsoDao=new RimborsoDao();
            Rimborsi rimborso=rimborsoDao.CreaRimborso(Data.getValue(),importoRimborso,Float.parseFloat(txtImportoSingolo.getText()));
            rimborso.setSpesa(spesaLocal);
            rimborso.setRegola((RegoleRimborsi)ComboCategoria.getValue());
            rimborsoDao.saveOrUpdate(rimborso);
            spesaLocal.addRimborso(rimborso);
            SpeseDao speseDao=new SpeseDao();
            speseDao.saveOrUpdate(spesaLocal);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Totale rimborso: euro "+importoRimborso );
            alert.showAndWait();
           }
        }else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori " );
            alert.showAndWait();}

    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaRimborsi();
    }
    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
