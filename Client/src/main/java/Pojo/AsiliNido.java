package Pojo;

/**
 * Created by m.tarozzi on 14/10/2017.
 */
@Entity
@Table(name="addizionaleComunale")
public class AsiliNido implements Externalizable{
    
    //FK PAGAMENTO
    //FK SOCIO
	
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="uuid",unique=true)
    @Expose
    private String id;
    
    @Column(nullable=false)
    private StringProperty figlio;
    @Transient
    @Expose
    private String _figlio;
    
    @Column(nullable=false)
    private FloatProperty spesa;
    @Transient
    @Expose
    private float _spesa;
    
    @Column(nullable=false)
    private IntegerProperty anno;
    @Transient
    @Expose
    private int _anno;
    
    @Column(nullable=false)
    private FloatProperty rimborso;
    @Transient
    @Expose
    private float _rimborso;

    @Column(nullable=false)
    private FloatProperty integrazione;
    @Transient
    @Expose
    private float _integrazione;
 
    @Column(nullable=false)
    private FloatProperty percentuale;
    @Transient
    @Expose
    private float _percentuale;
    
    //setter, getter
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }
    
    

}
