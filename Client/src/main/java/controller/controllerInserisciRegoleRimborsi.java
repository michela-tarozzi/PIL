package controller;

import Pojo.DAO.RegoleRimborsiDao;
import Pojo.RegoleRimborsi;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

    private RegoleRimborsi regola;
    public void Inserisci(ActionEvent event) {
        RegoleRimborsiDao regoleRimborsiDao = new RegoleRimborsiDao();
        if(regola!=null){
            regola.setAnno(Integer.parseInt(txtAnno.getText()));
            regola.setPercentuale(Float.parseFloat(txtPercentuale.getText()));
            regola.setRicorrenza(Integer.parseInt(txtRicorrenza.getText()));
            regola.setMaxSingolaPrestazione(Float.parseFloat(txtMaxSingolaPrestazione.getText()));
            regola.setMaxAnnuo(Float.parseFloat(txtMaxAnnuo.getText()));
            regola.setDescrizione(txtCategoria.getText());
            regoleRimborsiDao.update(regola);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifica");
            alert.setHeaderText("Regola aggiornata");
            alert.showAndWait();
            App.getInstance().gotoAnagraficaREGOLERIMBORSI();
        }else
        try {
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

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

    public void initialize() {
        regola =null;
        if (App.getInstance().getRegolaRimborsoGlobale()!=null)
        {
            regola=App.getInstance().getRegolaRimborsoGlobale();
            txtCategoria.setText(regola.getDescrizione());
            txtRicorrenza.setText(String.valueOf(regola.getRicorrenza()));
            txtMaxAnnuo.setText(String.valueOf(regola.getMaxAnnuo()));
            txtPercentuale.setText(String.valueOf(regola.getPercentuale()));
            txtMaxSingolaPrestazione.setText(String.valueOf(regola.getMaxSingolaPrestazione()));
            txtAnno.setText(String.valueOf(regola.getAnno()));
        }


    }

}
