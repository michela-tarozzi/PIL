
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
@Table(name="regoleCarovita")
public class RegoleCarovita implements Externalizable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    @Expose
    private String id;


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

    //setter, getter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        out.writeObject(this.getAnno());

        out.writeObject(this.getPercentuale());

    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (String) in.readObject();

        this.setAnno((int) in.readObject());

        this.setPercentuale((float) in.readObject());

    }


}

