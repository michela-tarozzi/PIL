package Pojo;

import com.google.gson.annotations.Expose;
import javafx.beans.property.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by m.tarozzi on 07/10/2017.
 */

@Entity
@Table(name="Quote")
public class Quote implements Externalizable {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;

    @Column(nullable = false)
    private ObjectProperty<LocalDate> data;
    @Transient
    @Expose
    private LocalDate _data;

    @Column(nullable=false)
    private FloatProperty importo;
    @Transient
    @Expose
    private float _importo;

    @ManyToOne(optional = true)
    @JoinColumn(name="idSocio", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_SOCIO"))
    private Socio socio;
    //SOCIO
    public void setSocio(Socio socio){this.socio=socio;}
    public Socio getSocio(){return this.socio;}

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
    public LocalDate getData() {
        if (this.data == null) {
            return _data;
        } else {
            return this.data.get();
        }
    }

    public void setData(LocalDate data) {
        if (this.data == null) {
            _data = data;
        } else {
            this.data.set(data);
        }
    }

    public ObjectProperty<LocalDate> dataProperty() {
        if (this.data == null) {
            this.data = new SimpleObjectProperty<>(this, "data", _data);

        }
        return this.data;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getData());
        out.writeObject(this.getImporto());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setData((LocalDate) in.readObject());
        this.setImporto((float)in.readObject());
    }
}
