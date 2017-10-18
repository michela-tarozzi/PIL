package controller;

import javafx.event.ActionEvent;
import main.App;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class controllerMain {
    public void VaiSoci(ActionEvent event) {
        App.getInstance().gotoAnagraficaSOCI();
    }

    public void VaiConti(ActionEvent event) {
        App.getInstance().gotoAnagraficaCONTI();
    }

    public void VaiAddizionaliComunali(ActionEvent event) {
        App.getInstance().gotoAnagraficaCOMUNALI();
    }

    public void VaiAddizionaliRegionali(ActionEvent event) {
        App.getInstance().gotoAnagraficaREGIONALI();
    }

    public void VaiRegoleCarovita(ActionEvent event) {App.getInstance().gotoAnagraficaREGOLECAROVITA();
    }

    public void VaiRegoleRimborsi(ActionEvent event) {App.getInstance().gotoAnagraficaREGOLERIMBORSI();
    }

    public void VaiComuni(ActionEvent event) {
        App.getInstance().gotoAnagraficaCOMUNI();
    }

    public void VaiRegioni(ActionEvent event) {
        App.getInstance().gotoAnagraficaREGIONI();
    }

    public void VaiAsiliNido(ActionEvent event) {App.getInstance().gotoAnagraficaAsiliNido();
    }

    public void VaiPensioni(ActionEvent event) {App.getInstance().gotoAnagraficaPensioni();

    }

    public void VaiBorse(ActionEvent event) {
        App.getInstance().gotoAnagraficaBorse();
    }

    public void VaiPagamenti(ActionEvent event) {
        App.getInstance().gotoAnagraficaPagamenti();
    }

    public void VaiRimborsi(ActionEvent event) {
        App.getInstance().gotoAnagraficaRimborsi();
    }

    public void GeneraPensioni(ActionEvent event) {App.getInstance().gotoGeneraPensioni();

    }

    public void PensioniNonPagate(ActionEvent event) {
        App.getInstance().gotoPensioniNonPagate();
    }

    public void VaiQuote(ActionEvent event) {
        App.getInstance().gotoAnagraficaQuote();
    }

    public void GeneraQuote(ActionEvent event) {
        App.getInstance().gotoGeneraQuote();
    }

    public void VaiSpeseRimborsi(ActionEvent event) { App.getInstance().gotoSpeseERimborsi();
    }

    public void VaiRimborsiDaPagare(ActionEvent event) {
        App.getInstance().gotoRimborsiDaPagare();
    }
}
