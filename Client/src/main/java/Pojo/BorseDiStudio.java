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
@Table(name="borseDiStudio")
public class BorseDiStudio implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    //NOMEFIGLIO
    //IBAN FIGLIO
    //CFU
    //ANNO
    //RITENUTA
    //LORDO
    //NETTO
    //FK SOCIO
    //FK PAGAMENTo


    @Column(nullable = false)
    private StringProperty figlio;
    @Transient
    @Expose
    private String _figlio;

    @Column(nullable = true)
    private StringProperty IBAN;
    @Transient
    @Expose
    private String _IBAN;

    @Column(nullable = false)
    private IntegerProperty CFU;
    @Transient
    @Expose
    private int _CFU;

    @Column(nullable = false)
    private IntegerProperty anno;
    @Transient
    @Expose
    private int _anno;

    @Column(nullable = false)
    private FloatProperty ritenuta;
    @Transient
    @Expose
    private float _ritenuta;

    @Column(nullable = false)
    private FloatProperty lordo;
    @Transient
    @Expose
    private float _lordo;

    @Column(nullable = false)
    private FloatProperty netto;
    @Transient
    @Expose
    private float _netto;

    @ManyToOne(optional = true)
    @JoinColumn(name="idSocio", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_SOCIO"))
    private Socio socio;
    //SOCIO
    public void setSocio(Socio quiz){this.socio=quiz;}
    public Socio getSocio(){return this.socio;}

    @ManyToOne(optional = true)
    @JoinColumn(name="idPagamento", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_PAGAMENTO"))
    private Pagamenti pagamento;
    //pagamwnto
    public void setPagamento(Pagamenti pagamento){this.pagamento=pagamento;}
    public Pagamenti getPagamento(){return this.pagamento;}

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
    public float getRitenuta() {
        if (this.ritenuta == null) {
            return _ritenuta;
        } else {
            return this.ritenuta.get();
        }
    }

    public FloatProperty ritenutaProperty() {
        if (this.ritenuta == null) {
            this.ritenuta = new SimpleFloatProperty(this, "ritenuta", _ritenuta);
        }
        return this.ritenuta;
    }

    public void setRitenuta(float ritenuta) {
        if (this.ritenuta == null) {
            _ritenuta = ritenuta;
        } else {
            this.ritenuta.set(ritenuta);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getLordo() {
        if (this.lordo == null) {
            return _lordo;
        } else {
            return this.lordo.get();
        }
    }

    public FloatProperty lordoProperty() {
        if (this.lordo == null) {
            this.lordo = new SimpleFloatProperty(this, "lordo", _lordo);
        }
        return this.lordo;
    }

    public void setLordo(float lordo) {
        if (this.lordo == null) {
            _lordo = lordo;
        } else {
            this.lordo.set(lordo);
        }
    }
    @Access(AccessType.PROPERTY)
    public float getNetto() {
        if (this.netto == null) {
            return _netto;
        } else {
            return this.netto.get();
        }
    }

    public FloatProperty nettoProperty() {
        if (this.netto == null) {
            this.netto = new SimpleFloatProperty(this, "netto", _netto);
        }
        return this.netto;
    }

    public void setNetto(float netto) {
        if (this.netto == null) {
            _netto = netto;
        } else {
            this.netto.set(netto);
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
    public int getCFU() {
        if (this.CFU == null) {
            return _CFU;
        } else {
            return this.CFU.get();
        }
    }
    public IntegerProperty CFUProperty() {
        if (this.CFU == null) {
            this.CFU = new SimpleIntegerProperty(this, "CFU", _CFU);
        }
        return this.CFU;
    }

    public void setCFU(int cfu) {
        if (this.CFU == null) {
            _CFU = cfu;
        } else {
            this.CFU.set(cfu);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getFiglio());
        out.writeObject(this.getAnno());
        out.writeObject(this.getCFU());
        out.writeObject(this.getIBAN());
        out.writeObject(this.getLordo());
        out.writeObject(this.getNetto());
        out.writeObject(this.getRitenuta());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setFiglio((String) in.readObject());
        this.setAnno((int) in.readObject());
        this.setCFU((int) in.readObject());
        this.setIBAN((String) in.readObject());
        this.setLordo((float) in.readObject());
        this.setNetto((float) in.readObject());
        this.setRitenuta((float) in.readObject());
    }







}
