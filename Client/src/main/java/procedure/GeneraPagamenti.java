package procedure;

import Pojo.*;
import Pojo.DAO.*;
import Pojo.Pagamenti;
import Pojo.Pensioni;
import Pojo.Rimborsi;
import Pojo.Socio;
import Utility.GetNumberToBonifico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * Created by m.tarozzi on 15/10/2017.
 */
public class GeneraPagamenti {

    public boolean GeneraPagamentoPensioni(ObservableList<Pensioni> pensioni, LocalDate data) {
        PagamentoDao pagamentoDao=new PagamentoDao();
        ObservableList<Pagamenti> pagamenti= FXCollections.observableArrayList();
        PensioniDao pensioniDao=new PensioniDao();
        Iterator<Pensioni> it= pensioni.iterator();
        while(it.hasNext())
        {
            Pensioni pensione=it.next();
            Pagamenti pagamento=pagamentoDao.CreaPagamento(pensione,data,pensione.getLordo(), pensione.getNetto(),pensione.getRitenuta()+
                    pensione.getAddizionaleComunale()+pensione.getAddizionaleRegionale());
            pensione.setPagamento(pagamento);
            pensione.setStato("PAGATA");
            pensioniDao.update(pensione);
            pagamenti.add(pagamento);
        }
        generaXML(pagamenti, data,"C:\\Users\\m.tarozzi\\IsaiaLevi\\Pensioni\\PagPensioni"+pensioni.get(0).getData().getYear()+pensioni.get(0).getData().getMonthValue()+pensioni.get(0).getData().getDayOfMonth()+".xml");
        GeneraRegistrazioniOracle generaRegistrazioniOracle=new GeneraRegistrazioniOracle();
        generaRegistrazioniOracle.generaRegistrazioni(pensioni,data);
        return true;
    }

    public boolean GeneraPagamentoRimborsi(ObservableList<Rimborsi> rimborsi, LocalDate data) {
        PagamentoDao pagamentoDao=new PagamentoDao();
        ObservableList<Pagamenti> pagamenti= FXCollections.observableArrayList();
        RimborsoDao rimborsoDao=new RimborsoDao();
        Iterator<Rimborsi> it= rimborsi.iterator();
        while(it.hasNext())
        {
            Rimborsi rimborso=it.next();
            Pagamenti pagamento=pagamentoDao.CreaPagamento(rimborso,data,rimborso.getImporto(), rimborso.getImporto(),0);
            rimborso.setPagamento(pagamento);
            rimborsoDao.update(rimborso);
            pagamenti.add(pagamento);
        }
        generaXML(pagamenti, data,"C:\\Users\\m.tarozzi\\IsaiaLevi\\Rimborsi\\PagRimborsi"+rimborsi.get(0).getData().getYear()+rimborsi.get(0).getData().getMonthValue()+rimborsi.get(0).getData().getDayOfMonth()+".xml");
        return true;
    }
    public boolean GeneraPagamentoAsili(ObservableList<AsiliNido> asili, LocalDate data) {
        PagamentoDao pagamentoDao=new PagamentoDao();
        ObservableList<Pagamenti> pagamenti= FXCollections.observableArrayList();
        AsiliNidoDao asiliNidoDao=new AsiliNidoDao();
        Iterator<AsiliNido> it= asili.iterator();
        while(it.hasNext())
        {
            AsiliNido asilo=it.next();
            Pagamenti pagamento=pagamentoDao.CreaPagamento(asilo,data,asilo.getRimborso(), asilo.getRimborso(),0);
            asilo.setPagamento(pagamento);
            asiliNidoDao.update(asilo);
            pagamenti.add(pagamento);
        }
        generaXML(pagamenti, data, "C:\\Users\\m.tarozzi\\IsaiaLevi\\Asili\\PagAsili"+asili.get(1).getAnno()+".xml");
        return true;
    }

