package Pojo;

/**
 * Created by m.tarozzi on 14/10/2017.
 */
@Entity
@Table(name="addizionaleRegionale")
public class AddizionaleRegionale implements Externalizable{

	package Pojo;

	import com.google.gson.annotations.Expose;
	import org.hibernate.annotations.GenericGenerator;

	import javax.persistence.Column;
	import javax.persistence.GeneratedValue;
	import javax.persistence.Id;

	/**
	 * Created by m.tarozzi on 14/10/2017.
	 */
	@Entity
	@Table(name="addizionaleComunale")
	public class AddizionaleComunale implements Externalizable{
	    @Id
	    @GeneratedValue(generator="uuid")
	    @GenericGenerator(name="uuid", strategy = "uuid2")
	    @Column(name="uuid",unique=true)
	    @Expose
	    private String id;
	   
	    @Column(nullable=false)
	    private StringProperty regione;
	    @Transient
	    @Expose
	    private String _regione;
	    
	    @Column(nullable=false)
	    private IntegerProperty anno;
	    @Transient
	    @Expose
	    private int _anno;
	    
	    @Column(nullable=false)
	    private FloatProperty sogliaMinima;
	    @Transient
	    @Expose
	    private float _sogliaMinima;
	    
	    @Column(nullable=false)
	    private FloatProperty sogliaMassima;
	    @Transient
	    @Expose
	    private float _sogliaMassima;
	    
	    @Column(nullable=false)
	    private FloatProperty aliquota;
	    @Transient
	    @Expose
	    private float _aliquota;
	    
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id=id;
	    }

	    
	    @Access(AccessType.PROPERTY)
	    @NotBlank
	    @NotEmpty
	    public String getRegione() {
	        if (this.regione == null) {
	            return _regione;
	        } else {
	            return this.regione.get();
	        }
	    }
	    public StringProperty regioneProperty() {
	        if (this.regione == null) {
	            this.regione = new SimpleStringProperty(this, "regione", _regione);
	        }
	        return this.regione;
	    }

	    public void setRegione(String regione) {
	        if (this.regione == null) {
	            _regione = regione;
	        } else {
	            this.regione.set(regione);
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

	    public void setAnno(String regione) {
	        if (this.anno == null) {
	            _anno = anno;
	        } else {
	            this.anno.set(anno);
	        }
	    }
	    
	    @Access(AccessType.PROPERTY)
	    public float getSogliaMinima() {
	        if (this.sogliaMinima == null) {
	            return _sogliaMinima;
	        } else {
	            return this.sogliaMinima.get();
	        }
	    }

	    public FloatProperty sogliaMinimaProperty() {
	        if (this.sogliaMinima == null) {
	            this.sogliaMinima = new SimpleFloatProperty(this, "sogliaMinima", _sogliaMinima);
	        }
	        return this.sogliaMinima;
	    }

	    public void setSogliaMinima(float sogliaMinima) {
	        if (this.sogliaMinima == null) {
	            _sogliaMinima = sogliaMinima;
	        } else {
	            this.sogliaMinima.set(sogliaMinima);
	        }
	    }

	    @Access(AccessType.PROPERTY)
	    public float getSogliaMassima() {
	        if (this.sogliaMassima == null) {
	            return _sogliaMassima;
	        } else {
	            return this.sogliaMassima.get();
	        }
	    }

	    public FloatProperty sogliaMassimaProperty() {
	        if (this.sogliaMassima == null) {
	            this.sogliaMassima = new SimpleFloatProperty(this, "sogliaMassima", _sogliaMassima);
	        }
	        return this.sogliaMassima;
	    }

	    public void setSogliaMassima(float sogliaMassima) {
	        if (this.sogliaMassima == null) {
	            _sogliaMassima = sogliaMassima;
	        } else {
	            this.sogliaMassima.set(sogliaMassima);
	        }
	    }

	    @Access(AccessType.PROPERTY)
	    public float getAliquota() {
	        if (this.aliquota == null) {
	            return _aliquota;
	        } else {
	            return this.aliquota.get();
	        }
	    }

	    public FloatProperty aliquotaProperty() {
	        if (this.aliquota == null) {
	            this.aliquota = new SimpleFloatProperty(this, "aliquota", _aliquota);
	        }
	        return this.aliquota;
	    }

	    public void setAliquota(float aliquota) {
	        if (this.aliquota == null) {
	            _aliquota = aliquota;
	        } else {
	            this.aliquota.set(aliquota);
	        }
	    }

	    public void writeExternal(ObjectOutput out) throws IOException {
	        out.writeObject(this.id);
	        out.writeObject(this.getregione());
	        out.writeObject(this.getAnno());
	        out.writeObject(this.getSogliaMinima());
	        out.writeObject(this.getSogliaMassima());
	        out.writeObject(this.getAliquota());

	    }

	    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	        this.id = (String) in.readObject();
	        this.setregione((String) in.readObject());
	        this.setAnno((int) in.readObject());
	        this.setSogliaMinima((float) in.readObject());
	        this.setSogliaMassima((float) in.readObject());
	        this.setaliquota((float) in.readObject());
	        
	    }
	    
	}

}
