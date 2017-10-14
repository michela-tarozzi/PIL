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
@Table(name="Spese")
public class Spese implements Externalizable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;

    @Column(nullable = false)
    private ObjectProperty<Date> data;
    @Transient
    @Expose
    private Date _data;

    @Column(nullable=false)
    private FloatProperty importo;
    @Transient
    @Expose
    private float _importo;


    @Column(nullable=false)
    private StringProperty numero;
    @Transient
    @Expose
    private String _numero;

    //SOCIO

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
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

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getNumero() {
        if (this.numero == null) {
            return _numero;
        } else {
            return this.numero.get();
        }
    }
    public StringProperty NumeroProperty() {
        if (this.numero == null) {
            this.numero = new SimpleStringProperty(this, "numero", _numero);
        }
        return this.numero;
    }

    public void setNumero(String numero) {
        if (this.numero == null) {
            _numero = numero;
        } else {
            this.numero.set(numero);
        }
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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getNumero());
        out.writeObject(this.getData());
        out.writeObject(this.getImporto());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setNumero((String) in.readObject());
        this.setData((Date) in.readObject());
        this.setImporto((float) in.readObject());
    }
}
