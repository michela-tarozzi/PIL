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
 * Created by m.tarozzi on 14/10/2017.
 */
@Entity
@Table(name="AliquoteAddizionali")
public class AliquoteAddizionali implements Externalizable {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;

    @Column(nullable=false)
    private IntegerProperty anno;
    @Transient
    @Expose
    private int _anno;

    @Column(nullable=false)
    private FloatProperty aliquotaComunale;
    @Transient
    @Expose
    private float _aliquotaComunale;

    @Column(nullable=false)
    private FloatProperty aliquotaRegionale;
    @Transient
    @Expose
    private float _aliquotaRegionale;

    @ManyToOne(optional = true)
    @JoinColumn(name="idSocio", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_SOCIO"))
    private Socio socio;
    //SOCIO
    public void setSocio(Socio quiz){this.socio=quiz;}
    public Socio getSocio(){return this.socio;}


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }


    @Access(AccessType.PROPERTY)
    public int getAnno() {
        if (this.anno == null) {
            return _anno;
        } else {
            return this.anno.get();
        }
    }
    public IntegerProperty annoProperty() {
        if (this.anno == null) {
            this.anno = new SimpleIntegerProperty(this, "anno", _anno);
        }
        return this.anno;
    }

    public void setAnno(int anno) {
        if (this.anno == null) {
            _anno = anno;
        } else {
            this.anno.set(anno);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getAliquotaComunale() {
        if (this.aliquotaComunale == null) {
            return _aliquotaComunale;
        } else {
            return this.aliquotaComunale.get();
        }
    }

    public FloatProperty aliquotaComunaleProperty() {
        if (this.aliquotaComunale == null) {
            this.aliquotaComunale = new SimpleFloatProperty(this, "aliquotaComunale", _aliquotaComunale);
        }
        return this.aliquotaComunale;
    }

    public void setAliquotaComunale(float aliquotaComunale) {
        if (this.aliquotaComunale == null) {
            _aliquotaComunale = aliquotaComunale;
        } else {
            this.aliquotaComunale.set(aliquotaComunale);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getAliquotaRegionale() {
        if (this.aliquotaRegionale == null) {
            return _aliquotaRegionale;
        } else {
            return this.aliquotaRegionale.get();
        }
    }

    public FloatProperty aliquotaRegionaleProperty() {
        if (this.aliquotaRegionale == null) {
            this.aliquotaRegionale = new SimpleFloatProperty(this, "aliquotaRegionale", _aliquotaRegionale);
        }
        return this.aliquotaRegionale;
    }

    public void setAliquotaRegionale(float aliquotaRegionale) {
        if (this.aliquotaRegionale == null) {
            _aliquotaRegionale = aliquotaRegionale;
        } else {
            this.aliquotaRegionale.set(aliquotaRegionale);
        }
    }
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getAnno());
        out.writeObject(this.getAliquotaComunale());
        out.writeObject(this.getAliquotaRegionale());
        out.writeObject(this.getSocio());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setAnno((int) in.readObject());
        this.setAliquotaComunale((float) in.readObject());
        this.setAliquotaRegionale((float) in.readObject());
        this.setSocio((Socio) in.readObject());
    }

    @Override
    public String toString() {
        return socio+ " "+ _anno +" - " + _aliquotaComunale+" - "+_aliquotaComunale;
    }
}
