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
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by m.tarozzi on 14/10/2017.
 */

@Entity
@Table(name="pensioni")
public class Pensioni implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    //FK SOCIO
    //FK PAGAMENTo

    @Column(nullable = false)
    private FloatProperty lordo;
    @Transient
    @Expose
    private float _lordo;

    @Column(nullable = false)
    private FloatProperty sussidio;
    @Transient
    @Expose
    private float _sussidio;

    @Column(nullable = false)
    private FloatProperty carovita;
    @Transient
    @Expose
    private float _carovita;

    @Column(nullable = false)
    private FloatProperty addizionaleComunale;
    @Transient
    @Expose
    private float _addizionaleComunale;

    @Column(nullable = false)
    private FloatProperty ritenuta;
    @Transient
    @Expose
    private float _ritenuta;

    @Column(nullable = false)
    private FloatProperty addizionaleRegionale;
    @Transient
    @Expose
    private float _addizionaleRegionale;

    @Column(nullable = false)
    private FloatProperty netto;
    @Transient
    @Expose
    private float _netto;

    @Column(nullable = false)
    private ObjectProperty<LocalDate> data;
    @Transient
    @Expose
    private LocalDate _data;

    @Column(nullable=false)
    private StringProperty stato;
    @Transient
    @Expose
    private String _stato;

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
    public float getSussidio() {
        if (this.sussidio == null) {
            return _sussidio;
        } else {
            return this.sussidio.get();
        }
    }

    public FloatProperty sussidioProperty() {
        if (this.sussidio == null) {
            this.sussidio = new SimpleFloatProperty(this, "sussidio", _sussidio);
        }
        return this.sussidio;
    }

    public void setSussidio(float sussidio) {
        if (this.sussidio == null) {
            _sussidio = sussidio;
        } else {
            this.sussidio.set(sussidio);
        }
    }
    @Access(AccessType.PROPERTY)
    public float getCarovita() {
        if (this.carovita == null) {
            return _carovita;
        } else {
            return this.carovita.get();
        }
    }

    public FloatProperty carovitaProperty() {
        if (this.carovita == null) {
            this.carovita = new SimpleFloatProperty(this, "carovita", _carovita);
        }
        return this.carovita;
    }

    public void setCarovita(float carovita) {
        if (this.carovita == null) {
            _carovita = carovita;
        } else {
            this.carovita.set(carovita);
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
    @Access(AccessType.PROPERTY)
    public float getAddizionaleComunale() {
        if (this.addizionaleComunale == null) {
            return _addizionaleComunale;
        } else {
            return this.addizionaleComunale.get();
        }
    }

    public FloatProperty addizionaleComunaleProperty() {
        if (this.addizionaleComunale == null) {
            this.addizionaleComunale = new SimpleFloatProperty(this, "addizionaleComunale", _addizionaleComunale);
        }
        return this.addizionaleComunale;
    }

    public void setAddizionaleComunale(float addizionaleComunale) {
        if (this.addizionaleComunale == null) {
            _addizionaleComunale = addizionaleComunale;
        } else {
            this.addizionaleComunale.set(addizionaleComunale);
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
    public float getAddizionaleRegionale() {
        if (this.addizionaleRegionale == null) {
            return _addizionaleRegionale;
        } else {
            return this.addizionaleRegionale.get();
        }
    }

    public FloatProperty addizionaleRegionaleProperty() {
        if (this.addizionaleRegionale == null) {
            this.addizionaleRegionale = new SimpleFloatProperty(this, "addizionaleRegionale", _addizionaleRegionale);
        }
        return this.addizionaleRegionale;
    }

    public void setAddizionaleRegionale(float addizionaleRegionale) {
        if (this.addizionaleRegionale == null) {
            _addizionaleRegionale = addizionaleRegionale;
        } else {
            this.addizionaleRegionale.set(addizionaleRegionale);
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
    @NotBlank
    @NotEmpty
    public String getStato() {
        if (this.stato == null) {
            return _stato;
        } else {
            return this.stato.get();
        }
    }
    public StringProperty statoProperty() {
        if (this.stato == null) {
            this.stato = new SimpleStringProperty(this, "stato", _stato);
        }
        return this.stato;
    }

    public void setStato(String stato) {
        if (this.stato == null) {
            _stato = stato;
        } else {
            this.stato.set(stato);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getSussidio());
        out.writeObject(this.getCarovita());
        out.writeObject(this.getLordo());
        out.writeObject(this.getRitenuta());
        out.writeObject(this.getAddizionaleRegionale());
        out.writeObject(this.getAddizionaleComunale());
        out.writeObject(this.getNetto());
        out.writeObject(this.getStato());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setSussidio((float) in.readObject());
        this.setCarovita((float) in.readObject());
        this.setLordo((float) in.readObject());
        this.setRitenuta((float) in.readObject());
        this.setAddizionaleRegionale((float) in.readObject());
        this.setAddizionaleComunale((float) in.readObject());
        this.setNetto((float) in.readObject());
        this.setStato((String) in.readObject());

    }







}
