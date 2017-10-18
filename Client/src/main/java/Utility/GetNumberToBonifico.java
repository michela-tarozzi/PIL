package Utility;

/**
 * Created by m.tarozzi on 18/10/2017.
 */
public class GetNumberToBonifico {
    private static int conta;
    private static volatile GetNumberToBonifico instance = null;

    private GetNumberToBonifico() {}

    public static GetNumberToBonifico getInstance() {
        if (instance == null) {
            synchronized(GetNumberToBonifico.class) {
                if (instance == null) {
                    instance = new GetNumberToBonifico();
                    conta=1;
                }
            }
        }
        return instance;
    }

    public String dammiIlProssimo(){
        String numero=String.valueOf(conta);
        if(numero.length()==1){numero="00"+numero;}
        else if (numero.length()==2){numero="0"+numero;}
        else{ reset(); numero=dammiIlProssimo();}
        conta++;
        return numero;
    }

    public void reset(){conta=1;}
}
