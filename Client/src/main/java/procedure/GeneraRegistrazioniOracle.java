package procedure;

import Pojo.Pensioni;
import Pojo.Quote;
import Pojo.Socio;
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
public class GeneraRegistrazioniOracle {                                    //dare      //avere
    // 01	910	02	69060000	000000	0000000000	000	PD	000000000000000	210,85	            	BOLLO AUTO TARGATA: EW520XC DI PELLEGRINO

    public void generaRegistrazioni(ObservableList<Pensioni> pensioni, LocalDate dataParametro)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        Iterator<Pensioni> it=pensioni.iterator();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        float dare=0;
        float sussidio=0;
        float carovita=0;
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        while (it.hasNext()){
            conta++;
            Pensioni pensione=it.next();
            data.put(String.valueOf(conta), new Object[]{"04","000","02",pensione.getSocio().getConto().getNumero(),"000000","0000000000","000","00","000000000000000",pensione.getLordo(),"","SUSSIDIO DI VECCHIAIA E CAROVITA DI "+pensione.getData().getMonthValue()+"/"+pensione.getData().getYear()});
            dare=dare+pensione.getLordo();
            sussidio=sussidio+pensione.getSussidio();
            carovita=carovita+pensione.getCarovita();
        }
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","97500000","000000","0000000000","000","00","000000000000000","",sussidio,"SUSSIDIO DI VECCHIAIA DEL "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","97500000","000000","0000000000","000","00","000000000000000","",carovita,"SUSSIDIO DI CAROVITA DEL "+dataParametro.getMonthValue()+"/"+dataParametro.getYear()});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void generaIscrizioneSocio(Socio socio)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","3.00","","ISCRIZIONE DI "+socio.getCognome()+" "+socio.getNome()+" ALLA SEZIONE "+socio.getCategoria()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","30600000","000000","0000000000","000","00","000000000000000","","3.00","ISCRIZIONE DI "+socio.getCognome()+" "+socio.getNome()+" ALLA SEZIONE "+socio.getCategoria()});
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void generaPagamentoRitenute()
    {
        //salvare una tabella con importo codice e periodo...
        // TODO: 21/10/2017
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","PAGAMENTO RITENUTE SU SUSSIDI DI VECCHIAIA "+"NUMERO TRIMESTRE"+"^ TRIMESTRE 2017"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","43100000","000000","0000000000","000","00","000000000000000","importo","","PAGAMENTO RITENUTE SU SUSSIDI DI VECCHIAIA "+"NUMERO TRIMESTRE"+"^ TRIMESTRE 2017"});
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void generaIscrizioneFimiv()
    {
        //salvare una tabella con importo codice e periodo...
        // TODO: 21/10/2017
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","63100000","000000","0000000000","000","00","000000000000000","importo","","SPESE POSTALI SU PAGAMENTO BOLLETTINO QUOTA ASSOCIATIVA FIMIV"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","69001000","000000","0000000000","000","00","000000000000000","importo","","QUOTA ASSOCIATIVA FIMIV ANNO"+"ANNO"+" + TESSERE SALUTE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","PAGAMENTO QUOTA ASSOCIATIVA FIMIV ANNO"+"ANNO"+" + TESSERE SALUTE"});
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void generaRegistrazioniQuote(ObservableList<Quote> quote, LocalDate dataParametro)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        Iterator<Quote> it=quote.iterator();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Quote");
        float dare=0;
        float sussidi=0;
        float assistenza=0;
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        while (it.hasNext()){
            conta++;
            Quote quota=it.next();
            if(quota.getImporto()==5.21)
            {
                data.put(String.valueOf(conta), new Object[]{"04","000","02",quota.getSocio().getConto().getNumero(),"000000","0000000000","000","00","000000000000000","","5.16","QUOTA DI "+quota.getData().getMonthValue()});
                sussidi=sussidi+Float.parseFloat("5.16");
                assistenza=assistenza+Float.parseFloat("0.05");
            }else
                if (quota.getImporto()==5.16)
                {
                    data.put(String.valueOf(conta), new Object[]{"04","000","02",quota.getSocio().getConto().getNumero(),"000000","0000000000","000","00","000000000000000","","5.16","QUOTA DI "+quota.getData().getMonthValue()});
                    sussidi=sussidi+Float.parseFloat("5.16");
                }
        }
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","97200000","000000","0000000000","000","00","000000000000000",sussidi,"","TOTALE QUOTE DI "+dataParametro.getMonthValue()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000",sussidi+assistenza,"","TRATTENUTE PER SUSSIDI E ASSISTENZA DI "+dataParametro.getMonthValue()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","30600000","000000","0000000000","000","00","000000000000000","",sussidi,"TRATTENUTE PER SUSSIDI DI "+dataParametro.getMonthValue()});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","33301000","000000","0000000000","000","00","000000000000000","",assistenza,"TRATTENUTE PER ASSISTENZA DI "+dataParametro.getMonthValue()});
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void generaRimborsiAsiliNido()
    {
        //salvare una tabella con importo codice e periodo...
        // TODO: 21/10/2017
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","RIMBORSI ASILI NIDO "+"ANNO"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","33303000","000000","0000000000","000","00","000000000000000","importo","","RIMBORSI ASILI NIDO "+"ANNO"});
//Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void generaPagamentoSussidi()
    {
        // TODO: 21/10/2017
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","PAGAMENTO RITENUTE SU SUSSUDI DI VECCHIAIA E INVALIDITA' "+"TRIMESTRE"+"^TRIMESTRE "+"ANNO"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","30600000","000000","0000000000","000","00","000000000000000","importo","","SUSSIDI DI VECCHIAIA E INVALIDITA' "+"TRIMESTRE"+"^TRIMESTRE "+"ANNO"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","43100000","000000","0000000000","000","00","000000000000000","","importo","RITENUTE SU SUSSUDI DI VECCHIAIA E INVALIDITA' "+"TRIMESTRE"+"^TRIMESTRE "+"ANNO"});
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void generaRimborsiAssistenziali()
    {
        // TODO: 21/10/2017 - IMPLEMENTARE LA REGISTRAZIONE PER CATEGORIA DI RIMBORSO
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","RIMBORSI ASSISTENZIALI MESE DI "+"MESE"});
        conta++;//AGGIUNGERE PER OGNI CATEGORIA
        data.put(String.valueOf(conta), new Object[]{"04","000","02","333010000","000000","0000000000","000","00","000000000000000","importo","","RIMBORSI ASSISTENZIALI MESE DI "+"MESE"+ "PER "+" CATEGORIA"});
       //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void generaAssegniStudio()
    {
        // TODO: 21/10/2017 - IMPLEMENTARE LA REGISTRAZIONE PER CATEGORIA DI RIMBORSO
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIETA","CDC","REPORTING","CONTO","COMMESSA","VOLUME","COMM. DI SPESA","CR","VUOTO","DARE","AVERE","DESCRIZIONE"});
        conta++;
        data.put(String.valueOf(conta), new Object[]{"04","000","02","46000000","000000","0000000000","000","00","000000000000000","","importo","RIMBORSI STUDIO AI FIGLI DEI SOCI DEL "+"ANNO"});
        conta++;//AGGIUNGERE PER OGNI RIMBORSO
        data.put(String.valueOf(conta), new Object[]{"04","000","02","333020000","000000","0000000000","000","00","000000000000000","importo","","RIMBORSO STUDIO A NOME FIGLIO DIGLIO DI NOME SOCIO DEL ANNO "+"ANNO"});
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
                else if (obj instanceof Float)
                {
                    cell.setCellValue((Float) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(statText);
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


