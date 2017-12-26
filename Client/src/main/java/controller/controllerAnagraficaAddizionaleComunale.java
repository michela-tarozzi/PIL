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
    public TableColumn<AddizionaleComunale,String> colonnaFASCIAZERO;
    @FXML
    public TableColumn<AddizionaleComunale,String> colonnaFASCIAUNO;
    @FXML
    public TableColumn<AddizionaleComunale,String> colonnaFASCIADUE;
    @FXML
    public TableColumn<AddizionaleComunale,String> colonnaFASCIATRE;
    @FXML
    public TableColumn<AddizionaleComunale,String> colonnaFASCIAQUATTRO;
    @FXML
    public TableColumn<AddizionaleComunale,String> colonnaFASCIACINQUE;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTAZERO;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTAUNO;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTADUE;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTATRE;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTAQUATTRO;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaALIQUOTACINQUE;
    @FXML
    public TableColumn<AddizionaleComunale,Float> colonnaSOGLIAESENTE;
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
        colonnaFASCIAZERO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("fasciaZero"));
        colonnaFASCIAUNO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("fasciaUno"));
        colonnaFASCIADUE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("fasciaDue"));
        colonnaFASCIATRE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("fasciaTre"));
        colonnaFASCIAQUATTRO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("fasciaQuattro"));
        colonnaFASCIACINQUE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, String>("fasciaCinque"));
        colonnaALIQUOTAZERO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquotaZero"));
        colonnaALIQUOTAUNO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquotaUno"));
        colonnaALIQUOTADUE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquotaDue"));
        colonnaALIQUOTATRE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquotaTre"));
        colonnaALIQUOTAQUATTRO.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquotaQuattro"));
        colonnaALIQUOTACINQUE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("aliquotaCinque"));
        colonnaSOGLIAESENTE.setCellValueFactory(new PropertyValueFactory<AddizionaleComunale, Float>("sogliaEsente"));


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
        addizionaleComunaleDao.CalcolaAliquoteAddizionali(Integer.parseInt(txtAnno.getText()));
    }
}
