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

    public void InserisciNuovaRegolaCarovita(ActionEvent actionEvent) { App.getInstance().gotoInserisciRegolaCarovita();    }

    public void InserisciNuovoAddizionale(ActionEvent actionEvent) { App.getInstance().gotoInserisciAddizionaleComunale();    }

    public void InserisciNuovoRimborsoAsilo(ActionEvent actionEvent) { App.getInstance().gotoInserisciAsilo();    }

    public void InserisciNuovaBorsaDiStudio(ActionEvent actionEvent) { App.getInstance().gotoInserisciBorsa();    }

    public void InserisciNuovoComune(ActionEvent actionEvent) { App.getInstance().gotoInserisciComune();    }

    public void InserisciNuovoConto(ActionEvent actionEvent) { App.getInstance().gotoInserisciConto();    }

    public void InserisciNuovoPagamento(ActionEvent actionEvent) { App.getInstance().gotoInserisciPagamento();    }

    public void InserisciNuovaPensione(ActionEvent actionEvent) { App.getInstance().gotoInserisciPensione();    }

    public void InserisciNuovaRegione(ActionEvent actionEvent) { App.getInstance().gotoInserisciRegione();    }

    public void InserisciNuovoSocio(ActionEvent actionEvent) { App.getInstance().gotoInserisciSocio();    }

    public void InserisciNuovoAddizionaleRegionale(ActionEvent actionEvent) { App.getInstance().gotoInserisciAddizionaleRegionale();    }

    public void InserisciNuovoRimborso(ActionEvent actionEvent) { App.getInstance().gotoInserisciRimborso();    }

    public void InserisciNuovaRegola(ActionEvent actionEvent) { App.getInstance().gotoInserisciRegolaRimborso();    }
}
