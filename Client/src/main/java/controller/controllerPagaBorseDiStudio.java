package controller;

import Pojo.BorseDiStudio;
import Pojo.DAO.BorseDiStudioDao;
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

/**
 * Created by m.tarozzi on 14/11/2017.
 */
public class controllerPagaBorseDiStudio {

    @FXML
    public TableView<BorseDiStudio> tableANAGRAFICA;
    @FXML
    public TableColumn<BorseDiStudio,String> colonnaANNO;
    @FXML
    public TableColumn<BorseDiStudio,String> ColonnaNOMEFIGLIO;
    @FXML
    public TableColumn<BorseDiStudio,String> ColonnaIBANFIGLIO;
    @FXML
    public TableColumn<BorseDiStudio,Integer> ColonnaCFU;
    @FXML
    public TableColumn<BorseDiStudio,Float> colonnaLORDO;
    @FXML
    public TableColumn<BorseDiStudio,Float> colonnaRITENUTA;
    @FXML
    public TableColumn<BorseDiStudio,Float> colonnaNETTO;
    @FXML
    public TableColumn<BorseDiStudio,String> colonnaSOCIO;
    @FXML
    public DatePicker dataPagamento;

    private String nomeClasse;
    private ObservableList<BorseDiStudio> borse= FXCollections.observableArrayList();
    private BorseDiStudioDao borseDiStudioDao=new BorseDiStudioDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colonnaANNO.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, String>("anno"));
        ColonnaNOMEFIGLIO.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, String>("figlio"));
        ColonnaIBANFIGLIO.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, String>("IBAN"));
        ColonnaCFU.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, Integer>("CFU"));
        colonnaLORDO.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, Float>("lordo"));
        colonnaRITENUTA.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, Float>("ritenuta"));
        colonnaNETTO.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, Float>("netto"));
        colonnaSOCIO.setCellValueFactory(new PropertyValueFactory<BorseDiStudio, String>("socio"));
        this.borse=borseDiStudioDao.getBorseDaPagare();
        tableANAGRAFICA.setItems(borse);
        try {
            TableFilter<BorseDiStudio> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }

    public void tornaHome(Event event) { App.getInstance().gotoHOME(); }

    public void PagaBorseDiStudio(ActionEvent event) {
        GeneraPagamenti generaPagamenti=new GeneraPagamenti();
        generaPagamenti.GeneraPagamentoBorse(tableANAGRAFICA.getItems(),dataPagamento.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inserimento");
        alert.setHeaderText("Borse di Studio pagate");
        alert.showAndWait();
    }
}