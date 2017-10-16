package controller;

import Pojo.DAO.RegoleRimborsiDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerInserisciRegoleRimborsi {
    @FXML
    public TextField txtAnno;
    @FXML
    public TextField txtCategoria;
    @FXML
    public TextField txtMaxAnnuo;
    @FXML
    public TextField txtPercentuale;
    @FXML
    public TextField txtMaxSingolaPrestazione;
    @FXML
    public TextField txtRicorrenza;

    public void Inserisci(ActionEvent event) {
        try {
            RegoleRimborsiDao regoleRimborsiDao = new RegoleRimborsiDao();
            regoleRimborsiDao.CreaRegola(
                    Integer.parseInt(txtAnno.getText()),
                    txtCategoria.getText(),
                    Float.parseFloat(txtMaxAnnuo.getText()),
                    Float.parseFloat(txtPercentuale.getText()),
                    Float.parseFloat(txtMaxSingolaPrestazione.getText()),
                    Integer.parseInt(txtRicorrenza.getText())
            );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Regola inserita");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaREGOLERIMBORSI();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggere gli errori");
            alert.showAndWait();
        }
    }

    public void Annulla(ActionEvent event) {
        App.getInstance().gotoAnagraficaREGOLERIMBORSI();
    }

}
