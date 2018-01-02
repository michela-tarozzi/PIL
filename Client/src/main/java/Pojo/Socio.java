package Pojo;

import com.google.gson.annotations.Expose;
import com.sun.org.apache.xpath.internal.operations.Quo;
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
 * Created by m.tarozzi on 07/10/2017.
 */

@Entity
@Table(name="Soci")

public class Socio implements Externalizable {
    //AGGIUNGERE CONTO CHE AVRà PENSIONE O QUOTE
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;

    @Column(nullable=false)
    private StringProperty CF;
    @Transient
    @Expose
    private String _cf;

    @Column(nullable=false)
    private StringProperty nome;
    @Transient
    @Expose
    private String _nome;
    @Column(nullable=false)
    private StringProperty cognome;
    @Transient
    @Expose
    private String _cognome;
    @Column
    private StringProperty indirizzo;
    @Transient
    @Expose
    private String _indirizzo;
    @Column(nullable=false)
    private StringProperty citta;
    @Transient
    @Expose
    private String _citta;
    @Column(nullable=false)
    private StringProperty categoria;
    @Transient
    @Expose
    private String _categoria;

    @Column(nullable=false)
    private StringProperty IBAN;
    @Transient
    @Expose
    private String _IBAN;

    @Column(nullable = false)
    private ObjectProperty<LocalDate> dataIscrizione;
    @Transient
    @Expose
    private LocalDate _dataIscrizione;

    @Column(nullable=false)
    private FloatProperty reddito;
    @Transient
    @Expose
    private float _reddito;

    @Column(nullable=false)
    private FloatProperty ritenuta;
    @Transient
    @Expose
    private float _ritenuta;

    @Column(nullable=false)
    private FloatProperty sussidioMensile;
    @Transient
    @Expose
    private float _sussidioMensile;

    @Column(nullable = false)
    private ObjectProperty<LocalDate> dataPensionamento;
    @Transient
    @Expose
    private LocalDate _dataPensionamento;

    @Column(nullable = true)
    private ObjectProperty<LocalDate> dataDecesso;
    @Transient
    @Expose
    private LocalDate _dataDecesso;

