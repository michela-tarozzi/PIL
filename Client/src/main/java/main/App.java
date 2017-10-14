package main;

import Pojo.AddizionaleComunale;
import Pojo.AddizionaleRegionale;
import Pojo.DAO.AddizionaleComunaleDao;
import Pojo.DAO.AddizionaleRegionaleDao;
import Pojo.DAO.SocioDao;
import Pojo.DAO.SpeseDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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

        SpeseDao speseDao=new SpeseDao();
        AddizionaleComunaleDao addizionaleComunaleDao=new AddizionaleComunaleDao();
        addizionaleComunaleDao.CreaAddizionaleComunale("codice","comune", 2017, 10000,50000,Float.parseFloat("28.70"));

        AddizionaleRegionaleDao addizionaleRegionaleDao=new AddizionaleRegionaleDao();
        addizionaleRegionaleDao.CreaAddizionaleRegionale("EmiliaRomagna",2017,10000,50000,Float.parseFloat("40") );

        SocioDao sd=new SocioDao();
        Date dat= new Date();
        sd.CreaSocio("TRZMHL90L71A944E","MICHELA","TAROZZI",
                "VIA IRNERIO 34","BOLOGNA","BOLOGNA",
                "IT7G1234512345123456789012", dat , "MUTUA" );
        sd.CreaSocioPensionato("TRZMHL90L71A944E","MICHELA","TAROZZI",
                "VIA IRNERIO 34","BOLOGNA","BOLOGNA",
                "IT7G1234512345123456789012", dat , "Pensionato",dat,Float.parseFloat("56843.76"),Float.parseFloat("34.65"),Float.parseFloat("75.23"));
        speseDao.CreaSpesa("1",dat,Float.parseFloat("160"));

        try{
            stage=primaryStage;
            gotoAnagrafica();
            primaryStage.show();
        }
        catch(Exception e){System.out.print(e);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception{
        URL fxmlCenterPath = this.getClass().getResource("/fxml/main.fxml");
        BorderPane root = loader.load(fxmlCenterPath);
        Parent page=(Parent)FXMLLoader.load(this.getClass().getResource(fxml));
        Scene scene=new Scene(root);
        root.setCenter(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return page;
    }

    public void gotoAnagrafica(){
        try {replaceSceneContent("/fxml/AnagraficaSocio.fxml");}
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

}
