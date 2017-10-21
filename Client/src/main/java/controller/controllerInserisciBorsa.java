package controller;

import Pojo.DAO.BorseDiStudioDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 * <TextField fx:id="txtAnno" GridPane.columnIndex="2" />
 <TextField fx:id="txtFiglio" GridPane.columnIndex="2" GridPane.rowIndex="1" />
 <TextField fx:id="txtIBAN" GridPane.columnIndex="2" GridPane.rowIndex="2" />
 <TextField fx:id="txtCFU" GridPane.columnIndex="2" GridPane.rowIndex="3" />
 <TextField fx:id="txtLordo" GridPane.columnIndex="2" GridPane.rowIndex="4" />
 <TextField fx:id="txtRitenuta" GridPane.columnIndex="2" GridPane.rowIndex="5" />
 <TextField fx:id="txtNetto" GridPane.columnIndex="2" GridPane.rowIndex="6" />
 */
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

    public void Inserisci(ActionEvent event) {
        try{
        BorseDiStudioDao borseDiStudioDao=new BorseDiStudioDao();
            borseDiStudioDao.CreaBorsaDiStudio(
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
            alert.setHeaderText("Correggi gli errori");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaBorse();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
