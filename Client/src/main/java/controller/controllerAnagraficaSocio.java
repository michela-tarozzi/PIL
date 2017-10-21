package controller;

import Pojo.DAO.SocioDao;
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
 * Created by m.tarozzi on 08/10/2017.
 */
public class controllerAnagraficaSocio {
    @FXML
    public TableView<Socio> tableANAGRAFICA;
    @FXML
    public TableColumn<Socio,String> ColonnaCF;
    @FXML
    public TableColumn<Socio,String> ColonnaCOGNOME;
    @FXML
    public TableColumn<Socio,String> ColonnaNOME;
    @FXML
    public TableColumn<Socio,String> colonnaINDIRIZZO;
    @FXML
    public TableColumn<Socio,String> colonnaCOMUNE;
    @FXML
    public TableColumn<Socio,String> colonnaREGIONE;
    @FXML
    public TableColumn<Socio,String> colonnaIBAN;
    @FXML
    public TableColumn<Socio,String> colonnaCategoria;
    @FXML
    public TableColumn<Socio,String> colonnaISCRIZIONE;
    @FXML
    public TableColumn<Socio,String> colonnaPENSIONE;
    @FXML
    public TableColumn<Socio,Float> colonnaSUSSIDIO;
    @FXML
    public TableColumn<Socio,Float> colonnaRITENUTA;
    @FXML
    public TableColumn<Socio,String> colonnaREDDITO;


    private String nomeClasse;
    private ObservableList<Socio> soci= FXCollections.observableArrayList();
    private SocioDao socio=new SocioDao();

    @FXML
    public void initialize() {
        nomeClasse = this.getClass().getName();
        ControllersDispatcher.setController(nomeClasse, this);
        tableANAGRAFICA.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ColonnaCF.setCellValueFactory(new PropertyValueFactory<Socio, String>("CF"));
        ColonnaCOGNOME.setCellValueFactory(new PropertyValueFactory<Socio, String>("cognome"));
        ColonnaNOME.setCellValueFactory(new PropertyValueFactory<Socio, String>("nome"));
        colonnaINDIRIZZO.setCellValueFactory(new PropertyValueFactory<Socio, String>("indirizzo"));
        colonnaREGIONE.setCellValueFactory(new PropertyValueFactory<Socio, String>("regione"));
        colonnaCOMUNE.setCellValueFactory(new PropertyValueFactory<Socio, String>("comune"));
        colonnaIBAN.setCellValueFactory(new PropertyValueFactory<Socio, String>("IBAN"));
        colonnaCategoria.setCellValueFactory(new PropertyValueFactory<Socio, String>("categoria"));
        colonnaISCRIZIONE.setCellValueFactory(new PropertyValueFactory<Socio, String>("dataIscrizione"));
        colonnaPENSIONE.setCellValueFactory(new PropertyValueFactory<Socio, String>("dataPensionamento"));
        colonnaREDDITO.setCellValueFactory(new PropertyValueFactory<Socio, String>("reddito"));
        colonnaRITENUTA.setCellValueFactory(new PropertyValueFactory<Socio, Float>("ritenuta"));
        colonnaSUSSIDIO.setCellValueFactory(new PropertyValueFactory<Socio, Float>("sussidioMensile"));

        this.soci=socio.getAll();
        tableANAGRAFICA.setItems(soci);
        try {
            TableFilter<Socio> t = TableFilter.forTableView(tableANAGRAFICA).lazy(false).apply();
        }catch(Exception e ){e.printStackTrace();}
    }


    public void InserisciNuovoSocio(ActionEvent event) {
        App.getInstance().gotoInserisciSocio();
    }

    public void tornaHome(Event event) {
        App.getInstance().gotoHOME();
    }

}
