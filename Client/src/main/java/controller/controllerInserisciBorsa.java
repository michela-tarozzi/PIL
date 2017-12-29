package controller;

import Pojo.DAO.BorseDiStudioDao;
import Pojo.DAO.SocioDao;
import Pojo.Socio;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

import java.util.function.Predicate;

public class controllerInserisciBorsa {
    @FXML
    public TextField txtAnno;
    @FXML
    public TextField txtFiglio;
    @FXML
    public TextField txtIBAN;
    @FXML
    public TextField txtCFU;
    @FXML
    public TextField txtLordo;
    @FXML
    public TextField txtRitenuta;
    @FXML
    public TextField txtNetto;
    @FXML
    public ComboBox comboSocio;

    public void Inserisci(ActionEvent event) {
        try{
        BorseDiStudioDao borseDiStudioDao=new BorseDiStudioDao();
            borseDiStudioDao.CreaBorsaDiStudio((Socio)comboSocio.getValue(),
                txtFiglio.getText(),
                Integer.parseInt(txtAnno.getText()),
                Integer.parseInt(txtCFU.getText()),
                txtIBAN.getText(),
                    Float.parseFloat(txtLordo.getText()),
                    Float.parseFloat( txtNetto.getText()),
                    Float.parseFloat( txtRitenuta.getText()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
        alert.setHeaderText("Borsa inserita");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaBorse();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggi gli errori "+e);
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaBorse();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
    @FXML
    public void initialize() {
        SocioDao socioDao=new SocioDao();
        ObservableList<Socio> soci=socioDao.getAll();
        Predicate<Socio> predicate=new Predicate<Socio>() {
            @Override
            public boolean test(Socio socio) {
                return !(socio.getCategoria().equals("PENSIONATO")||socio.getCategoria().equals("DECEDUTO")||socio.getCategoria().equals("DIMESSO"));
            }
        };
        soci.filtered(predicate).sorted();
        comboSocio.setItems(soci.filtered(predicate).sorted());
    }

}
