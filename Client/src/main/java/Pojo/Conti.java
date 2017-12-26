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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.tarozzi on 14/10/2017.
 */

@Entity
@Table(name="conti")
public class Conti implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    @Column(nullable = false)
    private StringProperty numero;
    @Transient
    @Expose
    private String _numero;

    @Column(nullable = false)
    private StringProperty descrizione;
    @Transient
    @Expose
    private String _descrizione;

    @OneToMany(mappedBy = "conto",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Expose
    public List<Pagamenti> pagamenti=new ArrayList<>();

    public void addPagamenti(Pagamenti pagamenti){
        this.pagamenti.add(pagamenti);
    }
    public void removePagamenti(Pagamenti pagamenti)
    {
        this.pagamenti.remove(pagamenti);
    }
    public void setPagamenti(List<Pagamenti> pagamenti) {
        this.pagamenti = pagamenti;
    }
    public List<Pagamenti> getPagamenti() {
        return pagamenti;
    }

    @OneToMany(mappedBy = "conto",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Expose
    public List<Eredi> eredi=new ArrayList<>();

    public void addEredi(Eredi eredi){
        this.eredi.add(eredi);
    }
    public void removeEredi(Eredi eredi)
    {
        this.eredi.remove(eredi);
    }
    public void setEredii(List<Eredi> eredi) {
        this.eredi = eredi;
    }
    public List<Eredi> getEredi() {
        return eredi;
    }

    @OneToMany(mappedBy = "conto",
            cascade = CascadeType.REMOVE, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Expose
    public List<Socio> soci=new ArrayList<>();

    public void addSocio(Socio socio){
        this.soci.add(socio);
    }
    public void removeSocio(Socio socio)
    {
        this.soci.remove(socio);
    }
    public void setSoci(List<Socio> soci) {
        this.soci = soci;
    }
    public List<Socio> getSoci() {
        return soci;
    }

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
    public String getNumero() {
        if (this.numero == null) {
            return _numero;
        } else {
            return this.numero.get();
        }
    }
    public StringProperty numeroProperty() {
        if (this.numero == null) {
            this.numero = new SimpleStringProperty(this, "numero", _numero);
        }
        return this.numero;
    }

    public void setNumero(String numero) {
        if (this.numero == null) {
            _numero = numero;
        } else {
            this.numero.set(numero);
        }
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



    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getNumero());
        out.writeObject(this.getDescrizione());
        out.writeObject(this.getPagamenti());
        out.writeObject(this.getEredi());
        out.writeObject(this.getSoci());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setNumero((String) in.readObject());
        this.setDescrizione((String) in.readObject());
        this.setPagamenti((List) in.readObject());
        this.setEredii((List) in.readObject());
        this.setSoci((List<Socio>)in.readObject());
    }

    @Override
    public String toString() {
        return _numero +" - " + _descrizione;
    }





}
