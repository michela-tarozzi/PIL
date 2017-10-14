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
@Table(name="regoleRimborsi")
public class RegoleRimborsi implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    //NOMEFIGLIO
    //IBAN FIGLIO
    //ricorrenza
    //ANNO
    //RITENUTA
    //LORDO
    //NETTO
    //FK SOCIO
    //FK PAGAMENTo


    @Column(nullable = false)
    private StringProperty descrizione;
    @Transient
    @Expose
    private String _descrizione;

    @Column(nullable = false)
    private StringProperty IBAN;
    @Transient
    @Expose
    private String _IBAN;

    @Column(nullable = false)
    private IntegerProperty ricorrenza;
    @Transient
    @Expose
    private int _ricorrenza;

    @Column(nullable = false)
    private IntegerProperty anno;
    @Transient
    @Expose
    private int _anno;

    @Column(nullable = false)
    private FloatProperty percentuale;
    @Transient
    @Expose
    private float _percentuale;

    @Column(nullable = false)
    private FloatProperty maxAnnuo;
    @Transient
    @Expose
    private float _maxAnnuo;

    @Column(nullable = false)
    private FloatProperty maxSingolaPrestazione;
    @Transient
    @Expose
    private float _maxSingolaPrestazione;

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
    public String getDescrizione() {
        if (this.descrizione == null) {
            return _descrizione;
        } else {
            return this.descrizione.get();
        }
    }
    public StringProperty descrizioneProperty() {
        if (this.descrizione == null) {
            this.descrizione = new SimpleStringProperty(this, "descrizione", _descrizione);
        }
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        if (this.descrizione == null) {
            _descrizione = descrizione;
        } else {
            this.descrizione.set(descrizione);
        }
    }

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getIBAN() {
        if (this.IBAN == null) {
            return _IBAN;
        } else {
            return this.IBAN.get();
        }
    }
    public StringProperty IBANProperty() {
        if (this.IBAN == null) {
            this.IBAN = new SimpleStringProperty(this, "IBAN", _IBAN);
        }
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        if (this.IBAN == null) {
            _IBAN = IBAN;
        } else {
            this.IBAN.set(IBAN);
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
    public float getMaxAnnuo() {
        if (this.maxAnnuo == null) {
            return _maxAnnuo;
        } else {
            return this.maxAnnuo.get();
        }
    }

    public FloatProperty maxAnnuoProperty() {
        if (this.maxAnnuo == null) {
            this.maxAnnuo = new SimpleFloatProperty(this, "maxAnnuo", _maxAnnuo);
        }
        return this.maxAnnuo;
    }

    public void setMaxAnnuo(float maxAnnuo) {
        if (this.maxAnnuo == null) {
            _maxAnnuo = maxAnnuo;
        } else {
            this.maxAnnuo.set(maxAnnuo);
        }
    }
    @Access(AccessType.PROPERTY)
    public float getMaxSingolaPrestazione() {
        if (this.maxSingolaPrestazione == null) {
            return _maxSingolaPrestazione;
        } else {
            return this.maxSingolaPrestazione.get();
        }
    }

    public FloatProperty maxSingolaPrestazioneProperty() {
        if (this.maxSingolaPrestazione == null) {
            this.maxSingolaPrestazione = new SimpleFloatProperty(this, "maxSingolaPrestazione", _maxSingolaPrestazione);
        }
        return this.maxSingolaPrestazione;
    }

    public void setMaxSingolaPrestazione(float maxSingolaPrestazione) {
        if (this.maxSingolaPrestazione == null) {
            _maxSingolaPrestazione = maxSingolaPrestazione;
        } else {
            this.maxSingolaPrestazione.set(maxSingolaPrestazione);
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
    public int getRicorrenza() {
        if (this.ricorrenza == null) {
            return _ricorrenza;
        } else {
            return this.ricorrenza.get();
        }
    }
    public IntegerProperty ricorrenzaProperty() {
        if (this.ricorrenza == null) {
            this.ricorrenza = new SimpleIntegerProperty(this, "ricorrenza", _ricorrenza);
        }
        return this.ricorrenza;
    }

    public void setRicorrenza(int cfu) {
        if (this.ricorrenza == null) {
            _ricorrenza = cfu;
        } else {
            this.ricorrenza.set(cfu);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getDescrizione());
        out.writeObject(this.getAnno());
        out.writeObject(this.getRicorrenza());
        out.writeObject(this.getIBAN());
        out.writeObject(this.getMaxAnnuo());
        out.writeObject(this.getMaxSingolaPrestazione());
        out.writeObject(this.getPercentuale());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setDescrizione((String) in.readObject());
        this.setAnno((int) in.readObject());
        this.setRicorrenza((int) in.readObject());
        this.setIBAN((String) in.readObject());
        this.setMaxAnnuo((float) in.readObject());
        this.setMaxSingolaPrestazione((float) in.readObject());
        this.setPercentuale((float) in.readObject());
    }







}