    @OneToMany(mappedBy = "socio",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<Spese> spese=new ArrayList<>();

    public void addSpesa(Spese spesa){
        this.spese.add(spesa);
    }
    public void removeSpesa(Spese spesa)
    {
        this.spese.remove(spesa);
    }
    public void setSpese(List<Spese> spese) {
        this.spese = spese;
    }
    public List<Spese> getSpese() {
        return spese;
    }

    @OneToMany(mappedBy = "socio",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Expose
    public List<RiservaMatematica> datiRiserva=new ArrayList<>();

    public void addRiserva(RiservaMatematica riserva){
        this.datiRiserva.add(riserva);
    }
    public void removeRiserva(RiservaMatematica riserva)
    {
        this.datiRiserva.remove(riserva);
    }
    public void setDatiRiserva(List<RiservaMatematica> Datiriserva) {
        this.datiRiserva = Datiriserva;
    }
    public List<RiservaMatematica> getDatiRiserva() {
        return datiRiserva;
    }



    @OneToMany(mappedBy = "socio",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Expose
    public List<Pensioni> pensioni=new ArrayList<>();

    public void addPensioni(Pensioni pensioni){
        this.pensioni.add(pensioni);
    }
    public void removePensioni(Pensioni pensioni)
    {
        this.pensioni.remove(pensioni);
    }
    public void setPensioni(List<Pensioni> pensioni) {
        this.pensioni = pensioni;
    }
    public List<Pensioni> getPensioni() {
        return pensioni;
    }

    @OneToMany(mappedBy = "socio",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Expose
    public List<Eredi> eredi=new ArrayList<>();

    public void addEredi(Eredi eredi){
        this.eredi.add(eredi);
    }
    public void removEredi(Eredi eredi)
    {
        this.eredi.remove(eredi);
    }
    public void setEredi(List<Eredi> eredi) {
        this.eredi = eredi;
    }
    public List<Eredi> getEredi() {
        return eredi;
    }

    @OneToMany(mappedBy = "socio",
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

    @OneToMany(mappedBy = "socio",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Expose
    public List<Quote> quote=new ArrayList<>();

    public void addQuote(Quote quote){
        this.quote.add(quote);
    }
    public void removeQuote(Quote quote)
    {
        this.quote.remove(quote);
    }
    public void setQuote(List<Quote> quote) {
        this.quote = quote;
    }
    public List<Quote> getQuote() {
        return quote;
    }

    @OneToMany(mappedBy = "socio",
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
    @JoinColumn(name="idComune", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_Comune"))
    private Comune comune;
    //SOCIO
    public void setComune(Comune comune){this.comune=comune;}
    public Comune getComune(){return this.comune;}

    @ManyToOne(optional = true)
    @JoinColumn(name="idConto", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_Conto"))
    private Conti conto;
    //SOCIO
    public void setConto(Conti conto){this.conto=conto;}
    public Conti getConto(){return this.conto;}

    @ManyToOne(optional = true)
    @JoinColumn(name="idRegione", referencedColumnName="uuid", foreignKey = @ForeignKey(name="FK_ID_REGIONE"))
    private Regioni regione;
    //SOCIO
    public void setRegione(Regioni regione){this.regione=regione;}
    public Regioni getRegione(){return this.regione;}

    @Access(AccessType.PROPERTY)
    public LocalDate getdataPensionamento() {

        if (this.dataPensionamento == null) {
            return _dataPensionamento;

        } else {
            return this.dataPensionamento.get();
        }
    }

    public void setdataPensionamento(LocalDate dataPensionamento) {
        if (this.dataPensionamento == null) {
            _dataPensionamento = dataPensionamento;
        } else {
            this.dataPensionamento.set(dataPensionamento);
        }
    }


    public ObjectProperty<LocalDate> dataPensionamentoProperty() {
        if (this.dataPensionamento == null) {
            this.dataPensionamento = new SimpleObjectProperty<>(this, "dataPensionamento", _dataPensionamento);

        }
        return this.dataPensionamento;
    }


    @Access(AccessType.PROPERTY)
    public LocalDate getdataDecesso() {

        if (this.dataDecesso == null) {
            return _dataDecesso;

        } else {
            return this.dataDecesso.get();
        }
    }

    public void setdataDecesso(LocalDate dataDecesso) {
        if (this.dataDecesso == null) {
            _dataDecesso = dataDecesso;
        } else {
            this.dataDecesso.set(dataDecesso);
        }
    }


    public ObjectProperty<LocalDate> dataDecessoProperty() {
        if (this.dataDecesso == null) {
            this.dataDecesso = new SimpleObjectProperty<>(this, "dataDecesso", _dataDecesso);

        }
        return this.dataDecesso;
    }

    @Access(AccessType.PROPERTY)
    public float getsussidioMensile() {
        if (this.sussidioMensile == null) {
            return _sussidioMensile;
        } else {
            return this.sussidioMensile.get();
        }
    }

    public FloatProperty sussidioMensileProperty() {
        if (this.sussidioMensile == null) {
            this.sussidioMensile = new SimpleFloatProperty(this, "sussidioMensile", _sussidioMensile);
        }
        return this.sussidioMensile;
    }

    public void setsussidioMensile(float sussidioMensile) {
        if (this.sussidioMensile == null) {
            _sussidioMensile = sussidioMensile;
        } else {
            this.sussidioMensile.set(sussidioMensile);
        }
    }
    @Access(AccessType.PROPERTY)
    public float getritenuta() {
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

    public void setritenuta(float ritenuta) {
        if (this.ritenuta == null) {
            _ritenuta = ritenuta;
        } else {
            this.ritenuta.set(ritenuta);
        }
    }

    @Access(AccessType.PROPERTY)
    public float getreddito() {
        if (this.reddito == null) {
            return _reddito;
        } else {
            return this.reddito.get();
        }
    }

    public FloatProperty redditoProperty() {
        if (this.reddito == null) {
            this.reddito = new SimpleFloatProperty(this, "reddito", _reddito);
        }
        return this.reddito;
    }

    public void setreddito(float reddito) {
        if (this.reddito == null) {
            _reddito = reddito;
        } else {
            this.reddito.set(reddito);
        }
    }

    @Access(AccessType.PROPERTY)
    public LocalDate getdataIscrizione() {
        if (this.dataIscrizione == null) {
            return _dataIscrizione;
        } else {
            return this.dataIscrizione.get();
        }
    }

    public void setdataIscrizione(LocalDate dataIscrizione) {
        if (this.dataIscrizione == null) {
            _dataIscrizione = dataIscrizione;
        } else {
            this.dataIscrizione.set(dataIscrizione);
        }
    }

    public ObjectProperty<LocalDate> dataIscrizioneProperty() {
        if (this.dataIscrizione == null) {
            this.dataIscrizione = new SimpleObjectProperty<>(this, "dataIscrizione", _dataIscrizione);

        }
        return this.dataIscrizione;
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

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getNome() {
        if (this.nome == null) {
            return _nome;
        } else {
            return this.nome.get();
        }
    }
    public StringProperty nomeProperty() {
        if (this.nome == null) {
            this.nome = new SimpleStringProperty(this, "nome", _nome);
        }
        return this.nome;
    }

    public void setNome(String nome) {
        if (this.nome == null) {
            _nome = nome;
        } else {
            this.nome.set(nome);
        }
    }

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getCitta() {
        if (this.citta == null) {
            return _citta;
        } else {
            return this.citta.get();
        }
    }
    public StringProperty cittaProperty() {
        if (this.citta == null) {
            this.citta = new SimpleStringProperty(this, "citta", _citta);
        }
        return this.citta;
    }

    public void setCitta(String citta) {
        if (this.citta == null) {
            _citta = citta;
        } else {
            this.citta.set(citta);
        }
    }


    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getIndirizzo() {
        if (this.indirizzo== null) {
            return _indirizzo;
        } else {
            return this.indirizzo.get();
        }
    }
    public StringProperty indirizzoScuolaProperty() {
        if (this.indirizzo == null) {
            this.indirizzo = new SimpleStringProperty(this, "indirizzo", _indirizzo);
        }
        return this.indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        if (this.indirizzo == null) {
            _indirizzo = indirizzo;
        } else {
            this.indirizzo.set(indirizzo);
        }
    }
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getCognome() {
        if (this.cognome == null) {
            return _cognome;
        } else {
            return this.cognome.get();
        }
    }
    public StringProperty cognomeProperty() {
        if (this.cognome == null) {
            this.cognome = new SimpleStringProperty(this, "cognome", _cognome);
        }
        return this.cognome;
    }

    public void setCognome(String cognome) {
        if (this.cognome == null) {
            _cognome = cognome;
        } else {
            this.cognome.set(cognome);
        }
    }
    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getCF() {
        if (this.CF == null) {
            return _cf;
        } else {
            return this.CF.get();
        }
    }
    public StringProperty cfProperty() {
        if (this.CF == null) {
            this.CF = new SimpleStringProperty(this, "cf", _cf);
        }
        return this.CF;
    }

    public void setCF(String cf) {
        if (this.CF == null) {
            _cf = cf;
        } else {
            this.CF.set(cf);
        }
    }

    @Access(AccessType.PROPERTY)
    @NotBlank
    @NotEmpty
    public String getCategoria() {
        if (this.categoria == null) {
            return _categoria;
        } else {
            return this.categoria.get();
        }
    }
    public StringProperty CategoriaProperty() {
        if (this.categoria == null) {
            this.categoria = new SimpleStringProperty(this, "categoria", _categoria);
        }
        return this.categoria;
    }

    public void setCategoria(String Categoria) {
        if (this.categoria == null) {
            _categoria = Categoria;
        } else {
            this.categoria.set(Categoria);
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getCF());
        out.writeObject(this.getCognome());
        out.writeObject(this.getNome());
        out.writeObject(this.getCitta());
        out.writeObject(this.getComune());
        out.writeObject(this.getIndirizzo());
        out.writeObject(this.getCategoria());
        out.writeObject(this.getIBAN());
        out.writeObject(this.getdataIscrizione());
        out.writeObject(this.getdataPensionamento());
        out.writeObject(this.getreddito());
        out.writeObject(this.getritenuta());
        out.writeObject(this.getsussidioMensile());
        out.writeObject(this.getSpese());
        out.writeObject(this.getDatiRiserva());
        out.writeObject(this.getPensioni());
        out.writeObject(this.getEredi());
        out.writeObject(this.getBorseDiStudio());
        out.writeObject(this.getAsiliNido());
        out.writeObject(this.getQuote());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setCF((String) in.readObject());
        this.setCognome((String) in.readObject());
        this.setNome((String) in.readObject());
        this.setCitta((String) in.readObject());
        this.setComune((Comune) in.readObject());
        this.setIndirizzo((String) in.readObject());
        this.setCategoria((String) in.readObject());
        this.setIBAN((String)in.readObject());
        this.setdataIscrizione((LocalDate) in.readObject());
        this.setdataPensionamento((LocalDate)in.readObject());
        this.setreddito((float)in.readObject());
        this.setritenuta((float)in.readObject());
        this.setsussidioMensile((float)in.readObject());
        this.setSpese((List) in.readObject());
        this.setDatiRiserva((List) in.readObject());
        this.setPensioni((List) in.readObject());
        this.setEredi((List) in.readObject());
        this.setBorseDiStudio((List) in.readObject());
        this.setAsiliNido((List) in.readObject());
        this.setQuote((List) in.readObject());
    }
    @Override
    public String toString() {
        return _cognome+ " "+ _nome +" - " + _cf;
    }

}
