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
/**
 * Created by m.tarozzi on 14/10/2017.
 */
@Entity
@Table(name="riservaMatematica")
public class RiservaMatematica implements Externalizable{

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
    private FloatProperty tasso;
    @Transient
    @Expose
    private float _tasso;

    @Column(nullable=false)
    private FloatProperty rateoAnnuo;
    @Transient
    @Expose
    private float _rateoAnnuo;

    @Column(nullable=false)
    private FloatProperty riservaEffettiva;
    @Transient
    @Expose
    private float _riservaEffettiva;

    @Column(nullable=false)
    private FloatProperty riservaTeorica;
    @Transient
    @Expose
    private float _riservaTeorica;

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
    public float getRateoAnnuo() {
        if (this.rateoAnnuo == null) {
            return _rateoAnnuo;
        } else {
            return this.rateoAnnuo.get();
        }
    }

    public FloatProperty rateoAnnuoProperty() {
        if (this.rateoAnnuo == null) {
            this.rateoAnnuo = new SimpleFloatProperty(this, "rateoAnnuo", _rateoAnnuo);
        }
        return this.rateoAnnuo;
    }

    public void setRateoAnnuo(float rateoAnnuo) {
        if (this.rateoAnnuo == null) {
            _rateoAnnuo = rateoAnnuo;
        } else {
            this.rateoAnnuo.set(rateoAnnuo);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getTasso() {
        if (this.tasso == null) {
            return _tasso;
        } else {
            return this.tasso.get();
        }
    }

    public FloatProperty tassoProperty() {
        if (this.tasso == null) {
            this.tasso = new SimpleFloatProperty(this, "tasso", _tasso);
        }
        return this.riservaEffettiva;
    }

    public void setTasso(float tasso) {
        if (this.tasso == null) {
            _tasso = tasso;
        } else {
            this.tasso.set(tasso);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getRiservaEffettiva() {
        if (this.riservaEffettiva == null) {
            return _riservaEffettiva;
        } else {
            return this.riservaEffettiva.get();
        }
    }

    public FloatProperty riservaEffettivaProperty() {
        if (this.riservaEffettiva == null) {
            this.riservaEffettiva = new SimpleFloatProperty(this, "riservaEffettiva", _riservaEffettiva);
        }
        return this.riservaEffettiva;
    }

    public void setRiservaEffettiva(float riservaEffettiva) {
        if (this.riservaEffettiva == null) {
            _riservaEffettiva = riservaEffettiva;
        } else {
            this.riservaEffettiva.set(riservaEffettiva);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getRiservaTeorica() {
        if (this.riservaTeorica == null) {
            return _riservaTeorica;
        } else {
            return this.riservaTeorica.get();
        }
    }

    public FloatProperty riservaTeoricaProperty() {
        if (this.riservaTeorica == null) {
            this.riservaTeorica = new SimpleFloatProperty(this, "riservaTeorica", _riservaTeorica);
        }
        return this.riservaTeorica;
    }

    public void setRiservaTeorica(float riservaTeorica) {
        if (this.riservaTeorica == null) {
            _riservaTeorica = riservaTeorica;
        } else {
            this.riservaTeorica.set(riservaTeorica);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getAnno());
        out.writeObject(this.getTasso());
        out.writeObject(this.getRateoAnnuo());
        out.writeObject(this.getRiservaEffettiva());
        out.writeObject(this.getRiservaTeorica());

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setAnno((int) in.readObject());
        this.setTasso((float)in.readObject());
        this.setRateoAnnuo((float) in.readObject());
        this.setRiservaEffettiva((float) in.readObject());
        this.setRiservaTeorica((float) in.readObject());

    }


}
