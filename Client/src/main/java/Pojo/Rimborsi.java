package Pojo;

import com.google.gson.annotations.Expose;
import javafx.beans.property.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * Created by m.tarozzi on 14/10/2017.
 */
@Entity
@Table(name="rimborsi")
public class Rimborsi implements Externalizable {
    //FK SPESA
    //FK PAGAMENTO
    //FK REGOLA

    //ID
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;

    //DATA
    @Column(nullable = false)
    private ObjectProperty<Date> data;
    @Transient
    @Expose
    private Date _data;

    //IMPORTO
    @Column(nullable=false)
    private FloatProperty importo;
    @Transient
    @Expose
    private float _importo;


    //IMPORTO SPESA
    @Column(nullable=false)
    private FloatProperty importoSpesa;
    @Transient
    @Expose
    private float _importoSpesa;




    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }


    @Access(AccessType.PROPERTY)
    public float getImporto() {
        if (this.importo == null) {
            return _importo;
        } else {
            return this.importo.get();
        }
    }

    public FloatProperty importoProperty() {
        if (this.importo == null) {
            this.importo = new SimpleFloatProperty(this, "importo", _importo);
        }
        return this.importo;
    }

    public void setImporto(float importo) {
        if (this.importo == null) {
            _importo = importo;
        } else {
            this.importo.set(importo);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getImportoSpesa() {
        if (this.importoSpesa == null) {
            return _importoSpesa;
        } else {
            return this.importoSpesa.get();
        }
    }

    public FloatProperty importoSpesaProperty() {
        if (this.importoSpesa == null) {
            this.importoSpesa = new SimpleFloatProperty(this, "importoSpesa", _importoSpesa);
        }
        return this.importoSpesa;
    }

    public void setImportoSpesa(float importoSpesa) {
        if (this.importoSpesa == null) {
            _importoSpesa = importoSpesa;
        } else {
            this.importoSpesa.set(importoSpesa);
        }
    }

    @Access(AccessType.PROPERTY)
    public Date getData() {

        if (this.data == null) {
            return _data;

        } else {
            return this.data.get();
        }
    }

    public void setData(Date data) {
        if (this.data == null) {
            _data = data;
        } else {
            this.data.set(data);
        }
    }


    public ObjectProperty<Date> dataProperty() {
        if (this.data == null) {
            this.data = new SimpleObjectProperty<>(this, "data", _data);

        }
        return this.data;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getData());
        out.writeObject(this.getImporto());
        out.writeObject(this.getImportoSpesa());


    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setData((Date) in.readObject());
        this.setImporto((float) in.readObject());
        this.setImportoSpesa((float) in.readObject());


    }



}