    public boolean generaXML(ObservableList<Pagamenti> pagamenti, LocalDate data, String path){

        try {
            LocalDateTime ora=LocalDateTime.now();
            File statText = new File(path);
            FileOutputStream fos = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            float sum=0;
            int conta=0;
            String mese =String.valueOf(data.getMonthValue());
            String giorno=String.valueOf(data.getDayOfMonth());
            if(giorno.length()==1){giorno="0"+giorno;}
            if (mese.length()==1){mese="0"+mese;}
            Writer w = new BufferedWriter(osw);
            Iterator<Pagamenti> it=pagamenti.iterator();
            while (it.hasNext())
            {
                Pagamenti pagamento=it.next();
                sum=sum+pagamento.getNetto();
                conta++;
            }
            w.write("<?xml version='1.0' encoding='ISO-8859-1' ?>\n" +
                    "<CBIPaymentRequest xsi:schemaLocation=\"urn:CBI:xsd:CBIPaymentRequest.00.04.00 CBIPaymentRequest.00.04.00.xsd\" xmlns=\"urn:CBI:xsd:CBIPaymentRequest.00.04.00\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                    "<GrpHdr>\n" +
                    "<MsgId>TBL33402015080002</MsgId>\n" +
                    "<CreDtTm>" +data.getYear()+"-"+mese+"-"+giorno+"T10.00.00+01:00"+
                    "</CreDtTm>\n" +
                    "<NbOfTxs>"+conta+"</NbOfTxs>\n" +
                    "<CtrlSum>"+String.format("%.2f", sum) + "</CtrlSum>\n" +
                    "<InitgPty>\n" +
                    "<Nm>ZANICHELLI EDITORE S.P.A.</Nm>\n" +
                    "<Id>\n" +
                    "<OrgId>\n" +
                    "<Othr>\n" +
                    "<Id>0132635Q</Id>\n" +
                    "<Issr>CBI</Issr></Othr>\n" +
                    "<Othr>\n" +
                    "<Id>08536570156</Id>\n" +
                    "<Issr>ADE</Issr></Othr></OrgId></Id></InitgPty>\n" +
                    "<FwdgAgt>\n" +
                    "<FinInstnId>\n" +
                    "<ClrSysMmbId>\n" +
                    "<MmbId>01030</MmbId></ClrSysMmbId></FinInstnId></FwdgAgt></GrpHdr>\n" +
                    "<PmtInf>\n" +
                    "<PmtInfId>BONSCTPENSISAIA"+data.getYear()+mese+
                    "</PmtInfId>\n" +
                    "<PmtMtd>TRF</PmtMtd>\n" +
                    "<PmtTpInf>\n" +
                    "<InstrPrty>NORM</InstrPrty>\n" +
                    "<SvcLvl>\n" +
                    "<Cd>SEPA</Cd></SvcLvl></PmtTpInf>\n" +
                    "<ReqdExctnDt>"+data.getYear()+"-"+mese+giorno+"</ReqdExctnDt>\n" +
                    "<Dbtr>\n" +
                    "<Nm>ZANICHELLI EDITORE S.P.A.</Nm>\n" +
                    "<PstlAdr>\n" +
                    "<Ctry>IT</Ctry>\n" +
                    "<AdrLine>VIA IRNERIO 34</AdrLine>\n" +
                    "<AdrLine>40126 BOLOGNA BO IT</AdrLine></PstlAdr>\n" +
                    "<Id>\n" +
                    "<OrgId>\n" +
                    "<Othr>\n" +
                    "<Id>08536570156</Id>\n" +
                    "<Issr>ADE</Issr></Othr></OrgId></Id>\n" +
                    "<CtryOfRes>IT</CtryOfRes></Dbtr>\n" +
                    "<DbtrAcct>\n" +
                    "<Id>\n" +
                    "<IBAN>IT48B0103002402000000142356</IBAN></Id></DbtrAcct>\n" +
                    "<DbtrAgt>\n" +
                    "<FinInstnId>\n" +
                    "<ClrSysMmbId>\n" +
                    "<MmbId>01030</MmbId></ClrSysMmbId></FinInstnId></DbtrAgt>\n" +
                    "<ChrgBr>SLEV</ChrgBr>\n");
            it=pagamenti.iterator();
            boolean borsa=false;
            while(it.hasNext()){
                Pagamenti pagamento=it.next();
                Socio socio;
                String descrizione="";
                if(pagamento.getPensioni().size()!=0){socio=pagamento.getPensioni().get(0).getSocio();descrizione="Isaia Levi "+mese+"/"+data.getYear();}
                else if(pagamento.getAsiliNido().size()!=0){socio=pagamento.getAsiliNido().get(0).getSocio();descrizione="Isaia Levi - Asili nido";}
                    else if (pagamento.getRimborsi().size()!=0){
                                Rimborsi rimborso=pagamento.getRimborsi().get(0);
                                Spese spesa=rimborso.getSpesa();
                                socio=spesa.getSocio();
                                descrizione="Isaia Levi - Rimborso assistenziale";
                            }
                        else if (pagamento.getEredi().size()!=0){socio=pagamento.getEredi().get(0).getSocio(); descrizione="Isaia Levi - sussidio "+pagamento.getEredi().get(0).getSocio().getCognome()+" "+pagamento.getEredi().get(0).getSocio().getNome();}
                else {
                            socio=pagamento.getBorseDiStudio().get(0).getSocio(); descrizione="Isaia Levi - Borsa di Studio";
                if( pagamento.getBorseDiStudio().get(0).getIBAN()!=null){ borsa=true;}}
                String numero=GetNumberToBonifico.getInstance().dammiIlProssimo();
                if (borsa){
                    w.write("<CdtTrfTxInf><PmtId><InstrId>" + numero +
                            "</InstrId><EndToEndId>BONSCTPENSISAIA" + data.getYear() + mese + numero +
                            "</EndToEndId></PmtId><PmtTpInf><CtgyPurp><Cd>CASH</Cd></CtgyPurp></PmtTpInf><Amt><InstdAmt Ccy=\"EUR\">" +
                            String.format("%.2f", pagamento.getNetto()) + "</InstdAmt></Amt><Cdtr><Nm>" +
                            pagamento.getBorseDiStudio().get(0).getFiglio() + "</Nm><CtryOfRes>IT</CtryOfRes></Cdtr><CdtrAcct><Id><IBAN>" +
                            pagamento.getBorseDiStudio().get(0).getIBAN() + "</IBAN></Id></CdtrAcct><RmtInf><Ustrd>" + descrizione + "</Ustrd></RmtInf></CdtTrfTxInf>\n");

                }else {
                    w.write("<CdtTrfTxInf><PmtId><InstrId>" + numero +
                            "</InstrId><EndToEndId>BONSCTPENSISAIA" + data.getYear() + mese + numero +
                            "</EndToEndId></PmtId><PmtTpInf><CtgyPurp><Cd>CASH</Cd></CtgyPurp></PmtTpInf><Amt><InstdAmt Ccy=\"EUR\">" +
                            String.format("%.2f", pagamento.getNetto()) + "</InstdAmt></Amt><Cdtr><Nm>" +
                            socio.getCognome() + " " + socio.getNome() + "</Nm><CtryOfRes>IT</CtryOfRes></Cdtr><CdtrAcct><Id><IBAN>" +
                            socio.getIBAN() + "</IBAN></Id></CdtrAcct><RmtInf><Ustrd>" + descrizione + "</Ustrd></RmtInf></CdtTrfTxInf>\n");
                }
                }
            w.write("</PmtInf></CBIPaymentRequest>");
            w.close();
            GetNumberToBonifico.getInstance().reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void GeneraPagamentoBorse(ObservableList<BorseDiStudio> borse, LocalDate data) {
        PagamentoDao pagamentoDao=new PagamentoDao();
        ObservableList<Pagamenti> pagamenti= FXCollections.observableArrayList();
        BorseDiStudioDao borseDiStudioDao=new BorseDiStudioDao();
        Iterator<BorseDiStudio> it= borse.iterator();
        while(it.hasNext())
        {
            BorseDiStudio borsa=it.next();
            Pagamenti pagamento=pagamentoDao.CreaPagamento(borsa,data,borsa.getLordo(), borsa.getNetto(),borsa.getRitenuta());
            borsa.setPagamento(pagamento);
            borseDiStudioDao.update(borsa);
            pagamenti.add(pagamento);
        }
        generaXML(pagamenti, data, "C:\\Users\\m.tarozzi\\IsaiaLevi\\Borse\\PagBorse"+borse.get(0).getAnno()+".xml");
    }
}
