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
@Table(name="addizionaleComunale")
public class AddizionaleComunale implements Externalizable{
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;

    @Column(nullable=false)
    private StringProperty codice;
    @Transient
    @Expose
    private String _codice;

    @Column(nullable=false)
    private StringProperty comune;
    @Transient
    @Expose
    private String _comune;

    @Column(nullable=false)
    private IntegerProperty anno;
    @Transient
    @Expose
    private int _anno;


    @Column(nullable=false)
    private FloatProperty aliquota;
    @Transient
    @Expose
    private float _aliquota;

    @Access(AccessType.PROPERTY)
    public float getAliquota() {
        if (this.aliquota == null) {
            return _aliquota;
        } else {
            return this.aliquota.get();
        }
    }

    public FloatProperty aliquotaProperty() {
        if (this.aliquota == null) {
            this.aliquota = new SimpleFloatProperty(this, "aliquota", _aliquota);
        }
        return this.aliquota;
    }

    public void setAliquota(float aliquota) {
        if (this.aliquota == null) {
            _aliquota = aliquota;
        } else {
            this.aliquota.set(aliquota);
        }
    }

    @Column(nullable=false)
    private FloatProperty redditoMinimo;
    @Transient
    @Expose
    private float _redditoMinimo;

    @Access(AccessType.PROPERTY)
    public float getRedditoMinimo() {
        if (this.redditoMinimo == null) {
            return _redditoMinimo;
        } else {
            return this.redditoMinimo.get();
        }
    }

    public FloatProperty redditoMinimoProperty() {
        if (this.redditoMinimo == null) {
            this.redditoMinimo = new SimpleFloatProperty(this, "redditoMinimo", _redditoMinimo);
        }
        return this.redditoMinimo;
    }

    public void setRedditoMinimo(float aliquotaUno) {
        if (this.redditoMinimo == null) {
            _redditoMinimo = aliquotaUno;
        } else {
            this.redditoMinimo.set(aliquotaUno);
        }
    }
    @Column(nullable=false)
    private FloatProperty redditoMassimo;
    @Transient
    @Expose
    private float _redditoMassimo;

    @Access(AccessType.PROPERTY)
    public float getRedditoMassimo() {
        if (this.redditoMassimo == null) {
            return _redditoMassimo;
        } else {
            return this.redditoMassimo.get();
        }
    }

    public FloatProperty redditoMassimoProperty() {
        if (this.redditoMassimo == null) {
            this.redditoMassimo = new SimpleFloatProperty(this, "redditoMassimo", _redditoMassimo);
        }
        return this.redditoMassimo;
    }

    public void setRedditoMassimo(float redditoMassimo) {
        if (this.redditoMassimo == null) {
            _redditoMassimo = redditoMassimo;
        } else {
            this.redditoMassimo.set(redditoMassimo);
        }
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getCodice() {
        if (this.codice == null) {
            return _codice;
        } else {
            return this.codice.get();
        }
    }
    public StringProperty codiceProperty() {
        if (this.codice == null) {
            this.codice = new SimpleStringProperty(this, "codice", _codice);
        }
        return this.codice;
    }

    public void setCodice(String codice) {
        if (this.codice == null) {
            _codice = codice;
        } else {
            this.codice.set(codice);
        }
    }

       @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getComune() {
        if (this.comune == null) {
            return _comune;
        } else {
            return this.comune.get();
        }
    }
    public StringProperty comuneProperty() {
        if (this.comune == null) {
            this.comune = new SimpleStringProperty(this, "comune", _comune);
        }
        return this.comune;
    }

    public void setComune(String comune) {
        if (this.comune == null) {
            _comune = comune;
        } else {
            this.comune.set(comune);
        }
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


    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getCodice());
        out.writeObject(this.getComune());
        out.writeObject(this.getAnno());
        out.writeObject(this.getAliquota());
        out.writeObject(this.getRedditoMinimo());
        out.writeObject(this.getRedditoMassimo());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setCodice((String) in.readObject());
        this.setComune((String) in.readObject());
        this.setAnno((int) in.readObject());
        this.setAliquota((float) in.readObject());
        this.setRedditoMinimo((float) in.readObject());
        this.setRedditoMassimo((float) in.readObject());

    }

}