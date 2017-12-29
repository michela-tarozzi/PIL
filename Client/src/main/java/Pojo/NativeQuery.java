package Pojo;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;

/**
 * Created by m.tarozzi on 15/12/2017.
 */
@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getSociMutua", query = "SELECT NOME, COGNOME FROM SOCI WHERE CATEGORIA = 'MUTUA' OR CATEGORIA= 'MUTUA&SUSSIDI'",
                callable = true, resultSetMapping = "SociMutua"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SociMutua",
                classes = {
                        @ConstructorResult(
                                targetClass = NomiSoci.class,
                                columns = {
                                        @ColumnResult(name = "NOME", type = String.class),
                                        @ColumnResult(name = "COGNOME", type = String.class),
                                }
                        )
                }
        ),
})

public class NativeQuery {
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
