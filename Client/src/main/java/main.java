import Pojo.DAO.SocioDao;
import Pojo.Socio;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by m.tarozzi on 04/10/2017.
 */
public class main extends Application {

    private Stage stage;
    private final FXMLLoader loader=new FXMLLoader();
    private static main instance;

    public static void main(String[] args) {launch(args);}

    public main(){instance=this;}

    public static main getInstance(){return instance;}

    @Override
    public void start(Stage primaryStage) {

        System.out.println("ciao");
        SocioDao sd=new SocioDao();
        Date dat= new Date();
        sd.CreaSocio("TRZMHL90L71A944E","MICHELA","TAROZZI",
                "VIA IRNERIO 34","BOLOGNA","BOLOGNA",
                "IT7G1234512345123456789012", dat , "MUTUA" );
        sd.CreaSocioPensionato("TRZMHL90L71A944E","MICHELA","TAROZZI",
                "VIA IRNERIO 34","BOLOGNA","BOLOGNA",
                "IT7G1234512345123456789012", dat , "Pensionato",dat,Float.parseFloat("56843.76"),Float.parseFloat("34.65"),Float.parseFloat("75.23"));
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




    public void gotoPippo() {
        URL fxmlPage = this.getClass().getResource("/fxml/AnagraficaSocio.fxml");
        URL fxmlCenterPath = this.getClass().getResource("/fxml/main.fxml");
        AnchorPane page = null;
        try {
            page = loader.load(fxmlPage);
            BorderPane root = loader.load(fxmlCenterPath);
            Scene scene = new Scene(root);
            root.setCenter(page);
            root.setTop(null);
            this.stage.setResizable(false);
            this.stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
        stage.toFront();
    }

}

//test