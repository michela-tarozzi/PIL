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
    private StringProperty comune;
    @Transient
    @Expose
    private String _comune;
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
    private ObjectProperty<Date> dataIscrizione;
    @Transient
    @Expose
    private Date _dataIscrizione;

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
    private ObjectProperty<Date> dataPensionamento;
    @Transient
    @Expose
    private Date _dataPensionamento;
    @Access(AccessType.PROPERTY)
    public Date getdataPensionamento() {

        if (this.dataPensionamento == null) {
            return _dataPensionamento;

        } else {
            return this.dataPensionamento.get();
        }
    }

    public void setdataPensionamento(Date dataPensionamento) {
        if (this.dataPensionamento == null) {
            _dataPensionamento = dataPensionamento;
        } else {
            this.dataPensionamento.set(dataPensionamento);
        }
    }


    public ObjectProperty<Date> dataPensionamentoProperty() {
        if (this.dataPensionamento == null) {
            this.dataPensionamento = new SimpleObjectProperty<>(this, "dataPensionamento", _dataPensionamento);

        }
        return this.dataPensionamento;
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
    public Date getdataIscrizione() {
        if (this.dataIscrizione == null) {
            return _dataIscrizione;
        } else {
            return this.dataIscrizione.get();
        }
    }

    public void setdataIscrizione(Date dataIscrizione) {
        if (this.dataIscrizione == null) {
            _dataIscrizione = dataIscrizione;
        } else {
            this.dataIscrizione.set(dataIscrizione);
        }
    }

    public ObjectProperty<Date> dataIscrizioneProperty() {
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
    public String getComune() {
        if (this.comune== null) {
            return _comune;
        } else {
            return this.comune.get();
        }
    }
    public StringProperty ComunProperty() {
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
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setCF((String) in.readObject());
        this.setCognome((String) in.readObject());
        this.setNome((String) in.readObject());
        this.setCitta((String) in.readObject());
        this.setComune((String) in.readObject());
        this.setIndirizzo((String) in.readObject());
        this.setCategoria((String) in.readObject());
        this.setIBAN((String)in.readObject());
        this.setdataIscrizione((Date)in.readObject());
        this.setdataPensionamento((Date)in.readObject());
        this.setreddito((float)in.readObject());
        this.setritenuta((float)in.readObject());
        this.setsussidioMensile((float)in.readObject());
    }
}
