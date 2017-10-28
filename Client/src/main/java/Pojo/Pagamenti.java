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
import java.util.Date;
import java.util.List;

/**
 * Created by m.tarozzi on 14/10/2017.
 */

@Entity
@Table(name="pagamenti")
public class Pagamenti implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    @Column(nullable = false)
    private ObjectProperty<LocalDate> data;
    @Transient
    @Expose
    private LocalDate _data;

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

    @Column(nullable = false)
    private FloatProperty trattenuta;
    @Transient
    @Expose
    private float _trattenuta;

    @OneToMany(mappedBy = "pagamento",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<Rimborsi> rimborsi=new ArrayList<>();

    public void addRimborso(Rimborsi rimborsi){
        this.rimborsi.add(rimborsi);
    }
    public void removeRimborso(Rimborsi rimborsi)
    {
        this.rimborsi.remove(rimborsi);
    }
    public void setRimborsi(List<Rimborsi> rimborsi) {
        this.rimborsi = rimborsi;
    }
    public List<Rimborsi> getRimborsi() {
        return rimborsi;
    }

    @Column
    private StringProperty descrizione;
    @Transient
    @Expose
    private String _descrizione;
    
    @OneToMany(mappedBy = "pagamento",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<Pensioni> pensioni=new ArrayList<>();

    public void addPensione(Pensioni pensioni){
        this.pensioni.add(pensioni);
    }
    public void removePensione(Pensioni pensioni)
    {
        this.pensioni.remove(pensioni);
    }
    public void setPensioni(List<Pensioni> pensioni) {
        this.pensioni = pensioni;
    }
    public List<Pensioni> getPensioni() {
        return pensioni;
    }

    @OneToMany(mappedBy = "pagamento",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<Eredi> eredi=new ArrayList<>();

    public void addEredi(Eredi eredi){
        this.eredi.add(eredi);
    }
    public void removeEredi(Eredi eredi)
    {
        this.eredi.remove(eredi);
    }
    public void setEredi(List<Eredi> eredi) {
        this.eredi = eredi;
    }
    public List<Eredi> getEredi() {
        return eredi;
    }

    @OneToMany(mappedBy = "pagamento",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<BorseDiStudio> borseDiStudio=new ArrayList<>();

    public void addBorseDiStudio(BorseDiStudio borseDiStudio){
        this.borseDiStudio.add(borseDiStudio);
    }
    public void removBorseDiStudio(BorseDiStudio borseDiStudio)
    {
        this.borseDiStudio.remove(borseDiStudio);
    }
    public void setBorseDiStudio(List<BorseDiStudio> borseDiStudio) {
        this.borseDiStudio = borseDiStudio;
    }
    public List<BorseDiStudio> getBorseDiStudio() {
        return borseDiStudio;
    }

    @OneToMany(mappedBy = "pagamento",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<AsiliNido> asiliNido=new ArrayList<>();

    public void addAsiliNido(AsiliNido asiliNido){
        this.asiliNido.add(asiliNido);
    }
    public void removAsiliNido(AsiliNido asiliNido)
    {
        this.asiliNido.remove(asiliNido);
    }
    public void setAsiliNido(List<AsiliNido> asiliNido) {
        this.asiliNido = asiliNido;
    }
    public List<AsiliNido> getAsiliNido() {
        return asiliNido;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name="idConto", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_CONTO"))
    private Conti conto;
    //SOCIO
    public void setConto(Conti conto){this.conto=conto;}
    public Conti getConto(){return this.conto;}
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
    public float getTrattenuta() {
        if (this.trattenuta == null) {
            return _trattenuta;
        } else {
            return this.trattenuta.get();
        }
    }

    public FloatProperty trattenutaProperty() {
        if (this.trattenuta == null) {
            this.trattenuta = new SimpleFloatProperty(this, "trattenuta", _trattenuta);
        }
        return this.trattenuta;
    }

    public void setTrattenuta(float trattenuta) {
        if (this.trattenuta == null) {
            _trattenuta = trattenuta;
        } else {
            this.trattenuta.set(trattenuta);
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

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getData());
        out.writeObject(this.getNetto());
        out.writeObject(this.getTrattenuta());
        out.writeObject(this.getLordo());
        out.writeObject(this.getRimborsi());
        out.writeObject(this.getPensioni());
        out.writeObject(this.getEredi());
        out.writeObject(this.getBorseDiStudio());
        out.writeObject(this.getAsiliNido());
        out.writeObject(this.getDescrizione());

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setData((LocalDate) in.readObject());
        this.setNetto((float) in.readObject());
        this.setTrattenuta((float) in.readObject());
        this.setLordo((float) in.readObject());
        this.setRimborsi((List) in.readObject());
        this.setPensioni((List) in.readObject());
        this.setEredi((List) in.readObject());
        this.setBorseDiStudio((List) in.readObject());
        this.setAsiliNido((List) in.readObject());
        this.setDescrizione((String) in.readObject());
    }
    @Override
    public String toString() {
        return _data+ " - "+ _netto;
    }






}
