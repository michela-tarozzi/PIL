package Pojo;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by m.tarozzi on 14/10/2017.
 */
public class AddizionaleComunale {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;
    //CODICE
    //COMUNE
    //ANNO
    //SOGLIAMINIMA
    //SOGLIA MASSIMA
    //ALIQUOTA
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }

}
