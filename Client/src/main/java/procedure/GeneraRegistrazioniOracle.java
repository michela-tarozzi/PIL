package procedure;

import Pojo.*;
import Utility.Calendario;
import javafx.collections.ObservableList;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by m.tarozzi on 18/10/2017.
 */
public class GeneraRegistrazioniOracle {

    public void generaRegistrazioni(ObservableList<Pensioni> pensioni, LocalDate dataParametro)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\Pensioni"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        Iterator<Pensioni> it=pensioni.iterator();
        float dare=0;
        float sussidio=0;
        float carovita=0;
        float ritenuta=0;
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        while (it.hasNext()){
            conta++;
            Pensioni pensione=it.next();
            data.put(String.valueOf(conta), new Object[]{"04","000","02",pensione.getSocio().getConto().getNumero(),"000000","0000000000","000","00","000000000000000",pensione.getLordo(),"","SUSSIDIO DI VECCHIAIA E CAROVITA DI "+pensione.getData().getMonthValue()+"/"+pensione.getData().getYear()});
            dare=dare+pensione.getLordo();
            ritenuta=ritenuta+pensione.getRitenuta()+pensione.getAddizionaleComunale()+pensione.getAddizionaleRegionale();
            sussidio=sussidio+pensione.getSussidio();
            carovita=carovita+pensione.getCarovita();
        }
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","97500000","000000","0000000000","000","00","000000000000000","",sussidio,"SUSSIDIO DI VECCHIAIA DEL "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","97500000","000000","0000000000","000","00","000000000000000","",carovita,"SUSSIDIO DI CAROVITA DEL "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta),new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","",sussidio+carovita-ritenuta,"PAGAMENTO SUSSIDI DI VECCHIAIA "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta),new Object[]{"04","000","02","30600000","000000","0000000000","000","00","000000000000000",sussidio+carovita,"","SUSSIDI DI VECCHIAIA "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta),new Object[]{"04","000","02","43100000","000000","0000000000","000","00","000000000000000","",ritenuta,"RITENUTE SU SUSSIDI DI VECCHIAIA "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});
        GeneraEstrazioniDati.printOnExcel(statText,"Pagamento", data);
    }

    public void generaIscrizioneSocio(Socio socio)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\IscrSocio"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","3.00","","ISCRIZIONE DI "+socio.getCognome()+" "+socio.getNome()+" ALLA SEZIONE "+socio.getCategoria()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","30600000","000000","0000000000","000","00","000000000000000","","3.00","ISCRIZIONE DI "+socio.getCognome()+" "+socio.getNome()+" ALLA SEZIONE "+socio.getCategoria()});
        GeneraEstrazioniDati.printOnExcel(statText,"Iscrizione Socio",data);
    }

    public void generaPagamentoRitenute(ObservableList<Pensioni> pensioni, LocalDate dataParametro)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\PagRitenute"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        int conta=1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        Iterator<Pensioni> pensioniIt=pensioni.iterator();
        float importoRitenuta=0;
        while (pensioniIt.hasNext()){
            Pensioni pensione=pensioniIt.next();
            importoRitenuta=importoRitenuta+pensione.getRitenuta()+pensione.getAddizionaleRegionale()+pensione.getAddizionaleComunale();
        }
            String trimestre="";
        switch (dataParametro.getMonthValue()){
            case 1: trimestre="primo";break;
            case 2: trimestre="primo";break;
            case 3: trimestre="primo";break;
            case 4: trimestre="secondo";break;
            case 5: trimestre="secondo";break;
            case 6: trimestre="secondo";break;
            case 7: trimestre="terzo";break;
            case 8: trimestre="terzo";break;
            case 9: trimestre="terzo";break;
            case 10: trimestre="quarto";break;
            case 11: trimestre="quarto";break;
            case 12: trimestre="quarto";break;
        }
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","", importoRitenuta,"PAGAMENTO RITENUTE SU SUSSIDI DI VECCHIAIA "+trimestre.toUpperCase()+" TRIMESTRE 2017"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","43100000","000000","0000000000","000","00","000000000000000",importoRitenuta,"","PAGAMENTO RITENUTE SU SUSSIDI DI VECCHIAIA "+trimestre.toUpperCase()+" TRIMESTRE 2017"});
        GeneraEstrazioniDati.printOnExcel(statText,"Pagamento Ritenute",data);
    }

    public void generaIscrizioneFimiv(float importo, float importoTessere,float spesePostali,int anno)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\IscrFIMIV"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        int conta=1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","63100000","000000","0000000000","000","00","000000000000000",spesePostali,"","SPESE POSTALI SU PAGAMENTO BOLLETTINO QUOTA ASSOCIATIVA FIMIV"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","69001000","000000","0000000000","000","00","000000000000000",importo+importoTessere,"","QUOTA ASSOCIATIVA FIMIV ANNO "+anno+" (DI CUI EURO "+importoTessere+" PER TESSERE SALUTE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","",importo+spesePostali+importoTessere,"PAGAMENTO QUOTA ASSOCIATIVA FIMIV ANNO "+anno+" + TESSERE SALUTE"});
        GeneraEstrazioniDati.printOnExcel(statText,"IscrizioneFimiv",data);
    }

    public void generaRegistrazioniQuote(ObservableList<Quote> quote, LocalDate dataParametro)
    {
        LocalDateTime ora=LocalDateTime.now();
        String mese=Calendario.tornaMeseIta(dataParametro);
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\Quote"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        Iterator<Quote> it=quote.iterator();
        float sussidi=0;
        float assistenza=0;
        int conta=1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        while (it.hasNext()){
            conta++;
            Quote quota=it.next();
            if(quota.getImporto()==5.21)
            {
                data.put(String.valueOf(conta), new Object[]{"04","000","02",quota.getSocio().getConto().getNumero(),"000000","0000000000","000","00","000000000000000","","5.16","QUOTA DI "+mese +" "+dataParametro.getYear()});
                sussidi=sussidi+Float.parseFloat("5.16");
                assistenza=assistenza+Float.parseFloat("0.05");
            }else
                if (quota.getImporto()==5.16)
                {
                    data.put(String.valueOf(conta), new Object[]{"04","000","02",quota.getSocio().getConto().getNumero(),"000000","0000000000","000","00","000000000000000","","5.16","QUOTA DI "+mese +" "+dataParametro.getYear()});
                    sussidi=sussidi+Float.parseFloat("5.16");
                }
        }
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","97200000","000000","0000000000","000","00","000000000000000",sussidi,"","TOTALE QUOTE DI "+mese +" "+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000",sussidi+assistenza,"","TRATTENUTE PER SUSSIDI E ASSISTENZA DI "+mese +" "+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","30600000","000000","0000000000","000","00","000000000000000","",sussidi,"TRATTENUTE PER SUSSIDI DI "+mese +" "+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","33301000","000000","0000000000","000","00","000000000000000","",assistenza,"TRATTENUTE PER ASSISTENZA DI "+mese +" "+dataParametro.getYear()});
        GeneraEstrazioniDati.printOnExcel(statText,"Registrazione Quote",data);
    }

    public void generaRimborsiAsiliNido(ObservableList<AsiliNido> asiliNido, int anno)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\AsiliNido"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        int conta=1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        float importo=0;
        Iterator<AsiliNido> it=asiliNido.iterator();
        while(it.hasNext()){
            AsiliNido asilo=it.next();
            conta++;
            data.put(String.valueOf(conta), new Object[]{"04","000","02","33303000","000000","0000000000","000","00","000000000000000",asilo.getRimborso(),"","RIMBORSI ASILI NIDO "+asilo.getFiglio()+" FIGLIO DEL SOCIO "+asilo.getSocio().getCognome()+" "+asilo.getSocio().getNome()+" ANNO "+anno});
            importo=importo+asilo.getRimborso();
        }
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","",importo,"RIMBORSI ASILI NIDO "+anno});
        conta++;
        GeneraEstrazioniDati.printOnExcel(statText,"Rimborsi Asili Nido",data);
    }

    public void generaRimborsiAssistenziali(ObservableList<Rimborsi> rimborsi, LocalDate dataParametro)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        int conta=1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        Map<String, Float> importi=new TreeMap<String, Float>();
        Iterator<Rimborsi> it=rimborsi.iterator();
        while(it.hasNext()){
            Rimborsi rimborso=it.next();
            if (importi.containsKey(rimborso.getRegola().getDescrizione())){
                float temp=importi.get(rimborso.getRegola().getDescrizione());
                temp=temp+rimborso.getImporto();
                importi.replace(rimborso.getRegola().getDescrizione(),temp);
            }else{
                importi.put(rimborso.getRegola().getDescrizione(),rimborso.getImporto());
            }
        }
        String mese=Calendario.tornaMeseIta(dataParametro);
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        Iterator<String> categorie=importi.keySet().iterator();
        while(categorie.hasNext()){
            String categoria=categorie.next();
            data.put(String.valueOf(conta), new Object[]{"04","000","02","333010000","000000","0000000000","000","00","000000000000000",importi.get(categoria),"","RIMBORSI ASSISTENZIALI MESE DI "+mese+ "PER "+categoria.toUpperCase()});
            conta++;
        }
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","RIMBORSI ASSISTENZIALI MESE DI "+"MESE"});
        GeneraEstrazioniDati.printOnExcel(statText,"Rimborsi Assistenziali",data);
    }

    public void generaAssegniStudio(ObservableList<BorseDiStudio> borseDiStudio, int anno)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\Michela\\IsaiaLevi\\Studio"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");
        int conta=1;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        float importo=0;
        float ritenuta=0;
        Iterator<BorseDiStudio> it=borseDiStudio.iterator();
        while(it.hasNext()){
            BorseDiStudio borsa=it.next();
            data.put(String.valueOf(conta), new Object[]{"04","000","02","33302000","000000","0000000000","000","00","000000000000000",borsa.getLordo(),"","RIMBORSI STUDIO AL FIGLIO DEL SOCIO "+borsa.getSocio().getCognome()+" "+borsa.getSocio().getNome()+" ANNO "+anno});
            importo=importo+borsa.getLordo();
            ritenuta=ritenuta+borsa.getRitenuta();
            conta++;
        }
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","",importo-ritenuta,"RIMBORSI STUDIO AI FIGLI DEI SOCI DEL "+anno});
        if(ritenuta>0){
            conta++;
            data.put(String.valueOf(conta), new Object[]{"04","000","02","43100000","000000","0000000000","000","00","000000000000000","",ritenuta,"RITENUTA SU RIMBORSI STUDIO AI FIGLI DEI SOCI DEL "+anno});
        }
        GeneraEstrazioniDati.printOnExcel(statText,"Reg Ass Studio",data);
    }
}


