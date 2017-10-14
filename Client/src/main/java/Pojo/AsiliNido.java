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
@Table(name="addizionaleComunale")
public class AsiliNido implements Externalizable {

    //FK PAGAMENTO
    //FK SOCIO

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    @Column(nullable = false)
    private StringProperty figlio;
    @Transient
    @Expose
    private String _figlio;

    @Column(nullable = false)
    private FloatProperty spesa;
    @Transient
    @Expose
    private float _spesa;

    @Column(nullable = false)
    private IntegerProperty anno;
    @Transient
    @Expose
    private int _anno;

    @Column(nullable = false)
    private FloatProperty rimborso;
    @Transient
    @Expose
    private float _rimborso;

    @Column(nullable = false)
    private FloatProperty integrazione;
    @Transient
    @Expose
    private float _integrazione;

    @Column(nullable = false)
    private FloatProperty percentuale;
    @Transient
    @Expose
    private float _percentuale;

    //setter, getter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getFiglio() {
        if (this.figlio == null) {
            return _figlio;
        } else {
            return this.figlio.get();
        }
    }
    public StringProperty figlioProperty() {
        if (this.figlio == null) {
            this.figlio = new SimpleStringProperty(this, "figlio", _figlio);
        }
        return this.figlio;
    }

    public void setFiglio(String figlio) {
        if (this.figlio == null) {
            _figlio = figlio;
        } else {
            this.figlio.set(figlio);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getSpesa() {
        if (this.spesa == null) {
            return _spesa;
        } else {
            return this.spesa.get();
        }
    }

    public FloatProperty spesaProperty() {
        if (this.spesa == null) {
            this.spesa = new SimpleFloatProperty(this, "spesa", _spesa);
        }
        return this.spesa;
    }

    public void setSpesa(float spesa) {
        if (this.spesa == null) {
            _spesa = spesa;
        } else {
            this.spesa.set(spesa);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getRimborso() {
        if (this.rimborso == null) {
            return _rimborso;
        } else {
            return this.rimborso.get();
        }
    }

    public FloatProperty rimborsoProperty() {
        if (this.rimborso == null) {
            this.rimborso = new SimpleFloatProperty(this, "rimborso", _rimborso);
        }
        return this.rimborso;
    }

    public void setRimborso(float rimborso) {
        if (this.rimborso == null) {
            _rimborso = rimborso;
        } else {
            this.rimborso.set(rimborso);
        }
    }
    @Access(AccessType.PROPERTY)
    public float getIntegrazione() {
        if (this.integrazione == null) {
            return _integrazione;
        } else {
            return this.integrazione.get();
        }
    }

    public FloatProperty integrazioneProperty() {
        if (this.integrazione == null) {
            this.integrazione = new SimpleFloatProperty(this, "integrazione", _integrazione);
        }
        return this.integrazione;
    }

    public void setIntegrazione(float integrazione) {
        if (this.integrazione == null) {
            _integrazione = integrazione;
        } else {
            this.integrazione.set(integrazione);
        }
    }
    @Access(AccessType.PROPERTY)
    public float getPercentuale() {
        if (this.percentuale == null) {
            return _percentuale;
        } else {
            return this.percentuale.get();
        }
    }

    public FloatProperty percentualeProperty() {
        if (this.percentuale == null) {
            this.percentuale = new SimpleFloatProperty(this, "percentuale", _percentuale);
        }
        return this.percentuale;
    }

    public void setPercentuale(float percentuale) {
        if (this.percentuale == null) {
            _percentuale = percentuale;
        } else {
            this.percentuale.set(percentuale);
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
        out.writeObject(this.getFiglio());
        out.writeObject(this.getAnno());
        out.writeObject(this.getSpesa());
        out.writeObject(this.getRimborso());
        out.writeObject(this.getPercentuale());
        out.writeObject(this.getIntegrazione());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setFiglio((String) in.readObject());
        this.setAnno((int) in.readObject());
        this.setSpesa((float) in.readObject());
        this.setRimborso((float) in.readObject());
        this.setPercentuale((float) in.readObject());
        this.setIntegrazione((float) in.readObject());
    }










}
