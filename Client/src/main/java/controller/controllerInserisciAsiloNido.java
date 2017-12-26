package controller;

import Pojo.DAO.AsiliNidoDao;
import Pojo.DAO.SocioDao;
import Pojo.Socio;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.App;

import java.util.function.Predicate;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciAsiloNido {
    @FXML
    public TextField txtAnno;
    @FXML
    public TextField txtFiglio;
    @FXML
    public TextField txtSpesa;
    @FXML
    public TextField txtRimborso;
    @FXML
    public TextField txtIntegrazione;
    @FXML
    public ComboBox comboSocio;


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


    public void Inserisci(ActionEvent event) {
        try{
        AsiliNidoDao asiliNidoDao=new AsiliNidoDao();
        asiliNidoDao.CreaAsiloNido((Socio)comboSocio.getValue(),txtFiglio.getText(),
                Integer.parseInt(txtAnno.getText()),
                Float.parseFloat(txtSpesa.getText()),
                Float.parseFloat(txtRimborso.getText()),
                Float.parseFloat(txtIntegrazione.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Socio inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaAsiliNido();}
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Scorreggi i dati");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaSOCI();
    }


    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
