package controller;

import Pojo.AddizionaleRegionale;
import Pojo.DAO.AddizionaleRegionaleDao;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerAnagraficaAddizionaleRegionale {

        @FXML
        public TableView<AddizionaleRegionale> tableANAGRAFICA;

        @FXML
        public TableColumn<AddizionaleRegionale,String> colonnaREGIONE;
        @FXML
        public TableColumn<AddizionaleRegionale,Integer> ColonnaANNO;
        @FXML
        public TableColumn<AddizionaleRegionale,Float> colonnaSOGLIAMINIMA;
        @FXML
        public TableColumn<AddizionaleRegionale,Float> colonnaSOGLIAMASSIMA;
        @FXML
        public TableColumn<AddizionaleRegionale,Float> colonnaALIQUOTA;

        private String nomeClasse;
        private ObservableList<AddizionaleRegionale> addizionaliRegionali = FXCollections.observableArrayList();
        private AddizionaleRegionaleDao addizionaleRegionaleDao =new AddizionaleRegionaleDao();

        @FXML
        public void initialize() {
            nomeClasse = this.getClass().getName();
            ControllersDispatcher.setController(nomeClasse, this);
            tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
              colonnaREGIONE.setCellValueFactory(new PropertyValueFactory<AddizionaleRegionale, String>("regione"));
            ColonnaANNO.setCellValueFactory(new PropertyValueFactory<AddizionaleRegionale, Integer>("anno"));
            colonnaSOGLIAMINIMA.setCellValueFactory(new PropertyValueFactory<AddizionaleRegionale, Float>("sogliaMinima"));
            colonnaSOGLIAMASSIMA.setCellValueFactory(new PropertyValueFactory<AddizionaleRegionale, Float>("sogliaMassima"));
            colonnaALIQUOTA.setCellValueFactory(new PropertyValueFactory<AddizionaleRegionale, Float>("aliquota"));

            this.addizionaliRegionali = addizionaleRegionaleDao.getAll();
            tableANAGRAFICA.setItems(addizionaliRegionali);
        }

        public void InserisciNuovoAddizionaleRegionale(ActionEvent event) { App.getInstance().gotoInserisciAddizionaleRegionale(); }

        public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
