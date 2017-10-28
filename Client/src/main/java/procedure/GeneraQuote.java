package procedure;

import Pojo.DAO.QuoteDao;
import Pojo.DAO.SocioDao;
import Pojo.Socio;
import javafx.collections.ObservableList;

import java.awt.geom.QuadCurve2D;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by m.tarozzi on 16/10/2017.
 */
public class GeneraQuote {
    //MUTUA=0.05
    //SUSSIDI=5.16
    //MUTUA&SUSSIDI=5.21
    public String generaQuote(LocalDate data){
        SocioDao socioDao=new SocioDao();
        QuoteDao quoteDao=new QuoteDao();
        ObservableList<Socio> soci= socioDao.getAll();
        Iterator<Socio> it = soci.iterator();
        int mutua=0;
        int sussidi=0;
        while(it.hasNext())
        {
            Socio socio=it.next();
            if(socio.getCategoria().equals("MUTUA")){
                //genera riga 0.05
                quoteDao.CreaQuota(socio,data, Float.parseFloat("0.05"));
                mutua++;
                }else
                    if(socio.getCategoria().equals("SUSSIDI")){
                        //genera riga 5.16
                        quoteDao.CreaQuota(socio,data, Float.parseFloat("5.16"));
                        sussidi++;
                    }else if(socio.getCategoria().equals("MUTUA&SUSSIDI"))
                        {
                            //genera riga 5.21
                            quoteDao.CreaQuota(socio,data, Float.parseFloat("5.21"));
                            mutua++;
                            sussidi++;
                        }
        }
        return mutua+";"+sussidi;
    }

    public float StimaQuote() {
        float stima=0;
        SocioDao socioDao=new SocioDao();
        ObservableList<Socio> soci= socioDao.getAll();
        Iterator<Socio> it = soci.iterator();
        while(it.hasNext())
        {
            Socio socio=it.next();
            if(socio.getCategoria().equals("MUTUA")){
                //genera riga 0.05
                stima=stima+Float.parseFloat("0.05");
            }else
            if(socio.getCategoria().equals("SUSSIDI")){
                //genera riga 5.16
                stima=stima+Float.parseFloat("5.16");
            }else if(socio.getCategoria().equals("MUTUA&SUSSIDI"))
            {
                stima=stima+Float.parseFloat("5.21");
            }
        }
        return stima;
    }

    public float StampaQuote(int anno)
    {
         float importo=0;
        QuoteDao quoteDao=new QuoteDao();
        SocioDao socioDao=new SocioDao();
        Iterator<Socio> soci=socioDao.getAll().iterator();
        while (soci.hasNext()){
            Socio socio=soci.next();
            if ((socio.getCategoria().equals("MUTUA&SUSSIDI")|| socio.getCategoria().equals("SUSSIDI"))&& socio.getdataIscrizione().getYear()==anno)
            {
                importo=quoteDao.ImportoAnnuoQuote(socio, anno);
            }
            else
                importo=Float.parseFloat("61.92");
            // TODO: 25/10/2017 stampa xml per jfxml 
        }
        return  importo;
    }
}