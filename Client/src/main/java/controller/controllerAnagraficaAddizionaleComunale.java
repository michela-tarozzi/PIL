package controller;

import Pojo.AddizionaleComunale;
import Pojo.DAO.AddizionaleComunaleDao;
import Utility.ControllersDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.App;
import org.controlsfx.control.table.TableFilter;

/**
 * Created by m.tarozzi on 15/10/2017.
 * <TableColumn fx:id="ColonnaCODICE" prefWidth="89.0" text="CODICE" />
 <TableColumn fx:id="ColonnaCOMUNE" prefWidth="89.0" text="COMUNE" />
 <TableColumn fx:id="ColonnaANNO" prefWidth="89.0" text="ANNO" />
 <TableColumn fx:id="colonnaSOGLIAMINIMA" prefWidth="89.0" text="SOGLIA MINIMA" />
 <TableColumn fx:id="colonnaSOGLIAMASSIMA" prefWidth="89.0" text="SOGLIA MASSIMA" />
 <TableColumn fx:id="colonnaALIQUOTA" prefWidth="89.0" text="ALIQUOTA'" />
 */
public class controllerAnagraficaAddizionaleComunale {

    @FXML
    public TableView<AddizionaleComunale> tableANAGRAFICA;
    @FXML
    public TableColumn<AddizionaleComunale,String> ColonnaCODICE;
    @FXML
    public TableColumn<AddizionaleComunale,String> ColonnaCOMUNE;
    @FXML
    public TableColumn<AddizionaleComunale,Integer> ColonnaANNO;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTA;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaREDDITOMINIMO;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaREDDITOMASSIMO;

    @FXML
    public TextField txtAnno;


    private String nomeClasse;
    private ObservableList<AddizionaleComunale> addizionaliComunali= FXCollections.observableArrayList();
    private AddizionaleComunaleDao addizionaleComunaleDao=new AddizionaleComunaleDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaCODICE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("codice"));
        ColonnaCOMUNE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("comune"));
        ColonnaANNO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Integer>("anno"));
        colonnaALIQUOTA.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquota"));
        colonnaREDDITOMINIMO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("redditoMinimo"));
        colonnaREDDITOMASSIMO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("redditoMassimo"));


        this.addizionaliComunali=addizionaleComunaleDao.getAll();
        tableANAGRAFICA.setItems(addizionaliComunali);
        try {
            TableFilter<AddizionaleComunale> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }

    public void InserisciNuovoAddizionale(ActionEvent event) {
        App.getInstance().gotoInserisciAddizionaleComunale();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

    public void CalcolaAddizionali(ActionEvent event) {
        AddizionaleComunaleDao addizionaleComunaleDao=new AddizionaleComunaleDao();
        addizionaleComunaleDao.CalcolaPercentualiPerTuttiISoci(Integer.parseInt(txtAnno.getText()));

    }
}
