package controller;

import Pojo.BorseDiStudio;
import Pojo.DAO.BorseDiStudioDao;
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
 * TableColumn fx:id="colonnaANNO" prefWidth="89.0" text="ANNO" />
 <TableColumn fx:id="ColonnaNOMEFIGLIO" prefWidth="160.0" text="NOME FIGLIO" />
 <TableColumn fx:id="ColonnaIBANFIGLIO" prefWidth="237.0" text="IBAN FIGLIO" />
 <TableColumn fx:id="ColonnaCFU" prefWidth="108.0" text="CFU" />
 <TableColumn fx:id="colonnaLORDO" prefWidth="171.0" text="LORDO'" />
 <TableColumn fx:id="colonnaRITENUTA" prefWidth="147.0" text="RITENUTA" />
 <TableColumn fx:id="colonnaNETTO" prefWidth="188.0" text="NETTO" />
 */
public class controllerAnagraficaBorseDiStudio {
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
        this.borse=borseDiStudioDao.getAll();
        tableANAGRAFICA.setItems(borse);
        try {
            TableFilter<BorseDiStudio> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }

    public void InserisciNuovaBorsaDiStudio(ActionEvent event) {
        App.getInstance().gotoInserisciBorsa();
    }

    public void tornaHome(Event event) { App.getInstance().gotoHOME(); }

}
