package main;

import Pojo.*;
import Pojo.DAO.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
/**
 * Created by m.tarozzi on 08/10/2017.
 */
public class App extends Application {
    private Stage stage;
    private final FXMLLoader loader=new FXMLLoader();
    private static App instance;

    public App(){instance=this;}

    public static App getInstance(){return instance;}

    public static void main(String[] args) {launch(args);}


    @Override
    public void start(Stage primaryStage) {
/*
        BorseDiStudioDao borseDiStudioDao=new BorseDiStudioDao();
        borseDiStudioDao.CreaBorsaDiStudio("figlio",2017,35,"iban",100,80,20);


        SpeseDao speseDao=new SpeseDao();
        AddizionaleComunaleDao addizionaleComunaleDao=new AddizionaleComunaleDao();
        addizionaleComunaleDao.CreaAddizionaleComunale("codice","comune", 2017, 10000,50000,Float.parseFloat("28.70"));

        AddizionaleRegionaleDao addizionaleRegionaleDao=new AddizionaleRegionaleDao();
        addizionaleRegionaleDao.CreaAddizionaleRegionale("EmiliaRomagna",2017,10000,50000,Float.parseFloat("40") );

        AsiliNidoDao asiliNidoDao=new AsiliNidoDao();
        asiliNidoDao.CreaAsiloNido("figlio",2017,100,100,100,100);

        ComuneDao comuneDao=new ComuneDao();
        Comune comune= comuneDao.CreaComune("A944E","BOLOGNA");
        RegioneDao regioneDao=new RegioneDao();
        Regioni regione=regioneDao.CreaRegione("EmiliaRomagna");
        ContiDao contiDao=new ContiDao();
        Conti conto=contiDao.CreaConto("123456789","conto dei conti");
        SocioDao sd=new SocioDao();
        LocalDate ld=LocalDate.now();
        Date dat= new Date();
        Socio socio=sd.CreaSocio("TRZMHL90L71A944E","MICHELA","TAROZZI",
                "VIA IRNERIO 34","BOLOGNA",comune,
                "IT7G1234512345123456789012", ld , "MUTUA" ,regione,conto);
        sd.CreaSocioPensionato("TRZMHL90L71A944E","MICHELA","TAROZZI",
                "VIA IRNERIO 34","BOLOGNA",comune,
                "IT7G1234512345123456789012", ld , "Pensionato", ld,Float.parseFloat("56843.76"),Float.parseFloat("34.65"),Float.parseFloat("75.23"),regione, conto);
        Spese spesa=speseDao.CreaSpesa(socio,"1",ld,Float.parseFloat("160"));
        socio.addSpesa(spesa);
        sd.save(socio);
*/
        try{
            stage=primaryStage;
            gotoHOME();
            primaryStage.show();
        }
        catch(Exception e){System.out.print(e);
        }
    }

    public void gotoHOME() {
        try {replaceSceneContent("/fxml/bottoniAnagrafiche.fxml");}
        catch (Exception e){
            System.out.println(e);
        }

    }

    private Parent replaceSceneContent(String fxml) throws Exception{
        URL fxmlCenterPath = this.getClass().getResource("/fxml/Home.fxml");
        BorderPane root = loader.load(fxmlCenterPath);
        Parent page=(Parent)FXMLLoader.load(this.getClass().getResource(fxml));
        Scene scene=new Scene(root);
        root.setCenter(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return page;
    }

    public void gotoAnagraficaSOCI(){
        try {replaceSceneContent("/fxml/AnagraficaSocio.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoAnagraficaCOMUNI(){
        try {replaceSceneContent("/fxml/AnagraficaComuni.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaCONTI(){
        try {replaceSceneContent("/fxml/AnagraficaConti.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoAnagraficaCOMUNALI(){
        try {replaceSceneContent("/fxml/AnagraficaAddizionaleComunale.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoAnagraficaREGIONALI(){
        try {replaceSceneContent("/fxml/AnagraficaAddizionaleRegionale.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciSocio() {
        try {replaceSceneContent("/fxml/InserisciSocio.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoInserisciAsilo() {
        try {replaceSceneContent("/fxml/InserisciAsiliNido.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciConto() {
        try {replaceSceneContent("/fxml/InserisciConto.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoInserisciComune() {
        try {replaceSceneContent("/fxml/InserisciComune.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoInserisciRegolaCarovita() {
        try {replaceSceneContent("/fxml/InserisciRegolaCarovita.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciAddizionaleComunale() {
        try {replaceSceneContent("/fxml/InserisciAddizionaleComunale.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoInserisciAddizionaleRegionale() {
        try {replaceSceneContent("/fxml/InserisciAddizionaleRegionale.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaREGOLERIMBORSI() {
        try {replaceSceneContent("/fxml/AnagraficaRegoleRimborsi.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaREGOLECAROVITA() {
        try {replaceSceneContent("/fxml/AnagraficaRegoleCarovita.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciRegolaRimborso() {
        try {replaceSceneContent("/fxml/InserisciRegoleRimborsi.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciRegione() {
        try {replaceSceneContent("/fxml/InserisciRegione.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaREGIONI() {
        try {replaceSceneContent("/fxml/AnagraficaRegioni.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaAsiliNido() {
        try {replaceSceneContent("/fxml/AnagraficaAsiliNido.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaPensioni() {
        try {replaceSceneContent("/fxml/AnagraficaPensioni.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciPensione() {
        try {replaceSceneContent("/fxml/InserisciPensione.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciBorsa() {
        try {replaceSceneContent("/fxml/InserisciBorsa.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoAnagraficaBorse() {
        try {replaceSceneContent("/fxml/AnagraficaBorseDiStudio.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciPagamento() {
        try {replaceSceneContent("/fxml/InserisciPagamento.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoAnagraficaPagamenti() {
        try {replaceSceneContent("/fxml/AnagraficaPagamenti.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoInserisciRimborso() {
        try {replaceSceneContent("/fxml/InserisciRimborso.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void gotoAnagraficaRimborsi() {
        try {replaceSceneContent("/fxml/AnagraficaRimborsi.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoGeneraPensioni() {
        try {replaceSceneContent("/fxml/GeneraPensioni.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoPensioniNonPagate() {
        try {replaceSceneContent("/fxml/AnagraficaPensioniNonPagate.fxml");}
    catch (Exception e){
        System.out.println(e);
    }

    }

    public void gotoAnagraficaQuote() {
        try {replaceSceneContent("/fxml/Quote.fxml");}
        catch (Exception e){
            System.out.println(e);
    }
}

    public void gotoGeneraQuote() {
        try {replaceSceneContent("/fxml/GeneraQuote.fxml");}
        catch (Exception e){
            System.out.println(e);
    }
    }

    public void gotoSpeseERimborsi() {
        try {replaceSceneContent("/fxml/InserisciSpesa.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void gotoRimborsiDaPagare() {
        try {replaceSceneContent("/fxml/AnagraficaRimborsiDaPagare.fxml");}
        catch (Exception e){
            System.out.println(e);
        }
    }
}
