package controller;

import Pojo.DAO.PagamentoDao;
import Pojo.Pagamenti;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciPagamento {

    @FXML
    public TextField txtLordo;
    @FXML
    public TextField txtNetto;
    @FXML
    public TextField txtTrattenuta;
    @FXML
    public DatePicker Data;

    public void Inserisci(ActionEvent event) {
        try{
            LocalDate ld=Data.getValue();
        PagamentoDao pagamentoDao=new PagamentoDao();
        pagamentoDao.CreaPagamento(ld,Float.parseFloat(txtLordo.getText()),Float.parseFloat(txtTrattenuta.getText()),Float.parseFloat(txtNetto.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Pagamento inserito");
        alert.showAndWait();
        App.getInstance().gotoAnagraficaPagamenti();}
        catch(Exception e ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere i dati");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaPagamenti();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
