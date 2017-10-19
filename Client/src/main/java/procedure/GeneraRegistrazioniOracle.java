package procedure;

import Pojo.Pensioni;
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
            data.put(String.valueOf(conta), new Object[]{"04","000","02",pensione.getSocio().getConto(),"000000","0000000000","000","00","000000000000000",pensione.getLordo(),"","SUSSIDIO DI VECCHIAIA E CAROVITA DI "+pensione.getData().getMonthValue()+"/"+pensione.getData().getYear()});
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


}


