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
@Table(name="regioni")
public class Regioni implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;

    @Column(nullable = false)
    private StringProperty nome;
    @Transient
    @Expose
    private String _nome;


    @OneToMany(mappedBy = "regione",
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





    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeObject(this.getNome());
        out.writeObject(this.getSoci());
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setNome((String) in.readObject());
        this.setSoci((List<Socio>)in.readObject());
    }




    @Override
    public String toString() {
        return _nome;
    }


}
