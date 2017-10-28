package controller;

import Pojo.DAO.RimborsoDao;
import Pojo.DAO.SocioDao;
import Pojo.Pagamenti;
import Pojo.Rimborsi;
import Pojo.Socio;
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
import org.controlsfx.control.table.TableFilter;

/**
 * Created by m.tarozzi on 15/10/2017.
 *
 */
public class controllerAnagraficaRimborsi {

    @FXML
    public TableView<Rimborsi> tableANAGRAFICA;
    @FXML
    public TableColumn<Rimborsi,String> ColonnaDATA;
    @FXML
    public TableColumn<Rimborsi,Float> ColonnaIMPORTO;
    @FXML
    public TableColumn<Rimborsi,Float> ColonnaIMPORTOSPESA;
    @FXML
    public TableColumn<Rimborsi,String> ColonnaSPESA;
    @FXML
    public TableColumn <Rimborsi,String>  ColonnaREGOLA;
    @FXML
    public TableColumn <Rimborsi, String> ColonnaPAGAMENTO;

    private String nomeClasse;
    private ObservableList<Rimborsi> rimborsi= FXCollections.observableArrayList();
    private RimborsoDao rimborsiDao=new RimborsoDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaDATA.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("data"));
        ColonnaREGOLA.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("regola"));
        ColonnaIMPORTO.setCellValueFactory(new PropertyValueFactory<Rimborsi, Float>("importo"));
        ColonnaIMPORTOSPESA.setCellValueFactory(new PropertyValueFactory<Rimborsi, Float>("importoSpesa"));
        ColonnaSPESA.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("spesa"));
        ColonnaPAGAMENTO.setCellValueFactory(new PropertyValueFactory<Rimborsi, String>("pagamento"));
        this.rimborsi=rimborsiDao.getAll();
        tableANAGRAFICA.setItems(rimborsi);
        try {
            TableFilter<Rimborsi> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }


    public void InserisciNuovoRimborso(ActionEvent event) {
        App.getInstance().gotoInserisciRimborso();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
