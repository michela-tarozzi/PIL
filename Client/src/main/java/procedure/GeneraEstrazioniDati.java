package procedure;

import Pojo.Pensioni;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by m.tarozzi on 21/10/2017.
 */
public class GeneraEstrazioniDati {

    public static void GeneraEstrazioneDati(ObservableList<Pensioni> pensioni)
    {
        LocalDateTime ora=LocalDateTime.now();
        File statText = new File("C:\\Users\\m.tarozzi\\IsaiaLevi\\"+ora.getYear()+ora.getMonth()+ora.getDayOfMonth()+ora.getHour()+ora.getMinute()+ora.getSecond()+".xls");

        XSSFWorkbook workbook = new XSSFWorkbook();
        Iterator<Pensioni> it=pensioni.iterator();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Pensioni");
        float sussidio=0;
        float carovita=0;
        float ritenuta=0;
        float regionale=0;
        float comunale=0;
        float lordo=0;
        float netto=0;
        int conta=1;
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put(String.valueOf(conta), new Object[]{"SOCIO","REGIONE","COMUNE","DATA","SUSSIDIO","CAROVITA","ALIQUOTA RITENUTA","RITENUTA","Add.REGIONALE","Add. COMUNALE","LORDO","NETTO"});
        while (it.hasNext()){
            conta++;
            Pensioni pensione=it.next();
            data.put(String.valueOf(conta), new Object[]{pensione.getSocio().toString(),pensione.getSocio().getRegione().toString(),pensione.getSocio().getComune().toString(),pensione.getData().toString(),pensione.getSussidio(),pensione.getCarovita(),pensione.getSocio().getritenuta(),pensione.getRitenuta(),pensione.getAddizionaleRegionale(),pensione.getAddizionaleComunale(),pensione.getLordo(),pensione.getNetto()});
            sussidio=sussidio+pensione.getSussidio();
            carovita=carovita+pensione.getCarovita();
            ritenuta=ritenuta+pensione.getRitenuta();
            regionale=regionale+pensione.getAddizionaleRegionale();
            comunale=comunale+pensione.getAddizionaleComunale();
            lordo=lordo+pensione.getLordo();
            netto=netto+pensione.getNetto();
        }
        conta++;
        data.put(String.valueOf(conta), new Object[]{"","","","",sussidio,carovita,"",ritenuta,regionale,comunale,lordo,netto});
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
