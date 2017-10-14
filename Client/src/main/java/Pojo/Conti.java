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
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();
        this.setNumero((String) in.readObject());
        this.setDescrizione((String) in.readObject());
    }







}
