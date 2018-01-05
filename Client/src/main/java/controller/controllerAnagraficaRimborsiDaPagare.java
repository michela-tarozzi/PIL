package controller;

import Pojo.DAO.RegoleRimborsiDao;
import Pojo.DAO.RimborsoDao;
import Pojo.Rimborsi;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import org.controlsfx.control.table.TableFilter;
import procedure.GeneraPagamenti;
import procedure.GeneraRegistrazioniOracle;

/**
 * Created by m.tarozzi on 18/10/2017.
 */
public class controllerAnagraficaRimborsiDaPagare {
    @FXML
    public TableView<Rimborsi> tableANAGRAFICA;
    @FXML
    public TableColumn<Rimborsi,String> ColonnaSocio;
    @FXML
    public TableColumn<Rimborsi,String> ColonnaDATA;
    @FXML
    public TableColumn<Rimborsi,Float> ColonnaIMPORTO;
    @FXML
    public TableColumn<Rimborsi,Float> ColonnaIMPORTOSPESA;
    @FXML
    public DatePicker Data;

    private String nomeClasse;
    private ObservableList<Rimborsi> rimborsi= FXCollections.observableArrayList();
    private RimborsoDao rimborsiDao=new RimborsoDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaSocio.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("spesa"));
        ColonnaDATA.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("data"));
        ColonnaIMPORTO.setCellValueFactory(new PropertyValueFactory<Rimborsi, Float>("importo"));
        ColonnaIMPORTOSPESA.setCellValueFactory(new PropertyValueFactory<Rimborsi, Float>("importoSpesa"));

        this.rimborsi=rimborsiDao.getRimborsiDaPagare();
        tableANAGRAFICA.setItems(rimborsi);
        try {
            TableFilter<Rimborsi> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }


    public void PagaRimborsi(ActionEvent event) {
        try{
        RimborsoDao rimborsoDao=new RimborsoDao();
        GeneraPagamenti generaPagamenti=new GeneraPagamenti();
        ObservableList<Rimborsi> rimb=tableANAGRAFICA.getItems();
        generaPagamenti.GeneraPagamentoRimborsi(rimb,Data.getValue());
            GeneraRegistrazioniOracle gro=new GeneraRegistrazioniOracle();
            gro.generaRimborsiAssistenziali(rimb,Data.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Pagamenti Generati");
            alert.showAndWait();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inserimento");
            alert.setHeaderText("Correggi  i  dati"+e);
            alert.showAndWait();
        }

    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }
}
