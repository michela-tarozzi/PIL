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
    private StringProperty fasciaUno;
    @Transient
    @Expose
    private String _fasciaUno;
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFasciaUno() {
        if (this.fasciaUno == null) {
            return _fasciaUno;
        } else {
            return this.fasciaUno.get();
        }
    }
    public StringProperty fasciaUnoProperty() {
        if (this.fasciaUno == null) {
            this.fasciaUno = new SimpleStringProperty(this, "fasciaUno", _fasciaUno);
        }
        return this.fasciaUno;
    }

    public void setFasciaUno(String fasciaUno) {
        if (this.fasciaUno == null) {
            _fasciaUno = fasciaUno;
        } else {
            this.fasciaUno.set(fasciaUno);
        }
    }
    @Column(nullable=false)
    private StringProperty fasciaDue;
    @Transient
    @Expose
    private String _fasciaDue;
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFasciaDue() {
        if (this.fasciaDue == null) {
            return _fasciaDue;
        } else {
            return this.fasciaDue.get();
        }
    }
    public StringProperty fasciaDueProperty() {
        if (this.fasciaDue == null) {
            this.fasciaDue = new SimpleStringProperty(this, "fasciaDue", _fasciaDue);
        }
        return this.fasciaDue;
    }

    public void setFasciaDue(String fasciaDue) {
        if (this.fasciaDue == null) {
            _fasciaDue = fasciaDue;
        } else {
            this.fasciaDue.set(fasciaDue);
        }
    }
    @Column(nullable=false)
    private StringProperty fasciaTre;
    @Transient
    @Expose
    private String _fasciaTre;
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFasciaTre() {
        if (this.fasciaTre == null) {
            return _fasciaTre;
        } else {
            return this.fasciaTre.get();
        }
    }
    public StringProperty fasciaTreProperty() {
        if (this.fasciaTre == null) {
            this.fasciaTre = new SimpleStringProperty(this, "fasciaTre", _fasciaTre);
        }
        return this.fasciaTre;
    }

    public void setFasciaTre(String fasciaTre) {
        if (this.fasciaTre == null) {
            _fasciaTre = fasciaTre;
        } else {
            this.fasciaTre.set(fasciaTre);
        }
    }
    @Column(nullable=false)
    private StringProperty fasciaQuattro;
    @Transient
    @Expose
    private String _fasciaQuattro;
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFasciaQuattro() {
        if (this.fasciaQuattro == null) {
            return _fasciaQuattro;
        } else {
            return this.fasciaQuattro.get();
        }
    }
    public StringProperty fasciaQuattroProperty() {
        if (this.fasciaQuattro == null) {
            this.fasciaQuattro = new SimpleStringProperty(this, "fasciaQuattro", _fasciaQuattro);
        }
        return this.fasciaQuattro;
    }

    public void setFasciaQuattro(String fasciaQuattro) {
        if (this.fasciaQuattro == null) {
            _fasciaQuattro = fasciaQuattro;
        } else {
            this.fasciaQuattro.set(fasciaQuattro);
        }
    }
    @Column(nullable=false)
    private StringProperty fasciaCinque;
    @Transient
    @Expose
    private String _fasciaCinque;
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFasciaCinque() {
        if (this.fasciaCinque == null) {
            return _fasciaCinque;
        } else {
            return this.fasciaCinque.get();
        }
    }
    public StringProperty fasciaCinqueProperty() {
        if (this.fasciaCinque == null) {
            this.fasciaCinque = new SimpleStringProperty(this, "fasciaCinque", _fasciaCinque);
        }
        return this.fasciaCinque;
    }

    public void setFasciaCinque(String fasciaCinque) {
        if (this.fasciaCinque == null) {
            _fasciaCinque = fasciaCinque;
        } else {
            this.fasciaCinque.set(fasciaCinque);
        }
    }


    @Column(nullable=false)
    private StringProperty fasciaZero;
    @Transient
    @Expose
    private String _fasciaZero;
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFasciaZero() {
        if (this.fasciaZero == null) {
            return _fasciaZero;
        } else {
            return this.fasciaZero.get();
        }
    }
    public StringProperty fasciaZeroProperty() {
        if (this.fasciaZero == null) {
            this.fasciaZero = new SimpleStringProperty(this, "fasciaZero", _fasciaZero);
        }
        return this.fasciaZero;
    }

    public void setFasciaZero(String fasciaZero) {
        if (this.fasciaZero == null) {
            _fasciaZero = fasciaZero;
        } else {
            this.fasciaZero.set(fasciaZero);
        }
    }


    @Column(nullable=false)
    private FloatProperty aliquotaZero;
    @Transient
    @Expose
    private float _aliquotaZero;

    @Access(AccessType.PROPERTY)
    public float getAliquotaZero() {
        if (this.aliquotaZero == null) {
            return _aliquotaZero;
        } else {
            return this.aliquotaZero.get();
        }
    }

    public FloatProperty aliquotaZeroProperty() {
        if (this.aliquotaZero == null) {
            this.aliquotaZero = new SimpleFloatProperty(this, "aliquotaZero", _aliquotaZero);
        }
        return this.aliquotaZero;
    }

    public void setAliquotaZero(float aliquotaZero) {
        if (this.aliquotaZero == null) {
            _aliquotaZero = aliquotaZero;
        } else {
            this.aliquotaZero.set(aliquotaZero);
        }
    }

    @Column(nullable=false)
    private FloatProperty aliquotaUno;
    @Transient
    @Expose
    private float _aliquotaUno;

    @Access(AccessType.PROPERTY)
    public float getAliquotaUno() {
        if (this.aliquotaUno == null) {
            return _aliquotaUno;
        } else {
            return this.aliquotaUno.get();
        }
    }

    public FloatProperty aliquotaUnoProperty() {
        if (this.aliquotaUno == null) {
            this.aliquotaUno = new SimpleFloatProperty(this, "aliquotaUno", _aliquotaUno);
        }
        return this.aliquotaUno;
    }

    public void setAliquotaUno(float aliquotaUno) {
        if (this.aliquotaUno == null) {
            _aliquotaUno = aliquotaUno;
        } else {
            this.aliquotaUno.set(aliquotaUno);
        }
    }
    @Column(nullable=false)
    private FloatProperty aliquotaDue;
    @Transient
    @Expose
    private float _aliquotaDue;

    @Access(AccessType.PROPERTY)
    public float getAliquotaDue() {
        if (this.aliquotaDue == null) {
            return _aliquotaDue;
        } else {
            return this.aliquotaDue.get();
        }
    }

    public FloatProperty aliquotaDueProperty() {
        if (this.aliquotaDue == null) {
            this.aliquotaDue = new SimpleFloatProperty(this, "aliquotaDue", _aliquotaDue);
        }
        return this.aliquotaDue;
    }

    public void setAliquotaDue(float aliquotaDue) {
        if (this.aliquotaDue == null) {
            _aliquotaDue = aliquotaDue;
        } else {
            this.aliquotaDue.set(aliquotaDue);
        }
    }
    @Column(nullable=false)
    private FloatProperty aliquotaTre;
    @Transient
    @Expose
    private float _aliquotaTre;

    @Access(AccessType.PROPERTY)
    public float getAliquotaTre() {
        if (this.aliquotaTre == null) {
            return _aliquotaTre;
        } else {
            return this.aliquotaTre.get();
        }
    }

    public FloatProperty aliquotaTreProperty() {
        if (this.aliquotaTre == null) {
            this.aliquotaTre = new SimpleFloatProperty(this, "aliquotaTre", _aliquotaTre);
        }
        return this.aliquotaTre;
    }

    public void setAliquotaTre(float aliquotaTre) {
        if (this.aliquotaTre == null) {
            _aliquotaTre = aliquotaTre;
        } else {
            this.aliquotaTre.set(aliquotaTre);
        }
    }

    @Column(nullable=false)
    private FloatProperty aliquotaQuattro;
    @Transient
    @Expose
    private float _aliquotaQuattro;

    @Access(AccessType.PROPERTY)
    public float getAliquotaQuattro() {
        if (this.aliquotaQuattro == null) {
            return _aliquotaQuattro;
        } else {
            return this.aliquotaQuattro.get();
        }
    }

    public FloatProperty aliquotaQuattroProperty() {
        if (this.aliquotaQuattro == null) {
            this.aliquotaQuattro = new SimpleFloatProperty(this, "aliquotaQuattro", _aliquotaQuattro);
        }
        return this.aliquotaQuattro;
    }

    public void setAliquotaQuattro(float aliquotaQuattro) {
        if (this.aliquotaQuattro == null) {
            _aliquotaQuattro = aliquotaQuattro;
        } else {
            this.aliquotaQuattro.set(aliquotaQuattro);
        }
    }
    @Column(nullable=false)
    private FloatProperty aliquotaCinque;
    @Transient
    @Expose
    private float _aliquotaCinque;

    @Access(AccessType.PROPERTY)
    public float getAliquotaCinque() {
        if (this.aliquotaCinque == null) {
            return _aliquotaCinque;
        } else {
            return this.aliquotaCinque.get();
        }
    }

    public FloatProperty aliquotaCinqueProperty() {
        if (this.aliquotaCinque == null) {
            this.aliquotaCinque = new SimpleFloatProperty(this, "aliquotaCinque", _aliquotaCinque);
        }
        return this.aliquotaCinque;
    }

    public void setAliquotaCinque(float aliquotaCinque) {
        if (this.aliquotaCinque == null) {
            _aliquotaCinque = aliquotaCinque;
        } else {
            this.aliquotaCinque.set(aliquotaCinque);
        }
    }

    @Column(nullable=false)
    private FloatProperty sogliaEsente;
    @Transient
    @Expose
    private float _sogliaEsente;

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


    @Access(AccessType.PROPERTY)
    public float getSogliaEsente() {
        if (this.sogliaEsente == null) {
            return _sogliaEsente;
        } else {
            return this.sogliaEsente.get();
        }
    }

    public FloatProperty sogliaMassimaProperty() {
        if (this.sogliaEsente == null) {
            this.sogliaEsente = new SimpleFloatProperty(this, "sogliaMassima", _sogliaEsente);
        }
        return this.sogliaEsente;
    }

    public void setSogliaEsente(float sogliaMassima) {
        if (this.sogliaEsente == null) {
            _sogliaEsente = sogliaMassima;
        } else {
            this.sogliaEsente.set(sogliaMassima);
        }
    }


    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getCodice());
        out.writeObject(this.getComune());
        out.writeObject(this.getAnno());
        out.writeObject(this.getSogliaEsente());
        out.writeObject(this.getAliquotaZero());
        out.writeObject(this.getAliquotaUno());
        out.writeObject(this.getAliquotaDue());
        out.writeObject(this.getAliquotaTre());
        out.writeObject(this.getAliquotaQuattro());
        out.writeObject(this.getAliquotaCinque());
        out.writeObject(this.getFasciaZero());
        out.writeObject(this.getFasciaUno());
        out.writeObject(this.getFasciaDue());
        out.writeObject(this.getFasciaTre());
        out.writeObject(this.getFasciaQuattro());
        out.writeObject(this.getFasciaCinque());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setCodice((String) in.readObject());
        this.setComune((String) in.readObject());
        this.setAnno((int) in.readObject());
        this.setSogliaEsente((float) in.readObject());
        this.setAliquotaZero((float) in.readObject());
        this.setAliquotaUno((float) in.readObject());
        this.setAliquotaDue((float) in.readObject());
        this.setAliquotaTre((float) in.readObject());
        this.setAliquotaQuattro((float) in.readObject());
        this.setAliquotaCinque((float) in.readObject());
        this.setFasciaZero((String) in.readObject());
        this.setFasciaUno((String) in.readObject());
        this.setFasciaDue((String) in.readObject());
        this.setFasciaTre((String) in.readObject());
        this.setFasciaQuattro((String) in.readObject());
        this.setFasciaCinque((String) in.readObject());
    }

}